package fr.polytech.di.questgenerator.actionexecutors.conquest;

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
public class ConquestStealActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		Quest quest = new Quest(parent);
		XMLStringObjectiveElement pnjSteal = DataHandler.getRandomFromCategories(parent, "pnj/being/*");
		XMLStringObjectiveElement objectiveObject = DataHandler.getRandomFromCategories(parent, "object/stuff/*");
		XMLStringObjectiveElement pnjGive = DataHandler.getRandomFromCategories(parent, "pnj/being/*");
		Action actionGotoSteal = new Action(quest, this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, pnjSteal)));
		Action actionSteal = new Action(quest, this.getClass(), depth, ActionType.STEAL, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, objectiveObject), new ObjectiveHelper(PNJ, pnjSteal)));
		Action actionGotoGive = new Action(quest, this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, pnjGive)));
		Action actionGive = new Action(quest, this.getClass(), depth, ActionType.GIVE, buildObjective(objectives, new ObjectiveHelper(OBJ_GIVE, objectiveObject), new ObjectiveHelper(LOC_OBJECTIVE, pnjGive)), false);
		return Quest.initQuest(quest, getSentence("Conquest_Steal", pnjGive, objectiveObject, pnjSteal), actionGotoSteal, actionSteal, actionGotoGive, actionGive);
	}
}
