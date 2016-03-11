package fr.polytech.di.questgenerator;

import fr.polytech.di.questgenerator.enums.Motivations;
import fr.polytech.di.questgenerator.enums.ObjectiveType;
import fr.polytech.di.questgenerator.enums.Strategies;
import fr.polytech.di.questgenerator.objects.Action;
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
	private static boolean debug = false;

	/**
	 * Used to get the max depth.
	 *
	 * @return The max depth.
	 */
	public static int getMaxDepth()
	{
		return maxDepth;
	}

	/**
	 * Used to set the max depth.
	 *
	 * @param maxDepth The max depth to set.
	 */
	public static void setMaxDepth(int maxDepth)
	{
		QuestGenerator.maxDepth = maxDepth;
	}

	private static int maxDepth = 3;

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
	 * @param parent The parent action, null if none.
	 * @param depth The depth of the quest.
	 * @param objectives The objectives for the quest.
	 * @return A Quest.
	 */
	public static Quest createNewRandomQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		return Strategies.getRandom().createQuest(parent, depth, objectives);
	}

	/**
	 * Start a subquest randomly.
	 *
	 * @param parent The parent action, null if none.
	 * @param depth The depth of the quest.
	 * @param objectives The objectives for the quest.
	 * @return A Quest.
	 */
	public static Quest createRandomSubquest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		return Strategies.getRandom().createQuest(parent, depth, objectives);
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

	/**
	 * Create a quest randomly that is from the given Motivation.
	 *
	 * @param parent The parent action, null if none.
	 * @param motivation The Motivation of the Strategy that will be picked.
	 * @param depth The depth of the quest.
	 * @param objectives The objectives for the quest.
	 * @return A Quest.
	 */
	public static Quest createByMotivation(Action parent, Motivations motivation, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		return Strategies.getByMotivation(motivation).createQuest(parent, depth, objectives);
	}

	/**
	 * Used to get the debug status.
	 *
	 * @return The debug status.
	 */
	public static boolean getDebug()
	{
		return debug;
	}

	/**
	 * Used to set the debug status.
	 *
	 * @param debug The status to set.
	 */
	public static void setDebug(boolean debug)
	{
		QuestGenerator.debug = debug;
	}
}
