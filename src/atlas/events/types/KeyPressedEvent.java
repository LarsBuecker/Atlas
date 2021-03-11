package atlas.events.types;

import atlas.events.Event;

public class KeyPressedEvent extends KeyEvent {

	public KeyPressedEvent(int keycode) {
		super(keycode, Event.EventType.KeyPressed);
	}

}
