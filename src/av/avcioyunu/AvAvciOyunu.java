/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package av.avcioyunu;

import javax.swing.SwingUtilities;

/**
 *
 * @author PC
 */
public class AvAvciOyunu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AnaPencere anaPencere = new AnaPencere();   //Ana pencereyi olustur 
                anaPencere.setLocationRelativeTo(null);     //Ekranda ortala
            }
        });
        
        
    }
    
}
