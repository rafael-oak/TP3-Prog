package tp3.etudiant.client;

import tp3.application.AbstractProduit;
import tp3.etudiant.section.AireDesPresentoirs;
import tp3.etudiant.section.Presentoir;
import tp3.etudiant.section.Vrac;

import java.util.*;

public class Panier {
    private Collection<AbstractProduit> listePanier;

    public Panier() {
        listePanier = new ArrayList<AbstractProduit>();
    }

    public void ajouteProduit(AbstractProduit produit) {
        listePanier.add(produit);
    }

    public List<AbstractProduit> getAchats() {
        return null;
    }

    public void vide() {
        listePanier.clear();
    }

    public boolean retireProduit(AbstractProduit produit){
        boolean reussi = false;
        if (listePanier.remove(produit)) {
            reussi = true;
        }
        return reussi;
    }

    // Methode que zack a ajouter
    public Collection<AbstractProduit> getPanier() {
        return listePanier;
    }
}
