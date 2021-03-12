package atlas.renderer;

import atlas.core.Application;
import atlas.math.Mat4f;
import atlas.math.Vec3f;

public class Camera {

	private Mat4f projectionMatrix = new Mat4f();
	private Mat4f viewMatrix = new Mat4f();
	private Mat4f viewProjectionMatrix = new Mat4f();

	private Vec3f position;
	private Vec3f rotation;
	
	private static final float NEAR = 0.01f;
	private static final float FAR = 1000;
	private static float fov = 70;
	
	public Camera() {
		this.position = new Vec3f();
		this.rotation = new Vec3f();
		recalculateViewMatrix();
		viewProjectionMatrix = projectionMatrix.mul(viewMatrix);
	}
	
	private void recalculateViewMatrix() {
		int width = Application.getInstance().getWindow().getWidth();
		int height = Application.getInstance().getWindow().getHeight();
		viewMatrix.PerspectiveProjection(fov, width, height, NEAR, FAR);
	}
	
	public Mat4f getViewProjectionMatrix() {
		return viewProjectionMatrix;
	}
	
	public void setPosition(Vec3f pos) {
		this.position = pos;
		recalculateViewMatrix();
	}
	
	public Vec3f getPosition() {
		return position;
	}

	public Mat4f getProjectionMatrix() {
		return projectionMatrix;
	}

	public Mat4f getViewMatrix() {
		return viewMatrix;
	}
}
