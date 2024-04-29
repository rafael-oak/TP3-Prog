/**  Classe qui gère les beignes
 * @author Zackary Dube
 * @version 1.0 (2024-04-10)
 */

package tp3.etudiant.produit;

public class Donut extends AbstractPastry {
    private double prixProduit = 5;
    private boolean aUnTrou;
    private String nomClass = "donut";
    private static int numeroCategorie = -1;

    // Constructeur de Donut
    public Donut(String nom, int joursAvantPeremption, double quantiteSucre, boolean aUnTrou) {
        super(nom, 0.028f, joursAvantPeremption, quantiteSucre);
        this.aUnTrou = aUnTrou;
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

    // Méthode qui decrit les produits Donut
    @Override
    public String decrit() {
        return super.decrit() + "   -troué : " + this.aUnTrou;
    }
}
