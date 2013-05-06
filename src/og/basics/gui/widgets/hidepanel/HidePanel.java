package og.basics.gui.widgets.hidepanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import og.basics.gui.icon.CommonIconProvider;

public class HidePanel extends JPanel {
	private static final long	serialVersionUID	= 874303619702233532L;
	private final JLabel		titleLable			= new JLabel();
	private boolean				hidden				= false;
	private final Component		compToBeHidden;

	/**
	 * constructs a HidePanel the compToBeHidden will be shown initially
	 * 
	 * @param title
	 *            the Title
	 * @param compToBeHidden
	 *            the component to be hidden on click
	 */
	public HidePanel(final String title, final Component compToBeHidden) {
		super();
		setLayout(new BorderLayout());
		titleLable.setText(title);
		this.compToBeHidden = compToBeHidden;
		initUI(true);
	}

	/**
	 * constructs a HidePanel
	 * 
	 * @param title
	 *            the Title
	 * @param compToBeHidden
	 *            the component to be hidden on click
	 * @param showInitially
	 *            set false if it should be hidden initially
	 */
	public HidePanel(final String title, final Component compToBeHidden, final boolean showInitially) {
		super();
		setLayout(new BorderLayout());
		titleLable.setText(title);
		this.compToBeHidden = compToBeHidden;
		initUI(showInitially);
	}

	private void initUI(final boolean showInitially) {
		titleLable.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(final MouseEvent arg0) {
				if (isHidden()) {
					unhideComp();
				} else {
					hideComp();
				}
			}
		});
		titleLable.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		titleLable.setForeground(Color.BLUE.darker().darker());
		titleLable.setBorder(new BevelBorder(2));
		final JPanel p = new JPanel(new BorderLayout());
		p.add(titleLable, BorderLayout.CENTER);
		p.setBackground(Color.GRAY.brighter());
		this.add(p, BorderLayout.NORTH);
		add(compToBeHidden, BorderLayout.CENTER);
		if (showInitially == true) {
			unhideComp();
		} else {
			hideComp();
		}
	}

	public void hideComp() {
		compToBeHidden.setVisible(false);
		titleLable.setIcon(CommonIconProvider.BUTTON_ICON_HIDDEN);
		titleLable.setToolTipText("Show " + titleLable.getText());
		setHidden(true);
	}

	public void unhideComp() {
		compToBeHidden.setVisible(true);
		titleLable.setIcon(CommonIconProvider.BUTTON_ICON_NOT_HIDDEN);
		titleLable.setToolTipText("Hide " + titleLable.getText());
		setHidden(false);
	}

	public String getTitle() {
		return titleLable.getText();
	}

	public void setTitle(final String title) {
		titleLable.setText(title);
	}

	public boolean isHidden() {
		return hidden;
	}

	private void setHidden(final boolean hidden) {
		this.hidden = hidden;
	}

	public Component getCompToBeHidden() {
		return compToBeHidden;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		final JFrame f = new JFrame();
		f.setTitle("Hallo Emmy!!!!!!!");
		f.setBounds(200, 200, 600, 800);
		f.setLayout(new BorderLayout());
		final HidePanel p = new HidePanel("Battery Settings", new JLabel("Hello"), true);
		f.add(p, BorderLayout.CENTER);

		f.setVisible(true);
		f.pack();
	}
}
