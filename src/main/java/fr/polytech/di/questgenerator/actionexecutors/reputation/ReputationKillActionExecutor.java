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
public class ReputationKillActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		XMLStringObjectiveElement pnjKill = DataHandler.getRandomFromCategories(parent, "pnj/boss/*");
		XMLStringObjectiveElement pnjReport = DataHandler.getRandomFromCategories(parent, "pnj/being/*");
		Action actionGotoKill = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, pnjKill)));
		Action actionKill = new Action(this.getClass(), depth, ActionType.KILL, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, pnjKill)));
		Action actionGotoReport = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, OBJECTIVE, pnjReport)));
		Action actionReport = new Action(this.getClass(), depth, ActionType.REPORT, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, OBJECTIVE, pnjReport)), false);
		return new Quest(parent, getSentence("Reputation_Kill", pnjKill, pnjReport), actionGotoKill, actionKill, actionGotoReport, actionReport);
	}
}
