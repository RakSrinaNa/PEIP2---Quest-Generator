package fr.polytech.di.questgenerator.actionexecutors.action.learn;

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
public class ActionLearnGiveActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, String>> objectives)
	{
		String objectiveObject = DataHandler.getRandomObject();
		String objectiveListen = DataHandler.getRandomPNJ("being/*");

		Action actionGet = new Action(depth, ActionType.GET, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, NONE, objectiveObject), new ObjectiveHelper(LOC_OBJECTIVE, NONE, DataHandler.getRandomFromCategories("pnj/*", "area/*"))));
		Action actionSubquest = new Action(depth, ActionType.SUBQUEST);
		Action actionGive = new Action(depth, ActionType.GIVE, buildObjective(objectives, new ObjectiveHelper(OBJ_GIVE, NONE, objectiveObject), new ObjectiveHelper(LOC_OBJECTIVE, NONE, objectiveListen)), false);
		Action actionListen = new Action(depth, ActionType.LISTEN, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, objectiveListen)), false);

		return new Quest(actionGet, actionSubquest, actionGive, actionListen);
	}
}
