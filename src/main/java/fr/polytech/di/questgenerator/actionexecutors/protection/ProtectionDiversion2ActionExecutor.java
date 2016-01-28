package fr.polytech.di.questgenerator.actionexecutors.protection;

import fr.polytech.di.questgenerator.enums.ActionType;
import fr.polytech.di.questgenerator.enums.ObjectiveType;
import fr.polytech.di.questgenerator.interfaces.ActionExecutor;
import fr.polytech.di.questgenerator.objects.Action;
import fr.polytech.di.questgenerator.objects.DataHandler;
import fr.polytech.di.questgenerator.objects.ObjectiveHelper;
import fr.polytech.di.questgenerator.objects.Quest;
import java.util.HashMap;
import java.util.Optional;
import static fr.polytech.di.questgenerator.enums.ObjectiveType.NONE;
import static fr.polytech.di.questgenerator.enums.ObjectiveType.OBJECTIVE;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class ProtectionDiversion2ActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, String>> objectives)
	{
		String objectiveLocation = DataHandler.getRandomArea("place/fortification/*");

		Action actionGoto = new Action(depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, objectiveLocation)));
		Action actionDamage = new Action(depth, ActionType.DAMAGE, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, NONE, objectiveLocation)), false);

		return new Quest(actionGoto, actionDamage);
	}
}