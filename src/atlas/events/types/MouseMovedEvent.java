package atlas.events.types;

import atlas.events.Event;

public class MouseMovedEvent extends Event {
	
	private int x, y;
	private boolean dragged;

	public MouseMovedEvent(int x, int y, boolean dragged) {
		super(Event.EventType.MouseMoved);
		this.x = x;
		this.y = y;
		this.dragged = dragged;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isDragged() {
		return dragged;
	}
}
