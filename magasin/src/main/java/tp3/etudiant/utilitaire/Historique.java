package tp3.etudiant.utilitaire;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Historique {
    public void ajoutHistorique(String ligne) {
        LocalDateTime now = LocalDateTime.now();
        String dateNow = now.format(DateTimeFormatter.ofPattern("dd-MM-uu : hh:mm:ss:SS"));
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter("working/Historique.txt", true);
            bw = new BufferedWriter(fw);
            bw.write(String.format("%s -> %s\n", dateNow, ligne));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Une erreur s'est produite");
        } catch (IOException ioe) {
            System.out.println("Une erreur s'est produite");
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException ioe) {
                System.out.println("Une erreur s'est produite");
            }
        }
    }
}
