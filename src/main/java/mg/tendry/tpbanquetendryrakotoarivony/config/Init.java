/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.tendry.tpbanquetendryrakotoarivony.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.transaction.Transactional;
import mg.tendry.tpbanquetendryrakotoarivony.entity.CompteBancaire;
import mg.tendry.tpbanquetendryrakotoarivony.service.GestionnaireCompte;

/**
 *
 * @author Tendry Arivony
 */
@ApplicationScoped
public class Init {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of Init
     */
    public Init() {

    }

    @Transactional
    public void init(
            @Observes
            @Initialized(ApplicationScoped.class) ServletContext context) {
             if (this.gestionnaireCompte.nbComptes() == 0) {
            this.gestionnaireCompte.creerCompte(new CompteBancaire("John Lennon", 150000));
            this.gestionnaireCompte.creerCompte(new CompteBancaire("Paul McCartney", 950000));
            this.gestionnaireCompte.creerCompte(new CompteBancaire("Ringo Starr", 20000));
            this.gestionnaireCompte.creerCompte(new CompteBancaire("Georges Harrisson", 100000));
        }
    }
}
