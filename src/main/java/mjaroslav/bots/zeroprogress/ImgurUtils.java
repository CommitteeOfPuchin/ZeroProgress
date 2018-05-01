package mjaroslav.bots.zeroprogress;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ImgurUtils {
	public static String upload(BufferedImage image) {
		OutputStreamWriter wr = null;
		BufferedReader in = null;
		HttpURLConnection conn = null;
		ByteArrayOutputStream baos = null;
		try {
			URL e = new URL("https://api.imgur.com/3/upload.json");
			baos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", baos);
			baos.flush();
			byte[] imageInByte1 = baos.toByteArray();
			String encoded = Base64.encodeBase64String(imageInByte1);
			String data = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(encoded, "UTF-8") + '&'
					+ "type=png" + '&' + "title=pom";
			conn = (HttpURLConnection) e.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Client-ID " + ZeroProgress.getAuth().imgurId);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
			wr.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			JsonObject response = (new JsonParser()).parse(in).getAsJsonObject();
			return response.getAsJsonObject("data").getAsJsonPrimitive("link").getAsString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.close(conn);
			try {
				wr.close();
				in.close();
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
}
