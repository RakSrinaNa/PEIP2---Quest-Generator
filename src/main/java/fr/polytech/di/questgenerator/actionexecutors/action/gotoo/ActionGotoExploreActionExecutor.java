package fr.polytech.di.questgenerator.actionexecutors.action.gotoo;

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
public class ActionGotoExploreActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		Quest quest = new Quest(parent);
		ObjectiveHelper objectiveHelper;
		if(objectives.get().get(OBJECTIVE).isInPath("pnj/being/*"))
			objectiveHelper = new ObjectiveHelper(OBJECTIVE, DataHandler.getRandomFromCategories(parent, "area/*"));
		else
			objectiveHelper = new ObjectiveHelper(OBJECTIVE, DataHandler.getRandomFromCategories(parent, "area/wild/*"));
		Action actionExplore = new Action(quest, this.getClass(), depth, ActionType.EXPLORE, buildObjective(objectives, objectiveHelper), false);
		return Quest.initQuest(quest, actionExplore);
	}

	@Override
	public boolean isActionAllowed(Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		return !(!objectives.isPresent() || !objectives.get().containsKey(OBJECTIVE)) && objectives.get().get(OBJECTIVE).isInPath("pnj/being/*", "pnj/beast/*", "area/place/*");
	}
}
