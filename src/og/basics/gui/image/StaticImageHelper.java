package og.basics.gui.image;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import sun.awt.image.BufferedImageGraphicsConfig;

public class StaticImageHelper {

	/**
	 * Converts an {@link ImageIcon} to a {@link BufferedImage}
	 * 
	 * @param icon
	 *            the {@link ImageIcon}
	 * @return a {@link BufferedImage}
	 */
	public static BufferedImage convertImageIcon(final ImageIcon icon) {
		final BufferedImage buImg = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		buImg.getGraphics().drawImage(icon.getImage(), 0, 0, null);
		return buImg;
	}

	/**
	 * Writes an {@link ImageIcon} to disk
	 * 
	 * @param icon
	 * @param file
	 */
	public static void writePNG(final ImageIcon icon, final File file) {
		writePNG(convertImageIcon(icon), file);
	}

	/**
	 * Writes a {@link BufferedImage} to disk
	 * 
	 * @param img
	 * @param file
	 */
	public static void writePNG(final BufferedImage img, final File file) {
		try {
			file.createNewFile();
			while (!file.canWrite()) {
				// do nothing until it can write...
			}
			ImageIO.write(img, "png", file);
		} catch (final IOException e) {
			System.out.println("Oh, Icon could not be written!!...Trying again!");
			try {
				ImageIO.write(img, "png", file);
			} catch (final IOException e1) {
				System.out.println("Hm that's strange...still not writable!...giving up!");
			}
		}
	}

	/**
	 * Resizes an {@link BufferedImage} so that the longest side is size long
	 * 
	 * @param img
	 * @param size
	 * @return
	 */
	public static BufferedImage resizeLongestSide2Size(final BufferedImage img, final int size) {
		if (img.getWidth() > img.getHeight())
			return resize2Width(img, size);
		else
			return resize2Height(img, size);
	}

	/**
	 * Resizes a {@link BufferedImage} to a width and keeps the aspect ratio
	 * 
	 * @param img
	 * @param width
	 * @return
	 */
	public static BufferedImage resize2Width(final BufferedImage img, final int width) {
		// get the aspect ratio right...
		final int height = Math.round((float) img.getHeight() / (float) img.getWidth() * width);
		return resize(img, width, height);
	}

	/**
	 * Resizes an {@link BufferedImage} to height and keeps the aspect ratio
	 * 
	 * @param img
	 * @param height
	 * @return
	 */
	public static BufferedImage resize2Height(final BufferedImage img, final int height) {
		// get the aspect ratio right...
		final int width = Math.round((float) img.getWidth() / (float) img.getHeight() * height);
		return resize(img, width, height);
	}

	/**
	 * Resizes an {@link BufferedImage}
	 * 
	 * @param img
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage resize(final BufferedImage img, final int width, final int height) {
		final int type = img.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : img.getType();
		final BufferedImage resizedImage = new BufferedImage(width, height, type);
		final Graphics2D g = resizedImage.createGraphics();
		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(img, 0, 0, width, height, null);
		g.dispose();
		return resizedImage;
	}

	/**
	 * Resizes a {@link BufferedImage} using an advanced algorithm...only
	 * usefull if the image is rizides to a smaller size! Keeps the aspect ratio
	 * 
	 * @param image
	 * @param height
	 * @return
	 */
	public static BufferedImage resizeAdvanced2Height(BufferedImage image, final int height) {
		// nur bei Verkleinerungen anwenden
		if (height < image.getHeight()) {
			image = createCompatibleImage(image);
			image = resize2Height(image, image.getHeight() * 3);
			image = blurImage16(image);
			image = resize2Height(image, height);
		} else {
			image = resize2Height(image, height);
		}
		return image;
	}

