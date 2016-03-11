package fr.polytech.di.questgenerator.objects.xml;

import fr.polytech.di.questgenerator.QuestGenerator;

/**
 * Represent an element in the objective xml file.
 * <p>
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class XMLStringObjectiveElement implements Comparable<XMLStringObjectiveElement>
{
	private final String value;
	private final String path;

	/**
	 * Constructor.
	 *
	 * @param path The path of the element.
	 * @param value The value of the element.
	 */
	public XMLStringObjectiveElement(String path, String value)
	{
		this.path = path;
		this.value = value;
	}

	@Override
	public String toString()
	{
		return this.getValue();
	}

	/**
	 * Used to get the value of the element.
	 *
	 * @return The value.
	 */
	public String getValue()
	{
		if(QuestGenerator.getDebug())
		{
			if(this.path.equals("class"))
				return "{" + this.value + "}";
			return "[(" + this.path + ") " + this.value + "]";
		}
		return this.value;
	}

	/**
	 * Used to know if this element is in the given path.
	 *
	 * @param path The path to test.
	 * @return True if in, false if not.
	 */
	public boolean isInPath(String path)
	{
		return path.endsWith("/*") ? this.path.startsWith(path.substring(0, path.length() - "/*".length())) : this.path.equals(path);
	}

	/**
	 * Used to know if this element is in one of the given paths.
	 *
	 * @param paths The paths to test.
	 * @return True if in, false if not.
	 */
	public boolean isInPath(String... paths)
	{
		for(String path : paths)
			if(isInPath(path))
				return true;
		return false;
	}

	/**
	 * Used to know if two elements are the same.
	 *
	 * @param element The elements to test with.
	 * @return True if equals, false if not.
	 */
	public boolean is(XMLStringObjectiveElement element)
	{
		return element != null && (element == this || (this.getValue().equals(element.getValue()) && this.getPath().equals(element.getPath())));
	}

	/**
	 * used to get the path of this element.
	 *
	 * @return The path.
	 */
	public String getPath()
	{
		return this.path;
	}

	@Override
	public int compareTo(XMLStringObjectiveElement o)
	{
		return this.getValue().compareTo(o.getValue());
	}
}
