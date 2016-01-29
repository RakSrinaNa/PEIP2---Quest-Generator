package fr.polytech.di.questgenerator.actionexecutors.comfort;

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
import static fr.polytech.di.questgenerator.enums.ObjectiveType.NONE;
import static fr.polytech.di.questgenerator.enums.ObjectiveType.OBJECTIVE;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class ComfortKillActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		XMLStringObjectiveElement pnjKill = DataHandler.getRandomPNJ("beast/pests/*");
		XMLStringObjectiveElement pnjReport = DataHandler.getRandomPNJ("being/*");

		Action actionGotoKill = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE ,pnjKill)));
		Action actionDamage = new Action(this.getClass(), depth, ActionType.DAMAGE, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, pnjKill)), false);
		Action actionGotoReport = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, pnjReport)));
		Action actionReport = new Action(this.getClass(), depth, ActionType.REPORT, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, pnjReport)), false);

		return new Quest(actionGotoKill, actionDamage, actionGotoReport, actionReport);
	}
}
