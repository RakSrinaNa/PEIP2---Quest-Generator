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
public class ActionStealTakeActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		Action actionKill = new Action(this.getClass(), depth, ActionType.KILL, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, PNJ)));
		Action actionTake = new Action(this.getClass(), depth, ActionType.TAKE, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, OBJ_GET), new ObjectiveHelper(PNJ, PNJ)), false);

		return new Quest(parent, actionKill, actionTake);
	}
}
