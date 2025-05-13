package com.example.project;

public class Sprite {
    //Initializes the instance variables
    private int x, y;

    //Constructor, declares the instance variables with parameters
    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Getter methods for the x and y coordinates
    public int getX(){return x;}
    public int getY(){return y;}

    //Setter methods which set the x and y coordinates to the parameters.
    public void setX(int newX){x = newX;}
    public void setY(int newY){y = newY;}

    //Returns the coordinates of the sprite nicely in an (x, y) format.
    public String getCoords()
    {
        return ("(" + x + "," + y + ")");
    }

    //Returns the row and column of the sprite in a "[row][col]" format.
    public String getRowCol(int size)
    { 
    
        int newY = size - y - 1;
        return "[" + newY + "]" + "[" + x + "]";
    }
    

    //Default behavior of sprite.move. Simply increments the x and y value depending on direction.
    public void move(String direction) 
    {
        //Y is incremented if direction is w as it is moving up.
        if (direction.equals("w"))
        {
            y++;
        }

        //Y is decremented if direction is s as it is moving down.
        else if (direction.equals("s"))
        {
            y--;
        }

        //X is incremented if direction is d as it is moving right.
        else if (direction.equals("d"))
        {
            x++;
        }
        //X is decremented if direction is a as it is moving left.
        else if (direction.equals("a"))
        {
            x--;
        }
    }

    //Returns the row of the Sprite.
    //The coordinates of the sprites are counted like an actual x-y grid would be, where higher y values are higher up
    //however the way java does 2d arrays is that a higher value would be lower down. Hence the need for this.
    public int getRow(int size)
    {
        return size - y - 1;
    }

    public void interact() { //you can leave this empty
    }

}
