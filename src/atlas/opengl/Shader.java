package atlas.opengl;

import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;

import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;

public class Shader {
	
	private static FloatBuffer matrix3Buffer = BufferUtils.createFloatBuffer(9);
	private static FloatBuffer matrix4Buffer = BufferUtils.createFloatBuffer(16);
	
	private int rendererId;

	public Shader(String vertexSrc, String fragmentSrc) {
		int vertexShader = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(vertexShader, vertexSrc);
		glCompileShader(vertexShader);
		
		if ( glGetShaderi(vertexShader, GL_COMPILE_STATUS) == 0 ) {
			System.err.println(this.getClass().getName() + " " + glGetShaderInfoLog(vertexShader, 1024));
			glDeleteShader(vertexShader);
			System.exit(1);
		}
		
		int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(fragmentShader, fragmentSrc);
		glCompileShader(fragmentShader);
		
		if ( glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == 0 ) {
			System.err.println(this.getClass().getName() + " " + glGetShaderInfoLog(fragmentShader, 1024));
			glDeleteShader(fragmentShader);
			System.exit(1);
		}
		
		rendererId = glCreateProgram();
		
		glAttachShader(rendererId, vertexShader);
		glAttachShader(rendererId, fragmentShader);
		
		glLinkProgram(rendererId);
		
		if ( glGetProgrami(rendererId, GL_LINK_STATUS) == 0 ) {
			System.err.println(this.getClass().getName() + " " + glGetProgramInfoLog(rendererId, 1024));
			glDeleteProgram(rendererId);
			System.exit(1);
		}
		
		glDetachShader(rendererId, vertexShader);
		glDetachShader(rendererId, fragmentShader);
	}
	
	public void bind() {
		glUseProgram(rendererId);
	}
	
	public void unbind() {
		glUseProgram(0);
	}
	
	public void UploadUniformInt(String name, int value) {
		int location = glGetUniformLocation(rendererId, name);
		glUniform1i(location, value);
	}
	
	public void UploadUniformFloat(String name, float value) {
		int location = glGetUniformLocation(rendererId, name);
		glUniform1f(location, value);
	}
	
	public void UploadUniformFloat2(String name, Vector2f values) {
		int location = glGetUniformLocation(rendererId, name);
		glUniform2f(location, values.x, values.y);
	}
	
	public void UploadUniformFloat3(String name, Vector3f values) {
		int location = glGetUniformLocation(rendererId, name);
		glUniform3f(location, values.x, values.y, values.z);
	}
	
	public void UploadUniformFloat4(String name, Vector4f values) {
		int location = glGetUniformLocation(rendererId, name);
		glUniform4f(location, values.x, values.y, values.z, values.w);
	}
	
	public void UploadUniformMat3(String name, Matrix3f matrix) {
		int location = glGetUniformLocation(rendererId, name);
		matrix3Buffer = matrix.get(matrix3Buffer);
		glUniformMatrix3fv(location, false, matrix3Buffer);
	}
	
	public void UploadUniformMat4(String name, Matrix4f matrix) {
		int location = glGetUniformLocation(rendererId, name);
		matrix4Buffer =  matrix.get(matrix4Buffer);
		glUniformMatrix4fv(location, false, matrix4Buffer);
	}
	
	public int getRendererId() {
		return rendererId;
	}
}
