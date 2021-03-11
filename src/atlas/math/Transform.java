package atlas.math;

import org.joml.Vector3f;

public class Transform {

	private Vector3f position; 
	private Vector3f rotation;
	private Vector3f scale;
	
	public Transform() {
		this.position = new Vector3f();
		this.rotation = new Vector3f();
		this.scale = new Vector3f();
	}
	
	public Transform(Vector3f positon, Vector3f rotation, Vector3f scale) {
		this.position = positon;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public void add(Vector3f position, Vector3f rotation, Vector3f scale) {
		this.position.add(position);
		this.rotation.add(rotation);
		this.scale.add(scale);
	}
	
	public void sub(Vector3f position, Vector3f rotation, Vector3f scale) {
		this.position.sub(position);
		this.rotation.sub(rotation);
		this.scale.sub(scale);
	}
	
	public Vector3f getPosition() {
		return position;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	public Vector3f getRotation() {
		return rotation;
	}
	public void setRotation(Vector3f rotaion) {
		this.rotation = rotaion;
	}
	public Vector3f getScale() {
		return scale;
	}
	public void setScale(Vector3f scale) {
		this.scale = scale;
	}
}
