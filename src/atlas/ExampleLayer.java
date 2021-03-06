package atlas;

import java.util.ArrayList;
import java.util.List;

import atlas.core.Input;
import atlas.core.KeyCodes;
import atlas.core.Layer;
import atlas.core.Log;
import atlas.events.Event;
import atlas.math.Mat4f;
import atlas.math.Vec3f;
import atlas.math.Vec4f;
import atlas.opengl.IndexBuffer;
import atlas.opengl.Shader;
import atlas.opengl.VertexArray;
import atlas.opengl.VertexBuffer;
import atlas.renderer.BufferElement;
import atlas.renderer.BufferElement.ShaderDataType;
import atlas.renderer.BufferLayout;
import atlas.renderer.Camera;
import atlas.renderer.Renderer;
import atlas.renderer.RendererAPI;

public class ExampleLayer extends Layer {
	
	private VertexArray vertexArray;
	private Shader shader;
	private Camera camera;
	private Input input = Input.getInstance();
	
	private Mat4f transform;
	private Vec3f rot = new Vec3f();

	public ExampleLayer() {
		super("Example Layer");
		
		vertexArray = new VertexArray();
		
//		float vertices[] = {
//				-0.5f, -0.5f, 0.0f, 0.8f, 0.2f, 0.8f, 1.0f,
//				 0.5f, -0.5f, 0.0f, 0.2f, 0.3f, 0.8f, 1.0f,
//				 0.0f,  0.5f, 0.0f, 0.8f, 0.8f, 0.2f, 1.0f
//		};
		
		float square[] = {
			-0.5f, -0.5f, 0.0f, 0.8f, 0.2f, 0.8f, 1.0f,
		 	 0.5f, -0.5f, 0.0f, 0.2f, 0.3f, 0.8f, 1.0f,
		 	 0.5f,  0.5f, 0.0f, 0.8f, 0.8f, 0.2f, 1.0f,
		 	-0.5f,  0.5f, 0.0f, 0.3f, 0.8f, 0.5f, 1.0f
		};
		
		VertexBuffer vertexBuffer = new VertexBuffer(square, 3);
		
		BufferElement position = new BufferElement(ShaderDataType.Float3, "a_Position", false);
		BufferElement color = new BufferElement(ShaderDataType.Float4, "a_Color", false);
		List<BufferElement> elementList = new ArrayList<BufferElement>();
		elementList.add(position);
		elementList.add(color);
		BufferLayout layout = new BufferLayout(elementList);
		
		vertexBuffer.setBufferLayout(layout);
		vertexArray.addVertexBuffer(vertexBuffer);
		
//		int indices[] = { 0, 1, 2 };
		int indices1[] = { 0, 1, 2, 2, 3, 0 };
		IndexBuffer indexBuffer = new IndexBuffer(indices1, indices1.length);
		vertexArray.setIndexBuffer(indexBuffer);
	
	
		shader = new Shader(Shader.loadShader("vertex.vs"), Shader.loadShader("fragment.fs"));
		
		camera = new Camera();
		
		transform = new Mat4f().Identity();
	}

	@Override
	public void OnAttach() {
		Log.clientLog("Example Layer Attached");
	}

	@Override
	public void onDetach() {
		Log.clientLog(this.getName() + " Detached");
	}

	@Override
	public void onUpdate(float delta) {
		rot.setX(rot.getX() + delta * 0.01f);
		rot.setY(rot.getY() + delta * 0.04f);
		rot.setZ(rot.getZ() + delta * 0.06f);
		transform.Rotation(rot);
		
		if (input.isKeyPressed(KeyCodes.AL_KEY_A)) {
			camera.setPosition(new Vec3f(camera.getPosition().getX() + delta * -0.01f, camera.getPosition().getY(), camera.getPosition().getZ()));
		}
		if (input.isKeyPressed(KeyCodes.AL_KEY_D)) {
			camera.setPosition(new Vec3f(camera.getPosition().getX() + delta * 0.01f, camera.getPosition().getY(), camera.getPosition().getZ()));
		}
		if (input.isKeyPressed(KeyCodes.AL_KEY_W)) {
			camera.setPosition(new Vec3f(camera.getPosition().getX(), camera.getPosition().getY(), camera.getPosition().getZ() + delta * 0.01f));
		}
		if (input.isKeyPressed(KeyCodes.AL_KEY_S)) {
			camera.setPosition(new Vec3f(camera.getPosition().getX(), camera.getPosition().getY(), camera.getPosition().getZ() + delta * -0.01f));
		}
	}

	@Override
	public void onRender() {
		RendererAPI.setClearColor(new Vec4f( 0.1f, 0.1f, 0.1f, 1));
		RendererAPI.clear();
		
		Renderer.BeginScene(camera);
		shader.bind();
		shader.UploadUniformFloat3("u_Color", new Vec3f(0.5f, 0.2f, 0.7f));
		Renderer.submit(shader, vertexArray, transform);
		Renderer.EndScene();
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

