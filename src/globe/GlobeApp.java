package globe;

import atlas.core.Application;

public class GlobeApp {

	public static void main(String[] args) {
		Application globeApp = new Application();
		
		globeApp.pushLayer(new EditorLayer());
		
		globeApp.run();
	}

}
