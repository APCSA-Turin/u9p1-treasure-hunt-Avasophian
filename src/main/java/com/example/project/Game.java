package com.example.project;
import java.util.Scanner;

public class Game{
    //Instance variables
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 
    private Sprite[] obstaclesOnlyAppearOnce;
    
    //Constructor method; calls (initialize).
    //Passes a scanner through as player input is required to play the game. 
    public Game(int playerDifficulty, Scanner scanner)
    {
        // Checks the value of playerdifficulty to see which grid size to use.
        if (playerDifficulty == 1)
        {
            size = 5;
        }
        else if (playerDifficulty == 2)
        {
            size = 7;
        }
        else if (playerDifficulty == 3)
        {
            size = 10;
        }
        //Calls initialize with playerDifficulty to initialize the grid.
        initialize(playerDifficulty);
        //Calls play to begin the game.
        play(scanner);
    }

    //Clears the screen. I do not know how this works.
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

    //A method that runs the game itself until the player wins or loses.
    public void play(Scanner scan)
    {

        while(true)
        {
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop
            //Places the player at the player's x and y coordinates.
            grid.placeSprite(player);
            //Displays the grid.
            grid.display();
            //prints a space in between the grid and the code, then prints the stats of the player using getter methods (ofc)
            System.out.println();
            System.out.println("Lives: " + player.getLives());
            System.out.println("Keys: " + player.getKeyCount());
            System.out.println("Treasure: " + player.getTreasureCount());
            System.out.println("Total Treasure: " + grid.getTotalTreasures());
            System.out.println("Empty Bucket?: " + player.getEmptyBucket());
            System.out.println("Filled Bucket?: " + player.getFilledBucket());
            System.out.println("Sword?: " + player.getSword());
            System.out.println(grid.getSprite(player.getRow(size), player.getX()).getCoords());

            //uses scanner to see the direction the player is moving in next
            String playerDirection = scan.nextLine();

            //Checks that the player can move in that direction using isValid() and isValid2()
            if (player.isValid(size, playerDirection) && player.isValid2(size, grid, playerDirection, grid.getTotalTreasures()))
            {
                //if the player can move, moves the player then calls interact()
                player.move(playerDirection);
                player.interact(size, playerDirection, grid.getTotalTreasures(), grid.getSprite(player.getRow(size), player.getX()));
                
                //There were errors with the dialogue not being visible after the player talks to a specialEnemy or person due to the clearscreen,
                // so I added this to add a "buffer"
                if (grid.getSprite(player.getRow(size), player.getX()) instanceof SpecialEnemy)
                {
                    scan.nextLine();
                }

                //Depending on which person player is interacting with, sets the door to a blank dot.
                //This is difficulty dependent and is used so that I can have certain pathways be unlocked when you interact with the people/
                if (grid.getSprite(player.getRow(size), player.getX()) instanceof Person)
                {
                    //Gets which person it is by finding the sprite at the player's location (as they've moved), and casting it to a person
                    Person p = (Person)grid.getSprite(player.getRow(size), player.getX());

                    //Each person has a "which" value to differentiate between them.
                    //This checks which person the player is interacting with, then removes the obstacle depending on which it is
                    //by setting it to a blank Dot.
                    if (p.getWhich() == 1)
                    {
                        grid.placeSprite(new Dot(4, 1));
                    }
                    else if (p.getWhich() == 2)
                    {
                        grid.placeSprite(new Dot(3, 5));
                    }
                }
                //Places the player in their new position.
                grid.placeSprite(player, playerDirection);
            }
            //Prints a win message and ends the loop if the player has won.
            if (player.getWin())
            {
                grid.win();
                break;
            }

            //Prints a win message and ends the loop if the player has died to a monster.
            if (player.getDead())
            {
                grid.gameover();
                break;
            }
        }
    }

    //Returns the player of the Game.
    public Player getPlayer()
    {
        return player; //returns player.
    }

    //Returns the grid of the Game.
    public Grid getGrid()
    {
        return grid; //returns grid.
    } 

