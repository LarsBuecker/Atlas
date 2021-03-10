package atlas;

import atlas.core.Layer;
import atlas.events.Event;

public class ExampleLayer extends Layer {

	public ExampleLayer() {
		super("Example Layer");
	}

	@Override
	public void OnAttach() {
		System.out.println("Example Layer Attached");
		
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRender() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGuiRender() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub
		System.out.println(event);
	}
	
}

