package fr.polytech.di.questgenerator.objects;

import fr.polytech.di.questgenerator.enums.Actions;
import java.util.Collections;
import java.util.LinkedHashSet;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class Quest
{
	private final LinkedHashSet<Action> actions;

	public Quest(Action... actions) throws IllegalArgumentException
	{
		if(actions == null || actions.length < 1)
			throw new IllegalArgumentException("Actions must not be empty");
		this.actions = new LinkedHashSet<>(actions.length);
		Collections.addAll(this.actions, actions);
	}

	public static Quest getEpsillon(int depth)
	{
		return new Quest(new Action(depth, Actions.NONE, false));
	}

	public LinkedHashSet<Action> getActions()
	{
		return this.actions;
	}

	public String[] getAsString()
	{
		StringBuilder sb = new StringBuilder();
		for(Action action : getActions())
		{
			if(sb.length() > 0)
				sb.append("\n");
			sb.append(action.getAsString());
			if(action.getSubquest().isPresent())
				for(String line : action.getSubquest().get().getAsString())
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
