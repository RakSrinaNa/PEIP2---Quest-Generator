package fr.polytech.di.questgenerator.objects;

import fr.polytech.di.questgenerator.enums.Resources;
import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveCategory;
import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveHandler;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Contains all the different objective values.
 *
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class DataHandler
{
	private final static ArrayList<XMLStringObjectiveCategory> strings;

	/**
	 * Returns a random object.
	 *
	 * @return A random object.
	 */
	public static String getRandomObject()
	{
		Optional<XMLStringObjectiveCategory> category = XMLStringObjectiveCategory.getCategoryByName(strings, "object");
		if(category.isPresent())
			return category.get().getRandomElement(true);
		return "RDM OBJ " + ThreadLocalRandom.current().nextInt(100);
	}

	/**
	 * Returns a random location.
	 *
	 * @return A random location.
	 */
	public static String getRandomLocation()
	{
		Optional<XMLStringObjectiveCategory> category = XMLStringObjectiveCategory.getCategoryByName(strings, "area");
		if(category.isPresent())
			return category.get().getRandomElement(true);
		return "RDM LOC " + ThreadLocalRandom.current().nextInt(100);
	}

	/**
	 * Return a random PNJ.
	 *
	 * @return Return a random PNJ.
	 */
	public static String getRandomPNJ()
	{
		Optional<XMLStringObjectiveCategory> category = XMLStringObjectiveCategory.getCategoryByName(strings, "pnj");
		if(category.isPresent())
			return category.get().getRandomElement(true);
		return "RDM PNJ " + ThreadLocalRandom.current().nextInt(100);
	}

	/**
	 * Return a random location of the given category.
	 *
	 * @param path The category the element picked should be.
	 * @return Return a random location.
	 */
	public static String getRandomLocation(String path)
	{
		Optional<XMLStringObjectiveCategory> category = XMLStringObjectiveCategory.getCategoryByName(strings, "area/" + path);
		if(category.isPresent())
			return category.get().getRandomElement(true);
		return getRandomLocation();
	}

	/**
	 * Return a random object of the given category.
	 *
	 * @param path The category the element picked should be.
	 * @return Return a random object.
	 */
	public static String getRandomObject(String path)
	{
		Optional<XMLStringObjectiveCategory> category = XMLStringObjectiveCategory.getCategoryByName(strings, "object/" + path);
		if(category.isPresent())
			return category.get().getRandomElement(true);
		return getRandomObject();
	}

	/**
	 * Return a random PNJ of the given category.
	 *
	 * @param path The category the element picked should be.
	 * @return Return a random PNJ.
	 */
	public static String getRandomPNJ(String path)
	{
		Optional<XMLStringObjectiveCategory> category = XMLStringObjectiveCategory.getCategoryByName(strings, "pnj/" + path);
		if(category.isPresent())
			return category.get().getRandomElement(true);
		return getRandomPNJ();
	}

	public static String getRandomFromCategories(String... categories)
	{
		ArrayList<String> candidates = new ArrayList<>();
		for(String category : categories)
		{
			Optional<XMLStringObjectiveCategory> categoryObj = XMLStringObjectiveCategory.getCategoryByName(strings, category);
			if(categoryObj.isPresent())
				candidates.addAll(categoryObj.get().getValues());
		}
		return candidates.get(ThreadLocalRandom.current().nextInt(candidates.size()));
	}

	/**
	 * Initialize the strings.
	 */
	static
	{
		ArrayList<XMLStringObjectiveCategory> stringsTemp = new ArrayList<>();
		try
		{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			XMLStringObjectiveHandler handler = new XMLStringObjectiveHandler();
			parser.parse(Resources.XMLS.getResource("strings.xml").toURI().toString(), handler);
			stringsTemp = handler.getCategories();
		}
		catch(IOException | URISyntaxException | ParserConfigurationException | SAXException e)
		{
			e.printStackTrace();
		}
		strings = stringsTemp;
	}
}
