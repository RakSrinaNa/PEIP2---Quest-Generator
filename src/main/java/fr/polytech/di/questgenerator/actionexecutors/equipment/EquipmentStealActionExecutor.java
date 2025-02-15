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
public class EquipmentStealActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		Quest quest = new Quest(parent);
		XMLStringObjectiveElement pnjObjective = DataHandler.getRandomFromCategories(parent, "pnj/being/*");
		Action actionGoto = new Action(quest, this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, pnjObjective)));
		Action actionSteal = new Action(quest, this.getClass(), depth, ActionType.STEAL, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, DataHandler.getRandomFromCategories(parent, "object/stuff/*")), new ObjectiveHelper(PNJ, pnjObjective)));
		return Quest.initQuest(quest, getSentence("Equipment_Steal", actionSteal.getObjective(OBJ_GET)), actionGoto, actionSteal);
	}
}
