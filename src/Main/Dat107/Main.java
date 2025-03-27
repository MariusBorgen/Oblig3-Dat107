
package Main.Dat107;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import DAO.Dat107.AnsattDAO;
import Entity.Dat107.Ansatt;
import DAO.Dat107.AvdelingDAO;
import Entity.Dat107.Avdeling;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;


public class Main {
	
	static Scanner scanner = new Scanner(System.in);
    static AnsattDAO ansattDAO = new AnsattDAO();
    static AvdelingDAO avdelingDAO = new AvdelingDAO();
    
    public static void main(String[] args) {
    	
    	boolean verdi = true;
        
        
        while (verdi) {
            System.out.println("\n--- Meny ---");
            System.out.println("1. Søk etter ansatt på ID");
            System.out.println("2. Søk etter ansatt på brukernavn");
            System.out.println("3. Vis alle ansatte");
            System.out.println("4. Oppdater ansatt sin stilling og/eller lønn");
            System.out.println("5. Legg til ny ansatt");
            System.out.println("6. Avslutt");
            System.out.println("7. Vis ansatte i en avdeling");
            System.out.println("8. Legg til ny avdeling med sjef");
            System.out.println("9. Vis alle avdelinger");
            System.out.println("10. Gå til prosjektmeny");
            System.out.print("Velg et alternativ: ");
            
            try {
            	int valg = scanner.nextInt();
                scanner.nextLine(); 
                
                verdi = tilMenyen(valg);
            } catch(InputMismatchException e) {
            	System.out.println("Du må skrive tall..");
            	scanner.nextLine();
            }  
        }
    }
    public static boolean tilMenyen(int valg) {
    	
    	boolean avslutt = true;
    	
    	switch(valg) {
    	
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
                break;
            } else {
                break;
            }
            
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
    	    System.out.print("Skriv brukernavn: ");
    	    String br = scanner.nextLine();

    	    System.out.print("Fornavn: ");
    	    String fornavn = scanner.nextLine();

    	    System.out.print("Etternavn: ");
    	    String etternavn = scanner.nextLine();

    	    System.out.print("Stilling: ");
    	    String stilling = scanner.nextLine();

    	    System.out.print("Månedslønn: ");
    	    double maanedslonn = scanner.nextDouble();
    	    scanner.nextLine();

    	    LocalDate ansettelsesdato = LocalDate.now(); 

    	   
    	    List<Avdeling> avdelinger = avdelingDAO.hentAlleAvdelinger();
    	    System.out.println("Velg avdeling for den nye ansatte:");
    	    for (Avdeling avd : avdelinger) {
    	        System.out.println("ID: " + avd.getAvdelingId() + " | " + avd.getNavn());
    	    }

    	    System.out.print("Skriv inn ID for avdelingen: ");
    	    int avdId = scanner.nextInt();
    	    scanner.nextLine();

    	    Avdeling valgtAvdeling = avdelingDAO.finnAvdelingMedId(avdId);
    	    if (valgtAvdeling == null) {
    	        System.out.println("Ugyldig avdeling-ID.");
    	    } else {
    	        ansattDAO.leggTilAnsatt(br, fornavn, etternavn,
    	                ansettelsesdato, stilling, maanedslonn, valgtAvdeling);
    	        System.out.println("Ny ansatt er lagt til i avdeling: " + valgtAvdeling.getNavn());
    	    }
    	    break;

    	case 6:
            System.out.println("Avslutter programmet...");
            scanner.close();
            avslutt = false;
            break;
    	case 7: 
    		visAnsatteIVedAvdeling();
    	    break;
    	case 8:
    	    System.out.print("Navn på ny avdeling: ");
    	    String navn = scanner.nextLine();

    	    System.out.print("Skriv inn ansatt-ID for den som skal være sjef: ");
    	    int sjefId = scanner.nextInt();
    	    scanner.nextLine();

    	    Ansatt sjef = ansattDAO.finnAnsattMedId(sjefId);

    	    if (sjef == null) {
    	        System.out.print("Ansatt finnes ikke. Vil du registrere ny ansatt som sjef nå? (j/n): ");
    	        String svar = scanner.nextLine();

    	        if (svar.equalsIgnoreCase("j")) {
    	            sjef = leggTilNyAnsattFraMeny();
    	        } else {
    	            System.out.println("Avbryter opprettelse av avdeling.");
    	            break;
    	        }
    	    }

    	    avdelingDAO.leggTilAvdeling(navn, sjef);
    	    System.out.println("Avdeling '" + navn + "' opprettet med " +
    	            sjef.getFornavn() + " " + sjef.getEtternavn() + " som sjef.");
    	    break;
    	case 9:
    	    visAlleAvdelinger();
    	    break;
    	case 10:
    	    new ProsjektMeny().visMeny();
    	    break;


    	default:
            System.out.println("Ugyldig valg, prøv igjen."); 
    	}
    	return avslutt;
    	
    }
    private static void visAnsatteIVedAvdeling() {
        System.out.print("Skriv inn avdeling-ID: ");
        int avdId = scanner.nextInt();
        scanner.nextLine();

        Avdeling avdeling = avdelingDAO.finnAvdelingMedId(avdId);

        if (avdeling == null) {
            System.out.println("Fant ingen avdeling med ID " + avdId);
        } else {
            System.out.println("Avdeling: " + avdeling.getNavn());
            System.out.println("Ansatte:");

            List<Ansatt> ansatte = avdelingDAO.hentAnsatteIVedAvdeling(avdId);

            if (ansatte.isEmpty()) {
                System.out.println("(Ingen ansatte i denne avdelingen)");
            } else {
                for (Ansatt a : ansatte) {
                    boolean erSjef = avdeling.getSjef().getAnsattId() == a.getAnsattId();
                    String sjefMarkering = erSjef ? " (SJEF)" : "";
                    System.out.println("- " + a.getFornavn() + " " + a.getEtternavn() + sjefMarkering);
                }
            }
        }
    }
    private static void leggTilNyAvdeling() {
        System.out.print("Navn på ny avdeling: ");
        String navn = scanner.nextLine();

        System.out.print("Skriv inn ansatt-ID for den som skal være sjef: ");
        int sjefId = scanner.nextInt();
        scanner.nextLine();

        Ansatt nySjef = ansattDAO.finnAnsattMedId(sjefId);

        if (nySjef == null) {
            System.out.println("Fant ingen ansatt med ID " + sjefId);
        } else {
            avdelingDAO.leggTilAvdeling(navn, nySjef);
            System.out.println("Avdeling lagt til. Sjefen er flyttet til den nye avdelingen.");
        }
    }
    private static void visAlleAvdelinger() {
        List<Avdeling> avdelinger = avdelingDAO.hentAlleAvdelinger();

        if (avdelinger.isEmpty()) {
            System.out.println("Ingen avdelinger funnet.");
        } else {
            System.out.println("--- Liste over avdelinger ---");
            for (Avdeling avd : avdelinger) {
                String sjef = avd.getSjef() != null
                        ? avd.getSjef().getFornavn() + " " + avd.getSjef().getEtternavn()
                        : "(Ingen sjef)";
                System.out.printf("ID: %d | Navn: %s | Sjef: %s%n",
                        avd.getAvdelingId(), avd.getNavn(), sjef);
            }
        }
    }
    private static Ansatt leggTilNyAnsattFraMeny() {
    	String brukernavn;
    	
    	while (true) {
            System.out.print("Brukernavn (3-4 bokstaver): ");
            brukernavn = scanner.nextLine();

           
            if (ansattDAO.finnAnsattMedBrukernavn(brukernavn) != null) {
                System.out.println("Brukernavnet er allerede i bruk. Velg et annet.");
            } else {
                break;
            }
        }
        System.out.print("Brukernavn (3-4 bokstaver): ");
        String br = scanner.nextLine();

        System.out.print("Fornavn: ");
        String fornavn = scanner.nextLine();

        System.out.print("Etternavn: ");
        String etternavn = scanner.nextLine();

        System.out.print("Stilling: ");
        String stilling = scanner.nextLine();

        System.out.print("Månedslønn: ");
        double maanedslonn = scanner.nextDouble();
        scanner.nextLine();

        LocalDate ansettelsesdato = LocalDate.now();

        Ansatt nyAnsatt = new Ansatt(br, fornavn, etternavn,
                                      ansettelsesdato, stilling, maanedslonn);
        
        EntityManager em = Persistence.createEntityManagerFactory("ansattPersistenceUnit").createEntityManager();
        em.getTransaction().begin();
        em.persist(nyAnsatt);
        em.getTransaction().commit();
        em.close();

        return nyAnsatt;
    }

    

    

}
