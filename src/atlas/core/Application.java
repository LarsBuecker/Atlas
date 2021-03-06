package atlas.core;

import atlas.events.Event;
import atlas.events.EventDispatcher;
import atlas.events.EventListener;
import atlas.events.types.WindowCloseEvent;

public class Application implements EventListener {

	private static Application instance = null;
	
	public static Application getInstance() {
		return instance;
	}
	
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
		
		Input.getInstance();
		
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
		EventDispatcher dispatcher = new EventDispatcher(e);
		dispatcher.dispatch(Event.EventType.WindowClose, (Event event) -> (onWindowClose((WindowCloseEvent) event)));
		
		for ( Layer layer : layerStack.getLayers() ) {
			layer.onEvent(e);
		}
	}
	
	public void run() {
		getDelta();
		
		while(isRunning) {
			update();
			render();
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
	
	private boolean onWindowClose(WindowCloseEvent e) {
		isRunning = false;
		return true;
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
	
	public Window getWindow() {
		return window;
	}
}
