package siw.model;


public class EventCategory {

    private int idcategory;
    private String name;
    private AnchestorEventCategory anchestor;

    public EventCategory() {
	anchestor = null;

    }

    public int getId() {
	return idcategory;
    }

    public void setId(int id) {
	this.idcategory = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public AnchestorEventCategory getAnchestor() {
	return anchestor;
    }

    public void setAnchestor(AnchestorEventCategory anchestor) {
	this.anchestor = anchestor;
    }
}
