package com.mycompany.gm;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Victory implements Screen {
    Image victory, reset;

    public Victory(){
        try {
            reset = ImageIO.read(new File("Resources/button_reset.png"));
            victory = ImageIO.read(new File("Resources/win.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetGame(){
        GameManager.getInstance().getStackScreen().pop();
    }
    
    
    @Override
    public void win() {
        
    }

    @Override
    public void click() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(victory, 0, 0, null);
        g.drawImage(reset, 300, 400, null);
    }

}