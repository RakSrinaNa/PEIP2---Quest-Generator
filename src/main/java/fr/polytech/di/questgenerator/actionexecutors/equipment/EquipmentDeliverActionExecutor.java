package fr.polytech.di.questgenerator.actionexecutors.equipment;

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
public class EquipmentDeliverActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		Quest quest = new Quest(parent);
		XMLStringObjectiveElement objectiveObject = DataHandler.getRandomFromCategories(parent, "object/stuff/*");
		XMLStringObjectiveElement objectivePNJ = DataHandler.getRandomFromCategories(parent, "pnj/being/*");
		Action actionGet = new Action(quest, this.getClass(), depth, ActionType.GET, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, objectiveObject), new ObjectiveHelper(LOC_OBJECTIVE, DataHandler.getRandomFromCategories(parent, "pnj/being/*", "area/place/*"))));
		Action actionGoto = new Action(quest, this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, objectivePNJ)));
		Action actionGive = new Action(quest, this.getClass(), depth, ActionType.GIVE, buildObjective(objectives, new ObjectiveHelper(OBJ_GIVE, objectiveObject), new ObjectiveHelper(LOC_OBJECTIVE, objectivePNJ)));
		return Quest.initQuest(quest, getSentence("Equipment_Deliver", objectiveObject, objectivePNJ), actionGet, actionGoto, actionGive);
	}
}
