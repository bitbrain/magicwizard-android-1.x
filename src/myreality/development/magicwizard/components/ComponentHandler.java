package myreality.development.magicwizard.components;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;

/**
 * Handles components by calling them by ID
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.2
 * @version 1.2
 */
public final class ComponentHandler {
	
	private SparseArray<Component> components;
	
	public ComponentHandler() {
		this.components = new SparseArray<Component>();
	}

	/**
	 * Adds a new component
	 * 
	 * @param id
	 * @param component
	 */
	public void add(Integer id, Component component) {
		components.put(id, component);
	}
	
	/**
	 * Removes an existing component
	 * 
	 * @param id
	 */
	public void remove(Integer id) {
		components.remove(id);
	}
	
	/**
	 * Handles a specific component
	 * 
	 * @param id
	 * @param context
	 */
	public void handle(Integer id, Activity context) {
		Component component = components.get(id);
		
		if (component != null) {
			component.handle(context);
		}
	}
}
