package com.example.project;
import java.util.Scanner;

public class SpecialEnemy extends Enemy 
{
    private boolean angryOrNot = false;
    public SpecialEnemy(int x, int y) 
    {
        super(x, y);
    }

    public boolean getAngryOrNot()
    {
        return angryOrNot;
    }

    public void talk()
    {
        System.out.println("If you want to be let through I mean I guess I can lol. ");
        System.out.println("But likeee if I don't like you I can just kill you LOL. ");
        System.out.print("So uhh think wisely I guess. ");
    
        Scanner scan = new Scanner(System.in);
        scan.nextLine();

        System.out.println("Your choices: 1. tell him you hate him. 2. tell him ur free saturday and Ask if he is too(Alright man😭) 3. tell him u mean no harm");
        int playerChoice = scan.nextInt();
        if (playerChoice == 2)
        {
            System.out.println("keep it school appropriate. and no, i am NOT free saturday.");
            System.out.println("but ok i guess you can pass. ");
            System.out.println("(this is YOUR fault for choosing this option btw did YOU expect a dating simulator????)");
            System.out.print("(i mean like it wouldnt be unusual for me to code something like that but.)");
            scan.nextLine();
            System.out.println("(ANYWAY.)");
            scan.nextLine();
            System.out.println();
            angryOrNot = true;
        }
       else if (playerChoice == 3)
        {
            System.out.print("i mean yeah but arent you trying to steal my treasure bro...");
            scan.nextLine();
            System.out.print("you: it's YOURS!?!?!?!?!?!?!?!?");
            scan.nextLine();
            System.out.print("yeah bro. do u not read ur job descriptions... you took this job to steal MY treasure.");
            scan.nextLine();
            System.out.print("i mean like its chill. i have 100 other dungeons anyway. but like keep up dude. why do u want it anyway?");
            scan.nextLine();
            System.out.println("Your choices: 1. tell him Ur broke 2. lie");
            playerChoice = scan.nextInt();
            if (playerChoice == 1)
            {
                System.out.println("okay ill help out.");
                scan.nextLine();
                System.out.println();
                angryOrNot = true;
            }

            else
            {
                System.out.println("DONT LIE.");
                scan.nextLine();
                System.out.println();
            }
        }
        else
        {
            System.out.println("...you obviously die");
            scan.nextLine();
            System.out.println();
        }
    }

}
