/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseyhteys;

/**
 *
 * @author jullw
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseYhteys {

    public static void main(String[] args) {

        try {
            

            DatabaseYhteys objekti_DB_yhteys = new DatabaseYhteys();  //Tehdaan yhteydestä objekti.

            Connection connection = null;
            connection = objekti_DB_yhteys.get_connection(); // Kutsuu alla olevaa oliota, jossa on tiedot mihin yhdistyä.

            System.out.println(connection); // Kertoo menikö yhdistyminen läpi

            Scanner lukija = new Scanner(System.in);

            System.out.println("-------------------------------------");
            System.out.println("1.Jos haluat luoda uuden käyttäjän (Kirjoita 1!)");
            System.out.println("2. Jos haluat kirjautua sisään (Kirjoita 2!");
            System.out.println("3. Jos haluat poistaa käyttäjän (Kirjoita 3!)");
            System.out.println("4. Jos haluat nähädä kaikki käyttäjät (Kijoita 4!");
            int komento = lukija.nextInt();
            lukija.nextLine();

            switch (komento) {

                case 1:
                    System.out.println("-------");
                    System.out.println("Käyttäjätunnuksen luonti");
                    System.out.print("Kirjoita kayttajanimi: ");
                    String kayttajanimi = lukija.nextLine();

                    System.out.print("Kirjoita sahkoposti: ");
                    String sahkoposti = lukija.nextLine();

                    System.out.print("Kirjoita salasana: ");
                    String salasana = lukija.nextLine();
                    Statement lisaa = connection.createStatement(); // Yhdistyy statementin kanssa pystyy lisäämään.
                    lisaa.executeUpdate("INSERT INTO kayttajat VALUES('" + kayttajanimi + "', '" + sahkoposti + "', '" + salasana + "')");
                    System.out.println("Käyttäjä lisätty tietokantaan!");
                    break;

                case 2:
                    System.out.println("-------");
                    System.out.println("Kirjautuminen sisään");

                    System.out.print("Kirjoita kayttajanimi: ");
                    String kayttaja = lukija.nextLine();

                    System.out.print("Kirjoita salasana: ");
                    String sala = lukija.nextLine();

                    String kaikkikayttajatt = "SELECT * FROM kayttajat";
                    Statement tietty = connection.createStatement(); // Yhteys statementilla
                    ResultSet tuloksia = tietty.executeQuery(kaikkikayttajatt); // Specif mitä haetaan ja ottaa ylös ne tulokset olioon
                    while (tuloksia.next()) {
                        String kayttis = tuloksia.getString("kayttajanimi");
                        String salainen = tuloksia.getString("salasana");

                        if (kayttis.equals(kayttaja) && salainen.equals(sala)) {
                            System.out.println("");
                            System.out.println("Käyttäjä löytyi tietokannasta");
                            System.out.println("Pystyt kirjautumaan sisään!");
                            System.exit(0);
                        }
                    }
                    System.out.println(" ");
                    System.out.println("Käyttäjä ei löytynyt tietokannasta!");
                    System.out.println("Luo uusi käyttäjä käynistämällä ohjelma uudelleen ja valitsemalla 1 vaihtoehdon");
                    tietty.close();
                    break;

                case 3:
                    System.out.println("-------");
                    System.out.println("Käyttäjätunnuksen poisto");
                    System.out.print("Kirjoita kayttajanimi: ");
                    String poistakayttajanimi = lukija.nextLine();
                    String poisto = "DELETE FROM kayttajat WHERE kayttajanimi = ?";

                    PreparedStatement poistaja = connection.prepareStatement(poisto); // Eri statement jolla voi poistaa
                    poistaja.setString(1, poistakayttajanimi); // numero pakko olla edessä syytä en löytänyt
                    poistaja.executeUpdate(); // mitään ylläolevista ei tapahdu ilman tätä
                    System.out.println("Käyttäjä poistettu tietokantannasta!");
                    break;

                case 4:
                    int laskija = 1;
                    System.out.print("Kaikki käyttäjät tulostuvat alle ...");
                    System.out.println(" ");
                    String kaikkikayttajat = "SELECT * FROM kayttajat";
                    Statement kaikki = connection.createStatement(); // Yhteys statementilla
                    ResultSet tulokset = kaikki.executeQuery(kaikkikayttajat); // Specif mitä haetaan ja ottaa ylös ne tulokset olioon
                    while (tulokset.next()) {
                        String kayttis = tulokset.getString("kayttajanimi");
                        String sahkis = tulokset.getString("sahkoposti");
                        String salainen = tulokset.getString("salasana");
                        System.out.format("\n" + laskija + ". " + "Käyttajätunnus: " + kayttis + " Sähköposti: " + sahkis + " Salasana: " + salainen + "-->");
                        laskija++;
                    }
                    System.out.println("");
                    kaikki.close();
                    break;
            }

        } catch (Exception e) { // statement komennolle catch
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }

    public Connection get_connection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); // Ajuri jolla yhdistyminen onnistuu alla tiedot mihin pitää yhdistyä
            connection = DriverManager.getConnection("Lisää databasen URL", 
"Käyttäjätunnus", "Salasana");
        } catch (Exception e) { // connectiolle komennolle catch
            System.out.println(e);
        }
        return connection; // Palauttaa alussa olevaan objektiin yhteyden.
    }

}
    
