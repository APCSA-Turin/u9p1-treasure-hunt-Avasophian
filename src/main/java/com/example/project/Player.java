package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite {
    private int treasureCount;
    private int numLives;
    private boolean win;

    public Player(int x, int y) 
    { //set treasureCount = 0 and numLives = 2 
        super (x, y);
        treasureCount = 0;
        numLives = 2;
    }


    public int getTreasureCount(){return treasureCount;}
    public int getLives(){return numLives;}
    public boolean getWin(){return win;}

    @Override
    public String getCoords()
    { //returns the coordinates of the sprite ->"(x,y)"
        return ("Player:" + super.getCoords());
    }

    @Override
    public String getRowCol(int size)
    { //returns the row and column of the sprite -> "[row][col]"
       return ("Player:" + super.getRowCol(size));
    }
  
    //move method should override parent class, sprite
    public void move(String direction) 
    { //move the (x,y) coordinates of the player
       
        int y = getY();
        int x = getX();
        if (direction.equals("w"))
        {
            setY(y + 1);
        }
        else if (direction.equals("s"))
        {
            setY(y - 1);
        }
        else if (direction.equals("d"))
        {
            setX(x + 1);
        }
        else if (direction.equals("a"))
        {
            setX(x - 1);
        }
    }


    public void interact(int size, String direction, int numTreasures, Object obj) 
    { // interact with an object in the position you are moving to 
    //numTreasures is the total treasures at the beginning of the game
        if (obj instanceof Treasure)        
        {
            if (obj instanceof Trophy)
            {
                if (treasureCount >= numTreasures)
                {
                    win = true;
                }
            }
            else
            {
                treasureCount ++;
            }
        }
        else if (obj instanceof Enemy)
        {
            numLives --;
            if (numLives == 0)
            {
                win = false;
            }
        }
    }


    public boolean isValid(int size, String direction)
    { //check grid boundaries
        int newX = getX();
        int newY = getY();
        
        if (newX >= size - 1  && direction.equals("d"))
        {
            return false;
        }
        else if (newX == 0 && direction.equals("a"))
        {
            return false;
        }
        else if (newY >= size - 1  && direction.equals("w"))
        {
            return false;
        }
        else if (newY == 0 && direction.equals("s"))
        {
            return false;
        }

        return true;
    }
}



