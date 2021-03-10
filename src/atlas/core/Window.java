package atlas.core;

import org.lwjgl.glfw.*;

import atlas.events.types.WindowCloseEvent;

public class Window {

	private long window;
	
	private String title;
	private int width, height;
	
	public Window(String title, int width, int height) {	
		this.title = title;
		this.width = width;
		this.height = height;
	}
	
	public void create() {
		if(!GLFW.glfwInit()) {
			System.err.print("Failed to init GLFW!");
			System.exit(-1);
		}
			
		window = GLFW.glfwCreateWindow(width, height, title, 0, 0);
		
		if ( window == 0 ) {
			System.err.println("Failed to create window!");
			System.exit(-1);
		}
		
		GLFWVidMode vidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		GLFW.glfwSetWindowPos(window, (vidMode.width() / 2), (vidMode.height() / 2));
		GLFW.glfwShowWindow(window);
	}
	
	
	public void update() {
		GLFW.glfwPollEvents();
		
		if (GLFW.glfwWindowShouldClose(window)) {
			WindowCloseEvent windowCloseEvnet = new WindowCloseEvent();
			Application.getInstance().onEvent(windowCloseEvnet);
		}
	}
	
	public void swapBuffers() {
		GLFW.glfwSwapBuffers(window);
	}
	
	public void dispose() {
		
	}
	
	public long getWindow() {
		return window;
	}
}
