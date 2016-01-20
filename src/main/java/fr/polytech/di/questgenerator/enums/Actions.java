package fr.polytech.di.questgenerator.enums;

import fr.polytech.di.questgenerator.Main;
import fr.polytech.di.questgenerator.actionexecutors.action.ActionCaptureActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.ActionEpsillonActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.ActionQuestActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.ActionReportActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.get.ActionGetGatherActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.get.ActionGetStealActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.get.ActionGetSubquestActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.gotoo.ActionGotoExploreActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.gotoo.ActionGotoLearnActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.learn.ActionLearnGiveActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.learn.ActionLearnListenActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.learn.ActionLearnReadActionListener;
import fr.polytech.di.questgenerator.actionexecutors.action.steal.ActionStealStealthActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.steal.ActionStealTakeActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.subquest.ActionSubquestGotoActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.action.subquest.ActionSubquestQuestActionExecutor;
import fr.polytech.di.questgenerator.interfaces.ActionExecutor;
import fr.polytech.di.questgenerator.objects.Quest;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public enum Actions
{
	NONE(0, ""),
	CAPTURE(1, "Capture {0}", ActionCaptureActionExecutor.class),
	DAMAGE(1, "Damage {0}"),
	DEFEND(1, "Defend {0}"),
	ESCORT(1, "Escort {0}"),
	EXCHANGE(2, "Exchange {0} for {1}"),
	EXPERIMENT(1, "Experiment {0}"),
	EXPLORE(1, "Explore {0}"),
	GATHER(1, "Gather {0}"),
	GET(2, "Get {0} from {1}", ActionEpsillonActionExecutor.class, ActionGetStealActionExecutor.class, ActionGetGatherActionExecutor.class, ActionGetSubquestActionExecutor.class),
	GIVE(2, "Give {0} to {1}"),
	GOTO(1, "Go to {0}", ActionEpsillonActionExecutor.class, ActionGotoExploreActionExecutor.class, ActionGotoLearnActionExecutor.class),
	KILL(1, "Kill {0}"),
	LEARN(0, "Learn where is {0}", ActionEpsillonActionExecutor.class, ActionLearnListenActionExecutor.class, ActionLearnReadActionListener.class, ActionLearnGiveActionExecutor.class),
	LISTEN(1, "Listen {0}"),
	QUEST(0, "Complete quest", ActionQuestActionExecutor.class),
	SUBQUEST(0, "Perfom subquest", ActionSubquestGotoActionExecutor.class, ActionSubquestQuestActionExecutor.class),
	READ(1, "Read {0}"),
	REPAIR(1, "Repair {0}"),
	REPORT(1, "Report to {0}"),
	SPY(1, "Spy {0}", ActionReportActionExecutor.class),
	STEAL(2, "Steal {0} from {1}", ActionStealStealthActionExecutor.class, ActionStealTakeActionExecutor.class),
	STEALTH(1, "Stealth {0}"),
	TAKE(2, "Take {0} from {1}"),
	USE(1, "Use {0}");

	private final int params;
	private final String sentence;
	private final List<Class<? extends ActionExecutor>> actionExecutors;

	Actions(int params, String sentence)
	{
		this.params = params;
		this.sentence = sentence;
		this.actionExecutors = new ArrayList<>(0);
	}

	@SafeVarargs
	Actions(int params, String sentence, Class<? extends ActionExecutor>... actionExecutors)
	{
		this.params = params;
		this.sentence = sentence;
		this.actionExecutors = Arrays.asList(actionExecutors);
	}

	public String getAsString(Optional<HashMap<Objectives, String>> params)
	{
		if(!params.isPresent())
			return this.sentence;
		switch(this)
		{
			//TODO
			default:
				return this.sentence;
		}
	}

	public Optional<Quest> getSubquest(int depth, Optional<HashMap<Objectives, String>> objectives)
	{
		if(depth > Main.MAX_DEPTH && actionExecutors.contains(ActionEpsillonActionExecutor.class))
			return Optional.empty();
		Quest quest = Quest.getEpsillon(depth);
		if(!actionExecutors.isEmpty())
			try
			{
				quest = getRandomActionExecutor().newInstance().process(depth + 1, objectives);
			}
			catch(InstantiationException | IllegalAccessException ignored)
			{
			}
		if(quest.isEmpty())
			return Optional.empty();
		return Optional.of(quest);
	}

	public boolean isEmpty()
	{
		return (this == NONE);
	}

	public Class<? extends ActionExecutor> getRandomActionExecutor()
	{
		if(!actionExecutors.contains(ActionEpsillonActionExecutor.class))
			return actionExecutors.get(ThreadLocalRandom.current().nextInt(actionExecutors.size()));
		if(Math.random() < 0.25)
			return ActionEpsillonActionExecutor.class;
		return actionExecutors.get(1 + ThreadLocalRandom.current().nextInt(actionExecutors.size() - 1));
	}
}
