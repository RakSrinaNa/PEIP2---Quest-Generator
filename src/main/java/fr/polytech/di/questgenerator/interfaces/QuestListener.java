package fr.polytech.di.questgenerator.interfaces;

import fr.polytech.di.questgenerator.objects.Action;
import fr.polytech.di.questgenerator.objects.Quest;

/**
 * Interface used to know what happened in a quest. After implementing this interface, you can use the {@link Quest#addQuestListener(QuestListener)} on the root quest to get notified of the events.
 * <p>
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public interface QuestListener
{
	/**
	 * Called when an action is completed.
	 *
	 * @param action The action completed.
	 */
	void actionDone(Action action);

	/**
	 * Called when a quest is done.
	 *
	 * @param quest The quest done.
	 */
	void questDone(Quest quest);
}
