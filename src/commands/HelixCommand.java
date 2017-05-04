package commands;

import java.util.Random;

import net.dv8tion.jda.events.message.MessageReceivedEvent;
import utils.Command;
import utils.Main;

/**
 * Acts as a psuedo Helix fossil.
 * 		- Format: !jesus <question>
 * @author Vladimir Srdic
 */
public class HelixCommand implements Command {
	private static final String HELP = "USAGE: !Jesus <your question>";
	private static final String[] responses = { "It is certain", "It is decidedly so", "Without a doubt",
			"Yes definitely", "You may rely on it", "As I see it, yes", "Most likely", "Outlook good", "Yes",
			"Signs point to yes", "Reply hazy try again", "Ask again later", "Better not tell you now",
			"Cannot predict now", "Concentrate and ask again ", "Don't count on it", "My reply is no",
			"My sources say no", "Outlook not so good", "Very doubtful", "no.", "START", "A", "B", "UP", "DOWN", "LEFT",
			"RIGHT", "SELECT", "Helix will remember that.", "X", "Y", "Never ask again", "Out for lunch" };

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		boolean val = args.length == 0 ? false : true;

		if (!val) {
			event.getTextChannel().sendMessage(HELP);
		}
		return val;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Random rand = new Random();
		String author = event.getAuthor().getAsMention();
		int responseVal = rand.nextInt(responses.length);
		event.getTextChannel().sendMessage(author + ":shell: Helix says: " + responses[responseVal] + " :shell:");
		Main.log("command", "Helix command issued by " + event.getAuthorName() + ".");
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
