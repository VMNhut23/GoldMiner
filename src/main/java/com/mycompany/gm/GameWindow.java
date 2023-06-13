package com.mycompany.gm;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class GameWindow implements Screen{
    Image background, background2;
    BufferedImage bufferedImage2;
    int count = 0;
    int count2 = 0;
    int tempScore;
    int scoreX = 80;
    int timeChuc, timeDonVi;
    int dem = 30;
    Player player;
    ArrayList<Image> listDiem;
    ArrayList<Gold> listGold = new ArrayList<>();
    ArrayList<Da> listDa1 = new ArrayList<>();
    ArrayList<Pig> listPig = new ArrayList<>();
    ArrayList<Tnt> listTnt = new ArrayList<>();

    public GameWindow() {
        dem = 60;
        Pig pig = new Pig(350, 550, "Resources/pig.png", 1000);
        player = new Player(300, 24, "Resources/player.png");
        
        Gold v1 = new Gold(50, 420, "Resources/vang_1.png", 500, 1);
        Gold v2 = new Gold(40, 350, "Resources/vang_3.png", 250, 3);
        Gold v7 = new Gold(250, 220, "Resources/vang_3.png", 250, 3);
        Gold v3 = new Gold(640, 380, "Resources/vang_3.png", 250, 3);

        Gold v8 = new Gold(360, 450, "Resources/vang_2.png", 350, 2);
        Gold v9 = new Gold(500, 350, "Resources/vang_3.png", 350, 3);
        Gold v5 = new Gold(650, 450, "Resources/vang_1.png", 500, 1);
        Gold v6 = new Gold(430, 250, "Resources/vang_3.png", 250, 3);
        
        
        Da d1 = new Da(600, 250, "Resources/da_1.png", 50);
        Da d2 = new Da(100, 300, "Resources/da_1.png", 50);
        Da d3 = new Da(290, 350, "Resources/da_1.png", 50);
        
        Tnt tnt1 = new Tnt(240, 450, "Resources/tnt1.png");
        Tnt tnt2 = new Tnt(540, 480, "Resources/tnt1.png");
        listDiem = new ArrayList<>();
        timeChuc = 3;
        timeDonVi = 0;
        try {
            listDiem.add(ImageIO.read(new File("Resources/so0.png")));
            listDiem.add(ImageIO.read(new File("Resources/so1.png")));
            listDiem.add(ImageIO.read(new File("Resources/so2.png")));
            listDiem.add(ImageIO.read(new File("Resources/so3.png")));
            listDiem.add(ImageIO.read(new File("Resources/so4.png")));
            listDiem.add(ImageIO.read(new File("Resources/so5.png")));
            listDiem.add(ImageIO.read(new File("Resources/SO6.png")));
            listDiem.add(ImageIO.read(new File("Resources/so7.png")));
            listDiem.add(ImageIO.read(new File("Resources/so8.png")));
            listDiem.add(ImageIO.read(new File("Resources/so9.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        listGold.add(v1);
        listGold.add(v2);
        listGold.add(v3);

        listGold.add(v5);
        listGold.add(v6);
        listGold.add(v7);
        listGold.add(v8);
        listGold.add(v9);
        listDa1.add(d1);
        listDa1.add(d2);
        listDa1.add(d3);
        listPig.add(pig);
        listTnt.add(tnt1);
        listTnt.add(tnt2);

        try {
            background = ImageIO.read(new File("Resources/bg.png"));
            background2 = ImageIO.read(new File("Resources/bg2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void win() {
        GameManager.getInstance().getStackScreen().pop();
        GameManager.getInstance().getStackScreen().push(new Victory());
    }
    @Override
    public void click() {   
        GameManager.getInstance().getStackScreen().pop();
        GameManager.getInstance().getStackScreen().push(new GameOver());
    }

    public void gapDo(){
        Player.moc.speedY = 3;
        Player.moc.keoVe = false;
    }

    public void updateDo(){
        if(player.moc.keoVe){
            Iterator itr1 = listGold.iterator();
            while (itr1.hasNext()) {
                Gold g= (Gold) itr1.next();
                if (g.biKeo) itr1.remove();
            }
            Iterator itr5 = listDa1.iterator();
            while (itr5.hasNext()) {
                Da s = (Da) itr5.next();
                if (s.biKeo) itr5.remove();
            }
            Iterator itr6 = listPig.iterator();
            while (itr6.hasNext()) {
                Pig s = (Pig) itr6.next();
                if (s.biKeo) itr6.remove();
            }
            Iterator itr7 = listTnt.iterator();
            while (itr7.hasNext()) {
                Tnt s = (Tnt) itr7.next();
                if (s.biKeo) this.click();
            }
        }
    }

    @Override
    public void update() {
        if(!Player.moc.keoVe) {
            Player.moc.anVang(listGold);
            Player.moc.anDa(listDa1);
            Player.moc.anPig(listPig);
            Player.moc.anTnt(listTnt, player.moc.myListTnt);
        }
        Player.moc.update();
        for(Pig pig : listPig){
            pig.update();
        }
        updateDo();

        count++;
        if(count2 == 59){
            dem--;
            count2 = 0;
        }
        count2++;
        if(dem == 0 && player.moc.tongDiem < 3000){
            this.click();
        }
        if(dem == 0 && player.moc.tongDiem >= 3000){
            this.win();
        }
        if(listGold.size() == 0 && listDa1.size() == 0 && player.moc.positionY <= 134){
            this.win();
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d;
        g2d = (Graphics2D)g.create();
        if(bufferedImage2 == null){
            bufferedImage2 = new BufferedImage(800, 600, 1);
        }
        Graphics2D bufferGraphic2D = (Graphics2D) bufferedImage2.getGraphics();
        bufferGraphic2D.drawImage(Player.moc.image, Player.moc.positionX, Player.moc.positionY, null);
        bufferGraphic2D.drawImage(background2, 0, 0, null);
        bufferGraphic2D.drawImage(background, 0, 150, null);
        player.draw(bufferedImage2);
        for(Gold currentGold : listGold){
            currentGold.draw(bufferedImage2);
        }
        for(Da currentDa : listDa1){
            currentDa.draw(bufferedImage2);
        }
        Player.moc.draw(bufferedImage2);
        tempScore = player.moc.tongDiem;
        scoreX = 80;
        for(int i = 0; i < 4; i++){
            bufferGraphic2D.drawImage(listDiem.get(tempScore%10), scoreX, 50, null);
            tempScore = tempScore/10;
            scoreX -= 15;
        }
        timeDonVi = dem % 10;
        timeChuc = dem / 10;
        bufferGraphic2D.drawImage(listDiem.get(timeChuc), 685, 50, null);
        bufferGraphic2D.drawImage(listDiem.get(timeDonVi), 700, 50, null);
        for(Thing currentPig : listPig){
            currentPig.draw(bufferedImage2);
        }
        for(Tnt currentTnt : listTnt){
            currentTnt.draw(bufferedImage2);
        }
        for(Tnt currentTnt : player.moc.myListTnt){
                currentTnt.draw(bufferedImage2);
        }
        g2d.drawImage(bufferedImage2, 0, 0, null);
    }
}

