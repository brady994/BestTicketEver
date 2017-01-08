package siw.model;

import java.util.HashMap;
import java.util.Map;

public class AnchestorEventCategory {
	private int idanchestor;
	private String name;
	private Map<Integer, EventCategory> children;
	
	public AnchestorEventCategory()
	{
		this.setChildren(new HashMap<Integer, EventCategory>());
	}
	
	public int getId() {
		return idanchestor;
	}
	public void setId(int id) {
		this.idanchestor = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Map<Integer, EventCategory> getChildren() {
		return children;
	}

	public void setChildren(Map<Integer, EventCategory> children) {
		this.children = children;
	}

}
