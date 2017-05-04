package utils;

import net.dv8tion.jda.events.ReadyEvent;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

/**
 * A listener for the bot to be able to react to messages sent.
 * @author Vladimir Srdic
 *
 */
public class BotListener extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.isPrivate() && event.getMessage().getAuthor().getId() != event.getJDA().getSelfInfo().getId()) {
			event.getPrivateChannel().sendMessage("Private commands coming soon! Please use all commands in a server that PDCBot is in!");
		}
		
		if (event.getMessage().getContent().startsWith("!") && event.getMessage().getAuthor().getId() != event.getJDA().getSelfInfo().getId()) {
			Main.handleCommand(Main.parser.parse(event.getMessage().getContent().toLowerCase(), event));
		}	
	}
	
	@Override
	public void onReady(ReadyEvent event) {
		//Main.log("status", "Logged in as " + event.getJDA().getSelfInfo().getUsername());
	}
}
