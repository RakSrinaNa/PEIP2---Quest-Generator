package fr.polytech.di.questgenerator.objects;

import fr.polytech.di.questgenerator.enums.Actions;
import fr.polytech.di.questgenerator.enums.Objectives;
import java.util.HashMap;
import java.util.Optional;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class Action
{
	private final Actions action;
	private final Optional<HashMap<Objectives, String>> objectives;
	private final Optional<Quest> subquest;
	private final boolean splittable;

	public Action(int depth, Actions action)
	{
		this(depth, action, true);
	}

	public Action(int depth, Actions action, boolean splittable)
	{
		this(depth, action, Optional.empty(), splittable);
	}

	public Action(int depth, Actions action, HashMap<Objectives, String> objectives)
	{
		this(depth, action, Optional.ofNullable(objectives));
	}

	public Action(int depth, Actions action, Optional<HashMap<Objectives, String>> objectives)
	{
		this(depth, action, objectives, true);
	}

	public Action(int depth, Actions action, HashMap<Objectives, String> objectives, boolean splittable)
	{
		this(depth, action, Optional.ofNullable(objectives), splittable);
	}

	public Action(int depth, Actions action, Optional<HashMap<Objectives, String>> objectives, boolean splittable)
	{
		this.action = action;
		this.objectives = objectives;
		this.splittable = splittable;
		this.subquest = this.genSubquest(depth);
	}

	private Optional<Quest> genSubquest(int depth)
	{
		if(!this.splittable || this.action.isEmpty())
			return Optional.empty();
		return action.getSubquest(depth, this.objectives);
	}

	public Optional<Quest> getSubquest()
	{
		return this.subquest;
	}

	public String getAsString()
	{
		return this.action.getAsString(objectives);
	}

	public Actions getActions()
	{
		return this.action;
	}
}
