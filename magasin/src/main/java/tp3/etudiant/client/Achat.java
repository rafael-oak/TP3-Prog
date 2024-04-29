package tp3.etudiant.client;

import tp3.echange.Descriptible;
import tp3.application.AbstractProduit;
import tp3.etudiant.produit.AbstractInventaire;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Achat implements Descriptible {
    Collection<AbstractProduit> listeAchat;
    private double montantBrute;
    private double rabaisGlobal;
    private double montantRabaisGlobal;
    private double montantRabaisProduit;
    private double montantTaxes;
    public final double TAXES_POUR_CE_TP = 14;
    private double montantTotal;


    private String acheteur;
    private int numFacturation = 0;
    private static int compteurFacture = 0;
    private LocalDateTime momentAchat;

    public Achat(String acheteur, LocalDateTime momentAchat, double rabaisGlobal) {
        this.acheteur = acheteur;
        this.momentAchat = momentAchat;
        this.rabaisGlobal = rabaisGlobal;
        this.numFacturation = this.compteurFacture++;
        listeAchat = new ArrayList<AbstractProduit>();
    }


    public String imprimeFacture() {
        String texte = "Facturé à : " + acheteur + "\nFacturé le : " + momentAchat + "\n\nContient " + listeAchat.size() + " produits";





        Iterator<AbstractProduit> iterListeAchat = listeAchat.iterator();
        while (iterListeAchat.hasNext()) {
            AbstractProduit produit = iterListeAchat.next();
            texte += "\n" + produit.getNom() + " : " + ((AbstractInventaire) produit).getPrixProduit() + "$";
        }
        texte += "\n\ncout brut: " + montantBrute + "$\nrabais accordés: " + montantRabaisGlobal + "$\ntaxes: " + montantTaxes + "$\ntotal : " + montantTotal + "$";
        return texte;

    }

    private String utilise2Decimale(double valeur) {
        return String.format("%.2f", valeur);
    }

    public String getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(String acheteur) {
        this.acheteur = acheteur;
    }

    public int getNumFacturation() {
        return numFacturation;
    }

    @Override
    public String decrit() {
        return "";
    }

    // methode ajouter
    public void ajoutPanierDansAchat(Collection<AbstractProduit> listePanier) {
        if (listeAchat.addAll(listePanier)) {
            calculeMontantBrute();
            calculeMontantRabais(this.rabaisGlobal);
            calculeMontantTaxes();
            calculeMontantTotal();
            calculeMontantRabaisProduit();
        }
    }
    public void calculeMontantRabais(double rabaisGlobal) {
        this.montantRabaisGlobal = this.montantBrute * (rabaisGlobal / 100);
    }
    public void calculeMontantTaxes() {
        this.montantTaxes = this.montantBrute * (TAXES_POUR_CE_TP / 100);
    }
    public void calculeMontantBrute() {
        Iterator<AbstractProduit> iterProduitAchat = listeAchat.iterator();
        while (iterProduitAchat.hasNext()) {
            AbstractProduit produit = iterProduitAchat.next();
            this.montantBrute += ((AbstractInventaire) produit).getPrixProduit();
        }
    }
    public void calculeMontantTotal() {
        this.montantTotal = (this.montantBrute + this.montantTaxes) - this.montantRabaisGlobal;
    }
    public void calculeMontantRabaisProduit() {
        Iterator<AbstractProduit>  iterProduit = listeAchat.iterator();
        while (iterProduit.hasNext()) {
            AbstractProduit produit = iterProduit.next();
            this.montantRabaisProduit += (((AbstractInventaire) produit).getPrixProduit() * (((AbstractInventaire) produit).getRabais() / 100));
        }
    }
}