package hedelmapotti;

import java.util.ArrayList;
import java.util.Random;

public class potti {

    private int lukitus;
    private int voitto;
    private int saldo;
    private int panos;
    private int yhVoitto;
    private Random random;
    private ArrayList<Integer> rulla;

    public potti(int saldo, int panos) {
        this.saldo = saldo;
        this.panos = panos;
        this.rulla = new ArrayList<>();
        this.random = new Random();
        this.lukitus = 0;
        this.voitto = 0;
        this.yhVoitto = 0;
        
    }

    public boolean tarkistaSaldo() {
        boolean bool = true;
        int saldo1 = getSaldo();
        int panos1 = getPanos();

        if (saldo1 >= panos1) {
            int saldonmuuttaja = saldo1 - panos1;
            setSaldo(saldonmuuttaja);
            bool = true;
        } else {
            System.out.println("Ei tarpeeksi Rahaa! Pelit Loppu tähän!");
            bool = false;

        }
        return bool;
    }

    public void kierros() {
        int i = 0;
        while(i < 3){
        ArrayList<Integer> lista = lista();
        int eka = lista.get(lukitus);
        lukitus++;
        int toka = lista.get(lukitus);
        lukitus++;
        int kolmas = lista.get(lukitus);
        lukitus++;
        System.out.println("");
        System.out.println("    Rahat: " + getSaldo());
        System.out.println("    Panos: " + getPanos());
        System.out.println(" _____________");
        System.out.println(" | " + eka + " | " + toka + " | " + kolmas + " | ");
        System.out.println(" _____________");
        System.out.println(" ");

        if (eka == 1 && toka == 1 && kolmas == 1) {
            int pano = (10 * getPanos()) + getSaldo();
            setSaldo(pano);
            setVoitto(10 * getPanos());
            System.out.println("Voitit: " + 10 * getPanos() + " euroa");
        } else if (eka == 2 && toka == 2 && kolmas == 2) {
            int pano = (8 * getPanos()) + getSaldo();
            setSaldo(pano);
            setVoitto(8 * getPanos());
            System.out.println("Voitit: " + 8 * getPanos() + " euroa");
        } else if (eka == 3 && toka == 3 && kolmas == 3) {
            int pano = (6 * getPanos()) + getSaldo();
            setSaldo(pano);
            setVoitto(6 * getPanos());
            System.out.println("Voitit: " + 6 * getPanos() + " euroa");
        } else if (eka == 4 && toka == 4 && kolmas == 4) {
            int pano = (4 * getPanos()) + getSaldo();
            setSaldo(pano);
            setVoitto(4 * getPanos());
            System.out.println("Voitit: " + 4 * getPanos() + " euroa");
        } else if (eka == 5 && toka == 5 && kolmas == 5) {
            int pano = (2 * getPanos()) + getSaldo();
            setSaldo(pano);
            setVoitto(2 * getPanos());
            System.out.println("Voitit: " + 2 * getPanos() + " euroa");
        } else if (eka == 1 || kolmas == 1) {
            int pano = (2 * getPanos()) + getSaldo();
            setSaldo(pano);
            setVoitto(2 * getPanos());
            System.out.println("Voitit: " + 2 * getPanos() + " euroa");
        } else {
            setVoitto(0);
            System.out.println("Ei voittoa tällä kieroksella:");
        }
        i++;
        getSetYhteisVoitto(getVoitto());
        }
        
        
    }

    public ArrayList lista() {
        int eka = satunnainen();
        int toka = satunnainen();
        int kolmas = satunnainen();

        this.rulla.add(eka);
        this.rulla.add(toka);
        this.rulla.add(kolmas);

        return this.rulla;
    }

    public int satunnainen() {
        int satu = random.nextInt(101);
        // Isommat prosentit tällä tavalla pienemille voitoille.
        if (satu >= 70 && satu <= 100) {
            satu = 5;
        } else if (satu >= 45 && satu <= 69) {
            satu = 4;
        } else if (satu >= 25 && satu <= 44) {
            satu = 3;
        } else if (satu >= 10 && satu <= 24) {
            satu = 2;
        } else if (satu >= 0 && satu <= 9) {
            satu = 1;
        }
        return satu;
    }

    public void lisaaPanos() {
        if (getPanos() < 5) {
            int a = 1 + getPanos();
            setPanos(a);
        } else {
            System.out.println("  ");
            System.out.println("Panosta ei voi suurentaa");
        }
    }

    public void vahennaPanos() {
        if (getPanos() > 1) {
            int a = getPanos() - 1;
            setPanos(a);
        } else {
            System.out.println("  ");
            System.out.println("Panosta ei voi pienentää");
        }
    }

    public int tuplaus() {
        int kruuna = 0;
        int klaava = 1;
        int i = random.nextInt(2);
        
        if (i == 0){
            return kruuna;
        }else{
            return klaava;
        }
    }
    public int getSetYhteisVoitto(int muuttuja){
        yhVoitto = yhVoitto + muuttuja;
        if(muuttuja == 50000){
            yhVoitto = 0;
        }
        return  yhVoitto;
    }

    public int getVoitto() {
        return this.voitto;
    }

    public int getPanos() {
        return this.panos;
    }

    public int getSaldo() {
        return this.saldo;
    }

    public void setVoitto(int voitto) {
        this.voitto = voitto;
    }

    public void setPanos(int panoksenmuuttaja) {
        this.panos = panoksenmuuttaja;
    }

    public void setSaldo(int saldonmuuttaja) {
        this.saldo = saldonmuuttaja;
    }
}
