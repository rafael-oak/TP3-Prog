/**  Classe abstraite qui gère les produits de la pâtisserie
 * @author Zackary Dube
 * @version 1.0 (2024-04-10)
 */

package tp3.etudiant.produit;

public abstract class AbstractPastry extends AbstractPerishable {
    private double quantiteSucre;
    public final int SEUIL_QUANTITE_SUCRE = 50;

    // Constructeur de Pastry
    public AbstractPastry(String nom, float volumeProduit, int joursAvantPeremption, double quantiteSucre) {
        super(nom, volumeProduit, joursAvantPeremption);
        assert quantiteSucre >= 0 : "La quantité de sucre ne peut pas être négatif";
        this.quantiteSucre = quantiteSucre;
    }
    public double getQuantiteSucre() {
        return this.quantiteSucre;
    }

    // Méthode qui decrit les produits Pastry
    @Override
    public String decrit() {
        return super.decrit() + "   quantité sucre : " + this.quantiteSucre;
    }

    // Méthode pour calculer le rabais en fonction de la quantité de sucre et des jours avant la péremption
    public void calculeRabais() {
        if (quantiteSucre > SEUIL_QUANTITE_SUCRE && super.getJoursAvantPeremption() > SEUIL_JOURS_AVANT_PEREMPTION) {
            setRabais(RABAIS_MAXIMUM);
        } else if (quantiteSucre > SEUIL_QUANTITE_SUCRE) {
            setRabais(RABAIS_MOYEN);
        } else if (getJoursAvantPeremption() > SEUIL_JOURS_AVANT_PEREMPTION) {
            setRabais(RABAIS_MINIMUM);
        } else {
            setRabais(0);
        }
    }
}
