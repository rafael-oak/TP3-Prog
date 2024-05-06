package tp3.etudiant.section;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Historique {
    public void ecrireFichierTexte(String... phrases) {


        FileWriter fw = null;
        BufferedWriter bw = null;

        try{
            fw = new FileWriter("historique.txt");
            bw = new BufferedWriter(fw);

            for (int i = 0; i < phrases.length; i++) {
                bw.write(phrases[i]);
                bw.write(System.lineSeparator());
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if (bw != null){
                    bw.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }


    }

}
