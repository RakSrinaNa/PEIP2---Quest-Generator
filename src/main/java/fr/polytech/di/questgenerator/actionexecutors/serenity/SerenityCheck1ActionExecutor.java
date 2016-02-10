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
import static fr.polytech.di.questgenerator.enums.ObjectiveType.OBJECTIVE;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class SerenityCheck1ActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		XMLStringObjectiveElement pnjListen = DataHandler.getRandomFromCategories("pnj/being/*");
		XMLStringObjectiveElement pnjReport = DataHandler.getRandomFromCategories("pnj/being/*");

		Action actionGotoListen = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, pnjListen)));
		Action actionListen = new Action(this.getClass(), depth, ActionType.LISTEN, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, pnjListen)), false);
		Action actionGotoReport = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, OBJECTIVE, pnjReport)));
		Action actionReport = new Action(this.getClass(), depth, ActionType.REPORT, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, OBJECTIVE, pnjReport)), false);

		return new Quest(actionGotoListen, actionListen, actionGotoReport, actionReport);
	}
}
