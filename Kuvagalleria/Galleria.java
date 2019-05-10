package kuvagalleria;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author jullw
 */
public class Galleria extends JFrame implements ActionListener  {
    
    static JFrame alusta;
    static JLabel labeli;
    static JPanel paneeli;
    static JButton vasen, satu, oikea;
    static ImageIcon ikoni;
    static int numero = 1;
    
    public static void main(String[] args) {
        
        Galleria galleria = new Galleria();
        
        alusta = new JFrame();
        paneeli = new JPanel();
        labeli = new JLabel();
        vasen = new JButton("Vasen");
        oikea = new JButton("Oikea");
        satu = new JButton("Satu");
        
        ImageIcon ikoni = (imgIc());
        int y = ikoni.getIconHeight();
        int x = ikoni.getIconWidth();

        if (x > 1800) {
            x = 1800;
        }
        if (y > 1054) {
            y = 1054;
        }

        alusta.setSize(x + 100, y + 100);
        paneeli.setBounds(0, y - 100, x, 100);
        alusta.setSize(x, y);
        labeli.setIcon(ikoni);

        System.out.println(x + " testaus" + y);

        alusta.add(paneeli);
        paneeli.add(vasen);
        paneeli.add(satu);
        paneeli.add(oikea);
        alusta.add(labeli);

        vasen.setBounds(10, 10, 20, 20);
        vasen.setBounds(x - 10, 10, 20, 20);

        vasen.addActionListener(galleria);
        satu.addActionListener(galleria);
        oikea.addActionListener(galleria);
        
        vasen.setBackground(Color.PINK);
        satu.setBackground(Color.PINK);
        oikea.setBackground(Color.PINK);
        vasen.setForeground(Color.red);
        satu.setForeground(Color.red);
        oikea.setForeground(Color.red);
        
        
        
        alusta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        alusta.show();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String komento = e.getActionCommand();
        if(komento.equals("Satu")){
            Random random = new Random();
            int min = 1;
            int max = 127;
            numero = random.nextInt((max - min) + 1) + min;
            setNum(numero);
            ImageIcon varIcon = imgIc();
            alusta.setSize(geX() + 100, geY() + 100);
            paneeli.setBounds(0, geY() - 100, geX(), 100);
            alusta.setSize(geX(), geY());
            labeli.setIcon(varIcon);
            }
        if(komento.equals("Vasen")){
            numero = numero -1;
            if(numero <= 0 ){
                numero = 127;
            }
            setNum(numero);
            ImageIcon varIcon = imgIc();
            alusta.setSize(geX() + 100, geY() + 100);
            paneeli.setBounds(0, geY() - 100, geX(), 100);
            alusta.setSize(geX(), geY());
            labeli.setIcon(varIcon);
        }
        if(komento.equals("Oikea")){
            numero = numero +1;
            if(numero >= 128 ){
                numero = 1;
            }
            setNum(numero);
            ImageIcon varIcon = imgIc();
            alusta.setSize(geX() + 100, geY() + 100);
            paneeli.setBounds(0, geY() - 100, geX(), 100);
            alusta.setSize(geX(), geY());
            labeli.setIcon(varIcon);
        }
    }
    
    public static ImageIcon imgIc(){
        String changNum = Integer.toString(getNum());
        String picChang = "/home/jullw/Kuvat/Jaapas/" + changNum + ".jpg";
        ImageIcon iconVar = new ImageIcon(picChang);
        
        int y = iconVar.getIconHeight();
        int x = iconVar.getIconWidth();
        
        Image pic = iconVar.getImage();
        Image newPic = pic.getScaledInstance(x, y, java.awt.Image.SCALE_FAST);
        ImageIcon icon = new ImageIcon(newPic);
        
        return icon;
    }
    public static void setNum(int num){
        numero = num;
    }
    public static int getNum(){
        return numero;
    }
    
    public static int geY(){
        int y = imgIc().getIconHeight();
        if (y > 1054) {
            y = 1054;
        }
        return y;
    }
    public static int geX(){
        int x = imgIc().getIconWidth();
        
        if (x > 1800) {
            x = 1800;
        }
        return x;
    }
}
