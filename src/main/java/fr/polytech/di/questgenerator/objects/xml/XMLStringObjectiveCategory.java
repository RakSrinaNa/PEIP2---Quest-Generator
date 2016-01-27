package fr.polytech.di.questgenerator.objects.xml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represent a category in the objective xml file.
 *
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class XMLStringObjectiveCategory
{
	private final String name;
	private final ArrayList<String> values;
	private final ArrayList<XMLStringObjectiveCategory> subcategories;

	/**
	 * Constructor.
	 *
	 * @param name The category name.
	 */
	public XMLStringObjectiveCategory(String name)
	{
		this.name = name;
		this.values = new ArrayList<>();
		this.subcategories = new ArrayList<>();
	}

	/**
	 * Get the category
	 *
	 * @param categories The categories in which we search into.
	 * @param path The path to get (formatted as xxx/yyyy).
	 * @return The category if found.
	 */
	public static Optional<XMLStringObjectiveCategory> getCategoryByName(List<XMLStringObjectiveCategory> categories, String path)
	{
		return getCategoryByName(categories, path.split("/"));
	}

	/**
	 * Get the category
	 *
	 * @param categories The categories in which we search into.
	 * @param path The path to get.
	 * @return The category if found.
	 */
	public static Optional<XMLStringObjectiveCategory> getCategoryByName(List<XMLStringObjectiveCategory> categories, String[] path)
	{
		if(path == null || path.length == 0)
			return Optional.empty();
		for(XMLStringObjectiveCategory category : categories)
			if(category.getName().equals(path[0]))
			{
				if(path.length == 1)
					return Optional.of(category);
				return getCategoryByName(category.getSubCategories(), Arrays.copyOfRange(path, 1, path.length));
			}
		return Optional.empty();
	}

	/**
	 * Add a value to this category.
	 *
	 * @param value The value to add.
	 */
	public void addValue(String value)
	{
		this.values.add(value);
	}

	/**
	 * Add a category to that category.
	 *
	 * @param subcategory The category to add.
	 */
	public void addSubcategory(XMLStringObjectiveCategory subcategory)
	{
		this.subcategories.add(subcategory);
	}

	/**
	 * Get the name of the category.
	 *
	 * @return The name.
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Get a random element among its values.
	 *
	 * @param allowSubcategories Allow to include subcategories' values or not.
	 * @return A random value.
	 */
	public String getRandomElement(boolean allowSubcategories)
	{
		if(allowSubcategories)
		{
			ArrayList<String> candidates = new ArrayList<>();
			candidates.addAll(getAllValues(false));
			for(XMLStringObjectiveCategory category : subcategories)
				candidates.addAll(category.getAllValues(true));
			return candidates.get(ThreadLocalRandom.current().nextInt(candidates.size()));
		}
		return this.values.get(ThreadLocalRandom.current().nextInt(this.values.size()));
	}

	/**
	 * Get all the values of that category.
	 *
	 * @param allowSubcategories Allow to include subcategories' values or not.
	 * @return A list of the values.
	 */
	private ArrayList<String> getAllValues(boolean allowSubcategories)
	{
		if(allowSubcategories)
		{
			ArrayList<String> values = new ArrayList<>();
			values.addAll(getAllValues(false));
			for(XMLStringObjectiveCategory category : subcategories)
				values.addAll(category.getAllValues(true));
			return values;
		}
		return this.values;
	}

	/**
	 * Get the subcategories.
	 *
	 * @return The subcategories.
	 */
	public List<XMLStringObjectiveCategory> getSubCategories()
	{
		return this.subcategories;
	}

	@Override
	public String toString()
	{
		return getName();
	}

	/**
	 * Return the values of this category.
	 *
	 * @return The values.
	 */
	public ArrayList<String> getValues()
	{
		return this.values;
	}
}
