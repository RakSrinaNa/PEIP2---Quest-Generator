package fr.polytech.di.questgenerator.enums;

import fr.polytech.di.questgenerator.Main;
import java.net.URL;

/**
 * Enum of the different resources available.
 *
 * Created by COUCHOUD Thomas & COLEAU Victor.
 */
public enum Resources
{
	TEXTS("texts");

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
	 * Get a file of that resource.
	 *
	 * @param path The file path inside the root node.
	 * @return The resource as URL.
	 */
	public URL getResource(String path)
	{
		return Main.class.getResource("/" + this.rootPath + "/" + path);
	}
}
