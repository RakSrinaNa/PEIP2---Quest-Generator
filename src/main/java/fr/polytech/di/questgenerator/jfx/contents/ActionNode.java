package fr.polytech.di.questgenerator.jfx.contents;

import fr.polytech.di.questgenerator.enums.Resources;
import fr.polytech.di.questgenerator.interfaces.MainRefresh;
import fr.polytech.di.questgenerator.objects.Action;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Element displaying an action to do with its subquest.
 * <p>
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public class ActionNode extends HBox
{
	private static final int IMG_ARROW_SIZE = 10;
	private static final int IMG_DONE_SIZE = 16;
	private static final Font FONT = Font.font("Verdana", 16);
	private static final Image TRANSPARENT_IMAGE = Resources.JFX_IMAGE.getImage("transparent.png", IMG_ARROW_SIZE, IMG_ARROW_SIZE);
	private static final Image OPENED_IMAGE = Resources.JFX_IMAGE.getImage("opened.png", IMG_ARROW_SIZE, IMG_ARROW_SIZE);
	private static final Image CLOSED_IMAGE = Resources.JFX_IMAGE.getImage("closed.png", IMG_ARROW_SIZE, IMG_ARROW_SIZE);
	private static final Image DONE_IMAGE = Resources.JFX_IMAGE.getImage("done.png", IMG_DONE_SIZE, IMG_DONE_SIZE);
	private static final Image NOT_DONE_IMAGE = Resources.JFX_IMAGE.getImage("not_done.png", IMG_DONE_SIZE, IMG_DONE_SIZE);
	private final Action action;
	private final MainRefresh mainRefresh;
	private QuestNode subquest;
	private ImageView imageArrow;
	private ImageView imageDone;
	private boolean doable;

	/**
	 * Constructor.
	 *
	 * @param mainRefresh The parent container that must be refreshable.
	 * @param doable Tell if the interface should take care if an action is doable or not.
	 * @param action The action to display.
	 */
	public ActionNode(MainRefresh mainRefresh, boolean doable, Action action)
	{
		super();
		this.mainRefresh = mainRefresh;
		this.doable = doable;
		this.action = action;
		VBox vBox = new VBox();
		vBox.getChildren().add(genText(action));
		if(action.getSubquest().isPresent())
		{
			subquest = new QuestNode(mainRefresh, doable, action.getSubquest().get(), action.getDepth() + 1);
			vBox.getChildren().add(subquest);
			this.getChildren().addAll(genImageDone(), genImageArrow(), vBox);
		}
		else
			this.getChildren().addAll(genImageDone(), vBox);
		this.setSpacing(5);
	}

	/**
	 * Used to generate the ImageView to put next to the text.
	 *
	 * @return The ImageView.
	 */
	private ImageView genImageArrow()
	{
		this.imageArrow = new ImageView(this.subquest == null ? TRANSPARENT_IMAGE : OPENED_IMAGE);
		this.imageArrow.setTranslateY(5);
		this.imageArrow.setOnMouseReleased(event -> switchSubquestStatus());
		return this.imageArrow;
	}

	/**
	 * Used to generate the ImageView to put next to the text.
	 *
	 * @return The ImageView.
	 */
	private ImageView genImageDone()
	{
		this.imageDone = new ImageView(this.action.isDone() ? DONE_IMAGE : NOT_DONE_IMAGE);
		this.imageDone.setTranslateY(3);
		this.imageDone.setPickOnBounds(true);
		if(!this.action.getSubquest().isPresent())
			this.imageDone.setOnMouseReleased(event -> switchDoneStatus());
		return this.imageDone;
	}

	/**
	 * Used to generate the text to display.
	 *
	 * @param action The Action to display.
	 * @return The Text.
	 */
	private Label genText(Action action)
	{
		Label text = new Label(action.getAsString());
		text.setWrapText(true);
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
			imageArrow.setImage(newState ? OPENED_IMAGE : CLOSED_IMAGE);
		}
	}

	/**
	 * used to switch the done status.
	 */
	private void switchDoneStatus()
	{
		if(action != null)
			action.setDone(!action.isDone());
		this.mainRefresh.refresh();
	}

	/**
	 * Used to refresh the Node.
	 */
	public void refresh()
	{
		if(subquest != null)
			subquest.refresh();
		if(doable && !this.action.isDoable() && !this.action.isDone())
		{
			this.setVisible(false);
			this.setManaged(false);
		}
		else
		{
			this.imageDone.setImage(this.action.isDone() ? DONE_IMAGE : NOT_DONE_IMAGE);
			this.setManaged(true);
			this.setVisible(true);
		}
	}

	/**
	 * Used to set the doable parameter. This one define if we should take care of the progression of teh quest for the printing.
	 *
	 * @param doable True if we take care of the progression, false if not.
	 */
	public void setDoable(boolean doable)
	{
		this.doable = doable;
		if(this.subquest != null)
			this.subquest.setDoable(doable);
	}
}
