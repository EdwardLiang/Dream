package keymanagers;

import infrastructure.App;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseManager implements EventHandler<MouseEvent> {
	@Override
	public void handle(MouseEvent me) {
		if (me.getEventType() == MouseEvent.MOUSE_CLICKED) {
			App.shaker
					.addMarker((float) me.getSceneX(), (float) me.getSceneY());
		}
		//else if(me.getEventType().equals(MouseEvent.))
	}
}
