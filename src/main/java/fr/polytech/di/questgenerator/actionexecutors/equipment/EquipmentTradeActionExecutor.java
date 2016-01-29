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
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		XMLStringObjectiveElement objectivePNJ = DataHandler.getRandomPNJ("being/*");

		Action actionGoto = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, objectivePNJ)));
		Action actionExchange = new Action(this.getClass(), depth, ActionType.EXCHANGE, buildObjective(objectives, new ObjectiveHelper(OBJ_GIVE, NONE, DataHandler.getRandomObject("weapon/*")), new ObjectiveHelper(OBJ_GET, NONE, DataHandler.getRandomObject("weapon/*"))), false);

		return new Quest(actionGoto, actionExchange);
	}
}
