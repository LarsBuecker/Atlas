package atlas.opengl;

import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;

public class OpenGLRendererAPI {

	public void setClearColor(Vector4f color) {
		GL11.glClearColor(color.x, color.y, color.z, color.w);
	}
	
	public void clear() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	public void drawIndexed(VertexArray vertexArray) {
		//GL11.glDrawElements(GL11.GL_TRIANGLES, vertexArray.getIndexBuffer().getCount(), GL11.GL_UNSIGNED_INT, null);
	}
}
