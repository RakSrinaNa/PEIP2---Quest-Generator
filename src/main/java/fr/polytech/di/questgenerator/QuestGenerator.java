package fr.polytech.di.questgenerator;

import fr.polytech.di.questgenerator.enums.Motivations;
import fr.polytech.di.questgenerator.enums.Objectives;
import fr.polytech.di.questgenerator.enums.Strategies;
import fr.polytech.di.questgenerator.objects.Quest;
import java.util.HashMap;
import java.util.Optional;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class QuestGenerator
{
	public static Quest createNewRandomQuest()
	{
		return Strategies.getRandom().createQuest();
	}

	public static Quest createNewRandomQuest(int depth, Optional<HashMap<Objectives, String>> objectives)
	{
		return Strategies.getRandom().createQuest(depth, objectives);
	}

	public static Quest createByMotivation(Motivations motivation)
	{
		return Strategies.getByMotivation(motivation).createQuest();
	}
}
