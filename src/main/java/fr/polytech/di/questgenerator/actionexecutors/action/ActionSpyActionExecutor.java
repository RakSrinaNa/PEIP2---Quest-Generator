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
import static fr.polytech.di.questgenerator.enums.ObjectiveType.NONE;
import static fr.polytech.di.questgenerator.enums.ObjectiveType.OBJECTIVE;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class ActionSpyActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, String>> objectives)
	{
		String objectivePnj = DataHandler.getRandomPNJ("being");
		String objectivePnj2 = DataHandler.getRandomPNJ("being");
		return new Quest(new Action(depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, OBJECTIVE, objectivePnj))), new Action(depth, ActionType.SPY, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, OBJECTIVE, objectivePnj)), false), new Action(depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, objectivePnj2))), new Action(depth, ActionType.REPORT, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, objectivePnj2)), false));
	}
}
