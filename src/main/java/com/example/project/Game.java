package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Game(int size){ //the constructor should call initialize() and play()
    }

    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){ //write your game logic here
        Scanner scanner = new Scanner(System.in);


        while(true){
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop

     
            }
            
     
    }

    public void initialize(int playerChoice){

        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
        if (playerChoice == 1)
        {
            Grid playerGrid = new Grid(playerChoice);
        }

        if (playerChoice == 2)
        {
        }

        if (playerChoice == 3)
        {
        }
        


    }

    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose Difficulty: ");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Good Luck");
        int playerDifficulty = scan.nextInt();
        Game g = new Game(0);
        if (playerDifficulty == 1)
        {
            g = new Game(10);
        }

        if (playerDifficulty == 2)
        {
            g = new Game(20);
        }

        if (playerDifficulty == 3)
        {
            g = new Game(30);
        }
        g.initialize(playerDifficulty);
        
    }
}