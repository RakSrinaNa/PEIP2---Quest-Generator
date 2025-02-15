package fr.polytech.di.questgenerator.actionexecutors.action.get;

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
public class ActionGetExchangeActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		Quest quest = new Quest(parent);
		XMLStringObjectiveElement objectiveObject = DataHandler.getRandomFromCategories(parent, "object/*");
		XMLStringObjectiveElement pnjGet = DataHandler.getRandomFromCategories(parent, "pnj/being/*", "area/*");
		XMLStringObjectiveElement pnjExchange = DataHandler.getRandomFromCategories(parent, "pnj/being/*");
		ObjectiveHelper getLocHelper;
		if(pnjGet.isInPath("pnj/being/*"))
			getLocHelper = new ObjectiveHelper(LOC_OBJECTIVE, pnjGet);
		else
			getLocHelper = new ObjectiveHelper(LOC_OBJECTIVE, DataHandler.getRandomFromCategories(parent, "pnj/being/*"));
		Action actionGotoSteal = new Action(quest, this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, pnjGet)));
		Action actionGet = new Action(quest, this.getClass(), depth, ActionType.GET, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, objectiveObject), getLocHelper));
		Action actionGotoSubquest = new Action(quest, this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, pnjExchange)));
		Action actionExchange = new Action(quest, this.getClass(), depth, ActionType.EXCHANGE, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, OBJ_GET), new ObjectiveHelper(OBJ_GIVE, objectiveObject), new ObjectiveHelper(PNJ, pnjExchange)), false);
		return Quest.initQuest(quest, actionGotoSteal, actionGet, actionGotoSubquest, actionExchange);
	}

	@Override
	public boolean isActionAllowed(Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		return !objectives.isPresent() || !objectives.get().containsKey(LOC_OBJECTIVE) && objectives.get().get(LOC_OBJECTIVE).isInPath("pnj/being/*");
	}
}
