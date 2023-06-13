package com.mycompany.gm;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class MenuScreen implements Screen {
    Image backGround, startImage;

    public MenuScreen(){
        try {
            backGround = ImageIO.read(new File("Resources/startMenu.png"));
            startImage = ImageIO.read(new File("Resources/button_start.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void win() {
        GameManager.getInstance().getStackScreen().push(new GameWindow());
    }
    @Override
    public void click() {
        GameManager.getInstance().getStackScreen().push(new GameWindow());
    }

    @Override
    public void update() {

    }

    public void startGame(){
        click();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backGround, 0, 0, null);
        g.drawImage(startImage, 50, 50, null);
    }

    
}
