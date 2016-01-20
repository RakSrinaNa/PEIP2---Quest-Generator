package fr.polytech.di.questgenerator.objects;

import fr.polytech.di.questgenerator.enums.Resources;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class DataHandler
{
	private static ArrayList<String> AREA;
	private static ArrayList<String> PNJ;
	private static ArrayList<String> OBJECT;

	static
	{
		try
		{
			AREA = Files.readAllLines(Paths.get(Resources.TEXTS.getResource("Area").toURI())).stream().filter(s -> !s.equals("")).collect(Collectors.toCollection(ArrayList::new));
			PNJ = Files.readAllLines(Paths.get(Resources.TEXTS.getResource("PNJ").toURI())).stream().filter(s -> !s.equals("")).collect(Collectors.toCollection(ArrayList::new));
			OBJECT = Files.readAllLines(Paths.get(Resources.TEXTS.getResource("Object").toURI())).stream().filter(s -> !s.equals("")).collect(Collectors.toCollection(ArrayList::new));
		}
		catch(IOException | URISyntaxException e)
		{
			e.printStackTrace();
		}
	}

	public static String getRandomObject()
	{
		if(OBJECT.size() > 0)
			return getRandom(OBJECT);
		return "RDM OBJ " + ThreadLocalRandom.current().nextInt(100);
	}

	public static String getrandomLocation()
	{
		if(AREA.size() > 0)
			return getRandom(AREA);
		return "RDM LOC " + ThreadLocalRandom.current().nextInt(100);
	}

	private static String getRandom(ArrayList<String> list)
	{
		return list.get(ThreadLocalRandom.current().nextInt(list.size()));
	}

	public static String getRandomPNJ()
	{
		if(PNJ.size() > 0)
			return getRandom(PNJ);
		return "RDM PNJ " + ThreadLocalRandom.current().nextInt(100);
	}
}
