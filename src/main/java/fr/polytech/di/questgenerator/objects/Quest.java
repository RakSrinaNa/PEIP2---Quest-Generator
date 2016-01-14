package fr.polytech.di.questgenerator.objects;

import fr.polytech.di.questgenerator.enums.Actions;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Set;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class Quest
{
	private final int depth;
	private final LinkedHashMap<Action, Optional<Quest>> actions;

	public Quest(int depth, Action... actions) throws IllegalArgumentException
	{
		if(actions == null || actions.length < 1)
			throw new IllegalArgumentException("Actions must not be empty");
		this.depth = depth;
		this.actions = new LinkedHashMap<>(actions.length);
		for(Action action : actions)
			this.actions.put(action, action.getSubquest());
	}

	public static Quest getEpsillon(int depth)
	{
		return new Quest(depth, new Action(depth, Actions.NONE, Optional.empty()));
	}

	public Set<Action> getActions()
	{
		return this.actions.keySet();
	}

	public String[] getAsString()
	{
		StringBuilder sb = new StringBuilder();
		for(Action action : getActions())
		{
			if(sb.length() > 0)
				sb.append("\n");
			sb.append(action.getAsString());
			if(actions.get(action).isPresent())
				for(String line : actions.get(action).get().getAsString())
					sb.append("\n\t").append(line);
		}
		return sb.toString().split("\n");
	}

	public boolean isEmpty()
	{
		for(Action action : getActions())
			if(action.getActions() != Actions.NONE)
				return false;
		return true;
	}
}
