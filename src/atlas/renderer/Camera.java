package atlas.renderer;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {

	private Matrix4f projectionMatrix;
	private Matrix4f viewMatrix;
	private Matrix4f viewProjectionMatrix;

	private Vector3f position;
	private Vector3f rotation;
	
	private static final float NEAR = 0.01f;
	private static final float FAR = 1000;
	private static float fov = 70;
	
	public Camera() {
		this.position = new Vector3f();
		this.rotation = new Vector3f();
		viewProjectionMatrix = projectionMatrix.mul(viewMatrix);
	}
	
	private void recalculateViewMatrix() {
		
	}
	
	public Matrix4f getViewProjectionMatrix() {
		return viewProjectionMatrix;
	}
}
