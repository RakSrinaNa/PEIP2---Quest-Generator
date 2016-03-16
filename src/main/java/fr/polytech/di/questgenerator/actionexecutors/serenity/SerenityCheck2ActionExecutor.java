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
import static fr.polytech.di.questgenerator.enums.ObjectiveType.*;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class SerenityCheck2ActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		Quest quest = new Quest(parent);
		XMLStringObjectiveElement objectiveObject = DataHandler.getRandomFromCategories(parent, "object/personal/*");
		XMLStringObjectiveElement pnjTake = DataHandler.getRandomFromCategories(parent, "pnj/being/*");
		ObjectiveHelper pnjGiveHelper = new ObjectiveHelper(OBJECTIVE, OBJECTIVE, DataHandler.getRandomFromCategories(parent, "pnj/being/*"));
		Action actionGotoTake = new Action(quest, this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, pnjTake)));
		Action actionTake = new Action(quest, this.getClass(), depth, ActionType.TAKE, buildObjective(objectives, new ObjectiveHelper(OBJ_GET, objectiveObject), new ObjectiveHelper(PNJ, pnjTake)), false);
		Action actionGotoGive = new Action(quest, this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, pnjGiveHelper), parent == null);
		Action actionGive = new Action(quest, this.getClass(), depth, ActionType.GIVE, buildObjective(objectives, new ObjectiveHelper(OBJ_GIVE, objectiveObject), new ObjectiveHelper(LOC_OBJECTIVE, pnjGiveHelper.getValue(objectives))), false);
		return Quest.initQuest(quest, getSentence("Serenity_Check2", pnjGiveHelper.getValue(objectives), pnjTake), actionGotoTake, actionTake, actionGotoGive, actionGive);
	}
}
