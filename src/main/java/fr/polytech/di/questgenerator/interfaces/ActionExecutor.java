package fr.polytech.di.questgenerator.interfaces;

import fr.polytech.di.questgenerator.enums.ObjectiveType;
import fr.polytech.di.questgenerator.enums.Resources;
import fr.polytech.di.questgenerator.objects.Action;
import fr.polytech.di.questgenerator.objects.ObjectiveHelper;
import fr.polytech.di.questgenerator.objects.Quest;
import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveElement;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Interface for the classes that define how a Quest should be generated.
 * <p>
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public interface ActionExecutor
{
	/**
	 * Generate the quest.
	 *
	 * @param parent The parent action of the quest, null if none.
	 * @param depth The depth of the quest.
	 * @param objectives The objectives for the quest.
	 * @return The Quest.
	 */
	Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives);

	/**
	 * Build a new objective HashMap.
	 *
	 * @param oldObjectives The old objectives.
	 * @param helpers The ObjectiveHelpers.
	 * @return The new HashMap of objectives.
	 */
	default Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> buildObjective(Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> oldObjectives, ObjectiveHelper... helpers)
	{
		HashMap<ObjectiveType, XMLStringObjectiveElement> objectives = new HashMap<>();
		for(ObjectiveHelper helper : helpers)
			if(helper != null)
				objectives.put(helper.getObjective(), helper.getValue(oldObjectives));
		return Optional.of(objectives);
	}

	/**
	 * Used to know is allowed depending of the passed objectives.
	 *
	 * @param objectives The objectives.
	 * @return True if allowed, false if not.
	 */
	default boolean isActionAllowed(Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		return true;
	}

	/**
	 * Used to get a random element among a list of elements.
	 *
	 * @param elements The elements.
	 * @return A random element from the list.
	 */
	default XMLStringObjectiveElement pickRandom(XMLStringObjectiveElement... elements)
	{
		return elements[ThreadLocalRandom.current().nextInt(elements.length)];
	}

	/**
	 * Used to get the description sentence for the quest.
	 *
	 * @param key The key of the sentence.
	 * @param args The arguments for the sentence.
	 * @return The formatted sentence.
	 */
	default String getSentence(String key, XMLStringObjectiveElement... args)
	{
		return MessageFormat.format(Resources.PROPERTIES.getPropertyString("QuestSentences", key), (Object[]) args);
	}
}
