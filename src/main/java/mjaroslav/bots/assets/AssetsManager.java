package mjaroslav.bots.assets;

import java.net.URL;

import javax.swing.ImageIcon;

public class AssetsManager {
	public static ImageIcon getLamp(String id) {
		if (id.equals(","))
			return dot();
		if (id.equals(" "))
			return space();
		return get(id);
	}

	public static URL getClientSecret() {
		return AssetsManager.class.getResource("client_secret.json");
	}

	public static ImageIcon get(int value) {
		return new ImageIcon(AssetsManager.class.getResource(value + ".png"));
	}

	public static ImageIcon get(String value) {
		return new ImageIcon(AssetsManager.class.getResource(value + ".png"));
	}

	public static ImageIcon icon() {
		return new ImageIcon(AssetsManager.class.getResource("icon.png"));
	}

	public static ImageIcon dot() {
		return new ImageIcon(AssetsManager.class.getResource("dot.png"));
	}

	public static ImageIcon space() {
		return new ImageIcon(AssetsManager.class.getResource("space.png"));
	}
}
