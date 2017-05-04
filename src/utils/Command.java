package utils;

import net.dv8tion.jda.events.message.MessageReceivedEvent;

/**
 * Used in the quick construction of new commands.
 * @author Vladimir Srdic
 *
 */
public interface Command {

	/**
	 * Used to determine if the user-given arguments are viable for this command.
	 * @param args - The arguments passed by the user.
	 * @param event - The message event to be read by the bot.
	 * @return - True if the arguments fit this command, false otherwise.
	 */
	public boolean called(String[] args, MessageReceivedEvent event);
	
	/**
	 * The actual execution of this command. Should not be called unless
	 * the "called" command is called first.
	 * @param args - The arguments passed by the user.
	 * @param event - The message event to be read by the bot.
	 */
	public void action(String[] args, MessageReceivedEvent event);
	
	/**
	 * A help message to be given to the user if the command is used incorrectly.
	 * @return - The HELP message of this command.
	 */
	public String help();
	
	/**
	 * Steps to be taken after a command is properly executed, used for logging
	 * or cleaning up.
	 * @param success - Whether the command succeeded to execute.
	 * @param event - The message event that was part of the command.
	 */
	public void executed(boolean success, MessageReceivedEvent event);
	
}
