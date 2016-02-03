package fr.polytech.di.questgenerator.actionexecutors.wealth;

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
public class WealthStealActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		XMLStringObjectiveElement objectivePNJ = DataHandler.getRandomFromCategories("pnj/being/*");

		Action actionGoto = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, objectivePNJ)));
		Action actionSteal = new Action(this.getClass(), depth, ActionType.STEAL, buildObjective(objectives, new ObjectiveHelper(PNJ, objectivePNJ), new ObjectiveHelper(OBJ_GET, DataHandler.getRandomFromCategories("object/personal/*", "object/luxury/*"))));

		return new Quest(actionGoto, actionSteal);
	}
}
