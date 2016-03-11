package fr.polytech.di.questgenerator.enums;

import fr.polytech.di.questgenerator.QuestGenerator;
import fr.polytech.di.questgenerator.actionexecutors.action.ActionCaptureActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.ActionEpsilonActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.ActionQuestActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.ActionSpyActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.get.ActionGetExchangeActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.get.ActionGetGatherActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.get.ActionGetStealActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.gotoo.ActionGotoExploreActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.gotoo.ActionGotoLearnActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.learn.ActionLearnGiveActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.learn.ActionLearnListenActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.learn.ActionLearnReadActionListener;
import fr.polytech.di.questgenerator.actionexecutors.action.steal.ActionStealStealthActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.steal.ActionStealTakeActionExecutor;
import fr.polytech.di.questgenerator.interfaces.ActionExecutor;
import fr.polytech.di.questgenerator.objects.Action;
import fr.polytech.di.questgenerator.objects.Quest;
import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveElement;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Each elementary action defining the quest.
 * <p>
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public enum ActionType
{
	NONE(0, ""),
	CAPTURE(1, "Capture {0}", ActionCaptureActionExecutor.class),
	DAMAGE(1, "Damage {0}"),
	DEFEND(1, "Defend {0}"),
	ESCORT(1, "Escort {0}"),
	EXCHANGE(3, "Exchange {0} for {1} to {2}"),
	EXPERIMENT(1, "Experiment {0}"),
	EXPLORE(1, "Explore {0}"),
	GATHER(1, "Gather {0}"),
	GET(2, "Get {0} from {1}", ActionEpsilonActionExecutor.class, ActionGetStealActionExecutor.class, ActionGetGatherActionExecutor.class, ActionGetExchangeActionExecutor.class),
	GIVE(2, "Give {0} to {1}"),
	GOTO(1, "Go to {0}", ActionEpsilonActionExecutor.class, ActionGotoExploreActionExecutor.class, ActionGotoLearnActionExecutor.class),
	KILL(1, "Kill {0}"),
	LEARN(1, "Learn where is {0}", ActionEpsilonActionExecutor.class, ActionLearnListenActionExecutor.class, ActionLearnReadActionListener.class, ActionLearnGiveActionExecutor.class),
	LISTEN(1, "Listen {0}"),
	QUEST(1, "Complete quest for {0}", ActionQuestActionExecutor.class),
	READ(1, "Read {0}"),
	REPAIR(1, "Repair {0}"),
	REPORT(1, "Report to {0}"),
	SPY(1, "Spy {0}", ActionSpyActionExecutor.class),
	STEAL(2, "Steal {0} from {1}", ActionStealStealthActionExecutor.class, ActionStealTakeActionExecutor.class),
	STEALTH(1, "Stealth {0}"),
	TAKE(2, "Take {0} from {1}"),
	USE(2, "Use {0} on {1}");

	private final int params;
	private final String sentence;
	private final List<Class<? extends ActionExecutor>> actionExecutors;

	/**
	 * Constructor.
	 *
	 * @param params Number of objectives expected.
	 * @param sentence The sentence describing the action.
	 */
	ActionType(int params, String sentence)
	{
		this.params = params;
		this.sentence = sentence;
		this.actionExecutors = new ArrayList<>(0);
	}

	/**
	 * Constructor.
	 *
	 * @param params Number of objectives expected.
	 * @param sentence The sentence describing the action.
	 * @param actionExecutors The possible ActionExecutor(s) defining how the action could be splitted.
	 */
	@SafeVarargs
	ActionType(int params, String sentence, Class<? extends ActionExecutor>... actionExecutors)
	{
		this.params = params;
		this.sentence = sentence;
		this.actionExecutors = Arrays.asList(actionExecutors);
	}

	/**
	 * Return the sentence of the action formatted with the objectives.
	 *
	 * @param objectives The objectives.
	 * @return The formatted string.
	 */
	public String getAsString(Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		if(!objectives.isPresent())
			return this.sentence;
		if(objectives.get().size() != (this.params + 1))
			return this.sentence + " - " + objectives.get().toString();
		String sentence = this.sentence + " - " + objectives.toString();
		switch(this)
		{
			case GOTO:
			case EXPLORE:
			case CAPTURE:
			case DAMAGE:
			case DEFEND:
			case ESCORT:
			case EXPERIMENT:
			case GATHER:
			case KILL:
			case LEARN:
			case LISTEN:
			case READ:
			case REPAIR:
			case REPORT:
			case SPY:
			case QUEST:
			case STEALTH:
				sentence = MessageFormat.format(this.sentence, objectives.get().get(ObjectiveType.OBJECTIVE));
				break;
			case USE:
				sentence = MessageFormat.format(this.sentence, objectives.get().get(ObjectiveType.OBJ_USE), objectives.get().get(ObjectiveType.LOC_OBJECTIVE));
				break;
			case EXCHANGE:
				sentence = MessageFormat.format(this.sentence, objectives.get().get(ObjectiveType.OBJ_GIVE), objectives.get().get(ObjectiveType.OBJ_GET), objectives.get().get(ObjectiveType.PNJ));
				break;
			case GET:
				sentence = MessageFormat.format(this.sentence, objectives.get().get(ObjectiveType.OBJ_GET), objectives.get().get(ObjectiveType.LOC_OBJECTIVE));
				break;
			case GIVE:
				sentence = MessageFormat.format(this.sentence, objectives.get().get(ObjectiveType.OBJ_GIVE), objectives.get().get(ObjectiveType.LOC_OBJECTIVE));
				break;
			case STEAL:
			case TAKE:
				sentence = MessageFormat.format(this.sentence, objectives.get().get(ObjectiveType.OBJ_GET), objectives.get().get(ObjectiveType.PNJ));
				break;
		}
		return sentence + (QuestGenerator.getDebug() ? (" - " + objectives.get().get(ObjectiveType.CLASS)) : "");
	}

	/**
	 * Generate a subquest for the action.
	 *
	 * @param parent The parent action, null if none.
	 * @param depth The depth of the subquest.
	 * @param objectives The objectives for the subquest.
	 * @return An Optional object containing the Quest.
	 */
	public Optional<Quest> genSubquest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		if(actionExecutors.isEmpty())
			return Optional.empty();
		if(depth > QuestGenerator.getMaxDepth() && actionExecutors.contains(ActionEpsilonActionExecutor.class))
			return Optional.empty();
		Quest quest = Quest.getEpsilon(parent);
		if(!actionExecutors.isEmpty())
			try
			{
				ArrayList<Class<? extends ActionExecutor>> candidates = new ArrayList<>();
				for(Class<? extends ActionExecutor> actionExecutor : this.actionExecutors)
					if(actionExecutor.newInstance().isActionAllowed(objectives))
						candidates.add(actionExecutor);
				quest = getRandomActionExecutor(depth, candidates).newInstance().generateQuest(parent, depth + 1, objectives);
			}
			catch(InstantiationException | IllegalAccessException ignored)
			{
			}
		if(quest.isEmpty())
			return Optional.empty();
		return Optional.of(quest);
	}

	/**
	 * Used to know if this Action is empty.
	 *
	 * @return true if empty, else false.
	 */
	public boolean isEmpty()
	{
		return (this == NONE);
	}

	/**
	 * Used to get a random ActionExecutor. If an epsilon ActionExecutor is present, he will have more chances to be picked as we get closer to the max depth.
	 *
	 * @param depth The depth of the Action.
	 * @return A random ActionExecutor.
	 */
	private Class<? extends ActionExecutor> getRandomActionExecutor(int depth, ArrayList<Class<? extends ActionExecutor>> executors)
	{
		if(!executors.contains(ActionEpsilonActionExecutor.class))
			return executors.get(ThreadLocalRandom.current().nextInt(executors.size()));
		if(Math.random() < (1 / executors.size()) + (depth / QuestGenerator.getMaxDepth()))
			return ActionEpsilonActionExecutor.class;
		return executors.get(1 + ThreadLocalRandom.current().nextInt(executors.size() - 1));
	}
}
