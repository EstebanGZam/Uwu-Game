package com.example.integrativeTask.controller;

import com.example.integrativeTask.Main;
import com.example.integrativeTask.control.EntityGame;
import com.example.integrativeTask.control.Object;

public class AssetSetter {

    private int map;

    public AssetSetter(int map){
      this.map=map;
    }
    public void setObject(EntityGame[] objects){
        switch (map){
            case 1:
                objects[0]= new Object("\\1.png", 48*18,48*16,0,"AK-47",1.5);
                objects[0].setCollisionOn(true);

                objects[1]= new Object("\\portal.png", 48*19,48*21,0,"portal",1.5);
                objects[1].setCollisionOn(true);
                break;
            case 2:

               // objects[0]= new Object("\\2.png", 48*18,48*16,0,"pistol ",1.5);
                //objects[0].setCollisionOn(true);
                break;
            case 3:
                objects[0]= new Object("\\1.png", 48*11,48*11,0,"AK-47",1.5);
                objects[0].setCollisionOn(true);
        }



    }
}
