/**  Classe qui gère l'air des présentoirs
 * @author Zackary Dube  & Rafael Carvalho Matias
 * @version 1.0 (2024-04-10)
 */

package tp3.etudiant.section;

import tp3.application.AbstractProduit;
import tp3.etudiant.boite.Boite;
import tp3.etudiant.produit.AbstractInventaire;
import java.util.*;

public class AireDesPresentoirs implements AireI {
    private Vrac vrac;
    private List<Presentoir> listPresentoir;
    private String sectionId = "Aire des presentoirs";

    // Constructeur de AireDesPresentoirs
    public AireDesPresentoirs(Vrac vrac) {
        listPresentoir = new ArrayList<Presentoir>();
        this.vrac = vrac;
    }

    /**
     * Méthode permettant de décrire l'aire des présentoirs et de les trier
     *
     * @param
     * @return String   Chaîne de caractères
     */
    @Override
    public String decrit() {
        trierListePresentoir();
        String produitString = " ";
        Iterator<Presentoir> iter = listPresentoir.iterator();
        while (iter.hasNext()) {
            Presentoir presentoir = iter.next();
            produitString += "\n" + presentoir.decrit();
        }
        return sectionId + "\n" + produitString;
    }

    /**
     * Méthode permettant de trouver le bon présentoir (s'il existe déjà) et d'y placer la boîte de produits reçue en paramètre
     * Si le présentoir n'existe pas, il le crée
     *
     * @param Boite                          Contient jusqu'à concurrence de 10 produits de même catégorie
     * @return Collection<AbstractProduit>   Retourne une collection de produits
     */
    @Override
    public Collection<AbstractProduit> placerProduits(Boite produits) {
        assert produits != null : "la boite ne doit pas etre null pour placer la boite dans un presentoir";
        Collection<AbstractProduit> collectionProduit = new ArrayList<AbstractProduit>();
        String nomCategorie = ((AbstractInventaire) produits.getContenu().get(0)).getNomClass();
        int categorie = produits.getContenu().get(0).getNumeroCategorie();
        boolean found = false;
        Iterator<Presentoir> iter = listPresentoir.iterator();
        while (iter.hasNext() && !found) {
            Presentoir presentoir = iter.next();
            if (presentoir.getNumeroCategoriePresentoir() == categorie) {
                collectionProduit.addAll(presentoir.placerProduits(produits));
                found = true;
            }
        }
        if (!found) {
            Presentoir presentoir = new Presentoir(nomCategorie, categorie);
            listPresentoir.add(presentoir);
            collectionProduit.addAll(presentoir.placerProduits(produits));
        }
        return collectionProduit;
    }

    /**
     * Méthode permettant de trouver le bon présentoir (s'il existe déjà) et d'y placer une collection de produits reçue en paramètre
     * Si le présentoir n'existe pas, il le crée
     *
     * @param Collection<AbstractProduit>   une collection de produits
     * @return boolean                      Retourne true s'il réussit à placer tous les produits et false s'il ne réussit pas
     */
    @Override
    public boolean placerProduits(Collection<AbstractProduit> produits) {
        assert produits != null : "la collection de produits ne doit pas etre null pour la placer dans un presentoir";
        Iterator<AbstractProduit> iterProduit = produits.iterator();
        Iterator<Presentoir> iterPresentoir = listPresentoir.iterator();
        boolean produitPlacer = false;
        while (iterProduit.hasNext()) {
            AbstractProduit produit = iterProduit.next();
            while(iterPresentoir.hasNext() && !produitPlacer) {
                Presentoir presentoir = iterPresentoir.next();
                if (presentoir.getPresentoirId().equals(produit.getNom())) {
                    presentoir.placerProduits(produits);
                    produitPlacer = true;
                }
            }
            if (!produitPlacer) {
                Presentoir presentoir = new Presentoir(produit.getNom(), produit.getNumProduit());
                listPresentoir.add(presentoir);
                produitPlacer = presentoir.placerProduits(produits);
            }
        }
        return produitPlacer;
    }

    /**
     * Méthode permettant de retirer une collection de produits de la liste de présentoir
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
     * Permet de vider la liste des présentoirs
     *
     * @param
     * @return void
     */
    @Override
    public void viderAire() {
        listPresentoir.clear();
    }

    /**
     * Permet de convertir la List<Presentoir> en Collection<AbstractProduit>
     *
     * @param
     * @return Collection<AbstractProduit>  "Retourne une collection de tous les produits
     */
    @Override
    public Collection<AbstractProduit> getAllProduits() {
        Iterator<Presentoir> iter = listPresentoir.iterator();
        Collection<AbstractProduit> collection = new ArrayList<>();
        while(iter.hasNext()) {
            Presentoir presentoir = iter.next();
            collection.addAll(presentoir.getAllProduits());
        }
        return collection;
    }

    /**
     * Méthode permettant de gérer les produits en surplus dans les présentoirs afin de les envoyer à la
     * classe Presentoir, pour qu'elle puisse les ajouter à la map du vrac
     *
     * @param Collection<AbstractProduit>   une collection de produits
     * @return void
     */
    @Override
    public void gereSurplus(Collection<AbstractProduit> restant) {
        Collection<AbstractProduit> produitNonStocker = new ArrayList<AbstractProduit>();
        Boite boite = new Boite(restant.size(), 0);
        Iterator<AbstractProduit> iterProduit = restant.iterator();
        while (iterProduit.hasNext()) {
            AbstractProduit produit = iterProduit.next();
            boite.ajouteProduit(produit);
        }
        produitNonStocker.addAll(vrac.placerProduits(boite));
        vrac.gereSurplus(produitNonStocker);
    }

    /**
     * Méthode permettant de retourner la collection de charité
     *
     * @param
     * @return Charite   Retourne la charite
     */
    public Charite getCharite() {
        return vrac.getCharite();
    }

    /**
     * Méthode permettant de trier les éléments de la liste List<Presentoir> listPresentoir
     *
     * @param
     * @return void
     */
    public void trierListePresentoir() {
        Comparator<Presentoir> comparateurPresentoir = new Comparator<Presentoir>() {
            @Override
            public int compare(Presentoir p1, Presentoir p2) {
                return (p1.getSizePresentoir() - p2.getSizePresentoir());
            }
        };
        Collections.sort(listPresentoir, comparateurPresentoir);
    }
}
