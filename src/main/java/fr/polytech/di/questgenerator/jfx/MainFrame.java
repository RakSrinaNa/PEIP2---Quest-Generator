package fr.polytech.di.questgenerator.jfx;

import fr.polytech.di.questgenerator.QuestGenerator;
import fr.polytech.di.questgenerator.enums.Resources;
import fr.polytech.di.questgenerator.jfx.contents.QuestItem;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class MainFrame extends Application
{
	private QuestItem quest;
	private Stage stage;

	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		this.stage = primaryStage;
		Scene scene = new Scene(createContent());
		primaryStage.setTitle("Quest generator");
		primaryStage.getIcons().add(new Image(Resources.JFX.getResource("icon64.png").toString()));
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
	}

	private Parent createContent()
	{
		VBox vBox = new VBox();
		vBox.setSpacing(20);

		quest = new QuestItem(this, QuestGenerator.createNewRandomQuest());

		vBox.getChildren().addAll(quest, genReloadButton());
		return vBox;
	}

	private Button genReloadButton()
	{
		Button button = new Button("Reload quest");
		button.setMaxWidth(Double.MAX_VALUE);
		button.setOnMouseReleased(event -> quest.modifyQuest(QuestGenerator.createNewRandomQuest()));
		return button;
	}

	public void sizeToScene()
	{
		this.stage.sizeToScene();
	}
}
