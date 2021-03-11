package atlas.events.types;

import atlas.events.Event;

public class KeyTypedEvent extends KeyEvent {

	public KeyTypedEvent(int keycode) {
		super(keycode, Event.EventType.KeyTyped);
	}

}
