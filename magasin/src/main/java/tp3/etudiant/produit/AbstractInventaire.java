package tp3.etudiant.produit;
import tp3.application.AbstractProduit;

public abstract class AbstractInventaire extends AbstractProduit {
    private double rabais;
    private double prixProduit;
    private float volumeProduit;
    protected static int numeroCategorieDisponible = 0;
    public final float RABAIS_MINIMUM = 0.05f;
    public final float RABAIS_MOYEN = 0.10f;
    public final float RABAIS_MAXIMUM = 0.15f;

    private String sectionProduit = "Default";

    // Constructeur de AbstractInventaire
    public AbstractInventaire(String nom, float volumeProduit) {
        super(nom);
        assert !nom.isEmpty() : "Le nom ne doit pas être null";
        this.volumeProduit = volumeProduit;
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

    // Méthode qui decrit les produits AbstractInventaire
    public String decrit() {
        return getNom() + "   numéro produit : " + getNumProduit() + "   numéro categorie : " + getNumeroCategorie() + " numéro série : " + getNumSerie();
    }
}