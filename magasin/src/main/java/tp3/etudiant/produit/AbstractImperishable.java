/**  Classe abstraite qui gère les produits impérissables
 * @author Zackary Dube
 * @version 1.0 (2024-04-10)
 */

package tp3.etudiant.produit;

public abstract class AbstractImperishable extends AbstractInventaire {
    private boolean usager;

    // Constructeur de Imperishable
    public AbstractImperishable(String nom, float volumeProduit, boolean usager) {
        super(nom, volumeProduit);
        this.usager = usager;
    }
    public boolean getUsager() {
        return this.usager;
    }

    // Méthode qui decrit les produits Perishable
    @Override
    public String decrit() {
        return super.decrit() + "\nusagé : " + this.usager;
    }
}
