package com.mycompany.gm;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Gold extends Thing{
    int score;
    boolean biKeo;
    int type = 0;

    public Gold(int positionX, int positionY, String strImage,int score, int type) {
        super(positionX, positionY, strImage);
        this.score = score;
        this.biKeo = false;
        this.type = type;
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