    //Initializes the grid with Player, Dots, Walls, etc. Basically just sets the grid up for the player to play in.
    public void initialize(int playerChoice){
        if (playerChoice == 1)
        {
            //Initializes a temporary grid with the layout for the Easy difficulty. 
            //For now, only Dots and Walls are placed.
            Sprite[][] g = 
            {
                {new Wall(0, 4), new Wall(1, 4), new Dot(2, 4), new Wall(3, 4), new Wall(4, 4)},
                {new Wall(0, 3), new Dot(1, 3), new Dot(2, 3), new Dot(3, 3), new Wall(4, 3)},
                {new Dot(0, 2), new Dot(1, 2), new Dot(2, 2), new Dot(3, 2), new Dot(4, 2)},
                {new Wall(0, 1), new Dot(1, 1), new Wall(2, 1), new Dot(3, 1), new Wall(4, 1)},
                {new Wall(0, 0), new Dot(1, 0), new Dot(2, 0), new Dot(3, 0), new Wall(4, 0)},
            };

            //Initializes the grid variable, and then sets it to the temporary Grid g.
            grid = new Grid(5);
            grid.setGrid(g); 
            
            //Sets the total treasures. This is important because it is needed for the player to be able to win.
            grid.setTotalTreasures(3);

            //A list of enemies which will be added to the grid.
            Enemy[] eList = {new Enemy(1, 0)};

            //Sets enemies to that list, then adds the elements using placeSprite.
            enemies = eList;
            grid.placeSprite(enemies[0]);

            //A list of treasures which will be added to the grid.
            Treasure[] tList = {new Treasure(2, 4), new Treasure(4, 2), new Treasure(0, 2)};

            //Sets treasures to that list, then adds the elements using placeSprite.
            treasures = tList;
            for (int i = 0; i < treasures.length; i++)
            {
                grid.placeSprite(treasures[i]);
            }

            //Initializes the trophy variable.
            trophy = new Trophy(2, 2);

            //Adds trophy using placeSprite.
            grid.placeSprite(trophy);
            
            //Initializes the player variable.
            player = new Player(2, 0);

            //adds player using placeSprite. 
            grid.placeSprite(player);

            //sets the lives of the player to the lives for that map
            player.setLives(1);


        }

        if (playerChoice == 2)
        {
            //Initializes a temporary grid with the layout for the Medium difficulty. 
            //For now, only Dots and Walls are placed
            Sprite[][] g = 
            {
                {new Dot(0, 6), new Dot(1, 6), new Dot(2, 6), new Wall(3, 6), new Dot(4, 6), new Dot(5, 6), new Dot(6, 6)},
                {new Dot(0, 5), new Wall(1, 5), new Dot(2, 5), new Wall(3, 5), new Dot(4, 5), new Wall(5, 5), new Dot(6, 5)},
                {new Dot(0, 4), new Wall(1, 4), new Dot(2, 4), new Wall(3, 4), new Dot(4, 4), new Wall(5, 4), new Dot(6, 4)},
                {new Dot(0, 3), new Wall(1, 3), new Dot(2, 3), new Wall(3, 3), new Dot(4, 3), new Wall(5, 3), new Dot(6, 3)},
                {new Dot(0, 2), new Wall(1, 2), new Dot(2, 2), new Wall(3, 2), new Dot(4, 2), new Wall(5, 2), new Dot(6, 2)},
                {new Wall(0, 1), new Wall(1, 1), new Dot(2, 1), new Wall(3, 1), new Dot(4, 1), new Wall(5, 1), new Wall(6, 1)},
                {new Dot(0, 0), new Dot(1, 0), new Dot(2, 0), new Dot(3, 0), new Dot(4, 0), new Dot(5, 0), new Dot(6, 0)},
            };

            //Initializes the grid variable, and then sets it to the temporary Grid g.
            grid = new Grid(7);
            grid.setGrid(g);

            //Sets the total treasures. This is important because it is needed for the player to be able to win.
            grid.setTotalTreasures(1);

            //A list of miscellaneous Sprite classes (not enemies or treasures) which will be added to the grid.
            Sprite[] miscellaneous = {new Person(6, 0, 1), new Door(2, 1), new Door (4, 1), new Bucket(2, 5), new Key(4, 3), new Fire(4, 4), new Sword(5, 6), new Water(2, 6)};

            //Iterates through that list and adds them to the grid using placeSprite.
            for (int i = 0; i < miscellaneous.length; i++)
            {
                grid.placeSprite(miscellaneous[i]);
            }

            //A list of enemies which will be added to the grid.
            Enemy[] eList = {new Enemy(0, 4), new Enemy(6, 3)};

            //Sets enemies to that list, then adds the elements using placeSprite.
            enemies = eList;
            grid.placeSprite(enemies[0]);
            grid.placeSprite(enemies[1]);

            //A list of treasures which will be added to the grid.
            Treasure[] tList = {new Treasure(0, 2)};

            //Sets treasures to that list, then adds the elements using placeSprite.
            treasures = tList;
            grid.placeSprite(treasures[0]);

            //Initializes the trophy variable.
            trophy = new Trophy(6, 2);

            //Adds trophy using placeSprite.
            grid.placeSprite(trophy);

            //Initializes the player variable.
            player = new Player(2, 0);

            //adds player using placeSprite. 
            grid.placeSprite(player);

            //sets the lives of the player to the lives for that map
            player.setLives(1);

        }

        if (playerChoice == 3)
        {
             //Initializes a temporary grid with the layout for the Hard difficulty. (yes, I did this all manually...)
            //For now, only Dots and Walls are placed
            Sprite[][] g = 
            {
                {new Wall(0, 9), new Wall(1, 9), new Wall(2, 9), new Wall(3, 9), new Wall(4, 9), new Wall(5, 9), new Wall(6, 9), new Wall(7, 9), new Wall(8, 9), new Wall(9, 9)},
                {new Dot(0, 8), new Dot(1, 8), new Dot(2, 8), new Dot(3, 8), new Dot(4, 8), new Dot(5, 8), new Dot(6, 8), new Dot(7, 8), new Dot(8, 8), new Dot(9, 8)},
                {new Dot(0, 7), new Dot(1, 7), new Dot(2, 7), new Wall(3, 7), new Wall(4, 7), new Wall(5, 7), new Wall(6, 7), new Wall(7, 7), new Wall(8, 7), new Wall(9, 7)},
                {new Dot(0, 6), new Wall(1, 6), new Wall(2, 6), new Wall(3, 6), new Wall(4, 6), new Wall(5, 6), new Wall(6, 6), new Wall(7, 6), new Wall(8, 6), new Wall(9, 6)},
                {new Dot(0, 5), new Dot(1, 5), new Dot(2, 5), new Wall(3, 5), new Dot(4, 5), new Dot(5, 5), new Dot(6, 5), new Dot(7, 5), new Dot(8, 5), new Dot(9, 5)},
                {new Wall(0, 4), new Wall(1, 4), new Wall(2, 4), new Wall(3, 4), new Dot(4, 4), new Wall(5, 4), new Wall(6, 4), new Wall(7, 4), new Wall(8, 4), new Wall(9, 4)},
                {new Wall(0, 3), new Dot(1, 3), new Dot(2, 3), new Dot(3, 3), new Dot(4, 3), new Dot(5, 3), new Dot(6, 3), new Wall(7, 3), new Wall(8, 3), new Wall(9, 3)},
                {new Wall(0, 2), new Wall(1, 2), new Wall(2, 2), new Wall(3, 2), new Dot(4, 2), new Wall(5, 2), new Wall(6, 2), new Wall(7, 2), new Wall(8, 2), new Wall(9, 2)},
                {new Wall(0, 1), new Wall(1, 1), new Wall(2, 1), new Wall(3, 1), new Dot(4, 1), new Wall(5, 1), new Wall(6, 1), new Wall(7, 1), new Wall(8, 1), new Wall(9, 1)},
                {new Dot(0, 0), new Dot(1, 0), new Dot(2, 0), new Dot(3, 0), new Dot(4, 0), new Dot(5, 0), new Dot(6, 0), new Dot(7, 0), new Dot(8, 0), new Dot(9, 0)},
            };
            //Initializes the grid variable, and then sets it to the temporary Grid g.
            grid = new Grid(10);
            grid.setGrid(g);

            //Sets the total treasures. This is important because it is needed for the player to be able to win.
            grid.setTotalTreasures(2);

            //A list of enemies which will be added to the grid.
            Enemy[] eList = {new Enemy(1, 7), new SpecialEnemy(7, 0)};

            //Sets enemies to that list, then adds the elements using placeSprite.
            enemies = eList;
            grid.placeSprite(enemies[0]);
            grid.placeSprite(enemies[1]);

            //A list of treasures which will be added to the grid.
            Treasure[] tList = {new Treasure(2, 7), new Treasure(7, 3)};

            //Sets treasures to that list, then adds the elements using placeSprite.
            treasures = tList;
            grid.placeSprite(treasures[0]);
            grid.placeSprite(treasures[1]);

            //Initializes the trophy variable.
            trophy = new Trophy(9, 8);
            
            //Adds trophy using placeSprite.
            grid.placeSprite(trophy);

            //A list of miscellaneous Sprite classes (not enemies or treasures) which will be added to the grid.
            Sprite[] miscellaneous = {new Water(1, 3), new Fire(0, 5), new Person(9, 5, 2), new Door(4, 1), new Bucket(0, 0), new Key(9, 0)};

            //Iterates through that list and adds them to the grid using placeSprite.
            for (int i = 0; i < miscellaneous.length; i++)
            {
                grid.placeSprite(miscellaneous[i]);
            }
            //Initializes the player variable.
            player = new Player(2, 0);

            //adds player using placeSprite. 
            grid.placeSprite(player);

            //sets the lives of the player to the lives for that map
            player.setLives(1);

        }
    }

    //Main method
    public static void main(String[] args) 
    {
        //Creates a scanner.
        Scanner scan = new Scanner(System.in);
        
        //Prompts player to enter which difficulty they want.
        System.out.println("Choose Difficulty: ");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Good Luck");
        int playerDifficulty = scan.nextInt();
        
        //Creates a new game with the difficulty that had just been entered.
        new Game(playerDifficulty, scan);
    }
}