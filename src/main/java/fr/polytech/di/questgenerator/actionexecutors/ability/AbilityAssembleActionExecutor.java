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
import static fr.polytech.di.questgenerator.enums.ObjectiveType.OBJECTIVE;
import static fr.polytech.di.questgenerator.enums.ObjectiveType.OBJ_USE;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class AbilityAssembleActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		XMLStringObjectiveElement objectiveObject = DataHandler.getRandomFromCategories("object/stuff/weapon/*");

		Action actionRepair = new Action(this.getClass(), depth, ActionType.REPAIR, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, objectiveObject)), false);
		Action actionUse = new Action(this.getClass(), depth, ActionType.USE, buildObjective(objectives, new ObjectiveHelper(OBJ_USE, objectiveObject), new ObjectiveHelper(LOC_OBJECTIVE, DataHandler.getRandomFromCategories("object/training/*"))), false);

		return new Quest(getSentence("Ability_Assemble", objectiveObject), actionRepair, actionUse);
	}
}
