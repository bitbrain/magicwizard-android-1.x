package myreality.development.magicwizard.components;

import android.app.Activity;
import android.util.SparseArray;
import android.view.View;

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
	public void handle(Integer id, Activity context, View sender) {
		Component component = components.get(id);
		
		if (component != null) {
			component.handle(context, sender);
		}
	}
	
	public void onCreate(Activity context) {
		for (int i = 0; i < components.size(); ++i) {
			Component c = components.valueAt(i);
			c.onCreate(context);
		}
	}
	
	public void onDestroy(Activity context) {
		for (int i = 0; i < components.size(); ++i) {
			Component c = components.valueAt(i);
			c.onDestroy(context);
		}
	}

	public void onPause(Activity context) {
		for (int i = 0; i < components.size(); ++i) {
			Component c = components.valueAt(i);
			c.onPause(context);
		}
	}

	public void onResume(Activity context) {
		for (int i = 0; i < components.size(); ++i) {
			Component c = components.valueAt(i);
			c.onResume(context);
		}
	}
}
