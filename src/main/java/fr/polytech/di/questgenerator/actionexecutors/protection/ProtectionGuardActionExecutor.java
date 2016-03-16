package fr.polytech.di.questgenerator.actionexecutors.protection;

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
public class ProtectionGuardActionExecutor implements ActionExecutor
{
	@Override
	public Quest generateQuest(Action parent, int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		XMLStringObjectiveElement objectiveArea = DataHandler.getRandomFromCategories(parent, "area/place/*");
		Action actionGoto = new Action(this.getClass(), depth, ActionType.GOTO, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, objectiveArea)));
		Action actionDefend = new Action(this.getClass(), depth, ActionType.DEFEND, buildObjective(objectives, new ObjectiveHelper(OBJECTIVE, pickRandom(objectiveArea, DataHandler.getRandomFromCategories(parent, "area/fortification/*")))), false);
		return new Quest(parent, getSentence(actionDefend.getObjective(OBJECTIVE).isInPath("area/place/*") ? "Protection_Guard_1" : "Protection_Guard_2", objectiveArea, actionDefend.getObjective(OBJECTIVE)), actionGoto, actionDefend);
	}
}