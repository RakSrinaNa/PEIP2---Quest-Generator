package fr.polytech.di.questgenerator.jfx;

import fr.polytech.di.questgenerator.interfaces.GameListener;
import fr.polytech.di.questgenerator.objects.DataHandler;
import fr.polytech.di.questgenerator.objects.xml.XMLStringObjectiveElement;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.text.TextAlignment;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by MrCraftCod on 03/03/2016.
 */
public class EventNode extends Button
{
	public EventNode(GameListener gameListener, Method method)
	{
		super(method.getName());
		this.setTextAlignment(TextAlignment.CENTER);
		this.setOnMouseReleased(event -> {
				try
				{
					ArrayList<Object> args = new ArrayList<>();
					for(Parameter param : method.getParameters())
					{
						ChoiceDialog<XMLStringObjectiveElement> dialog = new ChoiceDialog<>(null, DataHandler.getAllSorted());
						dialog.setTitle("Parameter " + param.getName());
						dialog.setHeaderText("Parameter " + param.getName());
						dialog.setContentText("Select value to send");
						Optional<XMLStringObjectiveElement> value = dialog.showAndWait();
						if(!value.isPresent())
							return;
						args.add(value.get());
					}
					method.invoke(gameListener, args.toArray());
				}
				catch(IllegalAccessException e)
				{
					e.printStackTrace();
				}
				catch(InvocationTargetException e)
				{
					e.getTargetException().printStackTrace();
				}
		});
	}
}
