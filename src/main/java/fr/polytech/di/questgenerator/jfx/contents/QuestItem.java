package fr.polytech.di.questgenerator.jfx.contents;

import fr.polytech.di.questgenerator.objects.Action;
import fr.polytech.di.questgenerator.objects.Quest;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Element displaying a quest.
 *
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class QuestItem extends VBox
{
	private VBox actions;
	private Text description;

	/**
	 * Constructor.
	 *
	 * @param quest The quest to display.
	 * @param depth The depth of the Quest.
	 */
	public QuestItem(Quest quest, int depth)
	{
		super();
		this.setSpacing(5);
		this.setStyle("-fx-background-color: " + getStringColor(depth) + ";" +
				"-fx-padding: 10;" +
				"-fx-border-style: solid;" +
				"-fx-border-width: 2;" +
				"-fx-border-radius: 5;" +
				"-fx-border-color: black;");

		this.actions = new VBox();
		this.actions.setSpacing(5);

		this.description = new Text();

		this.getChildren().add(description);
		this.getChildren().add(this.actions);

		loadQuest(quest);
	}

	/**
	 * Get a color string based on the depth.
	 *
	 * @param depth The depth.
	 * @return The color string.
	 */
	public static String getStringColor(int depth)
	{
		int red = 128 + (depth * 373) % 128;
		int green = 128 + (depth * 431) % 128;
		int blue = 128 + (depth * 607) % 128;
		return "rgb(" + red + "," + green + "," + blue + ")";
	}

	/**
	 * Used to modify the displayed quest.
	 *
	 * @param quest The quest to display.
	 */
	public void modifyQuest(Quest quest)
	{
		this.description.setText("");
		this.actions.getChildren().clear();
		loadQuest(quest);
	}

	/**
	 * Add ActionItems to this element based on the Actions of the quest.
	 *
	 * @param quest The quest.
	 */
	private void loadQuest(Quest quest)
	{
		if(quest.hasDescription())
			description.setText(quest.getDescription());
		for(Action action : quest.getActions())
			this.actions.getChildren().add(new ActionItem(action));
	}
}
