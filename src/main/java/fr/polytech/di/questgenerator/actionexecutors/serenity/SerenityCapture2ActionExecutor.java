package fr.polytech.di.questgenerator.actionexecutors.serenity;

import fr.polytech.di.questgenerator.enums.ActionType;
import fr.polytech.di.questgenerator.enums.ObjectiveType;
import fr.polytech.di.questgenerator.interfaces.ActionExecutor;
import fr.polytech.di.questgenerator.objects.Action;
import fr.polytech.di.questgenerator.objects.DataHandler;
import fr.polytech.di.questgenerator.objects.ObjectiveHelper;
import fr.polytech.di.questgenerator.objects.Quest;
import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveElement;
import java.util.HashMap;
import java.util.Optional;
import static fr.polytech.di.questgenerator.enums.ObjectiveType.*;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class SerenityCapture2ActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		XMLStringObjectiveElement objectiveObject = DataHandler.getRandomObject("weapon/*");
		XMLStringObjectiveElement pnjGive = DataHandler.getRandomPNJ("being/*");
		XMLStringObjectiveElement pnjCapture = DataHandler.getRandomPNJ("being/*");

		Action actionGet = new Action(this.getClass(), depth, ActionType.GET, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, NONE, objectiveObject), new ObjectiveHelper(LOC_OBJECTIVE, NONE, pnjGive)));
		Action actionGotoUse = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, pnjCapture)));
		Action actionUse = new Action(this.getClass(), depth, ActionType.USE, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, objectiveObject)), false);
		Action actionCapture = new Action(this.getClass(), depth, ActionType.CAPTURE, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, pnjCapture)), false);
		Action actionGotoGive = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, pnjGive)));
		Action actionGive = new Action(this.getClass(), depth, ActionType.GIVE, buildObjective(objectives, new ObjectiveHelper(LOC_OBJECTIVE, NONE, pnjGive), new ObjectiveHelper(OBJ_GIVE, NONE, pnjCapture)));

		return new Quest(actionGet, actionGotoUse, actionUse, actionCapture, actionGotoGive, actionGive);
	}
}
