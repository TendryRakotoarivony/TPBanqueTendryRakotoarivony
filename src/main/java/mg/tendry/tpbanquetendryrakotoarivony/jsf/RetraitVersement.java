/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.tendry.tpbanquetendryrakotoarivony.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.tendry.tpbanquetendryrakotoarivony.entity.CompteBancaire;
import mg.tendry.tpbanquetendryrakotoarivony.service.GestionnaireCompte;
import mg.tendry.tpbanquetendryrakotoarivony.util.Util;

/**
 *
 * @author Tendry Arivony
 */
@Named(value = "retraitVersement")
@ViewScoped
public class RetraitVersement implements Serializable {
    
    private Long idCompte;
    private CompteBancaire compteBancaire;
    private String typeMouvement;
    private int montant;
    
    public String getTypeMouvement() {
        return typeMouvement;
    }
    
    public void setTypeMouvement(String typeMouvement) {
        this.typeMouvement = typeMouvement;
    }
    
    public int getMontant() {
        return montant;
    }
    
    public void setMontant(int montant) {
        this.montant = montant;
    }
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    public Long getIdCompte() {
        return idCompte;
    }
    
    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }
    
    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    /**
     * Creates a new instance of RetraitVersement
     */
    public RetraitVersement() {
    }
    
    public void loadCompte() {
        this.compteBancaire = gestionnaireCompte.getCompte(idCompte);
    }

    public String validerMouvement() {
        boolean erreur = false;
        if (typeMouvement == null || typeMouvement.equals("")) {
            Util.messageErreur("Choisissez un mouvement !", "Choisissez un mouvement !", "form:typeMouvement");
            erreur = true;
        } else {
            if (typeMouvement.equals("retrait")) {
                if (compteBancaire.getSolde() < montant) {
                    Util.messageErreur("Le retrait doit être inférieur au solde du compte", "Le retrait doit être inférieur au solde du compte", "form:montant");
                    erreur = true;
                }
            }
        }
        if (erreur) {
            return null;
        }
        if (typeMouvement.equals("ajout")) {
            gestionnaireCompte.deposer(compteBancaire, montant);
        } else if (typeMouvement.equals("retrait")) {
            gestionnaireCompte.retirer(compteBancaire, montant);
        }
        Util.addFlashInfoMessage("Mouvement enregistré sur compte de " + compteBancaire.getNom());
        return "listeComptes?faces-redirect=true";
        
    }
    
}

