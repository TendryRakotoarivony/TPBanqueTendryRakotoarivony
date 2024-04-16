/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.tendry.tpbanquetendryrakotoarivony.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import mg.tendry.tpbanquetendryrakotoarivony.entity.CompteBancaire;
import mg.tendry.tpbanquetendryrakotoarivony.service.GestionnaireCompte;
import mg.tendry.tpbanquetendryrakotoarivony.util.Util;

/**
 *
 * @author Tendry Arivony
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    private List<CompteBancaire> compteList;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }

    /**
     * Retourne la liste des comptes pour affichage dans une DataTable.
     *
     * @return
     */
    public List<CompteBancaire> getAllComptes() {
        if (compteList == null) {
            compteList = gestionnaireCompte.getAllComptes();
        }
        return compteList;
    }

    public String supprimerCompte(CompteBancaire compte) {
        gestionnaireCompte.supprimer(compte);
        Util.addFlashInfoMessage("Compte de " + compte.getNom() + " supprimé");
        return "listeComptes?faces-redirect=true";
    }
}
