package fr.polytech.di.questgenerator.interfaces;

import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveElement;

/**
 * Interface used to connect a game to a quest. Allowing it to tell events that happened to update the quest progression.
 * <p>
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public interface GameListener
{
	/**
	 * @param pnj The pnj captured.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean captureEvent(XMLStringObjectiveElement pnj);

	/**
	 * @param target The target damaged.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean damageEvent(XMLStringObjectiveElement target);

	/**
	 * @param object The element defended.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean defendEvent(XMLStringObjectiveElement object);

	/**
	 * @param pnj The pnj escorted.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean escortEvent(XMLStringObjectiveElement pnj);

	/**
	 * @param objectGive The object to given.
	 * @param objectGet The object to received.
	 * @param to The pnj to do the exchange with.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean exchangeEvent(XMLStringObjectiveElement objectGive, XMLStringObjectiveElement objectGet, XMLStringObjectiveElement to);

	/**
	 * @param object The thing experimented.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean experimentEvent(XMLStringObjectiveElement object);

	/**
	 * @param area The area explored.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean exploreEvent(XMLStringObjectiveElement area);

	/**
	 * @param object The object gathered.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean gatherEvent(XMLStringObjectiveElement object);

	/**
	 * @param object The object to got.
	 * @param from The person from whom we get it.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean getEvent(XMLStringObjectiveElement object, XMLStringObjectiveElement from);

	/**
	 * @param object The object to given.
	 * @param to the pnj to give the object.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean giveEvent(XMLStringObjectiveElement object, XMLStringObjectiveElement to);

	/**
	 * @param area The area we went to.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean gotoEvent(XMLStringObjectiveElement area);

	/**
	 * @param pnj The pnj killed.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean killEvent(XMLStringObjectiveElement pnj);

	/**
	 * @param object The thing learnt.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean learnEvent(XMLStringObjectiveElement object);

	/**
	 * @param pnj The pnj listened.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean listenEvent(XMLStringObjectiveElement pnj);

	/**
	 * @param object The thing we read.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean readEvent(XMLStringObjectiveElement object);

	/**
	 * @param object the object repaired.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean repairEvent(XMLStringObjectiveElement object);

	/**
	 * @param to the person we reported to.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean reportEvent(XMLStringObjectiveElement to);

	/**
	 * @param on The person we spied.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean spyEvent(XMLStringObjectiveElement on);

	/**
	 * @param object The object we stole.
	 * @param from The person we stole.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean stealEvent(XMLStringObjectiveElement object, XMLStringObjectiveElement from);

	/**
	 * @param object The thing we stealth.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean stealthEvent(XMLStringObjectiveElement object);

	/**
	 * @param object The object we took.
	 * @param from The area/pnj we took the object from.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean takeEvent(XMLStringObjectiveElement object, XMLStringObjectiveElement from);

	/**
	 * @param used The object used.
	 * @param on The thing we used the object on.
	 * @return True if the event updated the progression, false if not.
	 */
	boolean useEvent(XMLStringObjectiveElement used, XMLStringObjectiveElement on);
}
