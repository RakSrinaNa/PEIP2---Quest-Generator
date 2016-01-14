package fr.polytech.di.questgenerator.actionexecutors.ability;

import fr.polytech.di.questgenerator.enums.Actions;
import fr.polytech.di.questgenerator.enums.Objectives;
import fr.polytech.di.questgenerator.interfaces.ActionExecutor;
import fr.polytech.di.questgenerator.objects.Action;
import fr.polytech.di.questgenerator.objects.Quest;
import java.util.HashMap;
import java.util.Optional;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class AbilityAssembleActionExecutor implements ActionExecutor
{
	@Override
	public Quest process(int depth, Optional<HashMap<Objectives, String>> objectives)
	{
		return new Quest(depth, new Action(depth, Actions.REPAIR), new Action(depth, Actions.USE));
	}
}
