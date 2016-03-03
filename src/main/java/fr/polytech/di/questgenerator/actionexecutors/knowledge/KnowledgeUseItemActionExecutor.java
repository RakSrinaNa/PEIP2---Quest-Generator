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
public class KnowledgeUseItemActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		XMLStringObjectiveElement objectiveObject = DataHandler.getRandomFromCategories("object/usable/*");
		XMLStringObjectiveElement objectivePNJ = DataHandler.getRandomFromCategories("pnj/being/*");
		XMLStringObjectiveElement objectiveArea = DataHandler.getRandomFromCategories("area/wonder/*");

		Action actionGet = new Action(this.getClass(), depth, ActionType.GET, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, objectiveObject), new ObjectiveHelper(LOC_OBJECTIVE, DataHandler.getRandomFromCategories("pnj/being/*"))));
		Action actionGotoUse = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, objectiveArea)));
		Action actionUse = new Action(this.getClass(), depth, ActionType.USE, buildObjective(objectives, new ObjectiveHelper(OBJ_USE, objectiveObject), new ObjectiveHelper(LOC_OBJECTIVE, objectiveArea)), false);
		Action actionGotoGive = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, objectivePNJ)));
		Action actionGive = new Action(this.getClass(), depth, ActionType.GIVE, buildObjective(objectives, new ObjectiveHelper(OBJ_GIVE, objectiveObject), new ObjectiveHelper(LOC_OBJECTIVE, objectivePNJ)));

		return new Quest(parent, getSentence("Knowledge_UseItem", objectiveObject, objectivePNJ, objectiveArea), actionGet, actionGotoUse, actionUse, actionGotoGive, actionGive);
	}
}
