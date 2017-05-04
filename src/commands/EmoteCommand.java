package commands;

import java.util.List;
import java.util.Random;

import net.dv8tion.jda.entities.Emote;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import utils.Command;
import utils.Main;

public class EmoteCommand implements Command {
	private static final String HELP = "USAGE: !emoteme";
	
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		String author = event.getAuthor().getAsMention();
		List<Emote> emotes = event.getGuild().getEmotes();
		Random r = new Random();
		int emoteNum = r.nextInt(emotes.size());
		event.getTextChannel().sendMessage(author + " " + emotes.get(emoteNum).getAsEmote());
		Main.log("command", "Random Emote command issued by " + event.getAuthorName() + ".");
	}

	@Override
	public String help() {
		return HELP;
	}

	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		return;
	}

}
