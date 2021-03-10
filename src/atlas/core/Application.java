package atlas.core;

import org.lwjgl.glfw.GLFW;

import atlas.events.Event;

public class Application {

	private static Application instance = null;
	
	private Window window;
	private LayerStack layerStack;
	
	private boolean isRunning = true;
	private long lastFrame;
	
	public Application() {
		if (instance != null) {
			System.err.println("Application already exists!");
			System.exit(1);
		}
		
		instance = this;
		
		window = new Window("Atlas", 1280, 720);
		window.create();
		
		layerStack = new LayerStack();
	}
	
	public void pushLayer(Layer layer) {
		layerStack.PushLayer(layer);
		layer.OnAttach();
	}
	
	public void pushOverlay(Layer overlay) {
		layerStack.PushOverlay(overlay);
		overlay.OnAttach();
	}
	
	public void onEvent(Event e) {
		
	}
	
	public void run() {
		getDelta();
		
		while(isRunning) {
			update();
			render();
			
			if( window.shouldClose())
				break;
		}
		
		window.dispose();
	}
	
	private void update() {
		
		for (Layer layer : layerStack.getLayers()) {
			layer.onUpdate(getDelta());
		}
		
		window.update();
	}
	
	public void render() {
		
		for (Layer layer : layerStack.getLayers()) {
			layer.onRender();
		}
		
		window.swapBuffers();
	}
	
	public long getTime() {
	    return System.nanoTime() / 1000000;
	}
	
	public int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
	         
	    return delta;
	}
}
