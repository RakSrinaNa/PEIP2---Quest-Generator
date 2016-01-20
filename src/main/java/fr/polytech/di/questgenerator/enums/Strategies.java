package fr.polytech.di.questgenerator.enums;

import fr.polytech.di.questgenerator.actionexecutors.ability.*;
import fr.polytech.di.questgenerator.actionexecutors.comfort.ComfortKillActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.comfort.ComfortObtainActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.conquest.ConquestAttackActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.conquest.ConquestStealActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.equipement.EquipementAssembleActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.equipement.EquipementDeliverActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.equipement.EquipementStealActionExecutor;
import fr.polytech.di.questgenerator.actionexecutors.equipement.EquipementTradeActionExecutor;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public enum Strategies
{
	KNOWLEDGE_DELIVER(Motivations.KNOWLEDGE, KnowledgeDeliverActionExecutor.class),
	KNOWLEDGE_SPY(Motivations.KNOWLEDGE, KnowledgeSpyActionExecutor.class),
	KNOWLEDGE_INTERVIEW(Motivations.KNOWLEDGE, KnowledgeInterviewActionExecutor.class),
	KNOWLEDGE_USE_ITEM(Motivations.KNOWLEDGE, KnowledgeUseItemActionExecutor.class),

	COMFORT_OBTAIN(Motivations.COMFORT, ComfortObtainActionExecutor.class),
	COMFORT_KILL(Motivations.COMFORT, ComfortKillActionExecutor.class),

	REPUTATION_OBTAIN(Motivations.REPUTATION, ReputationObtainActionExecutor.class),
	REPUTATION_KILL(Motivations.REPUTATION, ReputationKillActionExecutor.class),
	REPUTATION_VISIT(Motivations.REPUTATION, ReputationVisitActionExecutor.class),

	SERENITY_REVENGE(Motivations.SERENITY, SerenityRevengeActionExecutor.class),
	SERENITY_CAPTURE_1(Motivations.SERENITY, SerenityCapture1ActionExecutor.class),
	SERENITY_CAPTURE_2(Motivations.SERENITY, SerenityCapture2ActionExecutor.class),
	SERENITY_CHECK_1(Motivations.SERENITY, SerenityCheck1ActionExecutor.class),
	SERENITY_CHECK_2(Motivations.SERENITY, SerenityCheck2ActionExecutor.class),
	SERENITY_RECOVER(Motivations.SERENITY, SerenityRecoverActionExecutor.class),
	SERENITY_RESCUE(Motivations.SERENITY, SerenityRescueActionExecutor.class),

	PROTECTION_ATTACK(Motivations.PROTECTION, ProtectionAttackActionExecutor.class),
	PROTECTION_TREAT_1(Motivations.PROTECTION, ProtectionTreat1ActionExecutor.class),
	PROTECTION_TREAT_2(Motivations.PROTECTION, ProtectionTreat2ActionExecutor.class),
	PROTECTION_CREATE_1(Motivations.PROTECTION, ProtectionCreate1ActionExecutor.class),
	PROTECTION_CREATE_2(Motivations.PROTECTION, ProtectionCreate2ActionExecutor.class),
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

	EQUIPEMENT_ASSEMBLE(Motivations.EQUIPEMENT, EquipementAssembleActionExecutor.class),
	EQUIPEMENT_DELIVER(Motivations.EQUIPEMENT, EquipementDeliverActionExecutor.class),
	EQUIPEMENT_STEAL(Motivations.EQUIPEMENT, EquipementStealActionExecutor.class),
	EQUIPEMENT_TRADE(Motivations.EQUIPEMENT, EquipementTradeActionExecutor.class);

	private final Motivations motivation;
	private final Class<? extends ActionExecutor> actionExecutor;

	Strategies(Motivations motivation, Class<? extends ActionExecutor> actionExecutor)
	{
		this.motivation = motivation;
		this.actionExecutor = actionExecutor;
	}

	public static Strategies getRandom()
	{
		Strategies[] strategies = Strategies.values();
		return strategies[ThreadLocalRandom.current().nextInt(strategies.length)];
	}

	public static Strategies getByMotivation(Motivations motivation)
	{
		ArrayList<Strategies> candidates = new ArrayList<>();
		for(Strategies strategy : Strategies.values())
			if(strategy.getMotivation() == motivation)
				candidates.add(strategy);
		return candidates.get(ThreadLocalRandom.current().nextInt(candidates.size()));
	}

	public Quest createQuest()
	{
		return createQuest(0, Optional.empty());
	}

	public Quest createQuest(int depth, Optional<HashMap<Objectives, String>> objectives)
	{
		try
		{
			return actionExecutor.newInstance().process(depth, objectives);
		}
		catch(InstantiationException | IllegalAccessException ignored)
		{
		}
		return null;
	}

	public Motivations getMotivation()
	{
		return this.motivation;
	}
}
