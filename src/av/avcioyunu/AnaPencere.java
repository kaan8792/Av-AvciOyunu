/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package av.avcioyunu;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author PC
 */
public class AnaPencere extends JFrame{
    
    private final int pencereGenislik     = 500;    //Odevde belirtilen genislik ve yukseklik
    private final int pencereYukseklik    = 500;
    
    public AnaPencere(){
        setDefaultLookAndFeelDecorated(true);       //Ekran genisliginin ayarlanan genislik olmas�n� saglar. Kenarl�klarla ilgili duzeltme
        setTitle("Av-Avc� Oyunu");                  //Oyun basligini ayarla
        setDefaultCloseOperation(EXIT_ON_CLOSE);    //X butonuna basildiginda program sonlansin.
        setResizable(false);                        //Oyun penceresinin boyutu degistirilemez olmal�. 
        setVisible(true);                           //Pencereyi g�r�n�r yap
        setBounds(0, 0, pencereGenislik, pencereYukseklik);     //Pencerenin boyutunu ayarla
        Oyun oyun = new Oyun(pencereGenislik,pencereYukseklik); //Oyun olustur
        add(oyun);                                              //Oyunu pencereye ekle
        
    }


}
