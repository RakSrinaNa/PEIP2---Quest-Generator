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
		XMLStringObjectiveElement v1 = DataHandler.getRandomFromCategories("object/*");
		XMLStringObjectiveElement v2 = DataHandler.getRandomFromCategories("pnj/being/*", "area/*");
		XMLStringObjectiveElement v3 = DataHandler.getRandomFromCategories("pnj/being/*");

		ObjectiveHelper getLocHelper;
		if(v2.isInPath("pnj/being/*"))
			getLocHelper = new ObjectiveHelper(LOC_OBJECTIVE, v2);
		else
			getLocHelper = new ObjectiveHelper(LOC_OBJECTIVE, DataHandler.getRandomFromCategories("pnj/being/*"));


		Action actionGotoSteal = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, v2)));
		Action actionGet = new Action(this.getClass(), depth, ActionType.GET, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, v1), getLocHelper));
		Action actionGotoSubquest = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, v3)));
		Action actionSubquest = new Action(this.getClass(), depth, ActionType.SUBQUEST);
		Action actionExchange = new Action(this.getClass(), depth, ActionType.EXCHANGE, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, OBJ_GET), new ObjectiveHelper(OBJ_GIVE, v1), new ObjectiveHelper(PNJ, v3)), false);

		return new Quest(actionGotoSteal, actionGet, actionGotoSubquest, actionSubquest, actionExchange);
	}

	@Override
	public boolean isActionAllowed(Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		return !objectives.isPresent() || !objectives.get().containsKey(LOC_OBJECTIVE) && objectives.get().get(LOC_OBJECTIVE).isInPath("pnj/being/*");
	}
}
