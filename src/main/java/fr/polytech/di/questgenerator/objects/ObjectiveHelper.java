package fr.polytech.di.questgenerator.objects;

import fr.polytech.di.questgenerator.enums.ObjectiveType;
import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveElement;
import java.util.HashMap;
import java.util.Optional;

/**
 * Used to help the transfer of objectives.
 * <p>
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class ObjectiveHelper
{
	private final ObjectiveType destination;
	private final ObjectiveType source;
	private final XMLStringObjectiveElement defaultValue;

	/**
	 * Constructor.
	 *
	 * @param destination The ObjectiveType that will be our new Objective.
	 * @param defaultValue The default value if the source is not defined.
	 */
	public ObjectiveHelper(ObjectiveType destination, XMLStringObjectiveElement defaultValue)
	{
		this(destination, ObjectiveType.NONE, defaultValue);
	}

	/**
	 * Constructor.
	 *
	 * @param destination The ObjectiveType that will be our new Objective.
	 * @param source The Objective source (where to get the value if already defined).
	 */
	public ObjectiveHelper(ObjectiveType destination, ObjectiveType source)
	{
		this(destination, source, new XMLStringObjectiveElement("ERROR", "ERROR: DEFAULT VALUE NOT SET"));
	}

	/**
	 * Constructor.
	 *
	 * @param destination The ObjectiveType that will be our new Objective.
	 * @param source The Objective source (where to get the value if already defined).
	 * @param defaultValue The default value if the source is not defined.
	 */
	public ObjectiveHelper(ObjectiveType destination, ObjectiveType source, XMLStringObjectiveElement defaultValue)
	{
		this.destination = destination;
		this.source = source;
		this.defaultValue = defaultValue;
	}

	/**
	 * Get an Objective value from an HashMap, and if this value is not defined, return the default value.
	 *
	 * @param objectives The objectives.
	 * @return The objective value.
	 */
	public XMLStringObjectiveElement getValue(Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		if(!objectives.isPresent() || !objectives.get().containsKey(this.source) || objectives.get().get(this.source) == null)
			return defaultValue;
		return objectives.get().get(this.source);
	}

	/**
	 * Return the destination ObjectiveType.
	 *
	 * @return The ObjectiveType of the destination.
	 */
	public ObjectiveType getObjective()
	{
		return this.destination;
	}
}
