package fr.polytech.di.questgenerator.objects;

import fr.polytech.di.questgenerator.enums.Actions;
import fr.polytech.di.questgenerator.enums.Objectives;
import java.util.HashMap;
import java.util.Optional;

/**
 * Define an action to do.
 *
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class Action
{
	private final Actions action;
	private final Optional<HashMap<Objectives, String>> objectives;
	private final Optional<Quest> subquest;
	private final boolean splittable;

	/**
	 * Constructor.
	 *
	 * @param depth The depth of the action.
	 * @param action The Actions associated to this Action.
	 */
	public Action(int depth, Actions action)
	{
		this(depth, action, true);
	}

	/**
	 * Constructor.
	 *
	 * @param depth The depth of the action.
	 * @param action The Actions associated to this Action.
	 * @param splittable Define if this Action can be splitted.
	 */
	public Action(int depth, Actions action, boolean splittable)
	{
		this(depth, action, Optional.empty(), splittable);
	}

	/**
	 * Constructor.
	 *
	 * @param depth The depth of the action.
	 * @param action The Actions associated to this Action.
	 * @param objectives The objectives for the Action.
	 */
	public Action(int depth, Actions action, HashMap<Objectives, String> objectives)
	{
		this(depth, action, Optional.ofNullable(objectives));
	}

	/**
	 * Constructor.
	 *
	 * @param depth The depth of the action.
	 * @param action The Actions associated to this Action.
	 * @param objectives The objectives for the Action.
	 */
	public Action(int depth, Actions action, Optional<HashMap<Objectives, String>> objectives)
	{
		this(depth, action, objectives, true);
	}

	/**
	 * Constructor.
	 *
	 * @param depth The depth of the action.
	 * @param action The Actions associated to this Action.
	 * @param objectives The objectives for the Action.
	 * @param splittable Define if this Action can be splitted.
	 */
	public Action(int depth, Actions action, HashMap<Objectives, String> objectives, boolean splittable)
	{
		this(depth, action, Optional.ofNullable(objectives), splittable);
	}

	/**
	 * Constructor.
	 *
	 * @param depth The depth of the action.
	 * @param action The Actions associated to this Action.
	 * @param objectives The objectives for the Action.
	 * @param splittable Define if this Action can be splitted.
	 */
	public Action(int depth, Actions action, Optional<HashMap<Objectives, String>> objectives, boolean splittable)
	{
		this.action = action;
		this.objectives = objectives;
		this.splittable = splittable;
		this.subquest = this.genSubquest(depth);
	}

	/**
	 * Generate the subquest for this action.
	 *
	 * @param depth The depth of the subquest.
	 * @return The quest.
	 */
	private Optional<Quest> genSubquest(int depth)
	{
		if(!this.splittable || this.action.isEmpty())
			return Optional.empty();
		return action.genSubquest(depth, this.objectives);
	}

	/**
	 * Returns the subquest of this action.
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
	 * @return The action description.
	 */
	public String getAsString()
	{
		return this.action.getAsString(objectives);
	}

	@Override
	public String toString()
	{
		return getAsString();
	}

	/**
	 * Get the Actions associated to this Action.
	 * @return
	 */
	public Actions getActions()
	{
		return this.action;
	}
}
