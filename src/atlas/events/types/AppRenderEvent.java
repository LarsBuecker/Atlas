package atlas.events.types;

import atlas.events.Event;

public class AppRenderEvent extends Event {

	public AppRenderEvent(EventType type) {
		super(Event.EventType.AppRender);
	}

}
