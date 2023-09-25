// VLADIMIR SRDIC IS ME
package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.security.auth.login.LoginException;

import commands.EmoteCommand;
import commands.HelixCommand;
import commands.PingCommand;
import commands.RollCommand;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;

public class Main {

	private static JDA jda;
	public static final CommandParser parser = new CommandParser();

	public static HashMap<String, Command> commands = new HashMap<String, Command>();

	/**
	 * Sets up the JDA backing the bot as well as giving commands a proper name.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			jda = new JDABuilder().addListener(new BotListener())
					.setBotToken("Please make me an ENV").buildBlocking();
			jda.setAutoReconnect(true);
		} catch (IllegalArgumentException e) {
			System.out.println("The config was not populated. Please enter a bot token.");
		} catch (LoginException e) {
			System.out.println("The provided bot token was incorrect. Please provide valid details.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		log("status", "Bot loaded and online.");

		commands.put("roll", new RollCommand());
		commands.put("helix", new HelixCommand());
		commands.put("emoteme", new EmoteCommand());
	}

	/**
	 * Called to handle a command when it is called.
	 * 
	 * @param cmd
	 *            - A container holding all of the arguments parsed by
	 *            CommandParser from the user. See CommandParser/Container
	 *            javadoc.
	 */
	public static void handleCommand(CommandParser.CommandContainer cmd) {
		String inv = cmd.invoke;

		if (commands.containsKey(inv)) {
			boolean safe = commands.get(inv).called(cmd.args, cmd.event);

			Command current = commands.get(inv);

			if (safe) {
				current.action(cmd.args, cmd.event);
				current.executed(safe, cmd.event);
			}
		}
	}

	public static void log(String type, String message) {
		GregorianCalendar today = new GregorianCalendar();
		int month = today.get(Calendar.MONTH) + 1;
		String date = month + "_" + today.get(Calendar.DAY_OF_MONTH) + "_" + today.get(Calendar.YEAR);
		File curFile = new File(date + ".txt");
		BufferedWriter bw;
		try {
			if (curFile.exists()) {
				bw = new BufferedWriter(new FileWriter(curFile, true));
			} else {
				bw = new BufferedWriter(new FileWriter(curFile));
			}

			switch (type) {
			case "status":
				bw.write(new Timestamp(System.currentTimeMillis()) + " - =Bot= - " + message + "\n");
				break;
			case "command":
				bw.write(new Timestamp(System.currentTimeMillis()) + " - [Command] - " + message + "\n");
				break;
			default:
				bw.write(new Timestamp(System.currentTimeMillis()) + " - {" + type + "} - " + message + "\n");
				break;
			}

			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
