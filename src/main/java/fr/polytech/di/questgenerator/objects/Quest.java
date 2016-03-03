package fr.polytech.di.questgenerator.objects;

import fr.polytech.di.questgenerator.enums.ActionType;
import fr.polytech.di.questgenerator.interfaces.GameListener;
import fr.polytech.di.questgenerator.interfaces.QuestListener;
import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * A quest.
 *
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class Quest implements GameListener
{
	private final List<QuestListener> questListeners;
	private final Action parent;
	private final String description;
	private final LinkedHashSet<Action> actions;

	/**
	 * Constructor.
	 *
	 * @param actions The list of Action defining the quest.
	 */
	public Quest(Action parent, Action... actions)
	{
		this(parent, null, actions);
	}

	/**
	 * Constructor.
	 *
	 * @param description The description of the quest.
	 * @param actions The list of Action defining the quest.
	 */
	public Quest(Action parent, String description, Action... actions)
	{
		this.questListeners = new ArrayList<>();
		this.parent = parent;
		this.description = description;
		this.actions = new LinkedHashSet<>(actions.length);
		Collections.addAll(this.actions, actions);
		this.actions.stream().forEach(a -> a.setParentQuest(this));
	}

	/**
	 * Get the Epsilon Quest with is the quest that is empty.
	 *
	 * @return The Epsilon Quest.
	 */
	public static Quest getEpsilon(Action parent)
	{
		return new QuestEpsilon(parent);
	}

	@Override
	public String toString()
	{
		return actions.toString();
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
		return getAsString(true);
	}

	/**
	 * Get the quest as a string.
	 *
	 * @param subquests Include subquests or not.
	 * @return A list of string.
	 */
	public String[] getAsString(boolean subquests)
	{
		StringBuilder sb = new StringBuilder();
		if(this.hasDescription())
		{
			sb.append(this.getDescription());
			sb.append("\n\n");
		}
		for(Action action : getActions())
		{
			if(sb.length() > 0)
				sb.append("\n");
			sb.append(action.getAsString());
			if(subquests && action.getSubquest().isPresent())
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
		if(this instanceof QuestEpsilon)
			return true;
		for(Action action : getActions())
			if(action.getActionType() != ActionType.NONE)
				return false;
		return true;
	}

	/**
	 * Used to know if this Quest have a description.
	 *
	 * @return True if a description is present, false if not.
	 */
	public boolean hasDescription()
	{
		return this.description != null;
	}

	/**
	 * Used to get the description.
	 *
	 * @return the description.
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	 * Used to know if the given action is currently doable.
	 *
	 * @param action The action concerned.
	 * @return True if doable, false if not.
	 */
	public boolean isActionDoable(Action action)
	{
		if(!this.actions.contains(action))
			return false;
		for(Action a : this.actions)
		{
			if(a == action)
				break;
			if(!a.isDone())
				return false;
		}
		return true;
	}

	/**
	 * Used to know if that quest is marked as done.
	 *
	 * @return True if done, false if not.
	 */
	public boolean isDone()
	{
		for(Action a : this.actions)
			if(!a.isDone())
				return false;
		return true;
	}

	public Action getActionToDo()
	{
		for(Action a : this.actions)
			if(!a.isDone() && a.isDoable())
				return a.getActionToDo();
		return null;
	}

	public Action getParent()
	{
		return this.parent;
	}

	public void notifyQuestDone(Quest quest)
	{
		for(QuestListener listener : questListeners)
			listener.questDone(quest);
		if(getParent() != null)
			getParent().notifyQuestDone(quest);
	}

	public void notifyActionDone(Action action)
	{
		for(QuestListener listener : questListeners)
			listener.actionDone(action);
		if(getParent() != null)
			getParent().notifyActionDone(action);
		if(isDone())
			notifyQuestDone(this);
	}

	public void addQuestListener(QuestListener questListener)
	{
		this.questListeners.add(questListener);
	}

	@Override
	public boolean areaExploredEvent(XMLStringObjectiveElement area)
	{
		if(this.isDone())
			return false;
		boolean result = false;
		for(Action action : this.getActions())
			result |= action.areaExploredEvent(area);
		return result;
	}

	@Override
	public boolean objectGotEvent(XMLStringObjectiveElement object, XMLStringObjectiveElement from)
	{
		if(this.isDone())
			return false;
		boolean result = false;
		for(Action action : this.getActions())
			result |= action.objectGotEvent(object, from);
		return result;
	}

	@Override
	public boolean listenedEvent(XMLStringObjectiveElement pnj)
	{
		if(this.isDone())
			return false;
		boolean result = false;
		for(Action action : this.getActions())
			result |= action.listenedEvent(pnj);
		return result;
	}

	@Override
	public boolean usedEvent(XMLStringObjectiveElement used, XMLStringObjectiveElement on)
	{
		if(this.isDone())
			return false;
		boolean result = false;
		for(Action action : this.getActions())
			result |= action.usedEvent(used, on);
		return result;
	}
}
