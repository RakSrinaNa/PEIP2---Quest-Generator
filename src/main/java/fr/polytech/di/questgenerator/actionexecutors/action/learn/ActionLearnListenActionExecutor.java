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
	public Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		Quest quest = new Quest(parent);
		XMLStringObjectiveElement objectiveObj = DataHandler.getRandomFromCategories(parent, "pnj/being/*", "area/*");
		ObjectiveHelper listenHelper;
		ObjectiveHelper subquestHelper;
		if(objectiveObj.isInPath("pnj/being/*"))
		{
			listenHelper = new ObjectiveHelper(OBJECTIVE, objectiveObj);
			subquestHelper = new ObjectiveHelper(OBJECTIVE, objectiveObj);
		}
		else
		{
			XMLStringObjectiveElement pnjListen = DataHandler.getRandomFromCategories(parent, "pnj/being/*");
			listenHelper = new ObjectiveHelper(OBJECTIVE, pnjListen);
			subquestHelper = new ObjectiveHelper(OBJECTIVE, pnjListen);
		}
		Action actionGoto = new Action(quest, this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, objectiveObj)));
		Action actionSubquest = new Action(quest, this.getClass(), depth, ActionType.QUEST, buildObjective(objectives, subquestHelper));
		Action actionListen = new Action(quest, this.getClass(), depth, ActionType.LISTEN, buildObjective(objectives, listenHelper), false);
		return Quest.initQuest(quest, actionGoto, actionSubquest, actionListen);
	}
}
