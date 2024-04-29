/**  Classe qui gère les croissants
 * @author Zackary Dube
 * @version 1.0 (2024-04-10)
 */

package tp3.etudiant.produit;

public class Croissant extends AbstractBakery {
    private double prixProduit = 5;
    private boolean auBeurre;
    private String nomClass = "croissant";
    private static int numeroCategorie = -1;

    // Constructeur de Croissant
    public Croissant(String nom, int joursAvantPeremption, double nombreCalories, boolean auBeurre) {
        super(nom, 0.12f, joursAvantPeremption, nombreCalories);
        this.auBeurre = auBeurre;
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

    // Méthode qui decrit les produits Croissant
    @Override
    public String decrit() {
        return super.decrit() + "   au beurre : " + this.auBeurre;
    }
}
