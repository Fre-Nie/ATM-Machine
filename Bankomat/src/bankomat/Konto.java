package bankomat;
// Skriven av Fredrik Niemi
import java.io.*;

public class Konto {

    public int pinKod;
    public int kontoNummer;
    public int saldo;
    protected String användare;
    public String transaktioner;

    public Konto(int pinKod, int kontoNummer, int saldo, String användare) {
        this.pinKod = pinKod;
        this.kontoNummer = kontoNummer;
        this.saldo = saldo;
        this.användare = användare;
    }

    public void insättning(int insättning, String användare) {

        saldo = saldo + insättning;
        transaktioner = användare + " " + String.valueOf(insättning) + "kr Saldo: " + saldo;
        saveOnFile(transaktioner, användare);

    }

    public void uttag(int uttag, String användare) {
    	if (saldo < uttag) {
    		System.out.println("Du har inte så mycket pengar på kontot, försök igen!");
    	}
    	else {
    		saldo = saldo - uttag;
            transaktioner = användare + " -" + String.valueOf(uttag) + " \tSaldo: "+ saldo;
            saveOnFile(transaktioner, användare);
    	}
    }

    public void saveOnFile(String transaktioner, String användare) {
        String filNamn = användare + "s transaktioner.txt";
        boolean append; 
        try {
            FileWriter fileWriter = new FileWriter(filNamn, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(transaktioner);
            bufferedWriter.newLine();
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("Fel! Gick ej att spara transaktionerna.");
        }
    }
}
