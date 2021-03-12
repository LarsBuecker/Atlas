package atlas.gui;

import atlas.core.Layer;
import atlas.core.Log;
import atlas.events.Event;

public class ALGuiLayer extends Layer {

	public ALGuiLayer() {
		super("Atlas Gui Layer");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void OnAttach() {
		Log.coreLog(getName() + "Attached!");
	}

	@Override
	public void onDetach() { }

	@Override
	public void onUpdate(float delta) {
		
	}

	@Override
	public void onRender() {
		
	}

	@Override
	public void onGuiRender() {}

	@Override
	public void onEvent(Event event) {
		
	}

}
