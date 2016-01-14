package fr.polytech.di.questgenerator.interfaces;

import fr.polytech.di.questgenerator.enums.Objectives;
import fr.polytech.di.questgenerator.objects.Quest;
import java.util.HashMap;
import java.util.Optional;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public interface ActionExecutor
{
	Quest process(int depth, Optional<HashMap<Objectives, String>> objectives);

	default String getArg(Optional<HashMap<Objectives, String>> args, Objectives objective, String def)
	{
		if(!args.isPresent() || !args.get().containsKey(objective) || args.get().get(objective).equals(""))
			return def;
		return args.get().get(objective);
	}
}
