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
import java.util.List;
import mg.tendry.tpbanquetendryrakotoarivony.entity.CompteBancaire;
import mg.tendry.tpbanquetendryrakotoarivony.entity.OperationBancaire;
import mg.tendry.tpbanquetendryrakotoarivony.service.GestionnaireCompte;

/**
 *
 * @author Tendry Arivony
 */
@Named(value = "operations")
@ViewScoped
public class Operations implements Serializable {

    private Long id;
    private CompteBancaire compteBancaire;
    private List<OperationBancaire> operations;


    @Inject
    private GestionnaireCompte gestionnaireCompte;
/**
     * Creates a new instance of Operations
     */
    public Operations() {
    }


    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void loadCompte() {
        compteBancaire = gestionnaireCompte.getCompte(this.id);
    }
    
    public List<OperationBancaire> getOperations() {
        return this.compteBancaire.getOperations();
    }
}

