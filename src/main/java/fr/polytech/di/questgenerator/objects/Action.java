package fr.polytech.di.questgenerator.objects;

import fr.polytech.di.questgenerator.enums.ActionType;
import fr.polytech.di.questgenerator.enums.ObjectiveType;
import fr.polytech.di.questgenerator.interfaces.GameListener;
import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveElement;
import java.util.HashMap;
import java.util.Optional;
import static fr.polytech.di.questgenerator.enums.ActionType.EXPLORE;
import static fr.polytech.di.questgenerator.enums.ActionType.GET;
import static fr.polytech.di.questgenerator.enums.ActionType.LISTEN;
import static fr.polytech.di.questgenerator.enums.ObjectiveType.*;

/**
 * Define an actionType to do.
 *
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class Action implements GameListener
{
	private Quest parentQuest;
	private final ActionType actionType;
	private final Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives;
	private final Optional<Quest> subquest;
	private final boolean splittable;
	private final int depth;
	private boolean done;

	/**
	 * Constructor.
	 *
	 * @param actionExecutorClass The ActionExecutor that built that Action.
	 * @param depth The depth of the actionType.
	 * @param actionType The ActionType associated to this Action.
	 */
	public Action(Class actionExecutorClass, int depth, ActionType actionType)
	{
		this(actionExecutorClass, depth, actionType, true);
	}

	/**
	 * Constructor.
	 *
	 * @param actionExecutorClass The ActionExecutor that built that Action.
	 * @param depth The depth of the actionType.
	 * @param actionType The ActionType associated to this Action.
	 * @param splittable Define if this Action can be splitted.
	 */
	public Action(Class actionExecutorClass, int depth, ActionType actionType, boolean splittable)
	{
		this(actionExecutorClass, depth, actionType, Optional.empty(), splittable);
	}

	/**
	 * Constructor.
	 *
	 * @param actionExecutorClass The ActionExecutor that built that Action.
	 * @param depth The depth of the actionType.
	 * @param actionType The ActionType associated to this Action.
	 * @param objectives The objectives for the Action.
	 */
	public Action(Class actionExecutorClass, int depth, ActionType actionType, HashMap<ObjectiveType, XMLStringObjectiveElement> objectives)
	{
		this(actionExecutorClass, depth, actionType, Optional.ofNullable(objectives));
	}

	/**
	 * Constructor.
	 *
	 * @param actionExecutorClass The ActionExecutor that built that Action.
	 * @param depth The depth of the actionType.
	 * @param actionType The ActionType associated to this Action.
	 * @param objectives The objectives for the Action.
	 */
	public Action(Class actionExecutorClass, int depth, ActionType actionType, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		this(actionExecutorClass, depth, actionType, objectives, true);
	}

	/**
	 * Constructor.
	 *
	 * @param actionExecutorClass The ActionExecutor that built that Action.
	 * @param depth The depth of the actionType.
	 * @param actionType The ActionType associated to this Action.
	 * @param objectives The objectives for the Action.
	 * @param splittable Define if this Action can be splitted.
	 */
	public Action(Class actionExecutorClass, int depth, ActionType actionType, HashMap<ObjectiveType, XMLStringObjectiveElement> objectives, boolean splittable)
	{
		this(actionExecutorClass, depth, actionType, Optional.ofNullable(objectives), splittable);
	}

	/**
	 * Constructor.
	 *  @param actionExecutorClass The ActionExecutor that built that Action.
	 * @param depth The depth of the actionType.
	 * @param actionType The ActionType associated to this Action.
	 * @param objectives The objectives for the Action.
	 * @param splittable Define if this Action can be splitted.
	 */
	public Action(Class actionExecutorClass, int depth, ActionType actionType, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives, boolean splittable)
	{
		this.depth = depth;
		this.actionType = actionType;
		if(!objectives.isPresent())
			objectives = Optional.of(new HashMap<>());
		objectives.get().put(ObjectiveType.CLASS, new XMLStringObjectiveElement("class", actionExecutorClass.getSimpleName()));
		this.objectives = objectives;
		this.splittable = splittable;
		this.done = false;
		this.subquest = this.genSubquest(depth);
	}

	/**
	 * Generate the subquest for this actionType.
	 *
	 * @param depth The depth of the subquest.
	 * @return The quest.
	 */
	private Optional<Quest> genSubquest(int depth)
	{
		if(!this.splittable || this.actionType.isEmpty())
			return Optional.empty();
		return actionType.genSubquest(this, depth, this.objectives);
	}

	/**
	 * Returns the subquest of this actionType.
	 *
	 * @return The subquest.
	 */
	public Optional<Quest> getSubquest()
	{
		return this.subquest;
	}

	/**
	 * Used to get the formatted sentence describing this Action.
	 *
	 * @return The actionType description.
	 */
	public String getAsString()
	{
		return this.actionType.getAsString(objectives);
	}

	@Override
	public String toString()
	{
		return getAsString();
	}

	/**
	 * Get the ActionType associated to this Action.
	 *
	 * @return The ActionType of this Action.
	 */
	public ActionType getActionType()
	{
		return this.actionType;
	}

	/**
	 * Used to get the depth of this action.
	 *
	 * @return The depth.
	 */
	public int getDepth()
	{
		return this.depth;
	}

	/**
	 * Used to get the value of an objective for this action.
	 *
	 * @param objective The objective to get.
	 * @return The objective value.
	 */
	public XMLStringObjectiveElement getObjective(ObjectiveType objective)
	{
		if(!objectives.isPresent() || !objectives.get().containsKey(objective))
			return new XMLStringObjectiveElement("ERR", "ERR");
		return objectives.get().get(objective);
	}

	/**
	 * Used to know if that action is marked as done.
	 *
	 * @return True if done, false if not.
	 */
	public boolean isDone()
	{
		if(this.subquest.isPresent())
			return this.subquest.get().isDone();
		return this.done;
	}

	/**
	 * Set the value of the done state.
	 *
	 * @param done The value to set.
	 */
	public void setDone(boolean done)
	{
		this.done = done;
		notifyActionDone(this);
	}

	/**
	 * Used to know if this action is doable now.
	 *
	 * @return True if doable, false if not.
	 */
	public boolean isDoable()
	{
		if(this.getParentQuest() == null)
			return true;
		return this.getParentQuest().isActionDoable(this);
	}

	/**
	 * Set the parent quest of this action.
	 *
	 * @param quest The parent quest.
	 */
	public void setParentQuest(Quest quest)
	{
		this.parentQuest = quest;
	}

	public Quest getParentQuest()
	{
		return this.parentQuest;
	}

	public Action getActionToDo()
	{
		return this.subquest.isPresent() ? this.subquest.get().getActionToDo() : this;
	}

	public void notifyActionDone(Action action)
	{
		this.getParentQuest().notifyActionDone(action);
	}

	public void notifyQuestDone(Quest quest)
	{
		this.getParentQuest().notifyQuestDone(quest);
	}

	private boolean isCorrectObjective(ObjectiveType objectiveType, XMLStringObjectiveElement objective)
	{
		return this.getObjective(objectiveType).is(objective);
	}

	@Override
	public boolean areaExploredEvent(XMLStringObjectiveElement area)
	{
		if(this.actionType != EXPLORE || this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().areaExploredEvent(area);
		if(isCorrectObjective(OBJECTIVE, area))
			setDone(true);
		return false;
	}

	@Override
	public boolean objectGotEvent(XMLStringObjectiveElement object, XMLStringObjectiveElement from)
	{
		if(this.actionType != GET || this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().objectGotEvent(object, from);
		if(isCorrectObjective(OBJ_GET, object) && isCorrectObjective(LOC_OBJECTIVE, from))
			setDone(true);
		return false;
	}

	@Override
	public boolean listenedEvent(XMLStringObjectiveElement pnj)
	{
		if(this.actionType != LISTEN || this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().areaExploredEvent(pnj);
		if(isCorrectObjective(OBJECTIVE, pnj))
			setDone(true);
		return false;
	}

	@Override
	public boolean usedEvent(XMLStringObjectiveElement used, XMLStringObjectiveElement on)
	{
		if(this.actionType != USE || this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().usedEvent(used, on);
		if(isCorrectObjective(OBJ_USE, used) && isCorrectObjective(LOC_OBJECTIVE, on))
			setDone(true);
		return false;
	}
}
