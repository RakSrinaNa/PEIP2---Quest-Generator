package fr.polytech.di.questgenerator.actionexecutors.knowledge;

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
public class KnowledgeDeliverActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		XMLStringObjectiveElement objectivePNJ = DataHandler.getRandomPNJ("being/*");
		XMLStringObjectiveElement objectiveObject = DataHandler.getRandomObject();

		Action actionGet = new Action(this.getClass(), depth, ActionType.GET, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, NONE, objectiveObject), new ObjectiveHelper(LOC_OBJECTIVE, NONE, DataHandler.getRandomPNJ("being/*"))));
		Action actionGoto = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, objectivePNJ)));
		Action actionGive = new Action(this.getClass(), depth, ActionType.GIVE, buildObjective(objectives, new ObjectiveHelper(OBJ_GIVE, NONE, objectiveObject), new ObjectiveHelper(LOC_OBJECTIVE, NONE, objectivePNJ)), false);

		return new Quest(actionGet, actionGoto, actionGive);
	}
}
