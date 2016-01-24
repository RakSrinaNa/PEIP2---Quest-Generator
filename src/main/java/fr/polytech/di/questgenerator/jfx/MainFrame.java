package fr.polytech.di.questgenerator.jfx;

import fr.polytech.di.questgenerator.QuestGenerator;
import fr.polytech.di.questgenerator.enums.Resources;
import fr.polytech.di.questgenerator.jfx.contents.QuestItem;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
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
		BorderPane pane = new BorderPane();

		quest = new QuestItem(QuestGenerator.createNewRandomQuest(), 0);
		ScrollPane scroll = new ScrollPane(quest);
		scroll.setPrefSize(400, 600);
		scroll.setStyle("-fx-background: " + QuestItem.getStringColor(0) + ";");

		Button reloadButton = new Button("Reload quest");
		reloadButton.setMaxWidth(Double.MAX_VALUE);
		reloadButton.setOnMouseReleased(event -> quest.modifyQuest(QuestGenerator.createNewRandomQuest()));
		pane.setCenter(scroll);
		pane.setBottom(reloadButton);
		return pane;
	}
}
