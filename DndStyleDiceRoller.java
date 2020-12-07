import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DndStyleDiceRoller
{
  public static void main(String[] args)
  {
    try (Scanner scanner = new Scanner(System.in))
    {
      boolean keepRolling = true;
      while (keepRolling)
      {
        printInstructions();
        String input = scanner.next();

        if (finishedRolling(input))
          keepRolling = false;
        else if (!validInput(input))
          System.out.println("Bad input. Please try again\n");
        else
        {
          int numberOfDice = getNumberOfDice(input);
          int diceType = getDiceType(input);

          if (numberOfDice < 1)
            System.out.println("Invalid number of dice: [" + numberOfDice + "]. Please try again\n");
          else if (diceType < 2)
            System.out.println("Invalid dice type: [d" + diceType + "]. Please try again\n");
          else
          {
            rollDice(diceType, numberOfDice);
          }
        }
      }
    }
    catch (NumberFormatException e)
    {
      System.out.println("Invalid number: " + e.getMessage().substring(e.getMessage().indexOf(": ") + 2));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public static void printInstructions()
  {
    System.out.println("How many dice do you want to roll of a given type?");
    System.out.println("Enter in the form of NdM ('3d6' would roll a 6 sided die 3 times," +
                       " '4d10' would roll a 10 sided die 4 times, etc.)");
    System.out.println("You can enter multiple types to roll, just separate them with spaces");
    System.out.println("(Type 'quit' to stop...)");
    System.out.print("> ");
  }

  public static boolean finishedRolling(String input)
  {
    return input.toLowerCase().strip().equals("quit");
  }

  public static boolean validInput(String input)
  {
    Pattern pattern = Pattern.compile("\\d+[d]\\d+");
    Matcher matcher = pattern.matcher(input);
    return (input != null && input != "" && matcher.matches());
  }

  public static int getNumberOfDice(String input)
  {
    return Integer.valueOf(input.substring(0, input.indexOf('d')));
  }

  public static int getDiceType(String input)
  {
    return Integer.valueOf(input.substring(input.indexOf('d') + 1));
  }

  public static int rollDice(int diceType, int numberOfDice)
  {
    System.out.println("Rolling a d" + diceType + " " + numberOfDice + " time(s)");
    int total = 0;
    for (int i = 0; i < numberOfDice; i++)
    {
      Random rand = new Random();
      int score = rand.nextInt(diceType) + 1;
      System.out.println("Roll #" + (i + 1) + " = " + score);
      total += score;
    }
    System.out.println("Total = " + total + "\n");
    return total;
  }
}
