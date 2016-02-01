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
import static fr.polytech.di.questgenerator.enums.ObjectiveType.OBJECTIVE;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class ActionLearnListenActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		XMLStringObjectiveElement objectiveObj = DataHandler.getRandomFromCategories("pnj/being/*", "area/*");

		ObjectiveHelper listenHelper;
		if(objectiveObj.isInPath("pnj/being/*"))
			listenHelper = new ObjectiveHelper(OBJECTIVE, objectiveObj);
		else
			listenHelper = new ObjectiveHelper(OBJECTIVE, DataHandler.getRandomFromCategories("pnj/being/*"));

		Action actionGoto = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, objectiveObj)));
		Action actionSubquest = new Action(this.getClass(), depth, ActionType.SUBQUEST);
		Action actionListen = new Action(this.getClass(), depth, ActionType.LISTEN, buildObjective(objectives, listenHelper), false);

		return new Quest(actionGoto, actionSubquest, actionListen);
	}
}
