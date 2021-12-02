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
import java.util.Scanner;

/**
 *
 * @author hdorne
 */
public class Main {
    public static void main(String args[]) throws Exception {
        try {
            InputStream file = new FileInputStream("C:\\Users\\hdorne\\Desktop\\tp-pgm-fontes_dorne\\Fontes_Dorne\\src\\main\\ressources\\lena.pgm");;
            BufferedReader buffFile = new BufferedReader(new InputStreamReader(file));
            
            String format = buffFile.readLine();    // first line contains P2 or P5
            String line = buffFile.readLine();     // second line contains height and width
            
            while (line.startsWith("#")) {
                line = buffFile.readLine();
            }
            Scanner s = new Scanner(line);
            int width = s.nextInt();
            int height = s.nextInt();
            
            line = buffFile.readLine();// third line contains maxVal
            s = new Scanner(line);
            int maxVal = s.nextInt();
            
            byte[][] im = new byte[height][width];

            int count = 0;
            int b = 0;
            try {
                while (count < height*width) {
                    b = buffFile.read() ;
                    if ( b < 0 ) 
                        break ;
                    if (b == '\n') { 
                        // do nothing if new line encountered
                    } else {
                        if ( "P5".equals(format) ) { // Binary format
                            im[count / width][count % width] = (byte)((b >> 8) & 0xFF);
                            count++;
                            im[count / width][count % width] = (byte)(b & 0xFF);
                            count++;
                        }
                        else {  // ASCII format
                            im[count / width][count % width] = (byte)b ;
                            count++;
                        }
                    }
                }
            } catch (EOFException eof) {
                eof.printStackTrace(System.out) ;
            }
            System.out.println("Height=" + height);
            System.out.println("Width=" + height);
            System.out.println("Required elements=" + (height * width));
            System.out.println("Obtained elements=" + count);
        }
        catch(IOException t) {
            t.printStackTrace(System.err) ;
        }

    }
}
