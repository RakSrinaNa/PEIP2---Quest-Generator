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
public class ActionGetSubquestActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		XMLStringObjectiveElement objectiveObject = DataHandler.getRandomObject();
		XMLStringObjectiveElement objectivePNJ = DataHandler.getRandomPNJ("being/*");
		XMLStringObjectiveElement objectiveLocation = DataHandler.getRandomPNJ("being/*");

		Action actionGotoSteal = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, objectiveLocation)));
		Action actionGet = new Action(this.getClass(), depth, ActionType.GET, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, NONE, objectiveObject), new ObjectiveHelper(LOC_OBJECTIVE, NONE, objectiveLocation)));
		Action actionGotoSubquest = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, LOC_OBJECTIVE, objectivePNJ)));
		Action actionSubquest = new Action(this.getClass(), depth, ActionType.SUBQUEST);
		Action actionExchange = new Action(this.getClass(), depth, ActionType.EXCHANGE, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, OBJ_GET, DataHandler.getRandomObject()), new ObjectiveHelper(OBJ_GIVE, NONE, objectiveObject), new ObjectiveHelper(PNJ, LOC_OBJECTIVE, objectivePNJ)), false);

		return new Quest(actionGotoSteal, actionGet, actionGotoSubquest, actionSubquest, actionExchange);
	}
}
