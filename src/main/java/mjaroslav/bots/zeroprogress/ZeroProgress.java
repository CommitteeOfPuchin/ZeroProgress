package mjaroslav.bots.zeroprogress;

import java.io.File;

import mjaroslav.bots.zeroprogress.utils.JSONReader;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

public class ZeroProgress {
	public static IDiscordClient client;

	public static JSONReader<JSONAuth> readerAuth = new JSONReader<JSONAuth>(new JSONAuth(), new File("auth.json"),
			true);
	public static JSONReader<JSONConfig> readerConfig = new JSONReader<JSONConfig>(new JSONConfig(),
			new File("config.json"), true);

	public static boolean single = false;
	public static Thread checker = new CheckerThread();

	public static void main(String... args) {
		if (args.length > 0 && args[0].toLowerCase().equals("single"))
			single = true;
		checker.setDaemon(true);
		readerAuth.init();
		readerConfig.init();
		GoogleSheets.init();
		if (getAuth().botToken != null && !getAuth().botToken.equals("")) {
			login();
			client.getDispatcher().registerListener(new EventHandler());
		}
	}

	public static JSONAuth getAuth() {
		return readerAuth.json;
	}

	public static JSONConfig getConfig() {
		return readerConfig.json;
	}

	public static void login() {
		client = new ClientBuilder().withToken(getAuth().botToken).login();
	}

	public static void newMessage(long id) {
		readerConfig.json.messageId = id;
		readerConfig.write();
	}
}
