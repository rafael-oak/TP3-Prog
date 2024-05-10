/**  Classe qui gère le stockage des présentoirs et du vrac
 * @author Zackary Dube & Rafael Carvalho Matias
 * @version 1.0 (2024-04-10)
 */

package tp3.etudiant.section;

import tp3.application.AbstractProduit;
import tp3.etudiant.boite.Boite;
import java.util.*;

public abstract class Stockage {
    public Stockage() {

    }

    /**
     * Méthode abstraite permettant de gérer les produits en surplus
     *
     * @param Collection<AbstractProduit>   une collection de produits
     * @return void
     */
    public abstract void gereSurplus(Collection<AbstractProduit> restant);

    /**
     * Méthode abstraite permettant d'ajouter un produit dans le vrac ou présentoir
     *
     * @param AbstractProduit, String   Un produit et une chaîne de caractères désignant le nom du produit
     * @return boolean                  Retourne true s'il réussit à placer le produit et false s'il échoue
     */
    public abstract boolean ajouteStockage(AbstractProduit produit, String nom);

    /**
     * Méthode pour placer une boîte dans le vrac ou le présentoir
     *
     * @param Boite                         Contient jusqu'à concurrence de 10 produits de même catégorie
     * @return Collection<AbstractProduit>  Retourne une collection de produits qui n'ont pas pu être placés
     */
    public Collection<AbstractProduit> placerProduits(Boite produits) {
        Collection<AbstractProduit> produitNonPlacer = new ArrayList<>();
        String nom = produits.getContenu().get(0).getNom();
        Iterator<AbstractProduit> iter = produits.getContenu().iterator();
        while (iter.hasNext()) {
            AbstractProduit produit = iter.next();
            if (!ajouteStockage(produit, nom)) {
                produitNonPlacer.add(produit);
            }
        }
        return produitNonPlacer;
    }

    /**
     * Méthode pour placer une collection de produit dans le vrac
     *
     * @param Collection<AbstractProduit>  Collection de produits
     * @return boolean                     Retourne true s'il a été placé et false s'il n'a pas été placé
     */
    public boolean placerProduits(Collection<AbstractProduit> produits) {
        Collection<AbstractProduit> produitNonPlacer = new ArrayList<AbstractProduit>();
        Iterator<AbstractProduit> iter = produits.iterator();
        boolean reussi = true;
        while (iter.hasNext()) {
            AbstractProduit produit = iter.next();
            if (!ajouteStockage(produit, produit.getNom())) {
                reussi = false;
                produitNonPlacer.add(produit);
            }
        }
        if (!reussi) {
            gereSurplus(produitNonPlacer);
        }
        return reussi;
    }
}
