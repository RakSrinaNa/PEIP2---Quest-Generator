package fr.polytech.di.questgenerator.objects;

import fr.polytech.di.questgenerator.enums.ActionType;
import fr.polytech.di.questgenerator.enums.Objectives;
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
	private final Optional<HashMap<Objectives, String>> objectives;
	private final Optional<Quest> subquest;
	private final boolean splittable;
	private final int depth;

	/**
	 * Constructor.
	 * @param depth The depth of the actionType.
	 * @param actionType The ActionType associated to this Action.
	 */
	public Action(int depth, ActionType actionType)
	{
		this(depth, actionType, true);
	}

	/**
	 * Constructor.
	 *
	 * @param depth The depth of the actionType.
	 * @param actionType The ActionType associated to this Action.
	 * @param splittable Define if this Action can be splitted.
	 */
	public Action(int depth, ActionType actionType, boolean splittable)
	{
		this(depth, actionType, Optional.empty(), splittable);
	}

	/**
	 * Constructor.
	 *
	 * @param depth The depth of the actionType.
	 * @param actionType The ActionType associated to this Action.
	 * @param objectives The objectives for the Action.
	 */
	public Action(int depth, ActionType actionType, HashMap<Objectives, String> objectives)
	{
		this(depth, actionType, Optional.ofNullable(objectives));
	}

	/**
	 * Constructor.
	 *
	 * @param depth The depth of the actionType.
	 * @param actionType The ActionType associated to this Action.
	 * @param objectives The objectives for the Action.
	 */
	public Action(int depth, ActionType actionType, Optional<HashMap<Objectives, String>> objectives)
	{
		this(depth, actionType, objectives, true);
	}

	/**
	 * Constructor.
	 *
	 * @param depth The depth of the actionType.
	 * @param actionType The ActionType associated to this Action.
	 * @param objectives The objectives for the Action.
	 * @param splittable Define if this Action can be splitted.
	 */
	public Action(int depth, ActionType actionType, HashMap<Objectives, String> objectives, boolean splittable)
	{
		this(depth, actionType, Optional.ofNullable(objectives), splittable);
	}

	/**
	 * Constructor.
	 *
	 * @param depth The depth of the actionType.
	 * @param actionType The ActionType associated to this Action.
	 * @param objectives The objectives for the Action.
	 * @param splittable Define if this Action can be splitted.
	 */
	public Action(int depth, ActionType actionType, Optional<HashMap<Objectives, String>> objectives, boolean splittable)
	{
		this.depth = depth;
		this.actionType = actionType;
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
