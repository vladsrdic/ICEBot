package commands;

import net.dv8tion.jda.events.message.MessageReceivedEvent;
import utils.Command;
import utils.Main;

/**
 * Simply links to reddit's random NSFW link.
 * 		- Format: !randnsfw
 * @author Vladimir Srdic
 *
 */
public class PingCommand implements Command {
	private final String HELP = "USAGE: !randnsfw";

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		String author = event.getAuthor().getAsMention();
		event.getTextChannel().sendMessage("http://www.reddit.com/r/randnsfw");
		Main.log("command", "Randnsfw command issued by " + event.getAuthorName() + ".");
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
