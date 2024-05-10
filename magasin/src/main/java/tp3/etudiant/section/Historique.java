package tp3.etudiant.section;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Historique {

    private LocalDateTime unlocalDateTime = LocalDateTime.now();
    public void ecrireFichierTexte(String... phrases) {
        unlocalDateTime.format(DateTimeFormatter.ofPattern("dd-MM-uu : hh:mm:ss:SS"));

        FileWriter fw = null;
        BufferedWriter bw = null;

        try{
            fw = new FileWriter("historique.txt", true);
            bw = new BufferedWriter(fw);

            for (int i = 0; i < phrases.length; i++) {
                bw.write(unlocalDateTime + "->" + phrases[i]);
                bw.write(System.lineSeparator());
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void ouvertureAppli(){

    }
    public void creationEffacementProduit(){

    }
    public void commandeProduits(){

    }
    public void transfertProduitSection(){

    }
    public void transfertProduitPanier(){

    }
    public void achatProduit(){

    }
    public void fermetureAppli(){

    }
    public void enregistrementMagasin(){

    }


}
