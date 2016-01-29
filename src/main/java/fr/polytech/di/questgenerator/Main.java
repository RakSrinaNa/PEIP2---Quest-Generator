package fr.polytech.di.questgenerator;

import fr.polytech.di.questgenerator.objects.Quest;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * Main class.
 *
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class Main
{
	public static final int MAX_DEPTH = 3;

	/**
	 * Startup function.
	 *
	 * @param args Arguments.
	 */
	public static void main(String[] args)
	{
		Quest q = QuestGenerator.createNewRandomQuest();
		Stream.of(q.getAsString()).forEach(System.out::println);
		try(FileWriter fw = new FileWriter("qout\\v3\\quest" + System.currentTimeMillis() + ".txt"))
		{
			for(String line : q.getAsString())
				fw.write(line + "\n");
		}
		catch(IOException ignored)
		{
			ignored.printStackTrace();
		}
	}
}
