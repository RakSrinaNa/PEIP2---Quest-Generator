package fr.polytech.di.questgenerator.objects;

import com.intellij.util.containers.SortedList;
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
import java.util.Comparator;
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

	public static SortedList<XMLStringObjectiveElement> getAllSorted()
	{
		SortedList<XMLStringObjectiveElement> list = new SortedList<>(new Comparator<XMLStringObjectiveElement>()
		{
			@Override
			public int compare(XMLStringObjectiveElement o1, XMLStringObjectiveElement o2)
			{
				return o1.compareTo(o2);
			}
		});
		for(XMLStringObjectiveCategory category : strings)
			list.addAll(category.getAllValues(true));
		return list;
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
