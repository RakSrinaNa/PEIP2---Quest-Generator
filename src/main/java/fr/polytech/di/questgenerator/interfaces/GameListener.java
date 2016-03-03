package fr.polytech.di.questgenerator.interfaces;

import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveElement;

/**
 * Created by MrCraftCod on 03/03/2016.
 */
public interface GameListener
{
	boolean captureEvent(XMLStringObjectiveElement pnj);

	boolean damageEvent(XMLStringObjectiveElement target);

	boolean defendEvent(XMLStringObjectiveElement object);

	boolean escortEvent(XMLStringObjectiveElement pnj);

	boolean exchangeEvent(XMLStringObjectiveElement objectGive, XMLStringObjectiveElement objectGet, XMLStringObjectiveElement to);

	boolean experimentEvent(XMLStringObjectiveElement object);

	boolean exploredEvent(XMLStringObjectiveElement area);

	boolean gatherEvent(XMLStringObjectiveElement object);

	boolean getEvent(XMLStringObjectiveElement object, XMLStringObjectiveElement from);

	boolean giveEvent(XMLStringObjectiveElement object, XMLStringObjectiveElement to);

	boolean gotoEvent(XMLStringObjectiveElement area);

	boolean killEvent(XMLStringObjectiveElement pnj);

	boolean learnEvent(XMLStringObjectiveElement object);

	boolean listenEvent(XMLStringObjectiveElement pnj);

	boolean readEvent(XMLStringObjectiveElement object);

	boolean repairEvent(XMLStringObjectiveElement object);

	boolean reportEvent(XMLStringObjectiveElement to);

	boolean spyEvent(XMLStringObjectiveElement on);

	boolean stealEvent(XMLStringObjectiveElement object, XMLStringObjectiveElement from);

	boolean stealthEvent(XMLStringObjectiveElement object);

	boolean takeEvent(XMLStringObjectiveElement object, XMLStringObjectiveElement from);

	boolean useEvent(XMLStringObjectiveElement used, XMLStringObjectiveElement on);
}
