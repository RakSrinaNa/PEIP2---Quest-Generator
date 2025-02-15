package fr.polytech.di.questgenerator.actionexecutors.knowledge;

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
public class KnowledgeInterviewActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		Quest quest = new Quest(parent);
		XMLStringObjectiveElement pnjListen = DataHandler.getRandomFromCategories(parent, "pnj/being/*");
		ObjectiveHelper pnjReportHelper = new ObjectiveHelper(OBJECTIVE, OBJECTIVE, DataHandler.getRandomFromCategories(parent, "pnj/being/*"));

		Action actionGotoListen = new Action(quest, this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, pnjListen)));
		Action actionListen = new Action(quest, this.getClass(), depth, ActionType.LISTEN, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, pnjListen)), false);
		Action actionGotoReport = new Action(quest, this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, pnjReportHelper), parent == null);
		Action actionReport = new Action(quest, this.getClass(), depth, ActionType.REPORT, buildObjective(objectives, pnjReportHelper), false);
		return Quest.initQuest(quest, getSentence("Knowledge_Interview", pnjListen, pnjReportHelper.getValue(objectives)), actionGotoListen, actionListen, actionGotoReport, actionReport);
	}
}
