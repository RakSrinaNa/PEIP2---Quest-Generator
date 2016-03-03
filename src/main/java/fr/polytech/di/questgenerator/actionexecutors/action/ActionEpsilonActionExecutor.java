package fr.polytech.di.questgenerator.actionexecutors.action;

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
public class ActionEpsilonActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		return Quest.getEpsilon(parent);
	}
}
