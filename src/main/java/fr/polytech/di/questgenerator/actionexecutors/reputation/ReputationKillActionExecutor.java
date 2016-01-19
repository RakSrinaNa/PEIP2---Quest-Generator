package fr.polytech.di.questgenerator.actionexecutors.reputation;

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
public class ReputationKillActionExecutor implements ActionExecutor
{
	@Override
	public Quest process(int depth, Optional<HashMap<Objectives, String>> objectives)
	{
		return new Quest(new Action(depth, Actions.GOTO), new Action(depth, Actions.KILL), new Action(depth, Actions.GOTO), new Action(depth, Actions.REPORT));
	}
}
