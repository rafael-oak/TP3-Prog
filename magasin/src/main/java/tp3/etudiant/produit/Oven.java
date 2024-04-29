/**  Classe abstraite qui gère les fours
 * @author Zackary Dube
 * @version 1.0 (2024-04-10)
 */

package tp3.etudiant.produit;

public class Oven extends AbstractMateriel {
    private double prixProduit = 5;
    private String fournisseur;
    private String nomClass = "oven";
    public static final float SEUIL_PRIX_ACHAT_OVEN = 400;
    private static int numeroCategorie = -1;

    // Constructeur de Oven
    public Oven(String nom, boolean usager, float prixAchat, String fournisseur) {
        super(nom, 75, usager, prixAchat);
        assert !fournisseur.isEmpty() : "Le fournisseur ne peut pas être null";
        this.fournisseur = fournisseur;
        if (numeroCategorie == -1) {
            numeroCategorie = numeroCategorieDisponible++;
        }
        setPrixProduit(this.prixProduit);
    }

    public String getNomClass() {
        return this.nomClass;
    }
    public int getNumeroCategorie() {
        return numeroCategorie;
    }

    // Méthode qui decrit les produits Oven
    @Override
    public String decrit() {
        return super.decrit() + "   fournisseur produit : " + this.fournisseur;
    }

    // Méthode pour calculer le rabais en fonction du prix d'achat et s'il est usagé
    public void calculeRabais() {
        super.calculeRabais(SEUIL_PRIX_ACHAT_OVEN);
    }
}
