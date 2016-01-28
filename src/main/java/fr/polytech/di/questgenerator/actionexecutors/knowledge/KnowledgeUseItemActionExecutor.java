package fr.polytech.di.questgenerator.actionexecutors.knowledge;

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
public class KnowledgeUseItemActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, String>> objectives)
	{
		String objectiveObject = DataHandler.getRandomObject();
		String objectivePNJ = DataHandler.getRandomPNJ("being/*");

		Action actionGet = new Action(depth, ActionType.GET, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, NONE, objectiveObject), new ObjectiveHelper(LOC_OBJECTIVE, NONE, DataHandler.getRandomFromCategories("pnj/being/*", "area/*"))));
		Action actionGotoUse = new Action(depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, DataHandler.getRandomArea("wild/*"))));
		Action actionUse = new Action(depth, ActionType.USE, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, objectiveObject)), false);
		Action actionGotoGive = new Action(depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, objectivePNJ)));
		Action actionGive = new Action(depth, ActionType.GIVE, buildObjective(objectives, new ObjectiveHelper(OBJ_GIVE, NONE, objectiveObject), new ObjectiveHelper(LOC_OBJECTIVE, NONE, objectivePNJ)));

		return new Quest(actionGet, actionGotoUse, actionUse, actionGotoGive, actionGive);
	}
}
