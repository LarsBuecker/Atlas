package atlas;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import atlas.core.Layer;
import atlas.core.Log;
import atlas.events.Event;

public class ExampleLayer extends Layer {

	public ExampleLayer() {
		super("Example Layer");
	}

	@Override
	public void OnAttach() {
		Log.clientLog("Example Layer Attached");
		GL11.glClearColor(0.2f, 0.2f, 0.2f, 0);
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
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}

	@Override
	public void onGuiRender() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub
		Log.clientLog(event.toString());
	}
	
}

