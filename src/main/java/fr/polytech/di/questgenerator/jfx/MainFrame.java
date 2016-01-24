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
 * Main frame of the application.
 *
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class MainFrame extends Application
{
	private QuestItem quest;
	private Stage stage;

	/**
	 * Startup function.
	 *
	 * @param args Arguments.
	 */
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

	/**
	 * Create the content of the scene.
	 *
	 * @return The scene content.
	 */
	private Parent createContent()
	{
		VBox vBox = new VBox();
		vBox.setSpacing(20);
		quest = new QuestItem(this, QuestGenerator.createNewRandomQuest(), 0);
		Button reloadButton = new Button("Reload quest");
		reloadButton.setMaxWidth(Double.MAX_VALUE);
		reloadButton.setOnMouseReleased(event -> quest.modifyQuest(QuestGenerator.createNewRandomQuest()));
		vBox.getChildren().addAll(quest, reloadButton);
		return vBox;
	}

	/**
	 * Resize the Stage to the scene.
	 */
	public void sizeToScene()
	{
		this.stage.sizeToScene();
	}
}
