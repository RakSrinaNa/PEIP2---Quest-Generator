package fr.polytech.di.questgenerator.actionexecutors.conquest;

import fr.polytech.di.questgenerator.enums.ActionType;
import fr.polytech.di.questgenerator.enums.ObjectiveType;
import fr.polytech.di.questgenerator.interfaces.ActionExecutor;
import fr.polytech.di.questgenerator.objects.Action;
import fr.polytech.di.questgenerator.objects.Quest;
import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveElement;
import java.util.HashMap;
import java.util.Optional;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class ConquestStealActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		return new Quest(new Action(this.getClass(), depth, ActionType.GOTO), new Action(this.getClass(), depth, ActionType.STEAL), new Action(this.getClass(), depth, ActionType.GOTO), new Action(this.getClass(), depth, ActionType.GIVE, false));
	}
}
