/**  Classe qui gère l'entrepôt
 * @author Zackary Dube & Rafael Carvalho Matias
 * @version 1.0 (2024-04-10)
 */

package tp3.etudiant.section;

import tp3.etudiant.boite.Boite;

public class Entrepot {
    private Boite[][][] entreposage;//[rangee][section][tablette]->[categorie de produit][type de produit][boite de produit]
    private String sectionId = "entrepot";
    public static final int NOMBRE_SECTION = 5;
    public static final int NOMBRE_TABLETTE = 5;
    public static final int NOMBRE_CATEGORIES = 6;

    // Constructeur de Entrepôt
    public Entrepot() {
        entreposage = new Boite[NOMBRE_CATEGORIES]
                [NOMBRE_SECTION]
                [NOMBRE_TABLETTE];
    }

    /**
     * La méthode permet de stocker une boîte de produits dans une table d'entrepôt tridimensionnelle
     *
     * @param Boite     Contient jusqu'à concurrence de 10 produits de même catégorie
     * @return boolean  Retourne vrai si la boîte est entreposée dans l'entrepôt, sinon retourne faux
     */
    public boolean entreposeBoite(Boite boite) {
        assert boite != null : "Pour entreposer la boîte, elle ne doit pas être nulle";

        boolean reussi = false;
        int numeroCategorie = boite.getNumeroCategorie();
        int numeroProduit = boite.getNumeroProduit();

        if (entreposage[numeroCategorie][numeroProduit][NOMBRE_TABLETTE - 1] == null) {
            for (int j = NOMBRE_TABLETTE - 1; j > 0; j--) {
                entreposage[numeroCategorie][numeroProduit][j] = entreposage[numeroCategorie][numeroProduit][j - 1];
            }
            entreposage[numeroCategorie][numeroProduit][NOMBRE_TABLETTE - NOMBRE_TABLETTE] = boite;
            reussi = true;
        }

        return reussi;
    }

    /**
     * Méthode qui permet de retirer une boîte de la table tridimensionnelle d'entrepôt
     *
     * @param Boite     Contient jusqu'à concurrence de 10 produits de même catégorie
     * @return void
     */
    public void retireBoite(Boite boite) {
        assert boite != null : "Pour retirer la boîte de la table, elle ne doit pas être nulle";
        int numeroCategorie = boite.getNumeroCategorie();
        int numeroProduit = boite.getNumeroProduit();
        boolean reussi = false;
        for (int i = 0; i < entreposage[numeroCategorie][numeroProduit].length || !reussi; i++) {
            if (entreposage[numeroCategorie][numeroProduit][i] == boite) {
                entreposage[numeroCategorie][numeroProduit][i] = null;
                for (int j = i; j < NOMBRE_TABLETTE - 1; j++) {
                    entreposage[numeroCategorie][numeroProduit][j] = entreposage[numeroCategorie][numeroProduit][j+1];
                }
                reussi = true;
            }
        }
    }

    /**
     * Conversion 3D vers 2D : les deux premières dimensions sont fusionnées
     *
     * @param
     * @return void
     */
    public Boite[][] getBoites2D() {
        boolean elementAjoute = false;
        Boite[][] boite = new Boite[NOMBRE_CATEGORIES * NOMBRE_SECTION][NOMBRE_TABLETTE];
        int count = 0;
        for (int i = 0; i < NOMBRE_CATEGORIES; i++) {
            for (int y = 0; y < NOMBRE_SECTION; y++) {
                if (entreposage[i][y] != null) {
                    for (int j = 0; j < NOMBRE_TABLETTE; j++) {
                        if (entreposage[i][y][j] != null) {
                            elementAjoute = true;
                            boite[count][j] = entreposage[i][y][j];
                        }
                    }
                    if (elementAjoute) {
                        count++;
                        elementAjoute = false;
                    }
                }
            }
        }
        return boite;
    }

    /**
     * Une méthode pour transformer notre tableau tridimensionnel en un tableau unidimensionnel
     *
     * @param
     * @return void
     */
    public Boite[] getBoites1D() {
        Boite[] boite = new Boite[NOMBRE_CATEGORIES * NOMBRE_SECTION * NOMBRE_TABLETTE];
        int count = 0;
        for (int i = 0; i < NOMBRE_CATEGORIES; i++) {
            for (int y = 0; y < NOMBRE_SECTION; i++) {
                for (int j = 0; j < NOMBRE_TABLETTE; j++) {
                    if (entreposage[i][y][j] != null) {
                        boite[count++] = entreposage[i][y][j];
                    }
                }
            }
        }
        return boite;
    }

    /**
     * Méthode qui permet de récupérer la table tridimensionnelle de l'entrepôt
     *
     * @param
     * @return void
     */
    public Boite[][][] getBoites3D() {
        return entreposage;
    }
}

