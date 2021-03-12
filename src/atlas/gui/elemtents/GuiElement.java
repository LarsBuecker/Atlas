package atlas.gui.elemtents;

import java.util.ArrayList;
import java.util.List;

import atlas.events.Event;
import atlas.events.EventListener;

public abstract class GuiElement implements EventListener {

	private GuiElement parent;
	protected List<GuiElement> children = new ArrayList<GuiElement>();
	
	public GuiElement(GuiElement parent) {
		this.parent = parent;
	}

	public GuiElement getParent() {
		return parent;
	}
	
	public void addChild(GuiElement element) {
		children.add(element);
	}
	
	public void removeChild(GuiElement element) {
		children.remove(element);
	}
	
	@Override
	public abstract void onEvent(Event event);
	
}
