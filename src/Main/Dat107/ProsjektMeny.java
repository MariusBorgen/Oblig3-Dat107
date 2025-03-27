package Main.Dat107;

import java.util.Scanner;
import DAO.Dat107.ProsjektDAO;
import Entity.Dat107.Prosjekt;
import java.util.List;

public class ProsjektMeny {

    private static Scanner scanner = new Scanner(System.in);
    private ProsjektDAO prosjektDAO = new ProsjektDAO();

    public void visMeny() {
        boolean fortsett = true;

        while (fortsett) {
            System.out.println("\n--- Prosjektmeny ---");
            System.out.println("1. Vis alle prosjekter");
            System.out.println("2. Legg til nytt prosjekt");
            System.out.println("3. Finn prosjekt med ID");
            System.out.println("4. Tilbake til hovedmeny");
            System.out.print("Velg et alternativ: ");

            int valg = scanner.nextInt();
            scanner.nextLine();

            switch (valg) {
                case 1 -> visAlleProsjekter();
                case 2 -> leggTilProsjekt();
                case 3 -> finnProsjektMedId();
                case 4 -> fortsett = false;
                default -> System.out.println("Ugyldig valg. Prøv igjen.");
            }
        }
    }

    private void visAlleProsjekter() {
        List<Prosjekt> prosjekter = prosjektDAO.hentAlleProsjekter();
        if (prosjekter.isEmpty()) {
            System.out.println("Ingen prosjekter funnet.");
        } else {
            prosjekter.forEach(System.out::println);
        }
    }

    private void leggTilProsjekt() {
        System.out.print("Navn på nytt prosjekt: ");
        String navn = scanner.nextLine();
        System.out.print("Beskrivelse: ");
        String beskrivelse = scanner.nextLine();
        prosjektDAO.leggTilProsjekt(navn, beskrivelse);
        System.out.println("Prosjekt lagt til.");
    }

    private void finnProsjektMedId() {
        System.out.print("Skriv inn prosjekt-ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Prosjekt p = prosjektDAO.finnProsjektMedId(id);
        System.out.println(p != null ? p : "Fant ikke prosjekt med ID " + id);
    }
}

