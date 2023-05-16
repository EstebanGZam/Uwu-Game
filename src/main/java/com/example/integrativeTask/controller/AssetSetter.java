package com.example.integrativeTask.controller;

import com.example.integrativeTask.Main;
import com.example.integrativeTask.control.EntityGame;
import com.example.integrativeTask.control.Object;

public class AssetSetter {

    public AssetSetter(){

    }
    public void setObject(EntityGame[] objects){
        objects[0]= new Object("\\1.png", 48*12,48*12,0,"AK-47");
    }
}
