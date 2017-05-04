package utils;

import java.util.ArrayList;

import net.dv8tion.jda.events.message.MessageReceivedEvent;

/**
 * Splits a command into its various parts. Used to get parts of a command.
 * @author Vladimir Srdic
 *
 */
public class CommandParser {
	
	/**
	 * Parses the given command and puts it into a CommandContainer. This container
	 * holds the command invoked, the arguments passed by the user, and the event
	 * being used by the bot.
	 * @param rw - Raw data of the command.
	 * @param e - Command event
	 * @return - CommandContainer holding a parsed command.
	 */
	public CommandContainer parse(String rw, MessageReceivedEvent e) {
		ArrayList<String> split = new ArrayList<String>();
		String raw = rw;
		String beheaded = raw.replaceFirst("!", "");
		String[] splitBeheaded = beheaded.split(" ");
		for(String s : splitBeheaded) {
			split.add(s);
		}
		String invoke = split.get(0);
		String[] args = new String[split.size() - 1];
		split.subList(1, split.size()).toArray(args);
		
		return new CommandContainer(invoke, args, e);
	}
	
	/**
	 * Class representation of a container that holds the command "invoke", command
	 * arguments, and the command event.
	 * @author Vladimir Srdic
	 *
	 */
	public class CommandContainer {
		public final String invoke;
		public final String[] args;
		public final MessageReceivedEvent event;
		
		public CommandContainer(String invoke, String[] args, MessageReceivedEvent e) {
			this.invoke = invoke;
			this.args = args;
			this.event = e;
		}
	}
}
