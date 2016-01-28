package fr.polytech.di.questgenerator.actionexecutors.serenity;

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
public class SerenityRescueActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, String>> objectives)
	{
		String pnjDamage = DataHandler.getRandomPNJ("being/*");
		String pnjEscort = DataHandler.getRandomPNJ("being/*");
		String pnjReport = DataHandler.getRandomPNJ("being/*");

		Action actionGotoDamage = new Action(depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, pnjEscort)));
		Action actionDamage = new Action(depth, ActionType.DAMAGE, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, pnjDamage)), false);
		Action actionEscort = new Action(depth, ActionType.ESCORT, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, pnjEscort)), false);
		Action actionGotoReport = new Action(depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, pnjReport)));
		Action actionReport = new Action(depth, ActionType.REPORT, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, pnjReport)), false);

		return new Quest(actionGotoDamage, actionDamage, actionEscort, actionGotoReport, actionReport);
	}
}
