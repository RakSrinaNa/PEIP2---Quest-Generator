package fr.polytech.di.questgenerator.jfx.contents;

import fr.polytech.di.questgenerator.jfx.MainFrame;
import fr.polytech.di.questgenerator.objects.Action;
import fr.polytech.di.questgenerator.objects.Quest;
import javafx.scene.layout.VBox;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class QuestItem extends VBox
{
	private final MainFrame root;

	public QuestItem(MainFrame root, Quest quest)
	{
		super();
		this.root = root;
		this.setSpacing(5);
		loadQuest(quest);
	}

	public void modifyQuest(Quest quest)
	{
		this.getChildren().clear();
		loadQuest(quest);
	}

	private void loadQuest(Quest quest)
	{
		for(Action action : quest.getActions())
			this.getChildren().add(new ActionItem(root, action));
		root.sizeToScene();
	}
}
