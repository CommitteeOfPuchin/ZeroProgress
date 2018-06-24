package mjaroslav.bots.zeroprogress;

public class CheckerThread extends Thread {
	public static boolean flag = true;
	public static String numbers = "";

	@Override
	public void run() {
		while (flag) {
			numbers = Utils.getNumbers();
			if (ZeroProgress.client.getMessageByID(ZeroProgress.getConfig().messageId) != null) {
				ZeroProgress.client.getMessageByID(ZeroProgress.getConfig().messageId).edit(Utils.createEmbet(numbers));
			}
			if (ZeroProgress.getConfig().status)
				ZeroProgress.client
						.changePlayingText(ZeroProgress.getConfig().statusText.replace("{TIME}", Utils.getNumbers()));
			try {
				sleep(ZeroProgress.getConfig().timer * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		ZeroProgress.client.logout();
	}
}
