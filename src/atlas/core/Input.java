package atlas.core;

import java.nio.DoubleBuffer;

import org.joml.Vector2f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

public class Input {

	private static Input instance = null;
	
	public static Input getInstance() {
		if ( instance == null ) {
			instance = new Input();
		}
		return instance;
	}
	
	protected Input() {
		
	}
	
	public boolean isKeyPressed(int keycode) {
		long window = Application.getInstance().getWindow().getWindow();
		int state = GLFW.glfwGetKey(window, keycode);
		return (state == GLFW.GLFW_PRESS) || (state == GLFW.GLFW_REPEAT);
	}
	
	public boolean isMouseButtonPressed(int button) {
		long window = Application.getInstance().getWindow().getWindow();
		int state = GLFW.glfwGetMouseButton(window, button);
		return state == GLFW.GLFW_PRESS;
	}
	
	public Vector2f getMousePosition() {
		long window = Application.getInstance().getWindow().getWindow();
		DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);
		GLFW.glfwGetCursorPos(window, xBuffer, yBuffer);
		double x = xBuffer.get(0);
		double y = yBuffer.get(0);
		return new Vector2f((float) x, (float) y);
	}
	
}
