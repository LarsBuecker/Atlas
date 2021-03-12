package atlas.opengl;

import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glDetachShader;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUniform1f;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniform2f;
import static org.lwjgl.opengl.GL20.glUniform3f;
import static org.lwjgl.opengl.GL20.glUniform4f;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;
import static org.lwjgl.opengl.GL20.glUseProgram;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import atlas.math.Mat4f;
import atlas.math.Vec2f;
import atlas.math.Vec3f;
import atlas.math.Vec4f;

public class Shader {
	
	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
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
	
	public void UploadUniformFloat2(String name, Vec2f values) {
		int location = glGetUniformLocation(rendererId, name);
		glUniform2f(location, values.getX(), values.getY());
	}
	
	public void UploadUniformFloat3(String name, Vec3f values) {
		int location = glGetUniformLocation(rendererId, name);
		glUniform3f(location, values.getX(), values.getY(), values.getZ());
	}
	
	public void UploadUniformFloat4(String name, Vec4f values) {
		int location = glGetUniformLocation(rendererId, name);
		glUniform4f(location, values.getX(), values.getY(), values.getZ(), values.getW());
	}
	
	public void UploadUniformMat4(String name, Mat4f matrix) {
		int location = glGetUniformLocation(rendererId, name);
		matrix.store(matrixBuffer);
		matrixBuffer.flip();
		glUniformMatrix4fv(location, false, matrixBuffer);
	}
	
	public int getRendererId() {
		return rendererId;
	}
}
