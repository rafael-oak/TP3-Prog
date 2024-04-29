/**  Classe abstraite qui gère le matériel non périssable
 * @author Zackary Dube
 * @version 1.0 (2024-04-10)
 */

package tp3.etudiant.produit;

public abstract class AbstractMateriel extends AbstractImperishable {
    private float prixAchat;

    // Constructeur de Materiel
    public AbstractMateriel(String nom, float volumeProduit, boolean usager, float prixAchat) {
        super(nom, volumeProduit, usager);
        assert prixAchat > 0 : "Le prix d'achat ne peut pas être négatif ou zéro";
        this.prixAchat = prixAchat;
    }

    public float getPrixAchat() {
        return this.prixAchat;
    }

    // Méthode qui decrit les produits Materiel
    @Override
    public String decrit() {
        return super.decrit() + "   prix achat produit : " + this.prixAchat;
    }

    // Méthode pour calculer le rabais en fonction du prix d'achat et s'il est usagé
    public void calculeRabais(float seuilPrixAchat) {
        if (getUsager() && getPrixAchat() < seuilPrixAchat) {
            setRabais(RABAIS_MAXIMUM);
        } else if (getUsager()) {
            setRabais(RABAIS_MOYEN);
        } else if (getPrixAchat() < seuilPrixAchat) {
            setRabais(RABAIS_MINIMUM);
        } else {
            setRabais(0);
        }
    }
}
