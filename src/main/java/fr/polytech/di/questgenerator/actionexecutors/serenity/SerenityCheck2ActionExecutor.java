package fr.polytech.di.questgenerator.actionexecutors.serenity;

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
public class SerenityCheck2ActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		XMLStringObjectiveElement objectiveObject = DataHandler.getRandomObject("personal/*");
		XMLStringObjectiveElement pnjTake = DataHandler.getRandomPNJ("being/*");
		XMLStringObjectiveElement pnjGive = DataHandler.getRandomPNJ("being/*");

		Action actionGotoTake = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, pnjTake)));
		Action actionTake = new Action(this.getClass(), depth, ActionType.TAKE, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, NONE, objectiveObject), new ObjectiveHelper(PNJ, NONE, pnjTake)), false);
		Action actionGotoGive = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, pnjGive)));
		Action actionGive = new Action(this.getClass(), depth, ActionType.GIVE, buildObjective(objectives, new ObjectiveHelper(OBJ_GIVE, NONE, objectiveObject), new ObjectiveHelper(LOC_OBJECTIVE, NONE, pnjGive)), false);

		return new Quest(actionGotoTake, actionTake, actionGotoGive, actionGive);
	}
}
