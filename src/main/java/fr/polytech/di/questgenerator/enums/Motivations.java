package fr.polytech.di.questgenerator.enums;

import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;
/**
 * The different motivations for the quests.
 * <p>
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public enum Motivations
{
	KNOWLEDGE(0.183),
	COMFORT(0.016),
	REPUTATION(0.065),
	SERENITY(0.137),
	PROTECTION(0.182),
	CONQUEST(0.202),
	WEALTH(0.02),
	ABILITY(0.011),
	EQUIPMENT(0.185);

	private final double probability;

	/**
	 * Constructor.
	 *
	 * @param probability The probability for this motivation to be picked up randomly.
	 */
	Motivations(double probability)
	{
		this.probability = probability;
	}

	/**
	 * Get a random motivation, taking in account probabilities.
	 *
	 * @return A random motivation.
	 */
	public static Motivations getRandom()
	{
		int[] values = new int[Motivations.values().length];
		double[] probabilities = new double[Motivations.values().length];
		for(int i = 0; i < Motivations.values().length; i++)
		{
			values[i] = i;
			probabilities[i] = Motivations.values()[i].getProbability();
		}
		EnumeratedIntegerDistribution dist = new EnumeratedIntegerDistribution(values, probabilities);
		return Motivations.values()[dist.sample()];
	}

	/**
	 * Get the probability of this motivation.
	 *
	 * @return The probability.
	 */
	public double getProbability()
	{
		return probability;
	}
}
