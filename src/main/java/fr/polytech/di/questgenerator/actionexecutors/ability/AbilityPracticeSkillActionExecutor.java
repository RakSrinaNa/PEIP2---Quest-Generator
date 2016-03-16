package fr.polytech.di.questgenerator.actionexecutors.ability;

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
import static fr.polytech.di.questgenerator.enums.ObjectiveType.LOC_OBJECTIVE;
import static fr.polytech.di.questgenerator.enums.ObjectiveType.OBJ_USE;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class AbilityPracticeSkillActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		Quest quest = new Quest(parent);
		Action actionUse = new Action(quest, this.getClass(), depth, ActionType.USE, buildObjective(objectives, new ObjectiveHelper(OBJ_USE, DataHandler.getRandomFromCategories(parent, "skill/magic/*")), new ObjectiveHelper(LOC_OBJECTIVE, DataHandler.getRandomFromCategories(parent, "object/training/*"))), false);
		return Quest.initQuest(quest, getSentence("Ability_PracticeSkill", actionUse.getObjective(OBJ_USE)), actionUse);
	}
}
