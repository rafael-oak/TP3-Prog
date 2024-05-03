package tp3.etudiant.produit;
import tp3.application.AbstractProduit;

import java.time.LocalDateTime;
import java.time.Month;

public abstract class AbstractInventaire extends AbstractProduit {
    private double rabais;
    private double prixProduit;
    private float volumeProduit;
    private LocalDateTime dateAchat;
    protected static int numeroCategorieDisponible = 0;
    public final float RABAIS_MINIMUM = 0.05f;
    public final float RABAIS_MOYEN = 0.10f;
    public final float RABAIS_MAXIMUM = 0.15f;
//    public final int JOUR = (int)(1 + (Math.random() * 28));
//    public final int HEURE = (int)(Math.random() * 24);
//    public final int MINUTE = (int)(Math.random() * 59);
    private String sectionProduit = "Default";
    private LocalDateTime dateProduit;

    // Constructeur de AbstractInventaire
    public AbstractInventaire(String nom, float volumeProduit) {
        super(nom);
        assert !nom.isEmpty() : "Le nom ne doit pas être null";
        this.volumeProduit = volumeProduit;
//        this.dateProduit = LocalDateTime.of(2024,Month.MAY,JOUR,HEURE,MINUTE);;

    }

    public void setPrixProduit(double prixProduit) {
        this.prixProduit = prixProduit;
    }
    public double getPrixProduit() {
        return this.prixProduit;
    }

    public String getSectionProduit() {
        return this.sectionProduit;
    }
    public void setSectionProduit(String sectionProduit) {
        this.sectionProduit = sectionProduit;
    }
    public abstract String getNomClass();

    @Override
    public abstract int getNumeroCategorie();

    public float getVolumeProduit() {
        return this.volumeProduit;
    }
    public double getRabais() {
        return this.rabais;
    }
    public void setRabais(double rabais) {
        this.rabais = rabais;
    }
//    public LocalDateTime getDateProduit(){
//        return this.dateProduit;
//    }

    // Méthode qui decrit les produits AbstractInventaire
    public String decrit() {
        return getNom() + "   numéro produit : " + getNumProduit() + "   numéro categorie : " + getNumeroCategorie() + " numéro série : " + getNumSerie();
    }
}