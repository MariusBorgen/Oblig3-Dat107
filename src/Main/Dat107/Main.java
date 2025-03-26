package Main.Dat107;

import java.util.Scanner;
import DAO.Dat107.AnsattDAO;
import Entity.Dat107.Ansatt;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AnsattDAO ansattDAO = new AnsattDAO();
        
        while (true) {
            System.out.println("\n--- Meny ---");
            System.out.println("1. Søk etter ansatt på ID");
            System.out.println("2. Avslutt");
            System.out.print("Velg et alternativ: ");
            
            int valg = scanner.nextInt();
            
            switch (valg) {
                case 1:
                    System.out.print("Skriv inn ansatt-ID: ");
                    int id = scanner.nextInt();
                    Ansatt ansatt = ansattDAO.finnAnsattMedId(id);
                    
                    if (ansatt != null) {
                        ansatt.skrivUt();  
                    } else {
                        System.out.println("Ingen ansatt funnet med ID " + id);
                    }
                    break;
                case 2:
                    System.out.println("Avslutter programmet...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Ugyldig valg, prøv igjen.");
            }
        }
    }
}
