/**  Classe abstraite qui gère les produits de la boulangerie
 * @author Zackary Dube
 * @version 1.0 (2024-04-10)
 */

package tp3.etudiant.produit;

public abstract class AbstractBakery extends AbstractPerishable {
    private double nombreCalories;
    public final double SEUIL_NOMBRE_CALORIE = 500;

    // Constructeur de Bakery
    public AbstractBakery(String nom, float volumeProduit, int joursAvantPeremption, double nombreCalories) {
        super(nom, volumeProduit, joursAvantPeremption);
        assert nombreCalories > 0 : "Le nombre de calories ne peut pas être négatif ou zéro";
        this.nombreCalories = nombreCalories;
    }
    // Méthode qui decrit les produits Bakery
    @Override
    public String decrit() {
        return super.decrit() + "   nombre calorie : " + this.nombreCalories;
    }

    // Méthode pour calculer le rabais en fonction du nombre de calories et des jours avant la péremption
    public void calculeRabais() {
        if (nombreCalories < SEUIL_NOMBRE_CALORIE && getJoursAvantPeremption() > SEUIL_JOURS_AVANT_PEREMPTION) {
            setRabais(RABAIS_MAXIMUM);
        } else if (nombreCalories < SEUIL_NOMBRE_CALORIE) {
            setRabais(RABAIS_MOYEN);
        } else if (getJoursAvantPeremption() > SEUIL_JOURS_AVANT_PEREMPTION) {
            setRabais(RABAIS_MINIMUM);
        } else {
            setRabais(0);
        }
    }
}
