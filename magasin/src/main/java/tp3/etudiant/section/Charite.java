/**  Classe qui gère la charite
 * @author Zackary Dube & Rafael Carvalho Matias
 * @version 1.0 (2024-04-10)
 */

package tp3.etudiant.section;

import java.util.Comparator;
import tp3.application.AbstractProduit;
import tp3.echange.Descriptible;
import tp3.etudiant.produit.AbstractInventaire;
import java.util.*;

public class Charite implements Descriptible {
    List<AbstractProduit> charite;
    private String sectionId = "Charite";

    // Constructeur de Charite
    public Charite() {
        this.charite = new ArrayList<AbstractProduit>();
    }

    /**
     * Méthode qui permet de retourner une collection contenant tous les produits de la Collection<AbstractProduit>
     *
     * @param
     * @return Collection<AbstractProduit>   Retourne une collection de produits
     */
    public Collection<AbstractProduit> getAllProduits() {
        return charite;
    }

    /**
     * Méthode qui permet de retourner une collection contenant tous les produits de la Collection<AbstractProduit>
     *
     * @param
     * @return Collection<AbstractProduit>   Retourne une collection de produits
     */
    public Collection<AbstractProduit> getCharite() {
        return charite;
    }

    /**
     * Méthode permettant d'ajouter une collection de produits à la Collection<AbstractProduit> de charité
     *
     * @param Collection<AbstractProduit>   Une collection de produits
     * @return void
     */
    public void donneProduits(Collection<AbstractProduit> produits) {
        charite.addAll(produits);
    }

    /**
     * Méthode permettant d'ajouter un produit (AbstractProduit) à la Collection<AbstractProduit> de charité
     *
     * @param AbstractProduit   Un produit
     * @return void
     */
    public void donneProduit(AbstractProduit produit) {
        charite.add(produit);
    }

    /**
     * Méthode qui permet de décrire la liste de charité
     *
     * @param
     * @return String   Retourne une chaîne de caractères
     */
    @Override
    public String decrit() {
        trierListeCharite();
        String decrit = "";
        String nomAfficher = "";
        if (charite.size() > 0) {
            Iterator<AbstractProduit> iterCharite = charite.iterator();
            while (iterCharite.hasNext()) {
                AbstractProduit produit = iterCharite.next();
                String nomClass = ((AbstractInventaire) produit).getNomClass();
                if (nomAfficher.isEmpty() || !nomClass.equals(nomAfficher)) {
                    nomAfficher = nomClass;
                    decrit += "\n" + nomAfficher + " : ";
                }
                decrit += "\n" + produit.getDate() + "-" + produit.getNom() + " " + produit.getNumeroCategorie() + "-" + produit.getNumSerie();
            }
        }
        return decrit;
    }

    /**
     * Méthode qui permet de trier la liste de charité par date de création et par nom de produit
     *
     * @param
     * @return void
     */
    public void trierListeCharite() {
        Comparator<AbstractProduit> comparateurPresentoir = new Comparator<AbstractProduit>() {
            @Override
            public int compare(AbstractProduit p1, AbstractProduit p2) {
                String p1Critere1 = p1.getNom();
                String p2Critere1 = p2.getNom();
                String p1Critere2 = p1.getDate().toString();
                String p2Critere2 = p2.getDate().toString();
                int comparaison = p1Critere1.compareTo(p2Critere1);
                if (comparaison != 0) {
                    return comparaison;
                }
                return p1Critere2.compareTo(p2Critere2);
            }
        };
        Collections.sort(charite, comparateurPresentoir);
    }
}
