/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.fontes_dorne;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author hdorne
 */
public class PGMImage {

    //ATTRIBUTES
    private Integer[][] image;
    private String format;
    private int height;
    private int width;
    private int maxVal;

    
    //CONSTRUCTOR
    public PGMImage(int width, int height) {
        format = "";
        image = new Integer[height][width];
        this.width = width;
        this.height = height;
        this.maxVal = 255;
    }
    
    public PGMImage(String path) throws IOException {
        InputStream file = new FileInputStream(path);
        BufferedReader buffFile = new BufferedReader(new InputStreamReader(file));

        this.format = buffFile.readLine();    // first line contains P2 or P5
        String line = buffFile.readLine();     // second line contains height and width

        while (line.startsWith("#")) {
            line = buffFile.readLine();
        }
        Scanner s = new Scanner(line);
        this.width = s.nextInt();
        this.height = s.nextInt();

        line = buffFile.readLine();// third line contains maxVal
        s = new Scanner(line);
        this.maxVal = s.nextInt();

        this.image = new Integer[height][width];
        int count;

        for(int i = 0; i < height; i++){
            line = buffFile.readLine();
            s = new Scanner(line);
            count = 0;
            while(count < width){
                image[i][count] = s.nextInt();
                count++;
            }
        }
    }
    
    
    //METHODES
    public int[] generateHisto() {
        
        int histo[] = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
         
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                int pixel = this.image[i][j];
                histo[pixel/32]++;
            }
        }
        
        return histo;
    }
    
    public PGMImage generateSeuil(int seuil){
        PGMImage imageSeuil = new PGMImage(this.width, this.height);
        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.height; j++){
                if(this.image[i][j] >= seuil){
                    imageSeuil.image[i][j] = 255;
                } else {
                    imageSeuil.image[i][j] = 0;
                }
            }
        }
        return imageSeuil;
    }

    public void affiche() {
        String line;
        for(int i = 0; i < height; i++){
            line = image[i][0].toString();
            for(int j = 1; j < width; j++){
                line += " " + image[i][j];
            }
            System.out.println(line);
        }
    }
    
    

    
    //GETTERS ET SETTERS
    public Integer[][] getImage() {
        return image;
    }

    public void setImage(Integer[][] image) {
        this.image = image;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(int maxVal) {
        this.maxVal = maxVal;
    }
    
}
