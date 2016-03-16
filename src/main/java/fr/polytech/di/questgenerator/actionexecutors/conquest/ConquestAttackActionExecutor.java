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
import static fr.polytech.di.questgenerator.enums.ObjectiveType.OBJECTIVE;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class ConquestAttackActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		XMLStringObjectiveElement objectivePNJ = DataHandler.getRandomFromCategories(parent.getUsedObjectives(), "pnj/being/*");
		Action actionGoto = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, objectivePNJ)));
		Action actionDamage = new Action(this.getClass(), depth, ActionType.DAMAGE, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, objectivePNJ)), false);
		return new Quest(parent, getSentence("Conquest_Attack", objectivePNJ), actionGoto, actionDamage);
	}
}
