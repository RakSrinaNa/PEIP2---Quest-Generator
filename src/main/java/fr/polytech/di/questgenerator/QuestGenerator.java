package fr.polytech.di.questgenerator;

import fr.polytech.di.questgenerator.enums.Motivations;
import fr.polytech.di.questgenerator.enums.ObjectiveType;
import fr.polytech.di.questgenerator.enums.Strategies;
import fr.polytech.di.questgenerator.objects.Quest;
import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveElement;
import java.util.HashMap;
import java.util.Optional;

/**
 * Generates quests.
 *
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class QuestGenerator
{
	/**
	 * Start a quest randomly.
	 *
	 * @return A Quest.
	 */
	public static Quest createNewRandomQuest()
	{
		return Strategies.getRandom().createQuest();
	}

	/**
	 * Start a quest randomly.
	 *
	 * @param depth The depth of the quest.
	 * @param objectives The objectives for the quest.
	 * @return A Quest.
	 */
	public static Quest createNewRandomQuest(int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		return Strategies.getRandom().createQuest(depth, objectives);
	}

	/**
	 * Create a quest randomly that is from the given Motivation.
	 *
	 * @param motivation The Motivation of the Strategy that will be picked.
	 * @return A Quest.
	 */
	public static Quest createByMotivation(Motivations motivation)
	{
		return Strategies.getByMotivation(motivation).createQuest();
	}
}
