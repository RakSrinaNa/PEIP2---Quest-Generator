package fr.polytech.di.questgenerator.actionexecutors.equipment;

import fr.polytech.di.questgenerator.enums.ActionType;
import fr.polytech.di.questgenerator.enums.ObjectiveType;
import fr.polytech.di.questgenerator.interfaces.ActionExecutor;
import fr.polytech.di.questgenerator.objects.Action;
import fr.polytech.di.questgenerator.objects.DataHandler;
import fr.polytech.di.questgenerator.objects.ObjectiveHelper;
import fr.polytech.di.questgenerator.objects.Quest;
import java.util.HashMap;
import java.util.Optional;
import static fr.polytech.di.questgenerator.enums.ObjectiveType.NONE;
import static fr.polytech.di.questgenerator.enums.ObjectiveType.OBJ_GET;
import static fr.polytech.di.questgenerator.enums.ObjectiveType.PNJ;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class EquipmentStealActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, String>> objectives)
	{
		Action actionSteal = new Action(depth, ActionType.STEAL, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, NONE, DataHandler.getRandomObject("weapon/*")), new ObjectiveHelper(PNJ, NONE, DataHandler.getRandomPNJ("being/*"))));

		return new Quest(actionSteal);
	}
}
