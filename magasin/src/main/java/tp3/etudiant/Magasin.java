/**  Classe qui gère le magasin
 * @author Zackary Dube & Rafael Carvalho Matias
 * @version 1.0 (2024-04-10)
 */

package tp3.etudiant;

import tp3.application.AbstractProduit;
import tp3.echange.Descriptible;
import tp3.echange.Modele;
import tp3.echange.UI;
import tp3.etudiant.boite.Boite;
import tp3.etudiant.client.Achat;
import tp3.etudiant.client.Panier;
import tp3.etudiant.produit.AbstractInventaire;
import tp3.etudiant.section.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Magasin implements Modele {
    public final String SECTION_VRAC = "vrac";
    public final String SECTION_PRESENTOIR = "presentoir";
    private Entrepot entrepot;
    private AireDesPresentoirs aireDesPresentoirs;
    private Vrac vrac;
    private Collection<Achat> achats = new ArrayList<>();

    // Attribut pour le client
    private Panier panier;

    // Constructeur de Magasin
    public Magasin() {
        // Instanciez les attributs nécessaires
        entrepot = new Entrepot();
        vrac = new Vrac();
        panier = new Panier();
        aireDesPresentoirs = new AireDesPresentoirs(vrac);
    }

    /**
     * Permet à l'interface utilisateur de conserver une référence vers vos objets de section de magasin
     * (Présentoires et Vrac) afin de pouvoir interagir directement avec eux
     *
     * @param
     * @return void
     */
    @Override
    public Collection<AireI> getAllSections() {
        Collection<AireI> section = new ArrayList<AireI>();
        section.add(vrac);
        section.add(aireDesPresentoirs);
        return section;
    }

    /**
     * Permet à l'interface utilisateur de conserver une référence à votre objet Entrepôt afin de pouvoir
     * interagir directement avec lui (présenter son contenu, etc.)
     *
     * @param
     * @return Entrepot   Retourne l'objet Entrepot
     */
    public Entrepot getEntrepot() {
        return entrepot;
    }

    /**
     * Permet à l'interface utilisateur de conserver une référence à votre objet Charité afin de pouvoir
     * interagir directement avec lui (présenter son contenu)
     *
     * @param
     * @return Descriptible     Retourne le descriptible de charite
     */
    @Override
    public Descriptible getCharite() {
        return vrac.getCharite();
    }


    /**
     * Le magasin reçoit les boîtes que l'utilisateur a commandées, les place dans l'entrepôt,
     * puis retourne le nombre de boîtes qui n'ont pas pu être placées.
     *
     * @param Collection<Boite>  Collection de l'objet Boite
     * @return int               Retourne le nombre de boîtes qui n'ont pas pu être placées
     */
    @Override
    public int recevoirCommande(Collection<Boite> commande) {
        Iterator<Boite> iter = commande.iterator();
        int boitePlacer = 0;
        boolean reussi = true;
        while (iter.hasNext() && reussi) {
            if (entrepot.entreposeBoite(iter.next())) {
                boitePlacer++;
            } else {
                reussi = false;
            }
        }
        return commande.size() - boitePlacer;
    }

    /**
     * Méthode permettant de placer une collection de boîtes dans une section spécifique (Vrac, Présentoires, Charité)
     *
     * @param Collection<Boite>, AireI  Collection de l'objet Boîte et la section où placer la boîte (section)
     * @return void
     */
    public void placerProduits(Collection<Boite> boites, AireI section) {
        System.out.println("Je place le produit" + boites.toString());
        Collection<AbstractProduit> produits = new ArrayList<AbstractProduit>();
        Iterator<Boite> iter = boites.iterator();
        while (iter.hasNext()) {
            Boite boite = iter.next();
            produits.addAll(section.placerProduits(boite));
            entrepot.retireBoite(boite);
            if (produits.isEmpty()) {
                System.out.println("Rien a mettre dans la charite");
            } else {
                aireDesPresentoirs.gereSurplus(produits);
                System.out.println("Boite donner a la chariter");
            }
        }
    }










    @Override
    public void mettreDansPanier(Collection<AbstractProduit> items) {
        Iterator<AbstractProduit> iterItems = items.iterator();
        while (iterItems.hasNext()) {
            AbstractProduit produit = iterItems.next();
            if (vrac.getAllProduits().contains(produit)) {
                ((AbstractInventaire) produit).setSectionProduit("vrac");
            } else if (aireDesPresentoirs.getAllProduits().contains(produit)) {
                ((AbstractInventaire) produit).setSectionProduit("presentoir");
            }
            panier.ajouteProduit(produit);
        }
    }

    @Override
    public Achat acheterPanier(String acheteur, LocalDateTime date, double rabaisGlobal) {
        Achat achat = new Achat(acheteur, date, rabaisGlobal);
        achat.ajoutPanierDansAchat(panier.getPanier());
        panier.vide();
        return achat;
    }

    @Override
    public Collection<AbstractProduit> getContenuPanier() {
        return panier.getPanier();
    }

    @Override
    public void retirerDuPanier(List<AbstractProduit> itemARetirer) {
        Iterator<AbstractProduit> iterItemARetirer = itemARetirer.iterator();

        Collection<AbstractProduit> remettreDansVrac = new ArrayList<AbstractProduit>();
        Collection<AbstractProduit> remettreDansPresentoir = new ArrayList<AbstractProduit>();

        while (iterItemARetirer.hasNext()) {
            AbstractProduit produit = iterItemARetirer.next();
            if (panier.retireProduit(produit)) {
                if (((AbstractInventaire) produit).getSectionProduit() == SECTION_PRESENTOIR) {
                    remettreDansPresentoir.add(produit);
                } else if (((AbstractInventaire) produit).getSectionProduit() == SECTION_VRAC) {
                    remettreDansVrac.add(produit);
                }
            }
        }
        vrac.placerProduits(remettreDansVrac);
        aireDesPresentoirs.placerProduits(remettreDansPresentoir);
    }

    @Override
    public Collection<Boite> getLivraisons() {
        return null;
    }

    @Override
    public void archive(File file) {

    }

    @Override
    public void reconstruit(File file) {

    }

    @Override
    public void viderMagasin() {

    }

    @Override
    public String init(UI ui) {
        return null;
    }

    @Override
    public void stop() {

    }

    public Achat acheterPanier(String achateur, LocalDateTime dateTime) {
        return null;
    }
    /*
    @Override
    public void init() {
    }

     */
}
