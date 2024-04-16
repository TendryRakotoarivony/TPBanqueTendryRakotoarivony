/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.tendry.tpbanquetendryrakotoarivony.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
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
@Named(value = "modifierCompte")
@ViewScoped
public class ModifierCompte implements Serializable {
    
    private Long idCompte;
    private CompteBancaire compteBancaire;
    private String nomInitial;
    
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
    public ModifierCompte() {
    }
    
    public void loadCompte() {
        compteBancaire = gestionnaireCompte.getCompte(idCompte);
        this.nomInitial = compteBancaire.getNom();
    }

    public String updateCompte() {
        gestionnaireCompte.update(compteBancaire);
        Util.addFlashInfoMessage("Nom " +this.nomInitial+" changé en "+ compteBancaire.getNom());
        return "modifierCompte?idCompte="+compteBancaire.getId()+"&faces-redirect=true";
        
    }
    
}
