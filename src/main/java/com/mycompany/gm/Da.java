package com.mycompany.gm;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.awt.*;
import java.awt.image.BufferedImage;

public class Da extends Thing{
    int score;
    boolean biKeo;

    public Da(int positionX, int positionY, String strImage, int score) {
        super(positionX, positionY, strImage);
        this.score = score;
        biKeo = false;
    }

    @Override
    public void draw(BufferedImage bufferedImage) {
        Graphics g = bufferedImage.getGraphics();
        g.drawImage(this.image, this.positionX, this.positionY, null);
    }

    @Override
    public void update() {

    }
}
