package fr.polytech.di.questgenerator.actionexecutors.action.learn;

import fr.polytech.di.questgenerator.enums.Objectives;
import fr.polytech.di.questgenerator.interfaces.ActionExecutor;
import fr.polytech.di.questgenerator.objects.Quest;
import java.util.HashMap;
import java.util.Optional;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class ActionLeanEpsillonActionExecutor implements ActionExecutor
{
	@Override
	public Quest process(int depth, Optional<HashMap<Objectives, String>> objectives)
	{
		return Quest.getEpsillon(depth);
	}
}
