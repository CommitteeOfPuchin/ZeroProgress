package mjaroslav.bots.zeroprogress;

import com.google.gson.annotations.SerializedName;

public class JSONAuth {
	@SerializedName("bot_token")
	public String botToken = "";

	@SerializedName("imgur_id")
	public String imgurId;
}
