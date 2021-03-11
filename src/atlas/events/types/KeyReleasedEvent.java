package atlas.events.types;

import atlas.events.Event;

public class KeyReleasedEvent extends KeyEvent {

	public KeyReleasedEvent(int keycode) {
		super(keycode, Event.EventType.KeyReleased);
	}

}
