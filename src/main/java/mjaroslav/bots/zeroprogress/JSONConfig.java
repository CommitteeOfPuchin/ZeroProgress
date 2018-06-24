package mjaroslav.bots.zeroprogress;

import com.google.gson.annotations.SerializedName;

public class JSONConfig {
	@SerializedName("message_id")
	public long messageId = 0;

	@SerializedName("timer")
	public int timer = 3600;

	@SerializedName("image")
	public String image = "https://i.imgur.com/shLGgEV.png";

	@SerializedName("title")
	public String title = "TransLab | Steins;Gate 0 — Operation Utgard/Zero";

	@SerializedName("title_url")
	public String titleUrl = "https://vk.com/translab";

	@SerializedName("sub_title")
	public String subTitle = "Прогресс перевода Steins;Gate 0";

	@SerializedName("sub_title_url")
	public String subTitleUrl = "https://docs.google.com/spreadsheets/d/1WWEX5R1BZkgLyvhEcZw2NEIlmle6cYn6MfMWwmmd01g/edit#gid=434948351";

	@SerializedName("text")
	public String text = "Лишь когда вы достигните показателя отклонения 1.000000, пред Вами разверзнутся Врата 0";

	@SerializedName("status")
	public boolean status = true;

	@SerializedName("status_text")
	public String statusText = "{TIME}";

	@SerializedName("doc_id")
	public String docId = "1WWEX5R1BZkgLyvhEcZw2NEIlmle6cYn6MfMWwmmd01g";

	@SerializedName("list_id")
	public String listId = "Вычисления";

	@SerializedName("range")
	public String range = "N115:O115";

	@SerializedName("color")
	public String color = "FF9600";

	@SerializedName("commands")
	public Commands commands = new Commands();

	public static class Commands {
		@SerializedName("prefix")
		public String prefix = "/";

		@SerializedName("setmessage")
		public String setMessage = "setmessage";

		@SerializedName("update")
		public String update = "update";

		@SerializedName("recheck")
		public String recheck = "recheck";

		@SerializedName("exit")
		public String exit = "exit";
	}
}
