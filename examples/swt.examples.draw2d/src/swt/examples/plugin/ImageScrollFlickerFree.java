package swt.examples.plugin;
/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

/*
 * Canvas example snippet: scroll an image (flicker free, no double buffering)
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 */
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Shell;

public class ImageScrollFlickerFree {

	public static void main(final String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		Image originalImage = null;
		final FileDialog dialog = new FileDialog(shell, SWT.OPEN);
		dialog.setText("Open an image file or cancel");
		final String string = dialog.open();
		if (string != null) {
			originalImage = new Image(display, string);
		}
		if (originalImage == null) {
			final int width = 150, height = 200;
			originalImage = new Image(display, width, height);
			final GC gc = new GC(originalImage);
			gc.fillRectangle(0, 0, width, height);
			gc.drawLine(0, 0, width, height);
			gc.drawLine(0, height, width, 0);
			gc.drawText("Default Image", 10, 10);
			gc.dispose();
		}
		final Image image = originalImage;
		final Point origin = new Point(0, 0);
		final Canvas canvas = new Canvas(shell, SWT.NO_BACKGROUND | SWT.NO_REDRAW_RESIZE | SWT.V_SCROLL | SWT.H_SCROLL);
		final ScrollBar hBar = canvas.getHorizontalBar();
		hBar.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(final Event e) {
				final int hSelection = hBar.getSelection();
				final int destX = -hSelection - origin.x;
				final Rectangle rect = image.getBounds();
				canvas.scroll(destX, 0, 0, 0, rect.width, rect.height, false);
				origin.x = -hSelection;
			}
		});
		final ScrollBar vBar = canvas.getVerticalBar();
		vBar.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(final Event e) {
				final int vSelection = vBar.getSelection();
				final int destY = -vSelection - origin.y;
				final Rectangle rect = image.getBounds();
				canvas.scroll(0, destY, 0, 0, rect.width, rect.height, false);
				origin.y = -vSelection;
			}
		});
		canvas.addListener(SWT.Resize, new Listener() {
			@Override
			public void handleEvent(final Event e) {
				final Rectangle rect = image.getBounds();
				final Rectangle client = canvas.getClientArea();
				hBar.setMaximum(rect.width);
				vBar.setMaximum(rect.height);
				hBar.setThumb(Math.min(rect.width, client.width));
				vBar.setThumb(Math.min(rect.height, client.height));
				final int hPage = rect.width - client.width;
				final int vPage = rect.height - client.height;
				int hSelection = hBar.getSelection();
				int vSelection = vBar.getSelection();
				if (hSelection >= hPage) {
					if (hPage <= 0) {
						hSelection = 0;
					}
					origin.x = -hSelection;
				}
				if (vSelection >= vPage) {
					if (vPage <= 0) {
						vSelection = 0;
					}
					origin.y = -vSelection;
				}
				canvas.redraw();
			}
		});
		canvas.addListener(SWT.Paint, new Listener() {
			@Override
			public void handleEvent(final Event e) {
				final GC gc = e.gc;
				gc.drawImage(image, origin.x, origin.y);
				final Rectangle rect = image.getBounds();
				final Rectangle client = canvas.getClientArea();
				final int marginWidth = client.width - rect.width;
				if (marginWidth > 0) {
					gc.fillRectangle(rect.width, 0, marginWidth, client.height);
				}
				final int marginHeight = client.height - rect.height;
				if (marginHeight > 0) {
					gc.fillRectangle(0, rect.height, client.width, marginHeight);
				}
			}
		});
		shell.setSize(200, 150);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		originalImage.dispose();
		display.dispose();
	}

}