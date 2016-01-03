package view.base.extend;

import java.awt.Component;

import model.ClientModel;
import view.base.Panel;

public class AbstractView extends Panel{

	protected ClientModel clientModel;
	public void setModel(ClientModel clientModel) {
		this.clientModel = clientModel;
		for(Component component: getComponents())
			if(component instanceof AbstractView)
				((AbstractView)component).setModel(clientModel);
	}
}
