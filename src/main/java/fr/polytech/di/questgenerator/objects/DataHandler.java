package fr.polytech.di.questgenerator.objects;

import fr.polytech.di.questgenerator.enums.Resources;
import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveCategory;
import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveElement;
import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveHandler;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
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
	public static XMLStringObjectiveElement getRandomObject()
	{
		return getRandomObject("*");
	}

	/**
	 * Returns a random location.
	 *
	 * @return A random location.
	 */
	public static XMLStringObjectiveElement getRandomArea()
	{
		return getRandomArea("*");
	}

	/**
	 * Return a random PNJ.
	 *
	 * @return Return a random PNJ.
	 */
	public static XMLStringObjectiveElement getRandomPNJ()
	{
		return getRandomPNJ("*");
	}

	/**
	 * Return a random location of the given category.
	 *
	 * @param path The category the element picked should be.
	 * @return Return a random location.
	 */
	public static XMLStringObjectiveElement getRandomArea(String path)
	{
		return getRandomFromCategories("area/" + path);
	}

	/**
	 * Return a random object of the given category.
	 *
	 * @param path The category the element picked should be.
	 * @return Return a random object.
	 */
	public static XMLStringObjectiveElement getRandomObject(String path)
	{
		return getRandomFromCategories("object/" + path);
	}

	/**
	 * Return a random PNJ of the given category.
	 *
	 * @param path The category the element picked should be.
	 * @return Return a random PNJ.
	 */
	public static XMLStringObjectiveElement getRandomPNJ(String path)
	{
		return getRandomFromCategories("pnj/" + path);
	}

	public static XMLStringObjectiveElement getRandomFromCategories(String... categories)
	{

		ArrayList<XMLStringObjectiveElement> candidates = new ArrayList<>();
		for(String category : categories)
		{
			boolean subcategories = false;
			if(category.endsWith("/*"))
			{
				subcategories = true;
				category = category.substring(0, category.length() - "/*".length());
			}
			Optional<XMLStringObjectiveCategory> categoryObj = XMLStringObjectiveCategory.getCategoryByName(strings, category);
			if(categoryObj.isPresent())
				candidates.addAll(categoryObj.get().getAllValues(subcategories));
		}
		if(candidates.isEmpty())
			return new XMLStringObjectiveElement("", Arrays.toString(categories) + " - " + ThreadLocalRandom.current().nextInt(1000));
		return candidates.get(ThreadLocalRandom.current().nextInt(candidates.size()));
	}

	/**
	 * Return a random skill.
	 *
	 * @return Return a random skill.
	 */
	public static XMLStringObjectiveElement getRandomSkill()
	{
		return getRandomSkill("*");
	}

	/**
	 * Return a random skill of the given category.
	 *
	 * @param path The category the element picked should be.
	 * @return Return a random skill.
	 */
	public static XMLStringObjectiveElement getRandomSkill(String path)
	{
		return getRandomFromCategories("skill/" + path);
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
