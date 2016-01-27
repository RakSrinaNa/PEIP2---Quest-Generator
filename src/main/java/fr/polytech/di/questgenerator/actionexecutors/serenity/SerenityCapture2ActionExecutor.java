package fr.polytech.di.questgenerator.actionexecutors.serenity;

import fr.polytech.di.questgenerator.enums.ActionType;
import fr.polytech.di.questgenerator.enums.ObjectiveType;
import fr.polytech.di.questgenerator.interfaces.ActionExecutor;
import fr.polytech.di.questgenerator.objects.Action;
import fr.polytech.di.questgenerator.objects.Quest;
import java.util.HashMap;
import java.util.Optional;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class SerenityCapture2ActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, String>> objectives)
	{
		return new Quest(new Action(depth, ActionType.GET), new Action(depth, ActionType.GOTO), new Action(depth, ActionType.USE, false), new Action(depth, ActionType.CAPTURE, false), new Action(depth, ActionType.GOTO), new Action(depth, ActionType.GIVE));
	}
}
