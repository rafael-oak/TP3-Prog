package tp3.etudiant.utilitaire;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class APropos {

    public String lireFichierAPropos() {
        String data = "";
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader("working/APropos.txt");
            br = new BufferedReader(fr);
            String ligneCourante = br.readLine();
            while (ligneCourante != null) {
                data += ligneCourante + "\n";
                ligneCourante = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

}
