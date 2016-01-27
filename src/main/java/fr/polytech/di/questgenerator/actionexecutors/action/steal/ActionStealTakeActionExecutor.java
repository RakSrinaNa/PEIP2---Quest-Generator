package fr.polytech.di.questgenerator.actionexecutors.action.steal;

import fr.polytech.di.questgenerator.enums.ActionType;
import fr.polytech.di.questgenerator.enums.ObjectiveType;
import fr.polytech.di.questgenerator.interfaces.ActionExecutor;
import fr.polytech.di.questgenerator.objects.Action;
import fr.polytech.di.questgenerator.objects.DataHandler;
import fr.polytech.di.questgenerator.objects.ObjectiveHelper;
import fr.polytech.di.questgenerator.objects.Quest;
import java.util.HashMap;
import java.util.Optional;
import static fr.polytech.di.questgenerator.enums.ObjectiveType.OBJECTIVE;
import static fr.polytech.di.questgenerator.enums.ObjectiveType.OBJ_GET;
import static fr.polytech.di.questgenerator.enums.ObjectiveType.PNJ;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class ActionStealTakeActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, String>> objectives)
	{
		String objectivePnj = DataHandler.getRandomPNJ();
		return new Quest(new Action(depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, PNJ, objectivePnj))), new Action(depth, ActionType.KILL, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, PNJ, objectivePnj))), new Action(depth, ActionType.TAKE, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, OBJ_GET, DataHandler.getRandomObject()), new ObjectiveHelper(PNJ, PNJ, objectivePnj)), false));
	}
}
