package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) 
    { //initialize and create a grid with all DOT objects
        grid = new Sprite[size][size];
        for (int row = 0; row < grid.length; row++)
        {
            for (int col = 0; col < grid[0].length; col++)
            {
                grid[row][col] = new Dot(row, col);
            }
        }
    }

    public int[][] generate(int numOfThings)
    {
        int[][] coords = new int[numOfThings][2];

        for (int i = 0; i < coords.length; i++)
        {
            coords[i][0] = (int)(Math.random() * size + 1);
            coords[i][1] = (int)(Math.random() * size + 1);
        }
        return coords;
    }

    public Sprite[][] getGrid(){return grid;}

    public void setGrid(Sprite[][] newGrid)
    {
        grid = newGrid;
    }

    public void placeSprite(Sprite s)
    { //place sprite in new spot
        System.out.println(s.getY());
        int newY = grid.length - s.getY() - 1;
        grid[newY][s.getX()] = s;
    }

    public void placeSprite(Sprite s, String direction) 
    { //place sprite in a new spot based on direction

        int oldX = s.getX();
        int oldY = s.getY();
        if (direction.equals("w"))
        {
            oldY--;
        }
        else if (direction.equals("s"))
        {
            oldY++;
        }
        else if (direction.equals("d"))
        {
            oldX--;
        }
        else if (direction.equals("a"))
        {
            oldX++;
        }
        // s.move(direction);
        placeSprite(s);
        placeSprite(new Dot(oldX, oldY));
    }


    public void display() 
    { //print out the current grid to the screen 
        System.out.print("  ");
        for (int i = 0; i < grid.length; i++)
        {
            System.out.print(i + "  ");
        }
        System.out.println();
        for (int row = 0; row < grid.length; row++)
        {
            System.out.print(row + " ");
            for (int col = 0; col < grid[0].length; col++)
            {

                if (grid[row][col] instanceof Wall)
                {
                    System.out.print("⬜ ");
                }
                if (grid[row][col] instanceof Dot)
                {
                    System.out.print("⬛ ");
                }
                else if (grid[row][col] instanceof Trophy) 
                {
                    System.out.print("🏆 ");

                }
                else if (grid[row][col] instanceof Treasure) 
                {
                    System.out.print("💰 ");

                } 
                else if (grid[row][col] instanceof Enemy) 
                {
                    System.out.print("😈 ");

                }

                else if (grid[row][col] instanceof Player) 
                {
                    System.out.print("🗿 ");
                }
            } 
            System.out.println();
        }

    }
    
    public void gameover()
    { //use this method to display a loss
    }

    public void win(){ //use this method to display a win 
    }


}