package parcoursup;

public class Element {

	/* Name of the Element */
	String name;

	/* Number maximum of bind this Element can have */
	protected int capacity;

	public Element(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Element() {
		super();
		this.capacity = 1; //default value
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass().equals(this.getClass())) {
			if (((Element) obj).name.equals(this.name)) {
				if (((Element) obj).capacity == this.capacity) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return name;
	}

}
