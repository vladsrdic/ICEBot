package commands;

import java.util.Random;

import net.dv8tion.jda.events.message.MessageReceivedEvent;
import utils.Command;
import utils.Main;

/**
 * A command for PDCBot to be used for rolling dice in chat.
 * 		- Format: !roll XdY Z where X is the number of dice, positive, and Y
 * 			is the number of sides on the dice, positive. Z is the modifier to be
 * 			applied to the total rolled by the dice, can be positive or negative.
 * @author Vladimir Srdic
 *
 */
public class RollCommand implements Command {
	private final String HELP = " USAGE: !roll <number>d<sides> <modifier> || Whole, Positive numbers only!";
	private final String MAX = " Max dice: 20 || Max sides: 100 || Max modifier: 100";

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		String author = event.getAuthor().getAsMention();

		try {
			if (args.length == 2) {
				String[] dice;
				if (args[0].contains("d")) {
					dice = args[0].split("d");
				} else {
					event.getTextChannel().sendMessage(author + HELP);
					return false;
				}

				Integer.parseInt(dice[0]);
				Integer.parseInt(dice[1]);
				Integer.parseInt(args[1]);
				return true;
			} else if (args.length == 1) {
				String[] dice;
				if (args[0].contains("d")) {
					dice = args[0].split("d");
				} else {
					event.getTextChannel().sendMessage(author + HELP);
					return false;
				}

				Integer.parseInt(dice[0]);
				Integer.parseInt(dice[1]);
				return true;
			} else if (args.length == 0 || args.length > 2) {
				event.getTextChannel().sendMessage(author + HELP);
				return false;
			}

		} catch (NumberFormatException e) {
		}
		event.getTextChannel().sendMessage(author + HELP);
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		String author = event.getAuthor().getAsMention();
		try {
			Random rand = new Random();
			String[] dice = args[0].split("d");

			int n_dice = Integer.parseInt(dice[0]);
			int n_sides = Integer.parseInt(dice[1]);
			int mod = args.length == 1 ? 0 : Integer.parseInt(args[1]);
			
			if (n_dice <= 0 || n_sides <= 0) {
				throw new IllegalArgumentException("Negative number found");
			}
			
			if (n_dice > 20 || n_sides > 100 || mod > 100) {
				event.getTextChannel().sendMessage(author + MAX);
				return;
			}

			int rollTotal = 0;
			String stringRolls = "";
			for (int rolled = 1; rolled <= n_dice; rolled++) {
				int roll = rand.nextInt(n_sides) + 1;
				stringRolls += roll + ", ";
				rollTotal += roll;
			}

			rollTotal += mod;

			event.getTextChannel().sendMessage(author + " you rolled " + args[0] + ", the results are: " + stringRolls
					+ " with a modifier of " + mod + " for a total of " + rollTotal);
			Main.log("command", "Roll command issued by " + event.getAuthorName() + ".");
		} catch (IllegalArgumentException e) {
			event.getTextChannel().sendMessage(author + HELP);
		}
	}

	@Override
	public String help() {
		return HELP;
	}

	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

}
