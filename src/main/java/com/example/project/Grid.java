package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    //Declares the instance variables
    private Sprite[][] grid;
    private int size;
    private int totalTreasures = 0;

    //Initializes the grid variable based on the parameter size.
    public Grid(int size) 
    { 
        // Creates a new grid with length and width of size.
        grid = new Sprite[size][size];

        //Iterates through the grid, fills it with dots.
        for (int row = 0; row < grid.length; row++)
        {
            for (int col = 0; col < grid[0].length; col++)
            {
                grid[row][col] = new Dot(row, col);
            }
        }
    }

    //Sets the total amount of treasures to the parameter.
    public void setTotalTreasures(int newTreasures)
    {
        totalTreasures = newTreasures;
    }

    //Returns the total amount of treasures.
    public int getTotalTreasures()
    {
        return totalTreasures;
    }

    //Returns grid.
    public Sprite[][] getGrid(){return grid;}

    //Sets grid to the parameter.
    public void setGrid(Sprite[][] newGrid)
    {
        grid = newGrid;
    }

    //Places the given sprite in the spot defined by the sprite's coordinates
    //by setting grid at that coordinate to the spirte.
    public void placeSprite(Sprite s)
    { 
        //Sets newY to the actual Y value.
        //The coordinates of the sprites are counted like an actual x-y grid would be, where higher y values are higher up
        //however the way java does 2d arrays is that a higher value would be lower down. Hence the need for this.
        int newY = grid.length - s.getY() - 1;

        //Places the given sprite in the spot defined by the sprite's coordinates
        grid[newY][s.getX()] = s;
    }

    //Returns the sprite by getting the location of sprite on the grid.
    public Sprite getSprite(int y, int x)
    {
        return grid[y][x];
    }

    //Places sprite in the spot defined by the sprite's current coordinates and direction
    public void placeSprite(Sprite s, String direction) 
    { 
        //Sets the x and y coordinates of the sprite to new variables. 
        //This is so the variables can be modified without changing the attributes of the sprite.
        //What oldX and oldY are meant to represent are the variables of the Sprite s before they moved, essentially.
        //This method is basically calculating the coordinates of the sprite before moving, and then moving it based on that.
        int oldX = s.getX();
        int oldY = s.getY();

        //Checks which direction the variable direction is associated with.
        //Modifies oldX and oldY based on this.
        if (direction.equals("w"))
        {
            //If a sprite is moving up by one, the y coordinate before it moved is one less.
            oldY--;
        }
        else if (direction.equals("s"))
        {
             //If a sprite is moving down by one, the y coordinate before it moved is one more.
            oldY++;
        }
        else if (direction.equals("d"))
        {
            //If a sprite is moving right by one, the y coordinate before it moved is one left.
            oldX--;
        }
        else if (direction.equals("a"))
        {
            //If a sprite is moving left by one, the y coordinate before it moved is one lrighteft.
            oldX++;
        }
        //Places sprite in the position it's supposed to be in based on its coordinates.
        placeSprite(s);

        //Places a dot where sprite "was".
        placeSprite(new Dot(oldX, oldY));
    }

    //Displays the grid to the screen.
    public void display() 
    { 
        //The half space is so the number spacing looks more consistent.
        System.out.print("  ");
        //Iterates through from i to grid length to print the x-coordinates of the grid.
        for (int i = 0; i < grid.length; i++)
        {
            System.out.print(i + "  ");
        }
        System.out.println();

        //Iterates through the 2d array.
        for (int row = 0; row < grid.length; row++)
        {
            //With each row that's iterated through, prints the y-coordinates of the grid.
            System.out.print(grid.length - row  - 1+ " ");
            for (int col = 0; col < grid[0].length; col++)
            {
                //Checks to see what grid at the current location is an instance of.
                //Prints a different character based on that.
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
                    System.out.print("😄 ");
                }
                else if (grid[row][col] instanceof Door) 
                {
                    System.out.print("🚪 ");
                }
                else if (grid[row][col] instanceof Water) 
                {
                    System.out.print("🌊 ");
                }
                else if (grid[row][col] instanceof Bucket) 
                {
                    System.out.print("🥣 ");
                }
                else if (grid[row][col] instanceof Fire) 
                {
                    System.out.print("🔥 ");
                }
                else if (grid[row][col] instanceof Person) 
                {
                    System.out.print("🧝 ");
                }
                else if (grid[row][col] instanceof Key) 
                {
                    System.out.print("🔑 ");
                }
                else if (grid[row][col] instanceof Sword) 
                {
                    System.out.print("🔪 ");
                }
            } 
    
            System.out.println();

        }

    }
    
    //displays a loss
    public void gameover()
    { 
        System.out.println("You lost...");
        System.out.println();
    }

    //displays a win
    public void win()
    {
        System.out.println("You win!");
        System.out.println();
    }
}