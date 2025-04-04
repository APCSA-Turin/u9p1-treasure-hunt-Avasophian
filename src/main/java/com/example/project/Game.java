package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 
    private Sprite[] obstaclesOnlyAppearOnce;
    
    public Game(int playerDifficulty, Scanner scanner)
    {
        if (playerDifficulty == 1)
        {
            size = 5;
        }

        if (playerDifficulty == 2)
        {
            size = 7;
        }

        if (playerDifficulty == 3)
        {
            size = 10;
        }
        initialize(playerDifficulty);
        play(scanner);
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

    public void play(Scanner scan){ //write your game logic here

        while(true)
        {
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop
            grid.placeSprite(player);
            grid.display();
            System.out.println();
            System.out.println("Lives: " + player.getLives());
            System.out.println("Keys: " + player.getKeyCount());
            System.out.println("Treasure: " + player.getTreasureCount());
            System.out.println("Total Treasure: " + grid.getTotalTreasures());
            System.out.println("Empty Bucket?: " + player.getEmptyBucket());
            System.out.println("Filled Bucket?: " + player.getFilledBucket());
            System.out.println("Sword?: " + player.getSword());



            System.out.println(grid.getSprite(player.getRow(size), player.getX()).getCoords());


            String playerDirection = scan.nextLine();
            if (player.isValid(size, playerDirection) && player.isValid2(size, grid, playerDirection, grid.getTotalTreasures()))
            {
                player.move(playerDirection);
                player.interact(size, playerDirection, grid.getTotalTreasures(), grid.getSprite(player.getRow(size), player.getX()));
                if (grid.getSprite(player.getRow(size), player.getX()) instanceof Person)
                {
                    Person p = (Person)grid.getSprite(player.getRow(size), player.getX());
                    if (p.getWhich() == 1)
                    {
                        grid.placeSprite(new Dot(4, 1));
                    }
                    else if (p.getWhich() == 2)
                    {
                        grid.placeSprite(new Dot(3, 5));
                    }
                }
                grid.placeSprite(player, playerDirection);
            }

        }
    }
    public Player getPlayer()
    {
        return player;
    }

    public Grid getGrid()
    {
        return grid;
    } 

    public void initialize(int playerChoice){

        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
        if (playerChoice == 1)
        {

            Sprite[][] g = 
            {
                {new Wall(0, 4), new Wall(1, 4), new Dot(2, 4), new Wall(3, 4), new Wall(4, 4)},
                {new Wall(0, 3), new Dot(1, 3), new Dot(2, 3), new Dot(3, 3), new Wall(4, 3)},
                {new Dot(0, 2), new Dot(1, 2), new Dot(2, 2), new Dot(3, 2), new Dot(4, 2)},
                {new Wall(0, 1), new Dot(1, 1), new Wall(2, 1), new Dot(3, 1), new Wall(4, 1)},
                {new Wall(0, 0), new Dot(1, 0), new Dot(2, 0), new Dot(3, 0), new Wall(4, 0)},
            };
            grid = new Grid(5);
            grid.setGrid(g); 
            
            grid.setTotalTreasures(3);


            Enemy[] eList = {new Enemy(1, 0)};
            enemies = eList;
            grid.placeSprite(enemies[0]);


            Treasure[] tList = {new Treasure(2, 4), new Treasure(4, 2), new Treasure(0, 2)};
            treasures = tList;
            for (int i = 0; i < treasures.length; i++)
            {
                grid.placeSprite(treasures[i]);
            }

            trophy = new Trophy(2, 2);
            grid.placeSprite(trophy);
            
            player = new Player(2, 0);
            grid.placeSprite(player);
            player.setLives(1);


        }

        if (playerChoice == 2)
        {

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
            grid = new Grid(7);
            grid.setGrid(g);

            grid.setTotalTreasures(1);


            Sprite[] miscellaneous = {new Person(6, 0, 1), new Door(2, 1), new Door (4, 1), new Bucket(2, 5), new Key(4, 3), new Fire(4, 4), new Sword(5, 6), new Water(2, 6)};

            for (int i = 0; i < miscellaneous.length; i++)
            {
                grid.placeSprite(miscellaneous[i]);
            }


            Enemy[] eList = {new Enemy(0, 4), new Enemy(6, 3)};
            enemies = eList;
            grid.placeSprite(enemies[0]);
            grid.placeSprite(enemies[1]);

            Treasure[] tList = {new Treasure(0, 2)};
            treasures = tList;
            grid.placeSprite(treasures[0]);

            trophy = new Trophy(6, 2);
            grid.placeSprite(trophy);
            player = new Player(2, 0);
            grid.placeSprite(player);
            player.setLives(1);

        }

        if (playerChoice == 3)
        {

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

            grid = new Grid(10);
            grid.setGrid(g);

            grid.setTotalTreasures(2);

            Enemy[] eList = {new Enemy(1, 7), new SpecialEnemy(7, 0)};
            enemies = eList;
            grid.placeSprite(enemies[0]);
            grid.placeSprite(enemies[1]);


            Treasure[] tList = {new Treasure(2, 7), new Treasure(7, 3)};
            treasures = tList;
            grid.placeSprite(treasures[0]);
            grid.placeSprite(treasures[1]);

            trophy = new Trophy(9, 8);
            grid.placeSprite(trophy);

            Sprite[] miscellaneous = {new Water(1, 3), new Fire(0, 5), new Person(9, 5, 2), new Door(4, 1), new Bucket(0, 0), new Key(9, 0)};

            for (int i = 0; i < miscellaneous.length; i++)
            {
                grid.placeSprite(miscellaneous[i]);
            }

            player = new Player(2, 0);
            grid.placeSprite(player);
            player.setLives(1);

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
        new Game(playerDifficulty, scan);
    }
}