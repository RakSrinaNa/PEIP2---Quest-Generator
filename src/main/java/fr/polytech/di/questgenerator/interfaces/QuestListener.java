package fr.polytech.di.questgenerator.interfaces;

import fr.polytech.di.questgenerator.objects.Action;
import fr.polytech.di.questgenerator.objects.Quest;

/**
 * Created by MrCraftCod on 03/03/2016.
 */
public interface QuestListener
{
	void actionDone(Action action);

	void questDone(Quest quest);
}
