/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gm;

/**
 *
 * @author pc
 */
public class GoldMiner1 {

    public static void main(String[] args) {
        Window window = new Window();
        Thread thread = new Thread(window);
        thread.start();
    }
}
