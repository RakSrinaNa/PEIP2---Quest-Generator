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
public class EquipmentTradeActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		Quest quest = new Quest(parent);
		XMLStringObjectiveElement objectivePNJ = DataHandler.getRandomFromCategories(parent, "pnj/being/*");
		Action actionGoto = new Action(quest, this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, objectivePNJ)));
		Action actionExchange = new Action(quest, this.getClass(), depth, ActionType.EXCHANGE, buildObjective(objectives, new ObjectiveHelper(OBJ_GIVE, DataHandler.getRandomFromCategories(parent, "object/stuff/*")), new ObjectiveHelper(OBJ_GET, DataHandler.getRandomFromCategories(parent, "object/stuff/*")), new ObjectiveHelper(PNJ, objectivePNJ)), false);
		return Quest.initQuest(quest, getSentence("Equipment_Trade", actionExchange.getObjective(OBJ_GET)), actionGoto, actionExchange);
	}
}
