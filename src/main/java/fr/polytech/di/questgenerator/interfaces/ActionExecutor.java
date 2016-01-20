package fr.polytech.di.questgenerator.interfaces;

import fr.polytech.di.questgenerator.enums.Objectives;
import fr.polytech.di.questgenerator.objects.Quest;
import java.util.HashMap;
import java.util.Optional;

/**
 * Interface for the classes that define how a Quest should be generated.
 *
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public interface ActionExecutor
{
	/**
	 * Generate the quest.
	 *
	 * @param depth The depth of the quest.
	 * @param objectives The objectives for the quest.
	 * @return The Quest.
	 */
	Quest generateQuest(int depth, Optional<HashMap<Objectives, String>> objectives);

	/**
	 * Get an Objective value from an HashMap, and if this value is not defined, return the default value.
	 *
	 * @param objectives The objectives.
	 * @param objective The Objective to get.
	 * @param defaultValue The default value.
	 * @return The objective value.
	 */
	default String getArg(Optional<HashMap<Objectives, String>> objectives, Objectives objective, String defaultValue)
	{
		if(!objectives.isPresent() || !objectives.get().containsKey(objective) || objectives.get().get(objective).equals(""))
			return defaultValue;
		return objectives.get().get(objective);
	}
}
