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
public class SerenityCapture1ActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		XMLStringObjectiveElement objectiveObject = DataHandler.getRandomFromCategories("object/stuff/capture/*");
		XMLStringObjectiveElement objectiveGive = DataHandler.getRandomFromCategories("pnj/being/*");
		XMLStringObjectiveElement objectiveCapture = DataHandler.getRandomFromCategories("pnj/being/*");

		Action actionGet = new Action(this.getClass(), depth, ActionType.GET, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, objectiveObject), new ObjectiveHelper(LOC_OBJECTIVE, objectiveGive)));
		Action actionGotoUse = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, objectiveCapture)));
		Action actionUse = new Action(this.getClass(), depth, ActionType.USE, buildObjective(objectives, new ObjectiveHelper(OBJ_USE, objectiveObject), new ObjectiveHelper(LOC_OBJECTIVE, objectiveCapture)), false);
		Action actionGotoGive = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, objectiveGive)), false);
		Action actionGive = new Action(this.getClass(), depth, ActionType.GIVE, buildObjective(objectives, new ObjectiveHelper(LOC_OBJECTIVE, objectiveGive), new ObjectiveHelper(OBJ_GIVE, objectiveCapture)));

		return new Quest(parent, getSentence("Serenity_Capture1", objectiveCapture, objectiveGive), actionGet, actionGotoUse, actionUse, actionGotoGive, actionGive);
	}
}
