package bankomat;
// Skriven av Fredrik Niemi
import java.io.*;

public class Konto {

    public int pinKod;
    public int kontoNummer;
    public int saldo;
    protected String anv�ndare;
    public String transaktioner;

    public Konto(int pinKod, int kontoNummer, int saldo, String anv�ndare) {
        this.pinKod = pinKod;
        this.kontoNummer = kontoNummer;
        this.saldo = saldo;
        this.anv�ndare = anv�ndare;
    }

    public void ins�ttning(int ins�ttning, String anv�ndare) {

        saldo = saldo + ins�ttning;
        transaktioner = anv�ndare + " " + String.valueOf(ins�ttning) + "kr Saldo: " + saldo;
        saveOnFile(transaktioner, anv�ndare);

    }

    public void uttag(int uttag, String anv�ndare) {
    	if (saldo < uttag) {
    		System.out.println("Du har inte s� mycket pengar p� kontot, f�rs�k igen!");
    	}
    	else {
    		saldo = saldo - uttag;
            transaktioner = anv�ndare + " -" + String.valueOf(uttag) + " \tSaldo: "+ saldo;
            saveOnFile(transaktioner, anv�ndare);
    	}
    }

    public void saveOnFile(String transaktioner, String anv�ndare) {
        String filNamn = anv�ndare + "s transaktioner.txt";
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
