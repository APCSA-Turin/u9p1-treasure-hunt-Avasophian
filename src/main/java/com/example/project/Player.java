package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite 
{
    //Declares the instance variables
    private int treasureCount;
    private int numLives;
    private boolean win;
    private boolean dead = false;
    private int keyCount;
    private boolean hasFilledBucket;
    private boolean hasEmptyBucket;
    private boolean hasSword;

    //Initializes the instance variables
    public Player(int x, int y) 
    {
        super (x, y);
        treasureCount = 0;
        numLives = 2;
        keyCount = 0;
        hasFilledBucket = false;
        hasEmptyBucket = false;
        hasSword = false;
    }

    //Getter methods. Return the respective variable
    public int getKeyCount(){return keyCount;}
    public int getTreasureCount(){return treasureCount;}
    public boolean getFilledBucket(){return hasFilledBucket;}
    public boolean getEmptyBucket(){return hasEmptyBucket;}
    public boolean getSword(){return hasSword;}
    public int getLives(){return numLives;}
    public boolean getWin(){return win;}
    public boolean getDead(){return dead;}


    public void setLives(int newLives){numLives = newLives;}

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
  
    //Increments the x and y coordinates of the player depending on direction
    public void move(String direction) 
    { 
       //Gets the y and x of the method and sets them to variables
        int y = getY();
        int x = getX();

        //Depending on direction, increments/decrements(? is that a word?) y or x
        if (direction.equals("w"))
        {
            //Going up increments y
            setY(y + 1);
        }
        else if (direction.equals("s"))
        {
            //Going down decrements y
            setY(y - 1);
        }
        else if (direction.equals("d"))
        {
            //Going right increments x
            setX(x + 1);
        }
        else if (direction.equals("a"))
        {
            //Going left decrements x
            setX(x - 1);
        }
    }

    //Simulates an action depending on what object is in the position you're moving to
    public void interact(int size, String direction, int numTreasures, Object obj) 
    { 
        //Checks if the object is the instance of treasure
        if (obj instanceof Treasure)        
        {
            //Checks if the object is the instance of trophy
            if (obj instanceof Trophy)
            {
                //If it is a trophy, sets win to true if there's more or equal treasures than the number of treasures on the map (the win condition)
                if (treasureCount >= numTreasures)
                {
                    win = true;
                }
            }
            //Otherwise, increments treasurecount by one
            else
            {
                treasureCount ++;
            }
        }

        //Sets hasEmptyBucket to true, the condition which is needed to fill the bucket, if the object is a bucket
        else if (obj instanceof Bucket)
        {
            hasEmptyBucket = true;
        }

        //Checks if hasEmptyBucket is true and if the object in front is water, then fills the bucket if it is water
        else if (obj instanceof Water && hasEmptyBucket)
        {
            hasFilledBucket = true;
        }

        //Checks if object is an instance of Special Enemy
        else if (obj instanceof SpecialEnemy)
        {
            //Casts the object to a special enemy, then runs talk
            SpecialEnemy e = (SpecialEnemy)obj;
            e.talk();

            //Decrements the player's lives if they made the enemy angry
            if (!(e.getAngryOrNot()))
            {
                numLives --;

                //Kills the player if their lives reached zero
                if (numLives == 0)
                {
                    win = false;
                    dead = true;
                }
            }
        }

        //If the enemy is a normal enemy, checks that the enemy is a specialenemy
        else if (obj instanceof Enemy && !(obj instanceof SpecialEnemy)) 
        {
            //If the player doesn't have a sword, decrements the player's lives
            if (!(hasSword))
            {
                //Decrements the players life if they touch an enemy without a sword
                numLives --;
                if (numLives == 0)
                {
                    win = false;
                    dead = true;
                }
            }
        }

        //Increments number of keys if the object is a key
        else if (obj instanceof Key)
        {
            keyCount ++;
        }

        //Simulates talk if the object is a person
        else if (obj instanceof Person)
        {
            Person p = (Person)obj;
            p.talk();
        }

        //Gives player sword if the object is a sword
        else if (obj instanceof Sword)
        {
            hasSword = true;
        }
    }

    //Checks if a position is valid to move in by checking if it's within the boundaries of the grid
    public boolean isValid(int size, String direction)
    {
        //Stores the x and y coordinates into variables
        int newX = getX();
        int newY = getY();
        
        //If the x coordinate is greater than or equal to the rightmost boundary of the grid and the player is trying to move right, returns false
        if (newX >= size - 1  && direction.equals("d"))
        {
            return false;
        }
        //If the x coordinate is equal to the leftmost boundary of the grid and the player is trying to move left, returns false
        else if (newX == 0 && direction.equals("a"))
        {
            return false;
        }
        //If the y coordinate is greater than or equal to the upper boundary of the grid and the player is trying to move up, returns false
        else if (newY >= size - 1  && direction.equals("w"))
        {
            return false;
        }
        //If the y coordinate is equal to the lower boundary of the grid and the player is trying to move down, returns false
        else if (newY == 0 && direction.equals("s"))
        {
            return false;
        }
        //Returns true if none of those conditions were met
        return true;
    }

    //Checks to see if the player is being blocked by certain objects.
    //This includes walls, doors, fire if the player has no bucket, etc.
    public boolean isValid2(int size, Grid grid, String direction, int numTreasures) 
    {

        //Gets the row and column of the player
        int checkRow = getRow(size); 
        int checkCol = getX();
        int oldKeyCount = keyCount;
        
        //Increments or decrements the row and column based on where the player wants to move
        if (direction.equals("d"))
        {
            checkCol ++;
        }
         else if (direction.equals("a")) 
        {
            checkCol --;
        }
        else if (direction.equals("w")) 
        {
            checkRow --;
        } 
        else if (direction.equals("s"))
        {
            checkRow ++;
        }

        //Decrements key count if the player has used a key on opening a door
        if (grid.getSprite(checkRow, checkCol) instanceof Door && keyCount > 0)
        {
            keyCount --;
        }

        //This is just a lot of return conditions. Basically, checks that it's a valid space to move in, which means:
        //There's not a wall, there's not a door without you having a key, there's not fire without you having a filled bucket, 
        //there's not water without you having an empty bucket, and there's not a trophy with you not having enough treasures.
        return !(grid.getSprite(checkRow, checkCol) instanceof Wall || (grid.getSprite(checkRow, checkCol) instanceof Door && oldKeyCount == 0) || (grid.getSprite(checkRow, checkCol) instanceof Fire && hasFilledBucket == false) || (grid.getSprite(checkRow, checkCol) instanceof Water && hasEmptyBucket == false) || (grid.getSprite(checkRow, checkCol) instanceof Trophy && treasureCount < numTreasures)) ;
    }

    // public boolean isValidTalkable(int size, Grid grid, String direction) 
    // {

    //     int checkRow = getRow(size); 
    //     int checkCol = getX();
        
    //     if (direction.equals("d"))
    //     {
    //         checkCol ++;
    //     }
    //      else if (direction.equals("a")) 
    //     {
    //         checkCol --;
    //     }
    //     else if (direction.equals("w")) 
    //     {
    //         checkRow --;
    //     } 
    //     else if (direction.equals("s"))
    //     {
    //         checkRow ++;
    //     }

    //     return !(grid.getSprite(checkRow, checkCol) instanceof Enemy || hasSword == true);
    // }


}



