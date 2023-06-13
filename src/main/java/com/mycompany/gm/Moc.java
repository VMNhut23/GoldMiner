package com.mycompany.gm;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Moc {
    ArrayList<Tnt> myListTnt;
    int tongDiem = 0;
    int positionX, positionY;
    BufferedImage image;
    BufferedImage imageDay;
    int speedY = 0, speedX = 0;
    boolean keoVe = false;
    static int anpha = 0, anphaPlus = 1;
    int y;
    String strImage;

    public Moc(int positionX, int positionY, String strImage) {
        myListTnt  = new ArrayList<>();
        this.positionX = positionX;
        this.positionY = positionY;
        this.strImage = strImage;
        try {
            image = ImageIO.read(new File(strImage));
            this.imageDay = ImageIO.read(new File("Resources/day.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(BufferedImage bufferedImage){//day moc
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
        g.rotate(Math.toRadians(anpha), 385, 134);
        g.drawImage(this.imageDay, 385, 134, 2, this.positionY - 134, null);
        g.drawImage(image, 320, this.positionY, null);
    }

    public void anDa(ArrayList<Da> listDa){
        Rectangle myMoc = new Rectangle(this.positionX, y, 1, this.image.getHeight()-5);
        Rectangle myDa;
        for(Da currentDa: listDa){
            myDa = new Rectangle(currentDa.positionX-30, currentDa.positionY, 82, 70);
            if(myMoc.intersects(myDa)){
                    this.speedY = -1;
                    try {
                        this.image = ImageIO.read(new File("Resources/gapda_1.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                keoVe = true;
                currentDa.biKeo = true;
                tongDiem += currentDa.score;
            }
        }
    }

    public void anPig(ArrayList<Pig> listPig){
        Rectangle myMoc = new Rectangle(this.positionX, y,1, this.image.getHeight());
        Rectangle myDo;
        for(Pig currentThing :listPig){
            myDo = new Rectangle(currentThing.positionX, currentThing.positionY, 67,51);
            if(myMoc.intersects(myDo)){
                if(listPig == listPig){
                    this.speedY = -3;
                    try {
                        this.image = ImageIO.read(new File("Resources/gappig.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                keoVe = true;
                currentThing.biKeo = true;
                tongDiem += currentThing.score;
            }
        }
    }

    public void anVang(ArrayList<Gold> listGold){
        Rectangle myMoc = new Rectangle(this.positionX, y, 48, this.image.getHeight()-10);
        Rectangle myGold;
        for(Gold currentThing : listGold){
            myGold = new Rectangle(currentThing.positionX-10, currentThing.positionY  - (currentThing.positionY /15),
                   currentThing.image.getWidth() - (currentThing.image.getWidth() / 5),
                    currentThing.image.getHeight());
            if(myMoc.intersects(myGold)){
                    try {
                        switch (currentThing.type) {
                            case 1:
                                this.speedY = -1;
                                this.image = ImageIO.read(new File("Resources/gapvang_1.png"));
                                break;
                            case 2:
                                this.speedY = -2;
                                this.image = ImageIO.read(new File("Resources/gapvang_2.png"));
                                break;
                            case 3:
                                this.speedY = -3;
                                this.image = ImageIO.read(new File("Resources/gapvang_3.png"));
                                break;
                            case 4:
                                this.speedY = -4;
                                this.image = ImageIO.read(new File("Resources/gapvang_4.png"));
                                break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                keoVe = true;
                currentThing.biKeo = true;
                tongDiem += currentThing.score;
            }
        }
    }
    
public void anTnt(ArrayList<Tnt> listTnt, ArrayList<Tnt> myListTnt) {
    Rectangle myMoc = new Rectangle(this.positionX, y, 1, this.image.getHeight());
    Rectangle myTnt;
    for(Tnt currentTnt : listTnt){
        myTnt = new Rectangle(currentTnt.positionX-30, currentTnt.positionY,27, 56);
        if(myMoc.intersects(myTnt)){
            currentTnt.biKeo = true;
            this.speedY = -3;
            keoVe = true;
            try {
                this.image = ImageIO.read(new File("Resources/gaptnt.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



    public void update(){
        this.positionY += speedY;
        double d1 = Math.cos(Math.toRadians(anpha));
        double d = Math.sin(Math.toRadians(anpha));
        y = ((int) ((positionY - 134)*d1)) + 134;
        this.positionX  = -((int) ((positionY - 134)*d)) + 350;
        if(this.positionY <= 134){
            speedY = 0;
            speedX = 0;
            this.positionX = 350;
            this.positionY = 134;
            try {
                this.image = ImageIO.read(new File("Resources/moc.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(this.positionY >= 600){
            keoVe = true;
            speedY = -3;
            this.speedX = (int) (this.speedY*d);
        }
        if(this.positionX == 350 && this.positionY == 134){
            if(anpha == 60){
                anphaPlus = -1;
            } else if(anpha == -60){
                anphaPlus = 1;
            }
            anpha += anphaPlus;
        }
    }
}
