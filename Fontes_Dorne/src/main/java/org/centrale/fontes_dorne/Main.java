/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.fontes_dorne;

import java.util.Arrays;


/**
 *
 * @author hdorne
 */
public class Main {
    public static void main(String args[]) throws Exception {
        
        PGMImage image = new PGMImage("C:\\Users\\hdorne\\Desktop\\tp-pgm-fontes_dorne\\Fontes_Dorne\\src\\main\\ressources\\lena.pgm");
                
        PGMImage imageSeuil = image.generateSeuil(127);
        
        PGMImage imageDiff = image.generateDiff(imageSeuil);
        imageDiff.affiche();
    }        
}
