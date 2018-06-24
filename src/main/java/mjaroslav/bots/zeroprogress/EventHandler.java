package mjaroslav.bots.zeroprogress;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageSendEvent;
import sx.blah.discord.handle.obj.Permissions;

public class EventHandler {
	@EventSubscriber
	public void onReady(ReadyEvent event) {
		if (ZeroProgress.single) {
			if (ZeroProgress.client.getMessageByID(ZeroProgress.getConfig().messageId) != null)
				ZeroProgress.client.getMessageByID(ZeroProgress.getConfig().messageId)
						.edit(Utils.createEmbet(Utils.getNumbers()));
			ZeroProgress.client.logout();
		} else
			ZeroProgress.checker.start();
	}

	@EventSubscriber
	public void onMessageEvent(MessageReceivedEvent event) {
		if (event.getAuthor() != null && event.getGuild() != null
				&& event.getAuthor().getPermissionsForGuild(event.getGuild()).contains(Permissions.ADMINISTRATOR))
			if (checkCommand(event, ZeroProgress.getConfig().commands.setMessage)) {
				if (ZeroProgress.client.getMessageByID(ZeroProgress.getConfig().messageId) != null)
					ZeroProgress.client.getMessageByID(ZeroProgress.getConfig().messageId).delete();
				ZeroProgress.client.getChannelByID(event.getChannel().getLongID())
						.sendMessage(Utils.createEmbet(Utils.getNumbers()));
				if (ZeroProgress.getConfig().status)
					ZeroProgress.client.changePlayingText(
							ZeroProgress.getConfig().statusText.replace("{TIME}", Utils.getNumbers()));
			} else if (checkCommand(event, ZeroProgress.getConfig().commands.update)) {
				ZeroProgress.readerConfig.read();
			} else if (checkCommand(event, ZeroProgress.getConfig().commands.recheck)) {
				if (ZeroProgress.client.getMessageByID(ZeroProgress.getConfig().messageId) != null) {
					ZeroProgress.client.getMessageByID(ZeroProgress.getConfig().messageId)
							.edit(Utils.createEmbet(Utils.getNumbers()));
					if (ZeroProgress.getConfig().status)
						ZeroProgress.client.changePlayingText(
								ZeroProgress.getConfig().statusText.replace("{TIME}", Utils.getNumbers()));
				} else {
					ZeroProgress.client.getChannelByID(event.getChannel().getLongID())
							.sendMessage(Utils.createEmbet(Utils.getNumbers()));
					if (ZeroProgress.getConfig().status)
						ZeroProgress.client.changePlayingText(
								ZeroProgress.getConfig().statusText.replace("{TIME}", Utils.getNumbers()));
				}
			} else if (checkCommand(event, ZeroProgress.getConfig().commands.exit)) {
				CheckerThread.flag = false;
				ZeroProgress.client.logout();
			}
	}

	@EventSubscriber
	public void onMessageSendEvent(MessageSendEvent event) {
		if (event.getMessage().getEmbeds().size() > 0)
			ZeroProgress.newMessage(event.getMessageID());
	}

	public static boolean checkCommand(MessageReceivedEvent event, String command) {
		return event != null && event.getMessage() != null && event.getMessage().getContent() != null && event
				.getMessage().getContent().toLowerCase().equals(ZeroProgress.getConfig().commands.prefix + command);
	}
}
