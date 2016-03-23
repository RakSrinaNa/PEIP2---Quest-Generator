package fr.polytech.di.questgenerator.enums;

import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;
/**
 * The different motivations for the quests.
 * <p>
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public enum Motivations
{
	KNOWLEDGE(Resources.PROPERTIES.getPropertyString("MotivationsProbabilities", "knowledge")),
	COMFORT(Resources.PROPERTIES.getPropertyString("MotivationsProbabilities", "comfort")),
	REPUTATION(Resources.PROPERTIES.getPropertyString("MotivationsProbabilities", "reputation")),
	SERENITY(Resources.PROPERTIES.getPropertyString("MotivationsProbabilities", "serenity")),
	PROTECTION(Resources.PROPERTIES.getPropertyString("MotivationsProbabilities", "protection")),
	CONQUEST(Resources.PROPERTIES.getPropertyString("MotivationsProbabilities", "conquest")),
	WEALTH(Resources.PROPERTIES.getPropertyString("MotivationsProbabilities", "wealth")),
	ABILITY(Resources.PROPERTIES.getPropertyString("MotivationsProbabilities", "ability")),
	EQUIPMENT(Resources.PROPERTIES.getPropertyString("MotivationsProbabilities", "equipment"));

	private final double probability;

	/**
	 * Constructor.
	 *
	 * @param probability The probability for this motivation to be picked up randomly.
	 */
	Motivations(String probability)
	{
		this(Double.parseDouble(probability));
	}

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
