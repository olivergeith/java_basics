package og.basics.gui.Jcolorselectbutton;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.colorchooser.AbstractColorChooserPanel;

/* Used by ColorChooserDemo2.java. */
public class CrayonPanel extends AbstractColorChooserPanel implements ActionListener {
	private static final long	serialVersionUID	= 2389524499578003673L;
	final ButtonGroup			boxOfCrayons		= new ButtonGroup();
	List<JToggleButton>			buttons				= new ArrayList<JToggleButton>();

	@Override
	public void updateChooser() {
		final Color color = getColorFromModel();
		for (final JToggleButton button : buttons) {
			if (button.getBackground().equals(color))
				button.setSelected(true);
			return;
		}
	}

	protected JToggleButton createCrayon(final String name, final Color col) {
		final JToggleButton crayon = new JToggleButton();
		crayon.setActionCommand(name);
		crayon.addActionListener(this);
		crayon.setText(name);
		crayon.setHorizontalAlignment(JButton.HORIZONTAL);
		crayon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		crayon.setBackground(col);
		buttons.add(crayon);
		boxOfCrayons.add(crayon);
		add(crayon);
		return crayon;
	}

	@Override
	protected void buildChooser() {
		setLayout(new GridLayout(0, 1));

		createCrayon("yellow", Color.yellow);
		createCrayon("orange", Color.orange);
		createCrayon("red", Color.red);
		createCrayon("green", Color.green.darker());
		createCrayon("Samsung green", new Color(142, 205, 0));
		createCrayon("AOKP blue", new Color(39, 135, 173));
		createCrayon("AOKP blue (light)", new Color(51, 181, 229));
		createCrayon("Dark Gray", Color.darkGray);
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		Color newColor = null;
		final JToggleButton toggle = (JToggleButton) e.getSource();
		newColor = toggle.getBackground();
		getColorSelectionModel().setSelectedColor(newColor);
	}

	@Override
	public String getDisplayName() {
		return "Predefined Colors";
	}

	@Override
	public Icon getSmallDisplayIcon() {
		return null;
	}

	@Override
	public Icon getLargeDisplayIcon() {
		return null;
	}
}