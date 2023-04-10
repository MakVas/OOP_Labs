package com.makvas.oop_lab2.warships;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Destroyer {
    private ImageView DestroyerImageView;
    private Label DestroyerLabel;
    private ProgressBar DestroyerHPbar;

    public ImageView getDestroyerImageView() {
        return DestroyerImageView;
    }

    public void setDestroyerImageView(ImageView destroyerImageView) {
        DestroyerImageView = destroyerImageView;
    }

    public Label getDestroyerLabel() {
        return DestroyerLabel;
    }

    public void setDestroyerLabel(Label destroyerLabel) {
        DestroyerLabel = destroyerLabel;
    }

    public ProgressBar getDestroyerHPbar() {
        return DestroyerHPbar;
    }

    public void setDestroyerHPbar(ProgressBar destroyerHPbar) {
        DestroyerHPbar = destroyerHPbar;
    }
    private int kills;
    private double hp;
    private double damage;
    private boolean isAlive;
    private int coordinateX;
    private int coordinateY;
    private final static String type;
    private final Image DestoyerImage = new Image("C:\\Users\\xdjmv\\OneDrive\\Рабочий стол\\ВНТУ\\1й курс\\ООП\\Lab2\\Content\\Destroyer.png");
    private String name;

    static {
        type = "Destroyer";
        System.out.println("Static init...");
    }

    {
        kills = 0;
        isAlive = true;
        System.out.println("Not static init...");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destroyer destroyer = (Destroyer) o;
        return kills == destroyer.kills && Double.compare(destroyer.hp, hp) == 0 && Double.compare(destroyer.damage, damage) == 0 && isAlive == destroyer.isAlive && coordinateX == destroyer.coordinateX && coordinateY == destroyer.coordinateY && DestoyerImage.equals(destroyer.DestoyerImage) && name.equals(destroyer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kills, hp, damage, isAlive, coordinateX, coordinateY, DestoyerImage, name);
    }
    @Override
    public String toString() {
        return "Destroyer{" +
                "kills=" + kills +
                ", hp=" + hp +
                ", damage=" + damage +
                ", isAlive=" + isAlive +
                ", coordinateX=" + coordinateX +
                ", coordinateY=" + coordinateY +
                ", DestoyerImage=" + DestoyerImage +
                ", name='" + name + '\'' +
                '}';
    }

    public int getKills() {return kills;}
    public void setKills(int kills) {this.kills = kills;}
    public double getHP() {return hp;}
    public void setHP(double hp) {this.hp = hp;}
    public double getDamage() {return damage;}
    public void setDamage(double damage) {this.damage = damage;}
    public boolean isAlive() {return isAlive;}
    public void setAlive(boolean alive) {isAlive = alive;}
    public int getCoordinateX() {return coordinateX;}
    public void setCoordinateX(int coordinateX) {this.coordinateX = coordinateX;}
    public int getCoordinateY() {return coordinateY;}
    public void setCoordinateY(int coordinateY) {this.coordinateY = coordinateY;}
    public Image getDestoyerImage() {return DestoyerImage;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getType() {return type;}
    public void move() {
    }

    public void shooting() {
    }

    public void placeBomb() {
    }

    public void takeDamage() {
    }

    public Destroyer() {
        this("Destroyer", 50, 50,1,1);
        ImageView imageView = new ImageView(getClass().getResource("Destroyer.png").toString());
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.rgb(0, 255, 0));
        imageView.setEffect(dropShadow);
    }

    public Destroyer(String name, double hp, double damage, int coordinateX, int coordinateY) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        System.out.println(this);
    }
}