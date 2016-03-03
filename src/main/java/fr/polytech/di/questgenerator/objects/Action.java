package fr.polytech.di.questgenerator.objects;

import fr.polytech.di.questgenerator.enums.ActionType;
import fr.polytech.di.questgenerator.enums.ObjectiveType;
import fr.polytech.di.questgenerator.interfaces.GameListener;
import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveElement;
import java.util.HashMap;
import java.util.Optional;
import static fr.polytech.di.questgenerator.enums.ActionType.*;
import static fr.polytech.di.questgenerator.enums.ObjectiveType.*;

/**
 * Define an actionType to do.
 * <p>
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class Action implements GameListener
{
	private final ActionType actionType;
	private final Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives;
	private final Optional<Quest> subquest;
	private final boolean splittable;
	private final int depth;
	private Quest parentQuest;
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
	 *
	 * @param actionExecutorClass The ActionExecutor that built that Action.
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

	public Quest getParentQuest()
	{
		return this.parentQuest;
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
	public boolean captureEvent(XMLStringObjectiveElement pnj)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().captureEvent(pnj);
		if(this.actionType == CAPTURE && isCorrectObjective(OBJECTIVE, pnj))
		{
			setDone(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean damageEvent(XMLStringObjectiveElement target)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().damageEvent(target);
		if(this.actionType == DAMAGE && isCorrectObjective(OBJECTIVE, target))
		{
			setDone(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean defendEvent(XMLStringObjectiveElement object)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().defendEvent(object);
		if(this.actionType == DEFEND && isCorrectObjective(OBJECTIVE, object))
		{
			setDone(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean escortEvent(XMLStringObjectiveElement pnj)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().escortEvent(pnj);
		if(this.actionType == ESCORT && isCorrectObjective(OBJECTIVE, pnj))
		{
			setDone(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean exchangeEvent(XMLStringObjectiveElement objectGive, XMLStringObjectiveElement objectGet, XMLStringObjectiveElement to)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().exchangeEvent(objectGive, objectGet, to);
		if(this.actionType == EXCHANGE && isCorrectObjective(OBJ_GIVE, objectGive) && isCorrectObjective(OBJ_GET, objectGet) && isCorrectObjective(PNJ, to))
		{
			setDone(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean experimentEvent(XMLStringObjectiveElement object)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().experimentEvent(object);
		if(this.actionType == EXPERIMENT && isCorrectObjective(OBJECTIVE, object))
		{
			setDone(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean exploredEvent(XMLStringObjectiveElement area)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().exploredEvent(area);
		if(this.actionType == EXPLORE && isCorrectObjective(OBJECTIVE, area))
		{
			setDone(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean gatherEvent(XMLStringObjectiveElement object)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().gatherEvent(object);
		if(this.actionType == GATHER && isCorrectObjective(OBJECTIVE, object))
		{
			setDone(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean getEvent(XMLStringObjectiveElement object, XMLStringObjectiveElement from)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().getEvent(object, from);
		if(this.actionType == GET && isCorrectObjective(OBJ_GET, object) && isCorrectObjective(LOC_OBJECTIVE, from))
		{
			setDone(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean giveEvent(XMLStringObjectiveElement object, XMLStringObjectiveElement to)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().giveEvent(object, to);
		if(this.actionType == GIVE && isCorrectObjective(OBJ_GIVE, object) && isCorrectObjective(LOC_OBJECTIVE, to))
		{
			setDone(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean gotoEvent(XMLStringObjectiveElement area)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().gotoEvent(area);
		if(this.actionType == GOTO && isCorrectObjective(OBJECTIVE, area))
		{
			setDone(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean killEvent(XMLStringObjectiveElement pnj)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().killEvent(pnj);
		if(this.actionType == KILL && isCorrectObjective(OBJECTIVE, pnj))
		{
			setDone(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean learnEvent(XMLStringObjectiveElement object)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().learnEvent(object);
		if(this.actionType == LEARN && isCorrectObjective(OBJECTIVE, object))
		{
			setDone(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean listenEvent(XMLStringObjectiveElement pnj)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().exploredEvent(pnj);
		if(this.actionType == LISTEN && isCorrectObjective(OBJECTIVE, pnj))
		{
			setDone(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean readEvent(XMLStringObjectiveElement object)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().readEvent(object);
		if(this.actionType == READ && isCorrectObjective(OBJECTIVE, object))
		{
			setDone(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean repairEvent(XMLStringObjectiveElement object)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().repairEvent(object);
		if(this.actionType == REPAIR && isCorrectObjective(OBJECTIVE, object))
		{
			setDone(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean reportEvent(XMLStringObjectiveElement to)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().reportEvent(to);
		if(this.actionType == REPORT && isCorrectObjective(OBJECTIVE, to))
		{
			setDone(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean spyEvent(XMLStringObjectiveElement on)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().spyEvent(on);
		if(this.actionType == SPY && isCorrectObjective(OBJECTIVE, on))
		{
			setDone(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean stealEvent(XMLStringObjectiveElement object, XMLStringObjectiveElement from)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().stealEvent(object, from);
		if(this.actionType == STEAL && isCorrectObjective(OBJ_GET, object) && isCorrectObjective(PNJ, from))
		{
			setDone(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean stealthEvent(XMLStringObjectiveElement object)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().stealthEvent(object);
		if(this.actionType == STEALTH && isCorrectObjective(OBJECTIVE, object))
		{
			setDone(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean takeEvent(XMLStringObjectiveElement object, XMLStringObjectiveElement from)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().takeEvent(object, from);
		if(this.actionType == TAKE && isCorrectObjective(OBJ_GET, object) && isCorrectObjective(PNJ, from))
		{
			setDone(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean useEvent(XMLStringObjectiveElement used, XMLStringObjectiveElement on)
	{
		if(this.isDone() || !isDoable())
			return false;
		if(this.subquest.isPresent())
			return this.subquest.get().useEvent(used, on);
		if(this.actionType == USE && isCorrectObjective(OBJ_USE, used) && isCorrectObjective(LOC_OBJECTIVE, on))
		{
			setDone(true);
			return true;
		}
		return false;
	}
}
