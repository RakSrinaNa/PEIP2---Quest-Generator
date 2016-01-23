package fr.polytech.di.questgenerator.objects;

import fr.polytech.di.questgenerator.enums.ActionType;
import java.util.Collections;
import java.util.LinkedHashSet;

/**
 * A quest.
 *
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class Quest
{
	private final LinkedHashSet<Action> actions;

	/**
	 * Constructor.
	 *
	 * @param actions The list of Action defining the quest.
	 */
	public Quest(Action... actions)
	{
		this.actions = new LinkedHashSet<>(actions.length);
		Collections.addAll(this.actions, actions);
	}

	/**
	 * Get the Epsillon Quest with is the quest that is empty.
	 * @param depth
	 * @return
	 */
	public static Quest getEpsilon(int depth)
	{
		return new Quest(new Action(depth, ActionType.NONE, false));
	}

	/**
	 * Get the Action of the quest.
	 *
	 * @return The Action.
	 */
	public LinkedHashSet<Action> getActions()
	{
		return this.actions;
	}

	/**
	 * Get the quest as a string with all the subquests.
	 *
	 * @return A list of string.
	 */
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

	/**
	 * Used to know if the quest is empty.
	 *
	 * @return true if empty, else false.
	 */
	public boolean isEmpty()
	{
		for(Action action : getActions())
			if(action.getActions() != ActionType.NONE)
				return false;
		return true;
	}
}
