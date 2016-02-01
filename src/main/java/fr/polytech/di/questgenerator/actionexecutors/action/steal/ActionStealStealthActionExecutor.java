package fr.polytech.di.questgenerator.actionexecutors.action.steal;

import fr.polytech.di.questgenerator.enums.ActionType;
import fr.polytech.di.questgenerator.enums.ObjectiveType;
import fr.polytech.di.questgenerator.interfaces.ActionExecutor;
import fr.polytech.di.questgenerator.objects.Action;
import fr.polytech.di.questgenerator.objects.ObjectiveHelper;
import fr.polytech.di.questgenerator.objects.Quest;
import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveElement;
import java.util.HashMap;
import java.util.Optional;
import static fr.polytech.di.questgenerator.enums.ObjectiveType.*;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class ActionStealStealthActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		Action actionGoto = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, PNJ)));
		Action actionStealth = new Action(this.getClass(), depth, ActionType.STEALTH, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, PNJ)), false);
		Action actionTake = new Action(this.getClass(), depth, ActionType.TAKE, buildObjective(objectives, new ObjectiveHelper(PNJ, PNJ), new ObjectiveHelper(OBJ_GET, OBJ_GET)), false);

		return new Quest(actionGoto, actionStealth, actionTake);
	}
}
