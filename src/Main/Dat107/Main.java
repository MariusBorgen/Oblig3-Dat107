package Main.Dat107;

import java.time.LocalDate;
import java.util.List;
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
            System.out.println("2. Søk etter ansatt på brukernavn");
            System.out.println("3. Vis alle ansatte");
            System.out.println("4. Oppdater ansatt sin stilling og/eller lønn");
            System.out.println("5. Legg til ny ansatt");
            System.out.println("6. Avslutt");
            System.out.print("Velg et alternativ: ");
            
            int valg = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (valg) {
                case 1:
                    System.out.print("Skriv inn ansatt-ID: ");
                    int ansattId = scanner.nextInt();
                    Ansatt ansatt = ansattDAO.finnAnsattMedId(ansattId);
                    
                    if (ansatt != null) {
                        ansatt.skrivUt();
                    } else {
                        System.out.println("Ingen ansatt funnet med ID " + ansattId);
                    }
                    break;
                    
                case 2:
                    System.out.print("Skriv inn brukernavn: ");
                    String brukernavn = scanner.nextLine();
                    Ansatt ansattBruker = ansattDAO.finnAnsattMedBrukernavn(brukernavn);
                    
                    if (ansattBruker != null) {
                        ansattBruker.skrivUt();
                    }
                    break;
                    
                case 3:
                    List<Ansatt> ansatte = ansattDAO.hentAlleAnsatte();
                    if (ansatte.isEmpty()) {
                        System.out.println("Ingen ansatte funnet.");
                    } else {
                        ansatte.forEach(Ansatt::skrivUt);
                    }
                    break;
                    
                case 4:
                    System.out.print("Skriv inn ansatt-ID: ");
                    int oppdaterId = scanner.nextInt();
                    scanner.nextLine(); 
                    
                    System.out.print("Ny stilling (Enter for å beholde): ");
                    String nyStilling = scanner.nextLine();
                    nyStilling = nyStilling.isEmpty() ? null : nyStilling;
                    
                    System.out.print("Ny månedslønn (0 for å beholde): ");
                    double nyLonn = scanner.nextDouble();
                    Double nyLonnVal = (nyLonn == 0) ? null : nyLonn;
                    
                    ansattDAO.oppdaterAnsatt(oppdaterId, nyStilling, nyLonnVal);
                    System.out.println("Ansatt oppdatert.");
                    break;
                    
                case 5:
                    System.out.print("Brukernavn: ");
                    String nyttBrukernavn = scanner.nextLine();
                    
                    System.out.print("Fornavn: ");
                    String fornavn = scanner.nextLine();
                    
                    System.out.print("Etternavn: ");
                    String etternavn = scanner.nextLine();
                    
                    System.out.print("Stilling: ");
                    String stilling = scanner.nextLine();
                    
                    System.out.print("Månedslønn: ");
                    double maanedslonn = scanner.nextDouble();
                    
                    LocalDate ansettelsesdato = LocalDate.now();
                    
                    ansattDAO.leggTilAnsatt(nyttBrukernavn, fornavn, etternavn, ansettelsesdato, stilling, maanedslonn);
                    System.out.println("Ny ansatt lagt til.");
                    break;
                    
                case 6:
                    System.out.println("Avslutter programmet...");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Ugyldig valg, prøv igjen.");
            }
        }
    }
}
