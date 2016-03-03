package fr.polytech.di.questgenerator.interfaces;

import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveElement;

/**
 * Created by MrCraftCod on 03/03/2016.
 */
public interface GameListener
{
	boolean areaExploredEvent(XMLStringObjectiveElement area);

	boolean objectGotEvent(XMLStringObjectiveElement object, XMLStringObjectiveElement from);

	boolean listenedEvent(XMLStringObjectiveElement pnj);

	boolean usedEvent(XMLStringObjectiveElement used, XMLStringObjectiveElement on);
}
