package fr.polytech.di.questgenerator.actionexecutors.protection;

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
public class ProtectionDiversion1ActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		XMLStringObjectiveElement objectiveObject = DataHandler.getRandomFromCategories("object/diversion/*");
		XMLStringObjectiveElement objectiveArea = DataHandler.getRandomFromCategories("area/place/*");

		Action actionGet = new Action(this.getClass(), depth, ActionType.GET, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, objectiveObject), new ObjectiveHelper(LOC_OBJECTIVE, OBJECTIVE, DataHandler.getRandomFromCategories("pnj/being/*"))));
		Action actionGoto = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, objectiveArea)));
		Action actionUse = new Action(this.getClass(), depth, ActionType.USE, buildObjective(objectives, new ObjectiveHelper(OBJ_USE, objectiveObject), new ObjectiveHelper(LOC_OBJECTIVE, objectiveArea)), false);

		return new Quest(getSentence("Protection_Diversion1", actionGet.getObjective(LOC_OBJECTIVE), objectiveArea), actionGet, actionGoto, actionUse);
	}
}
