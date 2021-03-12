package atlas;

import java.util.ArrayList;
import java.util.List;

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

	public ExampleLayer() {
		super("Example Layer");
		
		vertexArray = new VertexArray();
		
		float vertices[] = {
				-0.5f, -0.5f, 0.0f, 0.8f, 0.2f, 0.8f, 1.0f,
				 0.5f, -0.5f, 0.0f, 0.2f, 0.3f, 0.8f, 1.0f,
				 0.0f,  0.5f, 0.0f, 0.8f, 0.8f, 0.2f, 1.0f
		};
		
		VertexBuffer vertexBuffer = new VertexBuffer(vertices, 3);
		
		BufferElement position = new BufferElement(ShaderDataType.Float3, "a_Position", false);
		BufferElement color = new BufferElement(ShaderDataType.Float4, "a_Color", false);
		List<BufferElement> elementList = new ArrayList<BufferElement>();
		elementList.add(position);
		elementList.add(color);
		BufferLayout layout = new BufferLayout(elementList);
		
		vertexBuffer.setBufferLayout(layout);
		vertexArray.addVertexBuffer(vertexBuffer);
		
		int indices[] = { 0, 1, 2 };
		IndexBuffer indexBuffer = new IndexBuffer(indices, indices.length);
		vertexArray.setIndexBuffer(indexBuffer);
	
		String vertexSrc = "#version 330 core\r\n"
				+ "			\r\n"
				+ "			layout(location = 0) in vec3 a_Position;\r\n"
				+ "			layout(location = 1) in vec4 a_Color;\r\n"
				+ "			uniform mat4 u_ViewProjection;\r\n"
				+ "			uniform mat4 u_Transform;\r\n"
				+ "			out vec3 v_Position;\r\n"
				+ "			out vec4 v_Color;\r\n"
				+ "			void main()\r\n"
				+ "			{\r\n"
				+ "				v_Position = a_Position;\r\n"
				+ "				v_Color = a_Color;\r\n"
				+ "				gl_Position = u_Transform * vec4(a_Position, 1.0);	\r\n"
				+ "			}";
		
		String fragmentSrc = "#version 330 core\r\n"
				+ "			\r\n"
				+ "			layout(location = 0) out vec4 color;\r\n"
				+ "			in vec3 v_Position;\r\n"
				+ "			in vec4 v_Color;\r\n"
				+ "			void main()\r\n"
				+ "			{\r\n"
				+ "				color = vec4(v_Position * 0.5 + 0.5, 1.0);\r\n"
				+ "				color = v_Color;\r\n"
				+ "			}";
	
	
		shader = new Shader(vertexSrc, fragmentSrc);
		
		camera = new Camera();
		camera.setPosition(new Vec3f(-10f, -1f, -1f));
	}

	@Override
	public void OnAttach() {
		Log.clientLog("Example Layer Attached");
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate(float delta) {
		
	}

	@Override
	public void onRender() {
		RendererAPI.setClearColor(new Vec4f( 0.1f, 0.1f, 0.1f, 1));
		RendererAPI.clear();
		
		Renderer.BeginScene(camera);
		shader.bind();
		shader.UploadUniformFloat3("u_Color", new Vec3f(0.5f, 0.2f, 0.7f));
		Renderer.submit(shader, vertexArray, new Mat4f().Identity().translate(new Vec3f(0, 0, 0)));
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

