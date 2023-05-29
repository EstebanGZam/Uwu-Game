package com.example.integrativeTask.controller;

import com.example.integrativeTask.Main;
import com.example.integrativeTask.control.Enemy;
import com.example.integrativeTask.control.EntityGame;
import com.example.integrativeTask.control.Object;
import com.example.integrativeTask.model.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AssetSetter {

    private int map;

    public AssetSetter(int map){
      this.map=map;
    }
    public void setObject(EntityGame[] objects,ArrayList <EntityGame> enemiesList, ArrayList<Point> points){

        switch (map){
            case 1:
                objects[0]= new Object("\\1.png", 48*18,48*16,0,"AK-47",1.5);
                objects[0].setCollisionOn(true);

                objects[1]= new Object("\\portal.png", 48*19,48*21,0,"portal",1.5);
                objects[1].setCollisionOn(false);
                generateEnemies(6,points,enemiesList);
               // Enemy enemy = new Enemy(18*48, 17*48,2,3);
                //enemiesList.add(enemy);

                break;
            case 2:
                //}enemies=7;
                objects[0]= new Object("\\2.png", 48*18,48*16,0,"pistol",1.5);
                objects[0].setCollisionOn(true);
                objects[1]= new Object("\\portal.png", 48*19,48*21,0,"portal",1.5);
                objects[1].setCollisionOn(false);
                generateEnemies(10,points,enemiesList);
                break;
            case 3:
                generateEnemies(10,points,enemiesList);
        }



    }

    public void generateEnemies(int numEnemies,ArrayList<Point> validPositionsList, ArrayList<EntityGame> enemies) {
        Random random = new Random();


        for (int i = 0; i < numEnemies; i++) {
            int randomIndex = random.nextInt(validPositionsList.size());
            Point randomPosition = validPositionsList.get(randomIndex);

            int randomX = randomPosition.getX();
            int randomY = randomPosition.getY();
            validPositionsList.remove(randomIndex);
            Enemy enemy = new Enemy(randomX*48, randomY*48,2,3);
            enemies.add(enemy);
        }
    }
}
