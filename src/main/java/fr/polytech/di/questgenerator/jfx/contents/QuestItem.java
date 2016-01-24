package fr.polytech.di.questgenerator.jfx.contents;

import fr.polytech.di.questgenerator.jfx.MainFrame;
import fr.polytech.di.questgenerator.objects.Action;
import fr.polytech.di.questgenerator.objects.Quest;
import javafx.scene.layout.VBox;

/**
 * Element displaying a quest.
 *
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class QuestItem extends VBox
{
	private final MainFrame root;

	/**
	 * Constructor.
	 *
	 * @param root The root frame.
	 * @param quest The quest to display.
	 * @param depth The depth of the Quest.
	 */
	public QuestItem(MainFrame root, Quest quest, int depth)
	{
		super();
		this.root = root;
		this.setSpacing(5);
		this.setStyle("-fx-background-color: " + getStringColor(depth) + ";");
		loadQuest(quest);
	}

	/**
	 * Get a color string based on the depth.
	 *
	 * @param depth The depth.
	 * @return The color string.
	 */
	private String getStringColor(int depth)
	{
		int red = 128 + (depth * 373) % 128;
		int green = 128 + (depth * 431) % 128;
		int blue = 128 + (depth * 607) % 128;
		return "#" + getPaddedHex(red) + getPaddedHex(green) + getPaddedHex(blue);
	}

	/**
	 * Get a int as an hexadecimal value with leading 0.
	 * @param i The number, must be 0<=i<256.
	 * @return The hexadecimal value.
	 */
	private String getPaddedHex(int i)
	{
		return (i < 10 ? "0" : "") + Integer.toHexString(i);
	}

	/**
	 * Used to modify the displayed quest.
	 *
	 * @param quest The quest to display.
	 */
	public void modifyQuest(Quest quest)
	{
		this.getChildren().clear();
		loadQuest(quest);
	}

	/**
	 * Add ActionItems to this element based on the Actions of the quest.
	 *
	 * @param quest The quest.
	 */
	private void loadQuest(Quest quest)
	{
		for(Action action : quest.getActions())
			this.getChildren().add(new ActionItem(root, action));
		root.sizeToScene();
	}
}
