package fr.polytech.di.questgenerator.actionexecutors.action.get;

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
public class ActionGetGatherActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		Action actionGoto = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, LOC_OBJECTIVE)));
		Action actionGather = new Action(this.getClass(), depth, ActionType.GATHER, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, OBJ_GET)), false);

		return new Quest(actionGoto, actionGather);
	}

	@Override
	public boolean isActionAllowed(Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		return !objectives.isPresent() || (!objectives.get().containsKey(OBJ_GET) || !objectives.get().containsKey(LOC_OBJECTIVE)) && objectives.get().get(OBJ_GET).isInPath("object/gatherable/*") && objectives.get().get(LOC_OBJECTIVE).isInPath("area/*");
	}
}
