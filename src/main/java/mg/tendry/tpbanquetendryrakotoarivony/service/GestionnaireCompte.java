/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.tendry.tpbanquetendryrakotoarivony.service;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import mg.tendry.tpbanquetendryrakotoarivony.entity.CompteBancaire;

/**
 *
 * @author Tendry Arivony
 */
@DataSourceDefinition (
    className="com.mysql.cj.jdbc.MysqlDataSource",
    name="java:app/jdbc/banque",
    serverName="localhost",
    portNumber=3306,
    user="tendry",              // nom et
    password="tendry3050!", // mot de passe que vous avez donnés lors de la création de la base de données
    databaseName="banque",
    properties = {
      "useSSL=false",
      "allowPublicKeyRetrieval=true",
      "driverClass=com.mysql.cj.jdbc.Driver",
      "serverTimezone=Africa/Nairobi" 
    }
)
@ApplicationScoped
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    @Transactional
    public void creerCompte(CompteBancaire compte) {
        em.persist(compte);
    }

    public List<CompteBancaire> getAllComptes() {
        TypedQuery query = em.createNamedQuery("CompteBancaire.findAll", CompteBancaire.class);
        return query.getResultList();
    }

    public long nbComptes() {
        TypedQuery<Long> query = em.createQuery("select count(c) from CompteBancaire c", Long.class);
        return query.getSingleResult();
    }

    @Transactional
    public void transfert(Long idSource, Long idDestination,int montant) {
        CompteBancaire source = this.getCompte(idSource);
        CompteBancaire destination = this.getCompte(idDestination);
        
        source = em.merge(source);
        source.retirer(montant);
        destination = em.merge(destination);
        destination.deposer(montant);
    }

    public CompteBancaire getCompte(Long id) {
        return em.find(CompteBancaire.class, id);
    }

    @Transactional
    public void deposer(CompteBancaire compte, int montant) {
        compte.deposer(montant);
        update(compte);
    }

    @Transactional
    public void retirer(CompteBancaire compte, int montant) {
        compte.retirer(montant);
        update(compte);
    }
    
    @Transactional
    public void supprimer(CompteBancaire compte){
        em.remove(em.merge(compte));
    }
    @Transactional
    public CompteBancaire update(CompteBancaire compte) {
        return em.merge(compte);
    }

}
