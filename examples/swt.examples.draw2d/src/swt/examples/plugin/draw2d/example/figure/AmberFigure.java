package swt.examples.plugin.draw2d.example.figure;

import org.eclipse.draw2d.RectangleFigure;

public class AmberFigure extends RectangleFigure {

	private boolean isSelected = false;

	public AmberFigure() {
		super();
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(final boolean isSelected) {
		this.isSelected = isSelected;
	}

}
