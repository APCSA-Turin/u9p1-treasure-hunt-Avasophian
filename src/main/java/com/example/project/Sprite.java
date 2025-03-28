package com.example.project;

public class Sprite {
    private int x, y;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){return x;}
    public int getY(){return y;}

    public void setX(int newX){x = newX;}
    public void setY(int newY){y = newY;}

    public String getCoords()
    { //returns the coordinates of the sprite ->"(x,y)"
        return ("(" + x + "," + y + ")");
    }

    public String getRowCol(int size)
    { //returns the row and column of the sprite -> "[row][col]"
    
        int newY = size - y - 1;
        return "[" + newY + "]" + "[" + x + "]";
    }
    

    public void move(String direction) 
    { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
        if (direction.equals("w"))
        {
            y++;
        }
        else if (direction.equals("s"))
        {
            y--;
        }
        else if (direction.equals("d"))
        {
            x++;
        }
        else if (direction.equals("a"))
        {
            x--;
        }
    }

    public void interact() { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }



}
