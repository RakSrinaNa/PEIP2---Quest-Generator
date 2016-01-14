package fr.polytech.di.questgenerator.objects;

import java.util.Random;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class DataHandler
{
	public static String getRandomObject()
	{
		return "RDM OBJ " + new Random().nextInt(100);
	}

	public static String getrandomLocation()
	{
		return "RDM LOC " + new Random().nextInt(100);
	}

	public static String getRandomPNJ()
	{
		return "RDM PNJ " + new Random().nextInt(100);
	}
}
