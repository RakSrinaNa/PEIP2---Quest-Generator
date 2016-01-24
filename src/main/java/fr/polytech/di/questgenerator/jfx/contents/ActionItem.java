package fr.polytech.di.questgenerator.jfx.contents;

import fr.polytech.di.questgenerator.enums.Resources;
import fr.polytech.di.questgenerator.jfx.MainFrame;
import fr.polytech.di.questgenerator.objects.Action;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class ActionItem extends HBox
{
	private static final int IMG_SIZE = 12;
	private static final Font FONT = Font.font("Verdana", 16);
	private static final Image TRANSPARENT_IMAGE = Resources.JFX_IMAGE.getImage("transparent.png", IMG_SIZE, IMG_SIZE);
	private static final Image OPENED_IMAGE = Resources.JFX_IMAGE.getImage("opened.png", IMG_SIZE, IMG_SIZE);
	private static final Image CLOSED_IMAGE = Resources.JFX_IMAGE.getImage("closed.png", IMG_SIZE, IMG_SIZE);
	private QuestItem subquest;
	private ImageView image;

	public ActionItem(MainFrame root, Action action)
	{
		super();

		VBox vBox = new VBox();
		vBox.getChildren().add(genText(action));
		if(action.getSubquest().isPresent())
		{
			subquest = new QuestItem(root, action.getSubquest().get());
			vBox.getChildren().add(subquest);
			this.getChildren().addAll(genImageView(), vBox);
		}
		else
			this.getChildren().addAll(genImageView(), vBox);

		this.setSpacing(5);
	}

	private ImageView genImageView()
	{
		this.image = new ImageView(this.subquest == null ? TRANSPARENT_IMAGE : OPENED_IMAGE);
		this.image.setTranslateY(4);
		this.image.setOnMouseReleased(event -> switchSubquestStatus());
		return this.image;
	}

	private Text genText(Action action)
	{
		Text text = new Text(action.getAsString());
		text.setOnMouseReleased(event -> switchSubquestStatus());
		text.setFont(FONT);
		return text;
	}

	private void switchSubquestStatus()
	{
		if(subquest != null)
		{
			boolean newState = !subquest.isVisible();
			subquest.setVisible(newState);
			subquest.setManaged(newState);
			image.setImage(newState ? OPENED_IMAGE : CLOSED_IMAGE);
		}
	}
}
