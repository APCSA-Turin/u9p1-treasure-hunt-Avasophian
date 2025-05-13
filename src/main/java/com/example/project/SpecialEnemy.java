package com.example.project;
import java.util.Scanner;

public class SpecialEnemy extends Enemy 
{
    //Instance variable, tracks whether you've upset the special enemy by conversing with it.
    private boolean angryOrNot = false;

    //Constructor, takes x and y coordinates.
    public SpecialEnemy(int x, int y) 
    {
        super(x, y);
    }

    //Returns the anger of the enemy.
    public boolean getAngryOrNot()
    {
        return angryOrNot;
    }

    //Simulates a conversation between the player and the enemy, with different choices based on player input.
    public void talk()
    {
        System.out.println("If you want to be let through I mean I guess I can lol. ");
        System.out.println("But likeee if I don't like you I can just kill you LOL. ");
        System.out.print("So uhh think wisely I guess. ");
        String pauser = "";

        Scanner scan = new Scanner(System.in);
        scan.nextLine();

        System.out.println("Your choices: 1. tell him you hate him.\n 2. ask for a hug\n 3. tell him u mean no harm");
        int playerChoice = scan.nextInt();
        if (playerChoice == 2)
        {
            System.out.println("Why would I give you a hug???");
            System.out.println("... but I guess you can pass. ");
            pauser = scan.nextLine();
            System.out.println();
            angryOrNot = true;
        }
       else if
        (playerChoice == 3)
        {
            System.out.print("i mean yeah but arent you trying to steal my treasure bro...");
            scan.nextLine();
            System.out.print("you: it's YOURS!?!?!?!?!?!?!?!?");
            scan.nextLine();
            System.out.print("yeah bro. do u not read ur job descriptions... you took this job to steal MY treasure.");
            scan.nextLine();
            System.out.print("i mean like its chill. i have 100 other dungeons anyway. but like keep up dude. why do u want it anyway?");
            scan.nextLine();
            System.out.println("Your choices: 1. tell him the truth 2. lie");
            playerChoice = scan.nextInt();
            if (playerChoice == 1)
            {
                System.out.println("okay ill help out.");
                pauser = scan.nextLine();
                System.out.println();
                angryOrNot = true;
            }

            else
            {
                System.out.println("DONT LIE.");
                pauser = scan.nextLine();
                System.out.println();
            }
        }
        else
        {
            System.out.println("...you obviously die");
            pauser = scan.nextLine();
            System.out.println();
        }
    }

}
