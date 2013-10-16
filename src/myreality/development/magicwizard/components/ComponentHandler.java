package myreality.development.magicwizard.components;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;

/**
 * Handles components by calling them by ID
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.2
 * @version 1.2
 */
public final class ComponentHandler {
	
	private Map<Long, Component> components;
	
	public ComponentHandler() {
		this.components = new HashMap<Long, Component>();
	}

	/**
	 * Adds a new component
	 * 
	 * @param id
	 * @param component
	 */
	public void add(Long id, Component component) {
		components.put(id, component);
	}
	
	public void add(int id, Component component) {
		long longId = id;
		add(longId, component);
	}
	
	/**
	 * Removes an existing component
	 * 
	 * @param id
	 */
	public void remove(Long id) {
		components.remove(id);
	}
	
	/**
	 * Handles a specific component
	 * 
	 * @param id
	 * @param context
	 */
	public void handle(Long id, Activity context) {
		Component component = components.get(id);
		
		if (component != null) {
			component.handle(context);
		}
	}
}
