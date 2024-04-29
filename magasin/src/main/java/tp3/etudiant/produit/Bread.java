/**  Classe qui gère les pains
 * @author Zackary Dube
 * @version 1.0 (2024-04-10)
 */

package tp3.etudiant.produit;

public class Bread extends AbstractBakery {
    private double prixProduit = 5;
    private float poids;
    private String nomClass = "bread";
    private static int numeroCategorie = -1;

    // Constructeur de Bread
    public Bread(String nom, int joursAvantPeremption, double nombreCalories, float poids) {
        super(nom, 2, joursAvantPeremption, nombreCalories);
        assert poids > 0 : "Le poids ne peut pas être négatif ou zéro";
        this.poids = poids;
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

    // Méthode qui decrit les produits Bread
    @Override
    public String decrit() {
        return super.decrit() + "   poids : " + this.poids;
    }
}
