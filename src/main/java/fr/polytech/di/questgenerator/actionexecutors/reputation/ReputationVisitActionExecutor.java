package fr.polytech.di.questgenerator.actionexecutors.reputation;

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
public class ReputationVisitActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		Quest quest = new Quest(parent);
		XMLStringObjectiveElement objectivePnj = DataHandler.getRandomFromCategories(parent, "pnj/being/*");
		Action actionGotoVisit = new Action(quest, this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, DataHandler.getRandomFromCategories(parent, "area/dangerous/*"))));
		Action actionGotoReport = new Action(quest, this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, OBJECTIVE, objectivePnj)), parent == null);
		Action actionReport = new Action(quest, this.getClass(), depth, ActionType.REPORT, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, OBJECTIVE, objectivePnj)), false);
		return Quest.initQuest(quest, getSentence("Reputation_Visit", actionGotoVisit.getObjective(OBJECTIVE), actionReport.getObjective(OBJECTIVE)), actionGotoVisit, actionGotoReport, actionReport);
	}
}
