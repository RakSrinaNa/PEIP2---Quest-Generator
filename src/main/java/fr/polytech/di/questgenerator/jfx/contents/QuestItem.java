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
	 */
	public QuestItem(MainFrame root, Quest quest)
	{
		super();
		this.root = root;
		this.setSpacing(5);
		loadQuest(quest);
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
