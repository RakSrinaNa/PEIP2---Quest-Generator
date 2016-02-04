package fr.polytech.di.questgenerator.enums;

import fr.polytech.di.questgenerator.actionexecutors.ability.*;
import fr.polytech.di.questgenerator.actionexecutors.comfort.ComfortKillActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.comfort.ComfortObtainActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.conquest.ConquestAttackActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.conquest.ConquestStealActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.equipment.EquipmentAssembleActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.equipment.EquipmentDeliverActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.equipment.EquipmentStealActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.equipment.EquipmentTradeActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.knowledge.KnowledgeDeliverActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.knowledge.KnowledgeInterviewActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.knowledge.KnowledgeSpyActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.knowledge.KnowledgeUseItemActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.protection.*;
import fr.polytech.di.questgenerator.actionexecutors.reputation.ReputationKillActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.reputation.ReputationObtainActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.reputation.ReputationVisitActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.serenity.*;
import fr.polytech.di.questgenerator.actionexecutors.wealth.WealthGatherActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.wealth.WealthMakeActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.wealth.WealthStealActionExecutor;
import fr.polytech.di.questgenerator.interfaces.ActionExecutor;
import fr.polytech.di.questgenerator.objects.Quest;
import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Strategies used for the beginning of a quest.
 *
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public enum Strategies
{
	KNOWLEDGE_DELIVER(Motivations.KNOWLEDGE, KnowledgeDeliverActionExecutor.class, true),
	KNOWLEDGE_SPY(Motivations.KNOWLEDGE, KnowledgeSpyActionExecutor.class),
	KNOWLEDGE_INTERVIEW(Motivations.KNOWLEDGE, KnowledgeInterviewActionExecutor.class, true),
	KNOWLEDGE_USE_ITEM(Motivations.KNOWLEDGE, KnowledgeUseItemActionExecutor.class),

	COMFORT_OBTAIN(Motivations.COMFORT, ComfortObtainActionExecutor.class, true),
	COMFORT_KILL(Motivations.COMFORT, ComfortKillActionExecutor.class, true),

	REPUTATION_OBTAIN(Motivations.REPUTATION, ReputationObtainActionExecutor.class, true),
	REPUTATION_KILL(Motivations.REPUTATION, ReputationKillActionExecutor.class, true),
	REPUTATION_VISIT(Motivations.REPUTATION, ReputationVisitActionExecutor.class, true),

	SERENITY_REVENGE(Motivations.SERENITY, SerenityRevengeActionExecutor.class),
	SERENITY_CAPTURE_1(Motivations.SERENITY, SerenityCapture1ActionExecutor.class),
	SERENITY_CAPTURE_2(Motivations.SERENITY, SerenityCapture2ActionExecutor.class),
	SERENITY_CHECK_1(Motivations.SERENITY, SerenityCheck1ActionExecutor.class, true),
	SERENITY_CHECK_2(Motivations.SERENITY, SerenityCheck2ActionExecutor.class, true),
	SERENITY_RECOVER(Motivations.SERENITY, SerenityRecoverActionExecutor.class, true),
	SERENITY_RESCUE(Motivations.SERENITY, SerenityRescueActionExecutor.class, true),

	PROTECTION_ATTACK(Motivations.PROTECTION, ProtectionAttackActionExecutor.class, true),
	PROTECTION_TREAT_1(Motivations.PROTECTION, ProtectionTreat1ActionExecutor.class),
	PROTECTION_TREAT_2(Motivations.PROTECTION, ProtectionTreat2ActionExecutor.class),
	PROTECTION_DIVERSION_1(Motivations.PROTECTION, ProtectionDiversion1ActionExecutor.class),
	PROTECTION_DIVERSION_2(Motivations.PROTECTION, ProtectionDiversion2ActionExecutor.class),
	PROTECTION_ASSEMBLE(Motivations.PROTECTION, ProtectionAssembleActionExecutor.class),
	PROTECTION_GUARD(Motivations.PROTECTION, ProtectionGuardActionExecutor.class),

	CONQUEST_ATTACK(Motivations.CONQUEST, ConquestAttackActionExecutor.class),
	CONQUEST_STEAL(Motivations.CONQUEST, ConquestStealActionExecutor.class),

	WEALTH_GATHER(Motivations.WEALTH, WealthGatherActionExecutor.class),
	WEALTH_STEAL(Motivations.WEALTH, WealthStealActionExecutor.class),
	WEALTH_MAKE(Motivations.WEALTH, WealthMakeActionExecutor.class),

	ABILITY_ASSEMBLE(Motivations.ABILITY, AbilityAssembleActionExecutor.class),
	ABILITY_OBTAIN(Motivations.ABILITY, AbilityObtainActionExecutor.class),
	ABILITY_USE(Motivations.ABILITY, AbilityUseActionExecutor.class),
	ABILITY_PRACTICE_COMBAT(Motivations.ABILITY, AbilityPracticeCombatActionExecutor.class),
	ABILITY_PRACTICE_SKILL(Motivations.ABILITY, AbilityPracticeSkillActionExecutor.class),
	ABILITY_RESEARCH_1(Motivations.ABILITY, AbilityResearch1ActionExecutor.class),
	ABILITY_RESEARCH_2(Motivations.ABILITY, AbilityResearch2ActionExecutor.class),

	EQUIPMENT_ASSEMBLE(Motivations.EQUIPMENT, EquipmentAssembleActionExecutor.class),
	EQUIPMENT_DELIVER(Motivations.EQUIPMENT, EquipmentDeliverActionExecutor.class),
	EQUIPMENT_STEAL(Motivations.EQUIPMENT, EquipmentStealActionExecutor.class),
	EQUIPMENT_TRADE(Motivations.EQUIPMENT, EquipmentTradeActionExecutor.class);

	private final static ArrayList<Strategies> subquestAllowedStrategies = new ArrayList<>();
	private final Motivations motivation;
	private final Class<? extends ActionExecutor> actionExecutor;
	private final boolean subquestAllowed;

	/**
	 * Constructor.
	 *
	 * @param motivation The motivation in which the Strategy is present.
	 * @param actionExecutor The ActionExecutor defining how the quest will start.
	 */
	Strategies(Motivations motivation, Class<? extends ActionExecutor> actionExecutor)
	{
		this(motivation, actionExecutor, false);
	}

	/**
	 * Constructor.
	 *
	 * @param motivation The motivation in which the Strategy is present.
	 * @param actionExecutor The ActionExecutor defining how the quest will start.
	 * @param subquestAllowed Determines if the strategy can be used to generate a subquest.
	 */
	Strategies(Motivations motivation, Class<? extends ActionExecutor> actionExecutor, boolean subquestAllowed)
	{
		this.motivation = motivation;
		this.actionExecutor = actionExecutor;
		this.subquestAllowed = subquestAllowed;
	}

	/**
	 * Pick a random Strategy.
	 *
	 * @return A random Strategy.
	 */
	public static Strategies getRandom()
	{
		Strategies[] strategies = Strategies.values();
		return strategies[ThreadLocalRandom.current().nextInt(strategies.length)];
	}

	/**
	 * Pick a random Strategy among the ones allowed for subquests.
	 *
	 * @return A random Strategy.
	 */
	public static Strategies getRandomSubquest()
	{
		return subquestAllowedStrategies.get(ThreadLocalRandom.current().nextInt(subquestAllowedStrategies.size()));
	}

	/**
	 * Pick a random Strategy for a given Motivation.
	 *
	 * @param motivation The Motivation the Strategy picked needs to be.
	 * @return A rantom Strategy with the wanted Motivation.
	 */
	public static Strategies getByMotivation(Motivations motivation)
	{
		ArrayList<Strategies> candidates = new ArrayList<>();
		for(Strategies strategy : Strategies.values())
			if(strategy.getMotivation() == motivation)
				candidates.add(strategy);
		return candidates.get(ThreadLocalRandom.current().nextInt(candidates.size()));
	}

	/**
	 * Create a quest.
	 *
	 * @return A Quest.
	 */
	public Quest createQuest()
	{
		return createQuest(0, Optional.empty());
	}

	/**
	 * Create a quest.
	 *
	 * @param depth The depth of the quest.
	 * @param objectives The objectives for the quest.
	 * @return A quest.
	 */
	public Quest createQuest(int depth, Optional<HashMap<ObjectiveType, XMLStringObjectiveElement>> objectives)
	{
		try
		{
			return actionExecutor.newInstance().generateQuest(depth, objectives);
		}
		catch(InstantiationException | IllegalAccessException ignored)
		{
		}
		return null;
	}

	/**
	 * Get the motivation of the Strategy.
	 *
	 * @return The Motivation.
	 */
	public Motivations getMotivation()
	{
		return this.motivation;
	}

	/**
	 * Used to know if that strategy is allowed to be picked to generate subquests.
	 *
	 * @return True if allowed, false if not.
	 */
	public boolean isSubquestAllowed()
	{
		return this.subquestAllowed;
	}

	static
	{
		for(Strategies strategy : Strategies.values())
			if(strategy.isSubquestAllowed())
				subquestAllowedStrategies.add(strategy);
	}
}
