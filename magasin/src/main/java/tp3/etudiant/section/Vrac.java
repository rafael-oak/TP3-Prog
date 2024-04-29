/**  Classe qui gère le vrac
 * @author Zackary Dube & Rafael Carvalho Matias
 * @version 1.0 (2024-04-10)
 */

package tp3.etudiant.section;

import tp3.application.AbstractProduit;
import tp3.etudiant.boite.Boite;
import tp3.etudiant.produit.AbstractInventaire;
import java.util.*;

public class Vrac extends Stockage implements AireI {
    private String sectionId = "Vrac";
    private Map<String, Set<AbstractProduit>> contenuVrac;
    private Charite charite;
    private float volumeTotal;
    public final float VOLUME_VRAC_MAX_DECIMETRE = 500;

    // Constructeur du VRAC
    public Vrac() {
        charite = new Charite();
        contenuVrac = new HashMap<String, Set<AbstractProduit>>();
    }

    /**
     * Méthode pour décrire le vrac
     *
     * @param
     * @return String   Retourne une chaîne de caractères
     */
    @Override
    public String decrit() {
        return sectionId + ", volume utilisé : " + this.volumeTotal + " / capacité totale : " + this.VOLUME_VRAC_MAX_DECIMETRE;
    }

    /**
     * Méthode qui ajoute les produits au contenu du vrac
     *
     * @param AbstractProduit, String   Un produit et une chaîne de caractères pour désigner le nom du produit
     * @return boolean                  Retourne true s'il place le produit et false s'il ne le place pas
     */
    public boolean ajouteStockage(AbstractProduit produit, String nom) {
        boolean reussi = false;
        if (((this.volumeTotal + ((AbstractInventaire) produit).getVolumeProduit()) < VOLUME_VRAC_MAX_DECIMETRE)) {
            contenuVrac.computeIfAbsent(produit.getNom(), k -> new HashSet<>()).add(produit);
            this.volumeTotal += ((AbstractInventaire) produit).getVolumeProduit();
            reussi = true;
        }
        return reussi;
    }

    /**
     * Méthode pour placer une boîte dans le vrac
     *
     * @param Boite                         Contient jusqu'à concurrence de 10 produits de même catégorie
     * @return Collection<AbstractProduit>  Retourne une collection de produits qui n'ont pas pu être placés
     */
    @Override
    public Collection<AbstractProduit> placerProduits(Boite produits) {
        Collection<AbstractProduit> produitNonPlacer = super.placerProduits(produits);
        return produitNonPlacer;
    }

    /**
     * Méthode pour placer une collection de produit dans le vrac
     *
     * @param Collection<AbstractProduit>  Collection de produits
     * @return boolean                     Retourne true s'il a été placé et false s'il n'a pas été placé
     */
    @Override
    public boolean placerProduits(Collection<AbstractProduit> produits) {
        boolean reussi = super.placerProduits(produits);
        return reussi;
    }

    /**
     * Méthode qui retourne les données de la map en collection
     *
     * @param
     * @return Collection<AbstractProduit>  Retourne une collection de produits
     */
    @Override
    public Collection<AbstractProduit> getAllProduits() {
        Collection<AbstractProduit> produitsRetours = new ArrayList<AbstractProduit>();
        Collection<Set<AbstractProduit>> produits = contenuVrac.values();
        for (Set<AbstractProduit> produit : produits) {
            produit.forEach(toto -> produitsRetours.add(toto));
        }
        return produitsRetours;
    }

    /**
     * Méthode qui gère les surplus
     *
     * @param Collection<AbstractProduit>  Collection de produits
     * @return void
     */
    @Override
    public void gereSurplus(Collection<AbstractProduit> restant) {
        charite.donneProduits(restant);
    }


    /**
     * Méthode permettant de retirer une collection de produits de la liste de vrac
     *
     * @param Collection<AbstractProduit>   une collection de produits
     * @return Collection<AbstractProduit>  "Retourne une collection de tous les produits en retirant les produits passés en paramètre
     */
    @Override
    public Collection<AbstractProduit> retireProduits(Collection<AbstractProduit> items) {
        Collection<AbstractProduit> collection = getAllProduits();
        viderAire();
        collection.removeAll(items);
        placerProduits(collection);
        return collection;
    }

    /**
     * Permet de vider la liste du vrac
     *
     * @param
     * @return void
     */
    @Override
    public void viderAire() {
        contenuVrac.clear();
        volumeTotal = 0;
    }

    /**
     * Méthode permettant de retourner la collection de charité
     *
     * @param
     * @return Charite   Retourne la charite
     */
    public Charite getCharite() {
        return charite;
    }
}
