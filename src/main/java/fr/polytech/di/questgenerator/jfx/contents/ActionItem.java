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
 * Element displaying an action to do with its subquest.
 *
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

	/**
	 * Constructor.
	 *
	 * @param root the root frame.
	 * @param action The action to display.
	 */
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

	/**
	 * Used to generate the ImageView to put next to the text.
	 *
	 * @return The ImageView.
	 */
	private ImageView genImageView()
	{
		this.image = new ImageView(this.subquest == null ? TRANSPARENT_IMAGE : OPENED_IMAGE);
		this.image.setTranslateY(4);
		this.image.setOnMouseReleased(event -> switchSubquestStatus());
		return this.image;
	}

	/**
	 * Used to generate the text to display.
	 *
	 * @param action The Action to display.
	 * @return The Text.
	 */
	private Text genText(Action action)
	{
		Text text = new Text(action.getAsString());
		text.setOnMouseReleased(event -> switchSubquestStatus());
		text.setFont(FONT);
		return text;
	}

	/**
	 * Switch subquest status, visible or not.
	 */
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
