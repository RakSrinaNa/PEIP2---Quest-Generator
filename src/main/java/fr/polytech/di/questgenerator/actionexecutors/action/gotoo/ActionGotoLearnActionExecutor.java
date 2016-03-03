package fr.polytech.di.questgenerator.actionexecutors.action.gotoo;

import fr.polytech.di.questgenerator.enums.ActionType;
import fr.polytech.di.questgenerator.enums.ObjectiveType;
import fr.polytech.di.questgenerator.interfaces.ActionExecutor;
import fr.polytech.di.questgenerator.objects.Action;
import fr.polytech.di.questgenerator.objects.ObjectiveHelper;
import fr.polytech.di.questgenerator.objects.Quest;
import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveElement;
import java.util.HashMap;
import java.util.Optional;
import static fr.polytech.di.questgenerator.enums.ObjectiveType.OBJECTIVE;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class ActionGotoLearnActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		Action actionLearn = new Action(this.getClass(), depth, ActionType.LEARN, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, OBJECTIVE)));
		Action actionGoto = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, OBJECTIVE)), false);

		return new Quest(parent, actionLearn, actionGoto);
	}

	@Override
	public boolean isActionAllowed(Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		return !(!objectives.isPresent() || !objectives.get().containsKey(OBJECTIVE)) && objectives.get().get(OBJECTIVE).isInPath("pnj/being/*", "pnj/beast/*", "area/place/*");
	}
}
