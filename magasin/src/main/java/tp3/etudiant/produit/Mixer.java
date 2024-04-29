/**  Classe abstraite qui gère les mixers
 * @author Zackary Dube
 * @version 1.0 (2024-04-10)
 */

package tp3.etudiant.produit;

public class Mixer extends AbstractMateriel {
    private double prixProduit = 5;
    private float nombreHeuresUtilisation;
    private String nomClass = "mixer";
    public static final float SEUIL_PRIX_ACHAT_MIXER = 100;
    private static int numeroCategorie = -1;

    // Constructeur de Mixer
    public Mixer(String nom, boolean usager, float prixAchat, float nombreHeuresUtilisation) {
        super(nom, 4.71f, usager, prixAchat);
        assert nombreHeuresUtilisation >= 0 : "Le nombre d'heure d'utilisation ne peut pas être négatif";
        this.nombreHeuresUtilisation = nombreHeuresUtilisation;
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

    // Méthode qui decrit les produits Materiel
    @Override
    public String decrit() {
        return super.decrit() + "   heures utilisations : " + this.nombreHeuresUtilisation;
    }

    // Méthode pour calculer le rabais en fonction du prix d'achat et s'il est usagé
    public void calculeRabais() {
        super.calculeRabais(SEUIL_PRIX_ACHAT_MIXER);
    }
}
