package com.mycompany.gm;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

                                                                                                
public class Player {
    int positionX, positionY;
    BufferedImage image;
    static Moc moc;
    public Player(int positionX, int positionY, String strImage) {
        this.positionX = positionX;
        this.positionY = positionY;
        moc = new Moc(this.positionX + 20, this.positionY + 110, "Resources/moc.png");
        try {
            this.image = ImageIO.read(new File(strImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void draw(BufferedImage bufferedImage){
        Graphics g = bufferedImage.getGraphics();
        g.drawImage(image, positionX, positionY, null);
    }
}