package mjaroslav.bots.zeroprogress;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;

import mjaroslav.bots.assets.AssetsManager;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.util.EmbedBuilder;

public class Utils {
	private static final int count = 12;

	public static String getNumbers() {
		List<List<Object>> res = GoogleSheets.getFrom(ZeroProgress.getConfig().docId, ZeroProgress.getConfig().listId,
				ZeroProgress.getConfig().range);
		String answer = "";
		for (List<Object> list : res)
			for (Object obj : list)
				if (obj instanceof String)
					answer += (String) obj;
		return answer;
	}

	public static EmbedObject createEmbet(String numbers) {
		return new EmbedBuilder().withAuthorName(ZeroProgress.getConfig().title).withDesc(ZeroProgress.getConfig().text)
				.withAuthorUrl(ZeroProgress.getConfig().titleUrl).withThumbnail(ZeroProgress.getConfig().image)
				.withTitle(ZeroProgress.getConfig().subTitle).withUrl(ZeroProgress.getConfig().subTitleUrl)
				.withImage(createAndUploadImage(numbers)).withColor(Utils.getColor(ZeroProgress.getConfig().color))
				.withTimestamp(new Date().getTime()).build();
	}

	public static Color getColor(String hex) {
		int colorInt;
		boolean hasAlpha = false;
		int alpha = 255;
		if (hex.length() == 8)
			hasAlpha = true;
		try {
			if (hasAlpha) {
				colorInt = Integer.parseInt(hex.substring(2, 8), 16);
				alpha = Integer.parseInt(hex.substring(0, 2), 16);
			} else
				colorInt = Integer.parseInt(hex, 16);
		} catch (NumberFormatException e) {
			colorInt = 0x000000;
		}
		Color temp = new Color(colorInt);
		return hasAlpha ? new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), alpha) : temp;
	}

	public static String createAndUploadImage(String numbers) {
		BufferedImage temp = new BufferedImage(40 * count, 100, BufferedImage.TYPE_INT_ARGB);
		String value = numbers;
		if (value.length() == 1)
			value += ",";
		while (value.length() < count)
			value += "0";
		Graphics2D g = temp.createGraphics();
		for (int id = 0; id < count; id++) {
			String s = String.valueOf(value.charAt(id)).replace(",", "dot").replace(".", "dot").replace(" ", "space");
			Image img = AssetsManager.getLamp(s).getImage();
			g.drawImage(img, id * 40, 0, null);
		}
		g.dispose();
		return ImgurUtils.upload(temp);
	}
}
