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
import static fr.polytech.di.questgenerator.enums.ObjectiveType.OBJECTIVE;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class EquipmentAssembleActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		Quest quest = new Quest(parent);
		Action actionRepair = new Action(quest, this.getClass(), depth, ActionType.REPAIR, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, DataHandler.getRandomFromCategories(parent, "object/stuff/*"))), false);
		return Quest.initQuest(quest, getSentence("Equipment_Assemble", actionRepair.getObjective(OBJECTIVE)), actionRepair);
	}
}
