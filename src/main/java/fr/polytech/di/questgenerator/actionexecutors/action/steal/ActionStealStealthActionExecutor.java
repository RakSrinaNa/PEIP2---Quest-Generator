package fr.polytech.di.questgenerator.actionexecutors.action.steal;

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
import static fr.polytech.di.questgenerator.enums.ObjectiveType.OBJECTIVE;
import static fr.polytech.di.questgenerator.enums.ObjectiveType.OBJ_GET;
import static fr.polytech.di.questgenerator.enums.ObjectiveType.PNJ;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class ActionStealStealthActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		XMLStringObjectiveElement objectivePNJ = DataHandler.getRandomPNJ();

		Action actionGoto = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, PNJ, objectivePNJ)));
		Action actionStealth = new Action(this.getClass(), depth, ActionType.STEALTH, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, PNJ, objectivePNJ)), false);
		Action actionTake = new Action(this.getClass(), depth, ActionType.TAKE, buildObjective(objectives, new ObjectiveHelper(PNJ, PNJ, objectivePNJ), new ObjectiveHelper(OBJ_GET, OBJ_GET, DataHandler.getRandomObject())), false);

		return new Quest(actionGoto, actionStealth, actionTake);
	}
}
