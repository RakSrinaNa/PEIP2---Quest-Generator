package fr.polytech.di.questgenerator.enums;

import fr.polytech.di.questgenerator.Main;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Enum of the different resources available.
 *
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public enum Resources
{
	XMLS("xmls"),
	JFX("jfx"),
	JFX_IMAGE("jfx/images");

	private final String rootPath;

	/**
	 * Constructor.
	 *
	 * @param rootPath The root path for this resource.
	 */
	Resources(String rootPath)
	{
		this.rootPath = rootPath;
	}

	/**
	 * Resize (conserving ratio) a Buffered image.
	 *
	 * @param image The image to resize.
	 * @param width The width to set.
	 * @param height The height to set.
	 * @return The resized image.
	 */
	public static BufferedImage resizeBufferedImage(BufferedImage image, float width, float height)
	{
		int baseWidth = image.getWidth(), baseHeight = image.getHeight();
		float ratio = baseWidth > baseHeight ? width / baseWidth : height / baseHeight;
		java.awt.Image tmp = image.getScaledInstance((int) (ratio * baseWidth), (int) (ratio * baseHeight), BufferedImage.SCALE_SMOOTH);
		BufferedImage buffered = new BufferedImage((int) (ratio * baseWidth), (int) (ratio * baseHeight), BufferedImage.TYPE_INT_ARGB);
		buffered.getGraphics().drawImage(tmp, 0, 0, null);
		return buffered;
	}

	/**
	 * Get a file of that resource.
	 *
	 * @param path The file path inside the root node.
	 * @return The resource as URL.
	 */
	public URL getResource(String path)
	{
		return Main.class.getResource("/" + this.rootPath + "/" + path);
	}

	/**
	 * Get a JavaFX Image.
	 *
	 * @param path The path of the file.
	 * @return The Image.
	 */
	public Image getImage(String path)
	{
		return new Image(getResource(path).toString());
	}

	/**
	 * Get a resized (conserving ratio) JavaFX WritableImage.
	 *
	 * @param path The path of the file.
	 * @param width the width to set.
	 * @param height The height to set.
	 * @return The image.
	 */
	public WritableImage getImage(String path, int width, int height)
	{
		try
		{
			return SwingFXUtils.toFXImage(resizeBufferedImage(ImageIO.read(getResource(path)), width, height), null);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
