package com.example.integrativeTask.control;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class Bullet extends  EntityGame{
    // Elementos graficos
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private int size;
    private int speed;
    Vector position;
    Vector direction;
    public Bullet(int x, int y, int speed, int lifes, Canvas canvas, double xDirection, double yDirection){
        super(x,y,speed,lifes);
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.position = new Vector(x,y);
        this.direction =  new Vector(xDirection,yDirection);
        this.size = 8;
        this.speed = speed;

    }

    public void paint(){
        graphicsContext.setFill(Color.YELLOW);
        graphicsContext.fillOval(position.getX(), position.getY(), size, size);
        position.setX(position.getX() + direction.getX());
        position.setY(position.getY() + direction.getY());

    }

    public double getPositionX() {
        return position.getX();
    }

    public void setPositionX(double x) {
        this.position.setX(x);
    }

    public double getPositionY() {
        return position.getY();
    }

    public void setPositionY(double y) {
        this.position.setY(y);
    }


}
