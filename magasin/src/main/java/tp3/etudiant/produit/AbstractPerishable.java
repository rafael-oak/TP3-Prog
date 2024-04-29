/**  Classe abstraite qui gère les produits périssables
 * @author Zackary Dube
 * @version 1.0 (2024-04-10)
 */

package tp3.etudiant.produit;

public abstract class AbstractPerishable extends AbstractInventaire {
    private int joursAvantPeremption;
    public final int SEUIL_JOURS_AVANT_PEREMPTION = 7;

    // Constructeur de Perishable
    public AbstractPerishable(String nom, float volumeProduit, int joursAvantPeremption) {
        super(nom, volumeProduit);
        assert joursAvantPeremption >= 0 : "Le jours avant péremption ne peut pas être négatif";
        this.joursAvantPeremption = joursAvantPeremption;
    }
    public int getJoursAvantPeremption() {
        return this.joursAvantPeremption;
    }

    // Méthode qui decrit les produits Perishable
    @Override
    public String decrit() {
        return super.decrit() + "\nJours avant péremption : " + this.joursAvantPeremption;
    }
}
