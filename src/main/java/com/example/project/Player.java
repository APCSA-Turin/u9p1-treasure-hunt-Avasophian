package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite 
{
    private int treasureCount;
    private int numLives;
    private boolean win;
    private int keyCount;
    private boolean hasFilledBucket;
    private boolean hasEmptyBucket;
    private boolean hasSword;
    


    public Player(int x, int y) 
    { //set treasureCount = 0 and numLives = 2 
        super (x, y);
        treasureCount = 0;
        numLives = 2;
        keyCount = 0;
        hasFilledBucket = false;
        hasEmptyBucket = false;
        hasSword = false;
    }

    public int getKeyCount(){return keyCount;}
    public int getTreasureCount(){return treasureCount;}
    public boolean getFilledBucket(){return hasFilledBucket;}
    public boolean getEmptyBucket(){return hasEmptyBucket;}
    public boolean getSword(){return hasSword;}

    public int getLives(){return numLives;}
    public boolean getWin(){return win;}

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
        else if (obj instanceof Bucket)
        {
            hasEmptyBucket = true;
        }

        else if (obj instanceof Water && hasEmptyBucket)
        {
            hasFilledBucket = true;
        }
        else if (obj instanceof SpecialEnemy)
        {
            SpecialEnemy e = (SpecialEnemy)obj;
            e.talk();
            if (!(e.getAngryOrNot()))
            {
                numLives --;
                if (numLives == 0)
                {
                    win = false;
                }
            }
        }

        else if (obj instanceof Enemy && !(obj instanceof SpecialEnemy)) 
        {
            if (!(hasSword))
            {
                numLives --;
                if (numLives == 0)
                {
                    win = false;
                }
            }
        }

        else if (obj instanceof Key)
        {
            keyCount ++;
        }

        else if (obj instanceof Person)
        {
            Person p = (Person)obj;
            p.talk();
        }

        else if (obj instanceof Sword)
        {
            hasSword = true;
        }
    }

    // public String interact2(int size, String direction, int numTreasures, Object obj)
    // {

    // }


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

    public boolean isValid2(int size, Grid grid, String direction, int numTreasures) 
    {

        int checkRow = getRow(size); 
        int checkCol = getX();
        int oldKeyCount = keyCount;
        
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

        if (grid.getSprite(checkRow, checkCol) instanceof Door && keyCount > 0)
        {
            keyCount --;
        }

        return !(grid.getSprite(checkRow, checkCol) instanceof Wall || (grid.getSprite(checkRow, checkCol) instanceof Door && oldKeyCount == 0) || (grid.getSprite(checkRow, checkCol) instanceof Fire && hasFilledBucket == false) || (grid.getSprite(checkRow, checkCol) instanceof Water && hasEmptyBucket == false) || (grid.getSprite(checkRow, checkCol) instanceof Trophy && treasureCount < numTreasures)) ;
    }

    public boolean isValidTalkable(int size, Grid grid, String direction) 
    {

        int checkRow = getRow(size); 
        int checkCol = getX();
        
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

        return !(grid.getSprite(checkRow, checkCol) instanceof Enemy || hasSword == true);
    }


}



