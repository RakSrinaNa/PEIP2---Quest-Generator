package fr.polytech.di.questgenerator.objects.xml;

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
		return "[(" + this.path + ") " + this.value + "]";
	}
}
