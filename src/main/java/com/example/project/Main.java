package com.example.project;


public class Main 
{
    public static void main(String[] args) 
    {
        // Grid g = new Grid(10);
        // Sprite s = new Sprite(1, 1);
        // // g.placeSprite(s);
        // // g.display();
        // // s.move("d");
        // // g.placeSprite(s, "d");
        // // g.display();

        // s.move("w");
        // System.out.println(s.getX());
        // System.out.println(s.getY());

        Grid g = new Grid(10);
        Player p = new Player(0, 9);
        int[][] c = new int [5][2];
        int x;
        int y;
        for (int row = 0; row < 5; row++)
        {
            x = (int)(Math.random() * 10);
            y = (int)(Math.random() * 10);
            g.placeSprite(new Treasure(x, y));
            int[] twoDirections = new int[2];
            twoDirections[0] = (int)(Math.random() * 4) + 1;
            twoDirections[1] = (int)(Math.random() * 4) + 1;
            for (int i = 0; i < 2; i++)
            if (twoDirections[i] == 1)
            {
                if (y + 1 < g.getGrid()[0].length)
                {
                    g.placeSprite(new Wall(x, y+1));
                }
            }
            else if (twoDirections[i] == 2)
            {
                if (x + 1 < g.getGrid().length)
                {
                    g.placeSprite(new Wall(x + 1, y));
                }
            }
            else if (twoDirections[i] == 3)
            {
                if (y - 1 > 0)
                {
                    g.placeSprite(new Wall(x, y - 1));
                }
            }

            else if (twoDirections[i] == 4)
            {
                if (x - 1 > 0)
                {
                    g.placeSprite(new Wall(x - 1, y));
                }
            }
           
        }


        for (int enemy = 0; enemy < 5; enemy++)
        {
            x = (int)(Math.random() * 10);
            y = (int)(Math.random() * 10);
            if (!(g.getGrid()[x][y] instanceof Treasure))
            {
                g.placeSprite(new Enemy(x, y));
            }
            if (y + 1 < g.getGrid()[0].length)
            {
                g.placeSprite(new Wall(x, y+1));
            }
            int[] twoDirections = new int[2];
            twoDirections[0] = (int)(Math.random() * 4) + 1;
            twoDirections[1] = (int)(Math.random() * 4) + 1;
            for (int i = 0; i < 2; i++)
            if (twoDirections[i] == 1)
            {
                if (y + 1 < g.getGrid()[0].length)
                {
                    g.placeSprite(new Wall(x, y+1));
                }
            }
            else if (twoDirections[i] == 2)
            {
                if (x + 1 < g.getGrid().length)
                {
                    g.placeSprite(new Wall(x + 1, y));
                }
            }
            else if (twoDirections[i] == 3)
            {
                if (y - 1 > 0)
                {
                    g.placeSprite(new Wall(x, y - 1));
                }
            }
        }

        g.display();



    }
}
