package bankomat;

import java.util.Scanner;
import java.io.*;

public class Bankomat {

    public static void main(String[] args) {
        boolean inloggad = false;
        boolean ig�ng = true;

        //L�sernord, kontonummer, saldo, namn
        Konto[] konto = new Konto[5];
        konto[0] = new Konto(1227, 0, 1000, "Korre");
        konto[1] = new Konto(1337, 1, 300, "Palle");
        konto[2] = new Konto(1234, 2, 8000, "Berry");
        konto[3] = new Konto(4321, 3, 100, "Gunner");
        konto[4] = new Konto(8765, 4, 10000, "Urban");

        while (ig�ng == true) {

            Scanner input = new Scanner(System.in);
            System.out.println(" ");
            System.out.println("V�lkommen till Bankomaten!");
            System.out.print("Ange Kundnummer: ");
            int kontoNummer = input.nextInt();
          
            String filNamn = konto[kontoNummer].anv�ndare + "s transaktioner.txt";
            String line = null;

            try {
                FileReader fileReader = new FileReader(filNamn);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while ((line = bufferedReader.readLine()) != null) {
                    String[] tokens = line.split("\\s+");
                    konto[kontoNummer].saldo = Integer.parseInt(tokens[3]);
                }
                bufferedReader.close();
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
                
            }
            
            while (kontoNummer != konto[0].kontoNummer && kontoNummer != konto[1].kontoNummer
                    && kontoNummer != konto[2].kontoNummer && kontoNummer != konto[2].kontoNummer
                    && kontoNummer != konto[3].kontoNummer && kontoNummer != konto[4].kontoNummer) {
                System.out.println("Felaktig inmatning av kontonummer. Vargod f�rs�k igen.");
                System.out.println(" ");
                System.out.print("Ange Kundnummer: ");
                kontoNummer = input.nextInt();

            }

            System.out.print("Ange PIN-Kod: ");
            int pinKod = input.nextInt();

            while (pinKod != konto[kontoNummer].pinKod && kontoNummer == konto[kontoNummer].kontoNummer) {
                System.out.println("Felaktig inmatning av pinkod. Vargod f�rs�k igen. ");
                System.out.println(" ");
                System.out.print("Ange PIN-Kod: ");
                pinKod = input.nextInt();
            }

            if (kontoNummer == konto[kontoNummer].kontoNummer
                    && pinKod == konto[kontoNummer].pinKod) {

                inloggad = true;
                int user = kontoNummer;
                while (inloggad == true) {
                    System.out.println(" ");
                    System.out.println("V�lkommen " + konto[kontoNummer].anv�ndare + "! Ditt saldo �r "
                            + konto[user].saldo + " kr.");
                    
                    System.out.println("1: Ins�ttning.");
                    System.out.println("2: Uttag.");
                    System.out.println("3: Se transaktioner.");
                    System.out.println("4: Avsluta. ");
                    System.out.println(" ");
                    System.out.print("V�lj anternativ: ");
                    int val = input.nextInt();
                    if (val != 1 && val != 2 && val != 3 && val != 4){
                        System.out.println("\b Felaktigt val!");
                    }
                    switch (val) {
                        case 1:
                            System.out.println(" ");
                            System.out.print("Hur mycket vill du s�tta in? ");
                            int s�ttaIn = input.nextInt();
                            konto[kontoNummer].ins�ttning(s�ttaIn, konto[kontoNummer].anv�ndare);
                            System.out.println(" ");
                            System.out.println("Ditt saldo�r "
                                    + (konto[kontoNummer].saldo) + "kr. Tack s� mycket!");
                            break;
                        case 2:
                            System.out.println(" ");
                            System.out.print("Hur mycket vill du ta ut? ");
                            int taUt = input.nextInt();
                            konto[kontoNummer].uttag(taUt, konto[kontoNummer].anv�ndare);                           
                            System.out.println(" ");
                            System.out.println("Ditt saldo �r "
                                    + (konto[kontoNummer].saldo) + "kr.");
                            break;

                        case 3:
                            try {
                                FileReader fileReader = new FileReader(filNamn);
                                BufferedReader bufferedReader = new BufferedReader(fileReader);

                                while ((line = bufferedReader.readLine()) != null) {
                                    System.out.println(line);
                                }
                                bufferedReader.close();
                            } catch (FileNotFoundException ex) {
                                System.out.println(
                                        "Kunde inte l�sa '"
                                        + filNamn + "'");
                            } catch (IOException ex) {
                                System.out.println(
                                        "Fel med att l�sa '"
                                        + filNamn + "'");
                            }
                        case 4:
                            inloggad = false;
                            break;

                    }
                }

            }

        }
    }

}
