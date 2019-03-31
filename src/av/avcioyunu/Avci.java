/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package av.avcioyunu;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class Avci extends JPanel{
    
    private int avciX;
    private int avciY;
    private final int avciGenislik      = 50;//Avcinin genisligi Odevde belirtilen genislik
    private final int avciYukseklik     = 50;//Avcinin yuksekligi Odevde belirtilen yukseklik
    private final int atlamaMiktari     = 10;//Odev i�eriginde belirtilen pixel atlama mikatar�.
    private final int ekranGenisligi;   //Oyun ekrani genisligi
    private final int ekranYuksekligi;  //Oyun ekrani yuksekligi
    
    public Avci(int ekranGenislik,int ekranYukseklik) {
        this.ekranGenisligi =ekranGenislik;
        this.ekranYuksekligi=ekranYukseklik;
        avciX = ekranGenislik/2-avciGenislik/2;        //Ekran�n tam ortas�na konumla
        avciY = ekranYukseklik/2-avciYukseklik/2;     
        
        mBounds();
        setBackground(Color.BLUE);
        setVisible(true);
    }
    
    //Olustugunda ve yeri degistiginde yeniden �iz.
    public void mBounds(){
        setBounds(this.avciX, this.avciY, this.avciGenislik, this.avciYukseklik);
    }
    
    //****** Avcinin Hareketleri ***********// 
    public void solaGit(){
        if (avciX-atlamaMiktari<=0) {
            avciX=0;
        }else{
            avciX-=atlamaMiktari;
        }
        mBounds();//setBounds ile kerandaki konum degistirildi.
    }
    public void sagaGit(){
        if (avciX+atlamaMiktari+avciGenislik+5>=ekranGenisligi) {
            avciX=ekranGenisligi-avciGenislik-5;
        }else{
            avciX+=atlamaMiktari;
        }
        mBounds();//setBounds ile kerandaki konum degistirildi.
    }
    public void yukariGit(){
        if (avciY-atlamaMiktari<=0) {
            avciY=0;
        }else{
            avciY-=atlamaMiktari;
        }
        mBounds();//setBounds ile kerandaki konum degistirildi.
    }
    public void asagiGit(){
        if (avciY+atlamaMiktari+avciYukseklik+25>=ekranYuksekligi) {
            avciY=ekranYuksekligi-avciYukseklik-25;//Jframenin title k�sm� koordinat sistemini kayd�rd��� i�in aradaki fark ��kar�l�yor.
        }else{
            avciY+=atlamaMiktari;
        }
        mBounds();//setBounds ile kerandaki konum degistirildi.
    }
    
    
    //****** Getter and Setter ***********// 
    public double getGenislikYaricap(){
        return getAvciGenislik()/2;
    }
    public double getYukseklikYaricap(){
        return getAvciYukseklik()/2;
    }
    
    public double getAvciMerkezX(){
        return getX() + getAvciGenislik()/2;
    }
    public double getAvciMerkezY(){
        return getY() + getAvciYukseklik()/2;
    }
    
    public int getX() {
        return avciX;
    }
    public void setX(int x) {
        this.avciX = x;
    }
    public int getY() {
        return avciY;
    }
    public void setY(int y) {
        this.avciY = y;
    }

    public int getAvciGenislik() {
        return avciGenislik;
    }

    public int getAvciYukseklik() {
        return avciYukseklik;
    }
    
}
