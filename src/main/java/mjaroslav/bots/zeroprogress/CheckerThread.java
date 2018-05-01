package mjaroslav.bots.zeroprogress;

public class CheckerThread extends Thread {
	public static boolean flag = true;

	@Override
	public void run() {
		while (flag) {
			if (ZeroProgress.client.getMessageByID(ZeroProgress.getConfig().messageId) != null)
				ZeroProgress.client.getMessageByID(ZeroProgress.getConfig().messageId).edit(Utils.createEmbet());
			try {
				sleep(ZeroProgress.getConfig().timer * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		ZeroProgress.client.logout();
	}
}
