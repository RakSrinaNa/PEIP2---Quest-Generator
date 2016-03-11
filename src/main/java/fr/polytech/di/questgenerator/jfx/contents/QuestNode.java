package fr.polytech.di.questgenerator.jfx.contents;

import fr.polytech.di.questgenerator.interfaces.MainRefresh;
import fr.polytech.di.questgenerator.objects.Action;
import fr.polytech.di.questgenerator.objects.Quest;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Element displaying a quest.
 *
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class QuestNode extends VBox
{
	private static final Font FONT = Font.font("Verdana", 16);
	private final MainRefresh mainRefresh;
	private VBox actions;
	private Label description;
	private boolean doable;
	private Quest quest;

	/**
	 * Constructor.
	 *
	 * @param mainRefresh The parent container that must be refreshable.
	 * @param doable Tell if the interface should take care if an action is doable or not.
	 * @param quest The quest to display.
	 * @param depth The depth of the Quest.
	 */
	public QuestNode(MainRefresh mainRefresh, boolean doable, Quest quest, int depth)
	{
		super();
		this.mainRefresh = mainRefresh;
		this.doable = doable;
		this.setSpacing(5);
		this.setStyle("-fx-background-color: " + getStringColor(depth) + ";" +
				"-fx-padding: 10;" +
				"-fx-border-style: solid;" +
				"-fx-border-width: 2;" +
				"-fx-border-radius: 5;" +
				"-fx-border-color: black;");

		this.actions = new VBox();
		this.actions.setSpacing(5);

		this.description = new Label();
		this.description.setWrapText(true);
		this.description.setFont(FONT);

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
		mainRefresh.refresh();
	}

	/**
	 * Add ActionItems to this element based on the Actions of the quest.
	 *
	 * @param quest The quest.
	 */
	private void loadQuest(Quest quest)
	{
		this.quest = quest;
		if(quest.hasDescription())
		{
			description.setText(quest.getDescription());
			description.setManaged(true);
		}
		else
			description.setManaged(false);
		for(Action action : quest.getActions())
			this.actions.getChildren().add(new ActionNode(mainRefresh, this.doable, action));
	}

	/**
	 * Used to refresh the Node.
	 */
	public void refresh()
	{
		this.actions.getChildren().stream().filter(node -> node instanceof ActionNode).forEach(node -> ((ActionNode) node).refresh());
	}

	/**
	 * Used to get teh quest.
	 *
	 * @return the quest.
	 */
	public Quest getQuest()
	{
		return this.quest;
	}

	/**
	 * Used to get the doable value.
	 *
	 * @return True if we take care of the progression, false if not.
	 */
	public boolean getDoable()
	{
		return this.doable;
	}

	/**
	 * Used to set the doable parameter. This one define if we should take care of the progression of teh quest for the printing.
	 *
	 * @param doable True if we take care of the progression, false if not.
	 */
	public void setDoable(boolean doable)
	{
		this.doable = doable;
		this.getChildren().stream().filter(child -> child instanceof ActionNode).forEach(child -> ((ActionNode) child).setDoable(doable));
	}

	/**
	 * Used to change the reload the current displayed quest.
	 */
	public void reloadQuest()
	{
		modifyQuest(this.getQuest());
	}
}
