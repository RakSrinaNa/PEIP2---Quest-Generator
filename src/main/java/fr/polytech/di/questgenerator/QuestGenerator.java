package fr.polytech.di.questgenerator;

import fr.polytech.di.questgenerator.enums.Motivations;
import fr.polytech.di.questgenerator.enums.Strategies;
import fr.polytech.di.questgenerator.objects.Quest;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class QuestGenerator
{
	public static Quest createNewRandomQuest()
	{
		return Strategies.getRandom().createQuest();
	}

	public static Quest createByMotivation(Motivations motivation)
	{
		return Strategies.getByMotivation(motivation).createQuest();
	}
}
