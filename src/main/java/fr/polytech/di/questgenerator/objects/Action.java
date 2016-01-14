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

	public Action(int depth, Actions action)
	{
		this(depth, action, Optional.empty());
	}

	public Action(int depth, Actions action, HashMap<Objectives, String> objectives)
	{
		this(depth, action, Optional.ofNullable(objectives));
	}

	public Action(int depth, Actions action, Optional<HashMap<Objectives, String>> objectives)
	{
		this.action = action;
		this.objectives = objectives;
		this.subquest = this.genSubquest(depth);
	}

	private Optional<Quest> genSubquest(int depth)
	{
		if(this.action.isEmpty())
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
