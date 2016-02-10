package fr.polytech.di.questgenerator.jfx;

import fr.polytech.di.questgenerator.QuestGenerator;
import fr.polytech.di.questgenerator.enums.Resources;
import fr.polytech.di.questgenerator.jfx.contents.QuestItem;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Main frame of the application.
 *
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class MainFrame extends Application
{
	public static final boolean DEBUG = false;
	public static final int MAX_DEPTH = 3;
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

		Button exportButton = new Button("Export");
		exportButton.setMaxWidth(Double.MAX_VALUE);
		exportButton.setOnMouseReleased(event -> {
			WritableImage image = quest.snapshot(new SnapshotParameters(), null);
			File file = new File("export" + System.currentTimeMillis() + ".png");
			try
			{
				ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
			}
			catch(IOException ignored)
			{
			}
		});

		VBox buttons = new VBox();
		buttons.getChildren().addAll(reloadButton, exportButton);

		pane.setCenter(scroll);
		pane.setBottom(buttons);

		return pane;
	}
}
