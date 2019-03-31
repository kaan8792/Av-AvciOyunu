/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package av.avcioyunu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author PC
 */
public class Av extends JPanel{
    private int x;//Avýn x koordinati
    private int y;//Avin y koordinati
    private int hareketeKalanSure;//Hareket etmesine kalan sure
    
    private final int genislik      = 10;//Avin genisligi Odevde belirtilen genislik
    private final int yukseklik     = 10;//Avin yuksekligi Odevde belirtilen yukseklik
    private final int atlamaMiktari = 10;//Odev içeriginde belirtilen pixel atlama mikatarý.
    private final int hiz;               //diziHiz dizisinden rastgele olarak secilen avýn hizi
    private final int puan          = 2; //Av basýna elde edilen paun. 
    private final int [] diziHiz    = {2000,1000,500,250,125};//Odev içeriginde belirtilen hizlar
    
    public Av(int ekranGenisligi){
        this.x   = (int)(Math.random()*(ekranGenisligi-genislik-5)); // Avýn x koordinatý ekranda rastgele secilir. Ekranýn kenarýndaki cizgiler paneli 5 px kapattigi icin 5px cikarilir.
        this.y   = 0;    //En tepeden baslýyorlar.
        this.hiz = this.diziHiz[(int)Math.floor(Math.random()*5)];//Avýn hýzý belirtilen miktrlarda rastgele 
        this.hareketeKalanSure = this.hiz;
        
        setBounds(this.x, this.y, this.genislik, this.yukseklik);
        setBackground(Color.red);
        setVisible(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.genislik, this.yukseklik);
    }
    
    //Avýn hareket hareket etmesi icin yeterli zaman gectiyse hareket ettir.
    public void hareketKontrolEt(int gecenZaman){
        if (hareketeKalanSure<=0) {
            y+=atlamaMiktari;
            setBounds(this.x, this.y, this.genislik, this.yukseklik);
            hareketeKalanSure=hiz;
        }else{
            hareketeKalanSure-=gecenZaman;
        } 
    }
    
    //****** Getter and Setter ***********// 
    public double getGenislikYaricap(){
        return getGenislik()/2;
    }
    public double getYukseklikYaricap(){
        return getYukseklik()/2;
    }

    public double getAvMerkezX(){
        return getAvX() + getGenislik()/2;
    }
    public double getAvMerkezY(){
            return getAvY() + getYukseklik()/2;
    }
    public int getAvX() {
        return x;
    }
    public int getAvY() {
        return y;
    }
    public void setAvX(int x) {
        this.x = x;
    }
    public void setAvY(int y) {
        this.y = y;
    }

    public int getHareketeKalanSure() {
        return hareketeKalanSure;
    }

    public void setHareketeKalanSure(int hareketeKalanSure) {
        this.hareketeKalanSure = hareketeKalanSure;
    }

    public int getGenislik() {
        return genislik;
    }

    public int getYukseklik() {
        return yukseklik;
    }
    
    
}
