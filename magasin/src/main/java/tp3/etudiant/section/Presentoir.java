/**  Classe qui gère un présentoir
 * @author Zackary Dube & Rafael Carvalho Matias
 * @version 1.0 (2024-04-10)
 */

package tp3.etudiant.section;

import java.util.Comparator;
import tp3.application.AbstractProduit;
import tp3.etudiant.boite.Boite;
import tp3.etudiant.produit.AbstractInventaire;

import java.util.*;

public class Presentoir extends Stockage {
    private String presentoirId;
    private int numeroCategoriePresentoir;
    private Map<String, List<AbstractProduit>> contenuPresentoir;
    public final int NOMBRE_MAXIMUM_PRODUIT_PAR_ESPACE = 20;
    public final int NOMBRE_MAXIMUM_PRODUIT_PAR_PRESENTOIR = 100;

    // Constructeur de presentoir
    public Presentoir(String presentoirId, int numeroCategoriePresentoir) {
        assert !presentoirId.isEmpty() : "Le presentoir id ne doit pas etre null";
        assert numeroCategoriePresentoir >= 0 : "Le numero de categorie de presentoir ne doit pas etre null";
        contenuPresentoir = new HashMap<String, List<AbstractProduit>>();
        this.presentoirId = presentoirId;
        this.numeroCategoriePresentoir = numeroCategoriePresentoir;
    }

    public String getPresentoirId() {
        return this.presentoirId;
    }
    public int getNumeroCategoriePresentoir() {
        return this.numeroCategoriePresentoir;
    }

    /**
     * Méthode pour décrire le présentoir
     *
     * @param
     * @return String   Retourne une chaîne de caractères
     */
    public String decrit() {
        return presentoirId;
    }

    /**
     * Méthode qui ajoute les produits au contenu du présentoir
     *
     * @param AbstractProduit, String   Un produit et une chaîne de caractères pour désigner le nom du produit
     * @return boolean                  Retourne true s'il place le produit et false s'il ne le place pas
     */
    public boolean ajouteStockage(AbstractProduit produit, String nom) {
        boolean reussi = false;
        if (contenuPresentoir.values().size() < NOMBRE_MAXIMUM_PRODUIT_PAR_PRESENTOIR && (contenuPresentoir.get(produit.getNom()) == null || contenuPresentoir.get(produit.getNom()).size() < NOMBRE_MAXIMUM_PRODUIT_PAR_ESPACE)) {
            contenuPresentoir.computeIfAbsent(nom, k -> new ArrayList<>()).add(produit);
            reussi = true;
        }
        return reussi;
    }

    /**
     * Méthode pour placer une boîte dans le vrac ou présentoir
     *
     * @param Boite                         Contient jusqu'à concurrence de 10 produits de même catégorie
     * @return Collection<AbstractProduit>  Retourne une collection de produits qui n'ont pas pu être placés
     */
    public Collection<AbstractProduit> placerProduits(Boite produits) {
        Collection<AbstractProduit> produitNonPlacer = super.placerProduits(produits);
        trierListesDansMap();
        return produitNonPlacer;
    }

    /**
     * Méthode pour placer une collection de produit dans le présentoir
     *
     * @param Collection<AbstractProduit>  Collection de produits
     * @return boolean                     Retourne true s'il a été placé et false s'il n'a pas été placé
     */
    public boolean placerProduits(Collection<AbstractProduit> produits) {
        boolean reussi = super.placerProduits(produits);
        trierListesDansMap();
        return reussi;
    }

    /**
     * Méthode qui retourne les données de la map en collection
     *
     * @param
     * @return Collection<AbstractProduit>  Retourne une collection de produits
     */
    public Collection<AbstractProduit> getAllProduits() {
        Collection<AbstractProduit> produitsRetours = new ArrayList<AbstractProduit>();
        Collection<List<AbstractProduit>> produits = contenuPresentoir.values();
        for (List<AbstractProduit> produit : produits) {
            produit.forEach(toto -> produitsRetours.add(toto));
        }
        return produitsRetours;
    }

    /**
     * Méthode qui retourne la taille () du présentoir
     *
     * @param
     * @return int   Retourne la grosseur du présentoir
     */
    public int getSizePresentoir() {
        return getAllProduits().size();
    }

    /**
     * Méthode qui gère les surplus
     *
     * @param Collection<AbstractProduit>  Collection de produits
     * @return void
     */
    public void gereSurplus(Collection<AbstractProduit> restant) {
        // Ne rien gerer
    }

    /**
     * Méthode qui trie les éléments de la Map par numéro de série
     *
     * @param
     * @return void
     */
    public void trierListesDansMap() {
        for (Map.Entry<String, List<AbstractProduit>> entry : contenuPresentoir.entrySet()) {
            List<AbstractProduit> liste = entry.getValue();
            Collections.sort(liste, new Comparator<AbstractProduit>() {
                @Override
                public int compare(AbstractProduit produit1, AbstractProduit produit2) {
                    return Integer.compare(produit1.getNumSerie(), produit2.getNumSerie());
                }
            });
            System.out.println(liste);
        }
    }

    /**
     * Méthode pour retirer un produit d'un presentoir en particulier et retourne true s'il réussi et false s'il ne réussi pas
     *
     * @param AbstractProduit   Un produit d'AbstractProduit
     * @return boolean          retourne true s'il réussi à le retirer
     */
    public boolean retirerProduit(AbstractProduit produit) {
        boolean produitRetirer = false;
        if (contenuPresentoir.containsKey(((AbstractInventaire) produit).getNom())) {
            List<AbstractProduit> listeProduit = contenuPresentoir.get(((AbstractInventaire) produit).getNom());
            if (listeProduit.contains(produit)) {
                listeProduit.remove(produit);
                if (listeProduit.isEmpty()) {
                    contenuPresentoir.remove(((AbstractInventaire) produit).getNom());
                }
                produitRetirer = true;
            }
        }
        return produitRetirer;
    }
}
