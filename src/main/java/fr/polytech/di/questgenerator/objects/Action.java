package fr.polytech.di.questgenerator.objects;

import fr.polytech.di.questgenerator.enums.ActionType;
import fr.polytech.di.questgenerator.enums.ObjectiveType;
import fr.polytech.di.questgenerator.interfaces.ActionExecutor;
import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveElement;
import java.util.HashMap;
import java.util.Optional;

/**
 * Define an actionType to do.
 *
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class Action
{
	private final ActionType actionType;
	private final Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives;
	private final Optional<Quest> subquest;
	private final boolean splittable;
	private final int depth;

	/**
	 * Constructor.
	 *
	 * @param parent The ActionExecutor that built that Action.
	 * @param depth The depth of the actionType.
	 * @param actionType The ActionType associated to this Action.
	 */
	public Action(Class parent, int depth, ActionType actionType)
	{
		this(parent, depth, actionType, true);
	}

	/**
	 * Constructor.
	 *
	 * @param parent The ActionExecutor that built that Action.
	 * @param depth The depth of the actionType.
	 * @param actionType The ActionType associated to this Action.
	 * @param splittable Define if this Action can be splitted.
	 */
	public Action(Class parent, int depth, ActionType actionType, boolean splittable)
	{
		this(parent, depth, actionType, Optional.empty(), splittable);
	}

	/**
	 * Constructor.
	 *
	 * @param parent The ActionExecutor that built that Action.
	 * @param depth The depth of the actionType.
	 * @param actionType The ActionType associated to this Action.
	 * @param objectives The objectives for the Action.
	 */
	public Action(Class parent, int depth, ActionType actionType, HashMap<ObjectiveType, XMLStringObjectiveElement> objectives)
	{
		this(parent, depth, actionType, Optional.ofNullable(objectives));
	}

	/**
	 * Constructor.
	 *
	 * @param parent The ActionExecutor that built that Action.
	 * @param depth The depth of the actionType.
	 * @param actionType The ActionType associated to this Action.
	 * @param objectives The objectives for the Action.
	 */
	public Action(Class parent, int depth, ActionType actionType, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		this(parent, depth, actionType, objectives, true);
	}

	/**
	 * Constructor.
	 *
	 * @param parent The ActionExecutor that built that Action.
	 * @param depth The depth of the actionType.
	 * @param actionType The ActionType associated to this Action.
	 * @param objectives The objectives for the Action.
	 * @param splittable Define if this Action can be splitted.
	 */
	public Action(Class<? extends ActionExecutor> parent, int depth, ActionType actionType, HashMap<ObjectiveType, XMLStringObjectiveElement> objectives, boolean splittable)
	{
		this(parent, depth, actionType, Optional.ofNullable(objectives), splittable);
	}

	/**
	 * Constructor.
	 *
	 * @param parent The ActionExecutor that built that Action.
	 * @param depth The depth of the actionType.
	 * @param actionType The ActionType associated to this Action.
	 * @param objectives The objectives for the Action.
	 * @param splittable Define if this Action can be splitted.
	 */
	public Action(Class parent, int depth, ActionType actionType, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives, boolean splittable)
	{
		this.depth = depth;
		this.actionType = actionType;
		if(objectives.isPresent())
			objectives.get().put(ObjectiveType.CLASS, new XMLStringObjectiveElement("class", parent.getSimpleName()));
		this.objectives = objectives;
		this.splittable = splittable;
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
		return actionType.genSubquest(depth, this.objectives);
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

	public int getDepth()
	{
		return this.depth;
	}
}
