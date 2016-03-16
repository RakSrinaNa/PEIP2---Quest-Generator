package fr.polytech.di.questgenerator.actionexecutors.action.learn;

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
public class ActionLearnReadActionListener implements ActionExecutor
{
	@Override
	public Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		XMLStringObjectiveElement objectivePlace = DataHandler.getRandomFromCategories(parent, "area/place/*");
		XMLStringObjectiveElement objectiveRead = DataHandler.getRandomFromCategories(parent, "object/readable/learning/*");
		Action actionGoto = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, objectivePlace)));
		Action actionGet = new Action(this.getClass(), depth, ActionType.GET, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, objectiveRead), new ObjectiveHelper(LOC_OBJECTIVE, objectivePlace)));
		Action actionRead = new Action(this.getClass(), depth, ActionType.READ, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, objectiveRead)), false);
		return new Quest(parent, actionGoto, actionGet, actionRead);
	}

	@Override
	public boolean isActionAllowed(Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		return !objectives.isPresent() || !objectives.get().containsKey(OBJECTIVE) && objectives.get().get(OBJECTIVE).isInPath("area/*");
	}
}
