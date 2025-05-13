package com.example.project;

// Subclass of Sprite
public class Enemy extends Sprite{ //child  of Sprite
    
    public Enemy(int x, int y) 
    {
        super(x, y);
    }


    //the methods below should override the super class 


    public String getCoords(){ //returns "Enemy:"+coordinates
        return "Enemy:" + super.getCoords();
    }


    public String getRowCol(int size)
    { //return "Enemy:"+row col
        return "Enemy:" + super.getRowCol(size);
    }
}