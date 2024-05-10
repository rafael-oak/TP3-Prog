package tp3.etudiant.client;

import tp3.echange.Descriptible;
import tp3.application.AbstractProduit;
import tp3.etudiant.produit.AbstractInventaire;
import tp3.etudiant.section.Vrac;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Achat implements Descriptible {
    double volumeTotalVrac;
    private Vrac vrac;
    public final double RABAIS_MAXIMUM = 25;
    Collection<AbstractProduit> listeAchat;
    private double rabaisGlobal;
    public final double TAXES_POUR_CE_TP = 14;
    private String acheteur;
    private int numFacturation = 0;
    private static int compteurFacture = 0;
    private LocalDateTime momentAchat;

    public Achat(String acheteur, LocalDateTime momentAchat, double rabaisGlobal, Vrac vrac) {
        this.acheteur = acheteur;
        this.momentAchat = momentAchat;
        this.rabaisGlobal = rabaisGlobal;
        this.numFacturation = this.compteurFacture++;
        listeAchat = new ArrayList<AbstractProduit>();
        this.vrac = vrac;
    }


    public String imprimeFacture() {
        String nomDate = "Facturé à : " + acheteur + "\nFacturé le : " + momentAchat + "\n\n";
        String nomPrixProduit = "contient " + listeAchat.size() + " produits\n";
        Iterator<AbstractProduit> iterProduit1 = listeAchat.iterator();
        while (iterProduit1.hasNext()) {
            AbstractProduit produit = iterProduit1.next();
            nomPrixProduit += produit.getNom() + " : " + ((AbstractInventaire) produit).getPrixProduit() + "$\n";
        }
        String coutBrutRabaisTaxesTotal = String.format("\ncout brut : %.2f$\nrabais accordes : %.2f$\ntaxes : %.2f$\ntotal : %.2f$\n\n",
                                        calculeMontantBrute(), calculeMontantRabaisTotal(), calculeMontantTaxes(), calculeMontantTotal());
        String detailsDesRabaisAccordes = "Détails des rabais accordés :\n";
        Iterator<AbstractProduit> iterProduit2 = listeAchat.iterator();
        while (iterProduit2.hasNext()) {
            AbstractProduit produit = iterProduit2.next();
            detailsDesRabaisAccordes += String.format("rabais produit : " + produit.getNom() + " de %.2f$\n", ((AbstractInventaire) produit).getRabais());
        }
        detailsDesRabaisAccordes += String.format("rabais global de %.2f$\nSection rabais - Vrac : %.2f$\nSection rabais - Presentoir : AVENIR", this.rabaisGlobal, calculeMontantRabaisVrac());
        String factureFinal = nomDate + nomPrixProduit + coutBrutRabaisTaxesTotal + detailsDesRabaisAccordes;
        return factureFinal;
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
        listeAchat.addAll(listePanier);
    }

    // Calculer le montant brute
    public double calculeMontantBrute() {
        double montantBrute = 0;
        Iterator<AbstractProduit> iterProduitAchat = listeAchat.iterator();
        while (iterProduitAchat.hasNext()) {
            AbstractProduit produit = iterProduitAchat.next();
            montantBrute += ((AbstractInventaire) produit).getPrixProduit();
        }
        return montantBrute;
    }
    // Calculer le montant des taxes
    public double calculeMontantTaxes() {
        return calculeMontantBrute() * (TAXES_POUR_CE_TP / 100);
    }
    // Calculer le montant du rabais globale
    public double calculeMontantRabaisGlobale() {
        return calculeMontantBrute() * (this.rabaisGlobal / 100);
    }
    // Calculer le montant du rabais par produit
    public double calculeMontantRabaisProduit() {
        double montantRabaisProduit = 0;
        Iterator<AbstractProduit>  iterProduit = listeAchat.iterator();
        while (iterProduit.hasNext()) {
            AbstractProduit produit = iterProduit.next();
            montantRabaisProduit += (((AbstractInventaire) produit).getPrixProduit() * (((AbstractInventaire) produit).getRabais() / 100));
        }
        return montantRabaisProduit;
    }
    // Calculer le rabais de section (Vrac|Presentoir)
    public double calculeRabaisSection() {
        Iterator<AbstractProduit> iterProduit = listeAchat.iterator();

        while (iterProduit.hasNext()) {
            AbstractProduit produit = iterProduit.next();
            if (((AbstractInventaire) produit).getSectionProduit().equals("vrac")) {
                this.volumeTotalVrac += ((AbstractInventaire) produit).getVolumeProduit();
            } else if (((AbstractInventaire) produit).getSectionProduit().equals("presentoir")) {
                //rabaisPresentoir += calculeRabaisPresentoir(produit);
            }
        }
        return calculeMontantRabaisVrac();
    }
    // Calculer montant rabais Vrac
    public double calculeMontantRabaisVrac() {
        double rabais = (this.volumeTotalVrac /  vrac.getVolumeTotal()) * RABAIS_MAXIMUM;
        return calculeMontantBrute() * (rabais / 100);
    }
    // Calculer montant rabais Vrac (AVENIR)
    public double calculeRabaisPresentoir() {
        return 0;
    }
    // Calculer le montant total des rabais
    public double calculeMontantRabaisTotal() {
        return calculeRabaisSection() + calculeMontantRabaisProduit() + calculeMontantRabaisGlobale();
    }
    // Calculer le montant total avec rabais et taxes
    public double calculeMontantTotal() {
        return (calculeMontantBrute() + calculeMontantTaxes()) - calculeMontantRabaisTotal();
    }
}