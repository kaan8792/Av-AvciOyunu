/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package av.avcioyunu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 *
 * @author PC
 */
public class Oyun extends JPanel {
    
    private int puan;                          //Oyunun mevcut puan�n� tutar
    private final int ustAltSkor = 100;        //Oyunda kazanmak icin veya kaybetmek i�in ust,alt skor durumu.
    
    private int avOlusturmayaKalan;            //Yeni av olu�turmaya kalan sure.
    private final int avOlustumaZamani  = 500; //Avlar�n olu�turulma mssi.
    private final int pencereGenisligi;        //Ana pencerenin genisligi.
    private final int pencereYuksekligi;       //Ana pencerenin yuksekligi
    private final int oyunTimerMs = 50;        //Timer ka� msde bir �al��sacak. 
    private final Timer oyunTimer ;            //Oyunun ana zamanlay�c�s� fps= 1000/timerMS
    private final int hareketKontrolMs=25;     //Avlarin hareketlerinin kontrol edilecegi ms
    private final Timer hateketTimer;          //Av hareketinin zamanlayicisi
    
    private ArrayList<Av> avlar = new ArrayList<>();//Oyun icerisinde mevcut olan avlarin listesi
    
    private Avci avci;
    
    public Oyun(int pencereGenisligi, int pencereYuksekligi ){
        this.pencereGenisligi  = pencereGenisligi;
        this.pencereYuksekligi = pencereYuksekligi;
        this.avOlusturmayaKalan= this.avOlustumaZamani;
        setBounds(0, 0, this.pencereGenisligi, this.pencereYuksekligi);
        setLayout(null);
        
        setVisible(true);
        setFocusable(true);
        addKeyListener(new KlavyeKontrol());
        
        oyunTimer = new Timer(oyunTimerMs, new OyunTimerKontrol());
        oyunTimer.start();
        
        hateketTimer = new Timer(hareketKontrolMs, new AvHareketTimerKontrol());
        hateketTimer.start();
        
        avci = new Avci(pencereGenisligi, pencereYuksekligi);
        add(avci);
        
    }
    //-100 e veya +100e ulasildiginde oyunu bitir.
    private void skorKontrol(){ 
        if (puan>=ustAltSkor) {
            System.out.println("Kazandin�z!");
            oyunuBitir("Kazandin�z!");
        }
        if (puan<=-ustAltSkor){
            System.out.println("Kaybettiniz!");
            oyunuBitir("Kaybettiniz!");
        }
    }
    
    //Oyunu durdurmak icin gerekli islemler.
    private void oyunuBitir(String mesaj){
        oyunTimer.stop();       //Timerleri durdur
        hateketTimer.stop();
        JOptionPane.showMessageDialog(this, mesaj); //Kazandigini veya kaybettigini ekranda goster.
        System.exit(0);                             //Programi sonlandir
    }
    //Avci ve av carpisma kontrolu
    private void carpismaKontrol(){
        double avciMerkezX = avci.getAvciMerkezX();
        double avciMerkezY = avci.getAvciMerkezY();
        ArrayList<Av> silinecekler = new ArrayList<>();
        for (Av av : avlar) {
            double avMerkezX = av.getAvMerkezX();
            double avMerkezY = av.getAvMerkezY();
            double uzaklikX = Math.abs(avMerkezX-avciMerkezX);
            double uzaklikY = Math.abs(avMerkezY-avciMerkezY);
            if (uzaklikX < av.getGenislikYaricap() + avci.getGenislikYaricap() && uzaklikY < av.getYukseklikYaricap() + avci.getYukseklikYaricap()) {
                puan+=2;
                System.out.println("Puan�n�z "+puan);
                silinecekler.add(av);
                remove(av);
                skorKontrol();
            }
        }
        avlar.removeAll(silinecekler);
        yenidenCiz();
    } 
    
    //Nesne eklendikten, hareket ettikten veya silindikten sonra panel yenileme islemi yapilmasi gerekir.
    private void yenidenCiz(){ 
        revalidate();
        repaint();
    }
    
    //Klavyede basilan tuslari kontrol et ve gerekli islemleri yap
    class KlavyeKontrol implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_LEFT){
                avci.solaGit();
                carpismaKontrol();
            }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                avci.sagaGit();
                carpismaKontrol();
            }else if(e.getKeyCode()==KeyEvent.VK_UP){
                avci.yukariGit();
                carpismaKontrol();
            }else if(e.getKeyCode()==KeyEvent.VK_DOWN){
                avci.asagiGit();
                carpismaKontrol();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
   //Gecen zamani kontrol et ve zaman� geldiyse yeni av olustur.
    class OyunTimerKontrol implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (avOlusturmayaKalan<=0) {
                Av av = new Av(pencereGenisligi);
                avlar.add(av);
                add(av);
                avOlusturmayaKalan=avOlustumaZamani;
                carpismaKontrol();
            }else{
                avOlusturmayaKalan-=oyunTimerMs;
            }
            yenidenCiz();
        }
    }
    
    //Avlar�n hareketi icin yeterli s�re gecti 
    class AvHareketTimerKontrol implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Av> silinecekler = new ArrayList<>();
            for (Av av : avlar) {
                av.hareketKontrolEt(hareketKontrolMs);
                if (av.getAvY()>=pencereYuksekligi) {
                    silinecekler.add(av);
                    remove(av);
                    puan--;
                    System.out.println("Puan�n�z "+puan);
                    skorKontrol();
                }
            }
            avlar.removeAll(silinecekler);
            carpismaKontrol();
        }
        
    }
    
}
