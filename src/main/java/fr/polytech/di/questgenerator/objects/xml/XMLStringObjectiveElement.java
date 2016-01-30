package fr.polytech.di.questgenerator.objects.xml;

import fr.polytech.di.questgenerator.Main;

/**
 * Represent an element in the objective xml file.
 *
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class XMLStringObjectiveElement
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
		if(Main.DEBUG)
			return "[(" + this.path + ") " + this.value + "]";
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
}
