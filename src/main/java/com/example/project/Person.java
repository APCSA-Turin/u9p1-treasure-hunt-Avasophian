package com.example.project;
import java.util.Scanner;

public class Person extends Sprite
{
    private int whichPerson;
    public Person(int x, int y, int whichPerson) 
    {
        super(x, y);
        this.whichPerson = whichPerson;
    }

    public int getWhich()
    {
        return whichPerson;
    }

    public void talk()
    {
        if (whichPerson == 1)
        {
            Scanner scan = new Scanner(System.in);
            System.out.print("You may ask how you can open that door in front of me without a key.");
            scan.nextLine();
            System.out.print("To do that, you must solve my riddles...");
            scan.nextLine();
            System.out.print("one.");
            scan.nextLine();
            System.out.print("(What, you thought I had the time to code three riddles? This project is due on Friday.)");
            scan.nextLine();
            System.out.print("Anyway.");
            scan.nextLine();
            System.out.println("I am an odd number. Take away a letter and I become even. What number am I?");
            while (true)
            {
                String playerAnswer = scan.nextLine();
                if (playerAnswer.equalsIgnoreCase("seven") || playerAnswer.equals("7")) 
                {
                    break;
                }
                System.out.println("Not quite.");
            }
            System.out.println("Correct! The answer is seven. I'll open the door for you.");
            scan.nextLine();
        }

        if (whichPerson == 2)
        {
            Scanner scan = new Scanner(System.in);
            System.out.print("Hey! Behind that magical wall there's a fire, so I casted a spell to block it off for now.");
            scan.nextLine();
            System.out.print("I can undo the spell, but you need to solve a riddle first. Also, you need water to extinguish it. So uh... hope you have that.");
            scan.nextLine();
            System.out.println("I start with the letter e, I end with the letter e. I contain only one letter, Yet I am not the letter e! What am I?");
            while (true)
            {
                String playerAnswer = scan.nextLine();
                if (playerAnswer.equalsIgnoreCase("an envelope") || playerAnswer.equalsIgnoreCase("envelope")) 
                {
                    break;
                }
                System.out.println("Not quite.");
            }
            System.out.println("Correct! The answer is an envelope. I'll undo the spell for you.");
            scan.nextLine();
        }

    }
}
