package fr.polytech.di.questgenerator.actionexecutors.action;

import fr.polytech.di.questgenerator.enums.ActionType;
import fr.polytech.di.questgenerator.enums.ObjectiveType;
import fr.polytech.di.questgenerator.interfaces.ActionExecutor;
import fr.polytech.di.questgenerator.objects.Action;
import fr.polytech.di.questgenerator.objects.DataHandler;
import fr.polytech.di.questgenerator.objects.ObjectiveHelper;
import fr.polytech.di.questgenerator.objects.Quest;
import java.util.HashMap;
import java.util.Optional;
import static fr.polytech.di.questgenerator.enums.ObjectiveType.*;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class ActionCaptureActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, String>> objectives)
	{
		String objectivePnj = DataHandler.getRandomPNJ();

		Action actionGet = new Action(depth, ActionType.GET, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, NONE, DataHandler.getRandomObject()), new ObjectiveHelper(LOC_OBJECTIVE, NONE, DataHandler.getRandomArea())));
		Action actionGoto = new Action(depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, OBJECTIVE, objectivePnj)));
		Action actionCapture = new Action(depth, ActionType.CAPTURE, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, OBJECTIVE, objectivePnj)), false);

		return new Quest(actionGet, actionGoto, actionCapture);
	}
}
