package fr.polytech.di.questgenerator.enums;

import fr.polytech.di.questgenerator.Main;
import java.net.URL;

public enum Resources
{
	TEXTS("texts");

	private final String rootPath;

	Resources(String rootPath)
	{
		this.rootPath = rootPath;
	}

	public URL getResource(String path)
	{
		return Main.class.getResource("/" + this.rootPath + "/" + path);
	}

	public String getResourceString(String path)
	{
		return getResource(path).toString();
	}
}
