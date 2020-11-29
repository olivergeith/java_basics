package swt.examples.plugin.draw2d.example.data;

public class ModTabElement {

	private final String name;
	private final String type;

	public ModTabElement(final String type, final String name) {
		this.type = type;
		this.name = name;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return getName();
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

}
