package atlas.gui.elemtents;

import atlas.events.Event;
import atlas.events.types.MousePressedEvent;
import atlas.events.EventDispatcher;
import atlas.math.Vec2f;

public class Panel extends GuiElement {

	public Panel() {
		super(null);
	}
	
	private Vec2f position;
	private int width;
	private int height;
	
	@Override
	public void onEvent(Event event) {
		EventDispatcher dispatcher = new EventDispatcher(event);
		dispatcher.dispatch(Event.EventType.MouseButtonPressed, (Event e) -> (onMouseButtonPressed((MousePressedEvent) e)));
	}
	
	private boolean onMouseButtonPressed(MousePressedEvent e) {
		if ( e.getX() > position.getX() && e.getX() < position.getX() + width && e.getY() > position.getY() && e.getY() < position.getY() + height) {
			return true;
		}
		return false;
	}
}
