package tekstieditori;

import java.awt.event.*;  // Importit käytettäville komponenteille.
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import java.io.*;

/**
 *
 * @author jullw
 */
public class Tekstieditori extends JFrame implements ActionListener {

    static JFrame alusta;
    static JMenuBar valikkoPalkki;
    static JMenu valikko, alaValikko;
    static JMenuItem tallennus, luoUusi, avaa, tulostus, leikkaa, kopio, liita, lopeta;
    static JTextArea tekstiAlue;

    public static void main(String[] args) {

        Tekstieditori editori = new Tekstieditori();

        alusta = new JFrame("Tekstieditori");  // Luo GUI alustan.
        alusta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ohjelma sulkeutuu oikealla sijaitsevasta yläruksista.
        alusta.setSize(800, 730); // Alustan aka framen koko

        valikkoPalkki = new JMenuBar();  // Luo kentän johon voi liittää valikoita
        alusta.setJMenuBar(valikkoPalkki); // Vie äskettäin luodon kentän alustan titlen alle.

        valikko = new JMenu("Valikko");  // Luodaan menu/valikko
        alaValikko = new JMenu("Muokkaa"); // Luodaan menu/valikkoon submenu/alavalikko
        valikkoPalkki.add(valikko); // Lisätään valikkopalkkikenttaan valikko.

        luoUusi = new JMenuItem("Luo uusi tiedosto"); // Luodaan Valikkoon kohdat
        avaa = new JMenuItem("Avaa tiedosto");  // --
        tallennus = new JMenuItem("Tallenna"); // --
        tulostus = new JMenuItem("Tulosta"); // --
        leikkaa = new JMenuItem("Leikkaa"); // --
        kopio = new JMenuItem("Kopioi");  // --
        liita = new JMenuItem("Liita");  // --
        lopeta = new JMenuItem("Lopeta"); // --

        valikko.add(luoUusi);  // Lisataan äsköttäin luodot kohdat valikkoon.
        valikko.add(avaa);
        valikko.add(tallennus);
        valikko.add(tulostus);
        alaValikko.add(leikkaa); // Lisataan alavalikkoon
        alaValikko.add(kopio);  // --
        alaValikko.add(liita);  // --
        valikko.add(alaValikko); // Lisataan alavalikko paavalikkoon.
        valikko.add(lopeta);    // Lisays alavalikon alapuolelle valikko kohta.

        tekstiAlue = new JTextArea();
        tekstiAlue.setSize(800, 720);

        alusta.add(tekstiAlue);

        luoUusi.addActionListener(editori);
        avaa.addActionListener(editori);
        tallennus.addActionListener(editori);
        tulostus.addActionListener(editori);
        leikkaa.addActionListener(editori);
        kopio.addActionListener(editori);
        liita.addActionListener(editori);
        lopeta.addActionListener(editori);

        alusta.show();
    }

    public void actionPerformed(ActionEvent tapahtuma) {
        String komento = tapahtuma.getActionCommand();

        switch (komento) {
            case "Luo uusi tiedosto":
                tekstiAlue.setText("");
                break;

            case "Avaa tiedosto":
                JFileChooser tiedosto = new JFileChooser("f:");

                int muuttuja = tiedosto.showOpenDialog(null);
                if (muuttuja == JFileChooser.APPROVE_OPTION) {
                    File tieto = new File(tiedosto.getSelectedFile().getAbsolutePath());

                    try {
                        String umuuttuja = "", um1 = "";
                        FileReader tiedostonKirjoittaja = new FileReader(tieto);
                        BufferedReader kirjoittaja = new BufferedReader(tiedostonKirjoittaja);

                        umuuttuja = kirjoittaja.readLine();
                        while ((um1 = kirjoittaja.readLine()) != null) {
                            umuuttuja = umuuttuja + "\n" + um1;
                        }
                        tekstiAlue.setText(umuuttuja);
                    } catch (Exception tapahtu) {
                        JOptionPane.showMessageDialog(alusta, tapahtu.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(alusta, "Hakeminen epäonnistui");
                }
            case "Tallenna":
                JFileChooser tallenne = new JFileChooser("f:");

                int tMuuttu = tallenne.showSaveDialog(null);
                if (tMuuttu == JFileChooser.APPROVE_OPTION) {

                    File tTiedosto = new File(tallenne.getSelectedFile().getAbsolutePath());

                    try {
                        FileWriter tietoKirjoitin = new FileWriter(tTiedosto, false);
                        BufferedWriter puskuKirjoittaja = new BufferedWriter(tietoKirjoitin);

                        puskuKirjoittaja.write(tekstiAlue.getText());
                        puskuKirjoittaja.flush();
                        puskuKirjoittaja.close();

                    } catch (Exception tapahtu) {
                        JOptionPane.showMessageDialog(alusta, tapahtu.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(alusta, "Käyttäjä lopetti käytännön");

                }
            case "Tulosta":
                try {
                    tekstiAlue.print();
                } catch (Exception tapahtu){
                    JOptionPane.showMessageDialog(alusta, tapahtu.getMessage());
                }
            case "Leikkaa":
                tekstiAlue.cut();
                
            case "Kopioi":
                tekstiAlue.copy();
                
            case "Liita":
                tekstiAlue.paste();
                
            case "Lopeta":
                System.exit(0);
        }

    }

}
