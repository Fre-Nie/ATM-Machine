package bankomat;

import java.util.Scanner;
import java.io.*;

public class Bankomat {

    public static void main(String[] args) {
        boolean inloggad = false;
        boolean igång = true;

        //Lösernord, kontonummer, saldo, namn
        Konto[] konto = new Konto[5];
        konto[0] = new Konto(1227, 0, 1000, "Korre");
        konto[1] = new Konto(1337, 1, 300, "Palle");
        konto[2] = new Konto(1234, 2, 8000, "Berry");
        konto[3] = new Konto(4321, 3, 100, "Gunner");
        konto[4] = new Konto(8765, 4, 10000, "Urban");

        while (igång == true) {

            Scanner input = new Scanner(System.in);
            System.out.println(" ");
            System.out.println("Välkommen till Bankomaten!");
            System.out.print("Ange Kundnummer: ");
            int kontoNummer = input.nextInt();
          
            String filNamn = konto[kontoNummer].användare + "s transaktioner.txt";
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
                System.out.println("Felaktig inmatning av kontonummer. Vargod försök igen.");
                System.out.println(" ");
                System.out.print("Ange Kundnummer: ");
                kontoNummer = input.nextInt();

            }

            System.out.print("Ange PIN-Kod: ");
            int pinKod = input.nextInt();

            while (pinKod != konto[kontoNummer].pinKod && kontoNummer == konto[kontoNummer].kontoNummer) {
                System.out.println("Felaktig inmatning av pinkod. Vargod försök igen. ");
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
                    System.out.println("Välkommen " + konto[kontoNummer].användare + "! Ditt saldo är "
                            + konto[user].saldo + " kr.");
                    
                    System.out.println("1: Insättning.");
                    System.out.println("2: Uttag.");
                    System.out.println("3: Se transaktioner.");
                    System.out.println("4: Avsluta. ");
                    System.out.println(" ");
                    System.out.print("Välj anternativ: ");
                    int val = input.nextInt();
                    if (val != 1 && val != 2 && val != 3 && val != 4){
                        System.out.println("\b Felaktigt val!");
                    }
                    switch (val) {
                        case 1:
                            System.out.println(" ");
                            System.out.print("Hur mycket vill du sätta in? ");
                            int sättaIn = input.nextInt();
                            konto[kontoNummer].insättning(sättaIn, konto[kontoNummer].användare);
                            System.out.println(" ");
                            System.out.println("Ditt saldoär "
                                    + (konto[kontoNummer].saldo) + "kr. Tack så mycket!");
                            break;
                        case 2:
                            System.out.println(" ");
                            System.out.print("Hur mycket vill du ta ut? ");
                            int taUt = input.nextInt();
                            konto[kontoNummer].uttag(taUt, konto[kontoNummer].användare);                           
                            System.out.println(" ");
                            System.out.println("Ditt saldo är "
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
                                        "Kunde inte läsa '"
                                        + filNamn + "'");
                            } catch (IOException ex) {
                                System.out.println(
                                        "Fel med att läsa '"
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