	/**
	 * Blurrs an {@link BufferedImage}
	 * 
	 * @param image
	 * @return
	 */
	public static BufferedImage blurImage(final BufferedImage image) {
		final float ninth = 1.0f / 9.0f;
		final float[] blurKernel = {
				ninth, ninth, ninth, ninth, ninth, ninth, ninth, ninth, ninth
		};

		final Map<RenderingHints.Key, Object> map = new HashMap<RenderingHints.Key, Object>();
		map.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		map.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		map.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		final RenderingHints hints = new RenderingHints(map);
		final BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, blurKernel), ConvolveOp.EDGE_NO_OP, hints);
		return op.filter(image, null);
	}

	/**
	 * Blurrs an {@link BufferedImage}
	 * 
	 * @param image
	 * @return
	 */
	public static BufferedImage blurImage16(final BufferedImage image) {
		final float ninth = 1.0f / 16.0f;
		final float[] blurKernel = {
				ninth, ninth, ninth, ninth, ninth, ninth, ninth, ninth, ninth, ninth, ninth, ninth, ninth, ninth, ninth, ninth
		};

		final Map<RenderingHints.Key, Object> map = new HashMap<RenderingHints.Key, Object>();
		map.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		map.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		map.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		final RenderingHints hints = new RenderingHints(map);
		final BufferedImageOp op = new ConvolveOp(new Kernel(4, 4, blurKernel), ConvolveOp.EDGE_NO_OP, hints);
		return op.filter(image, null);
	}

	/**
	 * Blurrs an {@link BufferedImage}
	 * 
	 * @param image
	 * @return
	 */
	public static BufferedImage blurImage25(final BufferedImage image) {
		final float ninth = 1.0f / 21.0f; // should be 25
		final float[] blurKernel = {
				ninth, ninth, ninth, ninth, ninth, //
				ninth, ninth, ninth, ninth, ninth, //
				ninth, ninth, ninth, ninth, ninth, //
				ninth, ninth, ninth, ninth, ninth, //
				ninth, ninth, ninth, ninth, ninth
		};

		final Map<RenderingHints.Key, Object> map = new HashMap<RenderingHints.Key, Object>();
		map.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		map.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		map.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		final RenderingHints hints = new RenderingHints(map);
		final BufferedImageOp op = new ConvolveOp(new Kernel(5, 5, blurKernel), ConvolveOp.EDGE_NO_OP, hints);
		return op.filter(image, null);
	}

	/**
	 * Blurrs an {@link BufferedImage}
	 * 
	 * @param image
	 * @return
	 */
	public static BufferedImage blurImage25a(final BufferedImage image) {
		final float ninth = 1.0f / 13.0f; // should be 13
		final float[] blurKernel = {
				0, 0, ninth, 0, 0, //
				0, ninth, ninth, ninth, 0, //
				ninth, ninth, ninth, ninth, ninth, //
				0, ninth, ninth, ninth, 0, //
				0, 0, ninth, 0, 0
		};

		final Map<RenderingHints.Key, Object> map = new HashMap<RenderingHints.Key, Object>();
		map.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		map.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		map.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		final RenderingHints hints = new RenderingHints(map);
		final BufferedImageOp op = new ConvolveOp(new Kernel(5, 5, blurKernel), ConvolveOp.EDGE_NO_OP, hints);
		return op.filter(image, null);
	}

	/**
	 * Blurrs an {@link BufferedImage}
	 * 
	 * @param image
	 * @return
	 */
	public static BufferedImage blurImage25b(final BufferedImage image, final int intensity) {
		final float ninth = 1.0f / (13.0f - intensity); // should be 13
		final float[] blurKernel = {
				0, 0, ninth, 0, 0, //
				0, ninth, ninth, ninth, 0, //
				ninth, ninth, ninth, ninth, ninth, //
				0, ninth, ninth, ninth, 0, //
				0, 0, ninth, 0, 0
		};

		final Map<RenderingHints.Key, Object> map = new HashMap<RenderingHints.Key, Object>();
		map.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		map.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		map.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		final RenderingHints hints = new RenderingHints(map);
		final BufferedImageOp op = new ConvolveOp(new Kernel(5, 5, blurKernel), ConvolveOp.EDGE_NO_OP, hints);
		return op.filter(image, null);
	}

	/**
	 * Don't know what this does, but it is needed for advanced rezise ;-)
	 * 
	 * @param image
	 * @return
	 */
	private static BufferedImage createCompatibleImage(final BufferedImage image) {
		final GraphicsConfiguration gc = BufferedImageGraphicsConfig.getConfig(image);
		final int w = image.getWidth();
		final int h = image.getHeight();
		final BufferedImage result = gc.createCompatibleImage(w, h, Transparency.TRANSLUCENT);
		final Graphics2D g2 = result.createGraphics();
		g2.drawRenderedImage(image, null);
		g2.dispose();
		return result;
	}

	// ####################################################################
	// Image Flipping
	// ####################################################################

	public static BufferedImage horizontalflip(final BufferedImage img) {
		final int w = img.getWidth();
		final int h = img.getHeight();
		final BufferedImage dimg = new BufferedImage(w, h, img.getType());
		final Graphics2D g = dimg.createGraphics();
		g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
		g.dispose();
		return dimg;
	}

	public static BufferedImage verticalflip(final BufferedImage img) {
		final int w = img.getWidth();
		final int h = img.getHeight();
		final BufferedImage dimg = new BufferedImage(w, h, img.getColorModel().getTransparency());
		final Graphics2D g = dimg.createGraphics();
		g.drawImage(img, 0, 0, w, h, 0, h, w, 0, null);
		g.dispose();
		return dimg;
	}

	public static BufferedImage rotate(final BufferedImage img, final int angle) {
		final int w = img.getWidth();
		final int h = img.getHeight();
		final BufferedImage dimg = new BufferedImage(w, h, img.getType());
		final Graphics2D g = dimg.createGraphics();
		g.rotate(Math.toRadians(angle), w / 2, h / 2);
		g.drawImage(img, null, 0, 0);
		g.dispose();
		return dimg;
	}

	public static BufferedImage fadeImage(final BufferedImage img) {
		final BufferedImage reflection = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		final Graphics2D g = reflection.createGraphics();
		g.drawRenderedImage(img, null);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
		g.setPaint(new GradientPaint(0, Math.round(img.getHeight() * 8f / 10f), new Color(0.0f, 0.0f, 0.0f, 0.0f), 0, 0, new Color(0.0f, 0.0f, 0.0f, 0.5f)));

		g.fillRect(0, 0, img.getWidth(), img.getHeight());
		g.dispose();
		return reflection;
	}

	public static BufferedImage createReflectionImage(final BufferedImage img, final boolean blur) {
		BufferedImage flip = StaticImageHelper.verticalflip(img);
		if (blur)
			flip = StaticImageHelper.blurImage(flip);
		flip = StaticImageHelper.fadeImage(flip);
		return flip;
	}

}
