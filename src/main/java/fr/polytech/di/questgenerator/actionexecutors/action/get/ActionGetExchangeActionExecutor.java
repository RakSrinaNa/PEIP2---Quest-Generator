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
		XMLStringObjectiveElement objectiveObject = DataHandler.getRandomFromCategories("object/*");
		XMLStringObjectiveElement pnjGet = DataHandler.getRandomFromCategories("pnj/being/*", "area/*");
		XMLStringObjectiveElement pnjExchange = DataHandler.getRandomFromCategories("pnj/being/*");
		ObjectiveHelper getLocHelper;
		if(pnjGet.isInPath("pnj/being/*"))
			getLocHelper = new ObjectiveHelper(LOC_OBJECTIVE, pnjGet);
		else
			getLocHelper = new ObjectiveHelper(LOC_OBJECTIVE, DataHandler.getRandomFromCategories("pnj/being/*"));
		Action actionGotoSteal = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, pnjGet)));
		Action actionGet = new Action(this.getClass(), depth, ActionType.GET, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, objectiveObject), getLocHelper));
		Action actionGotoSubquest = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, pnjExchange)));
		Action actionExchange = new Action(this.getClass(), depth, ActionType.EXCHANGE, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, OBJ_GET), new ObjectiveHelper(OBJ_GIVE, objectiveObject), new ObjectiveHelper(PNJ, pnjExchange)), false);
		return new Quest(parent, actionGotoSteal, actionGet, actionGotoSubquest, actionExchange);
	}

	@Override
	public boolean isActionAllowed(Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		return !objectives.isPresent() || !objectives.get().containsKey(LOC_OBJECTIVE) && objectives.get().get(LOC_OBJECTIVE).isInPath("pnj/being/*");
	}
}
