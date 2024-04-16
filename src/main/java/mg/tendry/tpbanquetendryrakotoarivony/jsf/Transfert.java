/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.tendry.tpbanquetendryrakotoarivony.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.tendry.tpbanquetendryrakotoarivony.service.GestionnaireCompte;

/**
 *
 * @author Tendry Arivony
 */
@Named(value = "transfert")
@RequestScoped
public class Transfert implements Serializable {

    private Long idSource;
    private Long idDestinataire;
    private int montant;
    @Inject
    GestionnaireCompte gestionnaireCompte;

    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public Long getIdDestinataire() {
        return idDestinataire;
    }

    public void setIdDestinataire(Long idDestinataire) {
        this.idDestinataire = idDestinataire;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    /**
     * Creates a new instance of TransfertArgent
     */
    public Transfert() {
    }
    public String transfererArgent() {
        gestionnaireCompte.transfert(idSource, idDestinataire, montant);
        return "listeComptes?amp;faces-redirect=true";
    }

}
