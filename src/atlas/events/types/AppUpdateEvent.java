package atlas.events.types;

import atlas.events.Event;

public class AppUpdateEvent extends Event {

	public AppUpdateEvent() {
		super(Event.EventType.AppUpdate);
	}	
}
