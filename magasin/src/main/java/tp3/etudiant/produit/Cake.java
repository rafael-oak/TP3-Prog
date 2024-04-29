/**  Classe qui gère les gâteaux
 * @author Zackary Dube
 * @version 1.0 (2024-04-10)
 */

package tp3.etudiant.produit;

public class Cake extends AbstractPastry {
    private double prixProduit = 5;
    private int nombreEtage;
    private String nomClass = "cake";
    private static int numeroCategorie = -1;

    // Constructeur de Cake
    public Cake(String nom, int joursAvantPeremption, double quantiteSucre, int nombreEtage) {
        super(nom, 1.6f, joursAvantPeremption, quantiteSucre);
        assert nombreEtage > 0 : "Le nombre d'étage ne peut pas être négatif ou zéro";
        this.nombreEtage = nombreEtage;
        if (numeroCategorie == -1) {
            numeroCategorie = numeroCategorieDisponible++;
        }
        setPrixProduit(this.prixProduit);
    }
    public int getNumeroCategorie() {
        return numeroCategorie;
    }
    public String getNomClass() {
        return this.nomClass;
    }

    // Méthode qui decrit les produits Cake
    @Override
    public String decrit() {
        return super.decrit() + "   nombre étage : " + this.nombreEtage;
    }
}
