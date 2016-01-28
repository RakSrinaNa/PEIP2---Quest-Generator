package fr.polytech.di.questgenerator.interfaces;

import fr.polytech.di.questgenerator.enums.ObjectiveType;
import fr.polytech.di.questgenerator.objects.ObjectiveHelper;
import fr.polytech.di.questgenerator.objects.Quest;
import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveElement;
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
	Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives);

	default Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> buildObjective(Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> oldObjectives, ObjectiveHelper... helpers)
	{
		HashMap<ObjectiveType, XMLStringObjectiveElement> objectives = new HashMap<>();
		for(ObjectiveHelper helper : helpers)
			objectives.put(helper.getObjective(), helper.getValue(oldObjectives));
		return Optional.of(objectives);
	}
}
