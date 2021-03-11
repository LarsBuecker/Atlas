package atlas.renderer;

import org.joml.Matrix4f;

import atlas.opengl.Shader;
import atlas.opengl.VertexArray;

public class Renderer {

	private static SceneData sceneData;
	
	public static void BeginScene(Camera camera) {
		sceneData.ViewProjectionMatrix = camera.getViewProjectionMatrix();
	}
	
	public static void EndScene() {
		
	}
	
	public static void submit(Shader shader, VertexArray vertexArray, Matrix4f transform) {
		shader.bind();
		shader.UploadUniformMat4("u_ViewProjection", sceneData.ViewProjectionMatrix);
		shader.UploadUniformMat4("u_Transform", transform);
		
		vertexArray.bind();
		RendererAPI.drawIndexed(vertexArray);
	}
}
