/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.tendry.tpbanquetendryrakotoarivony.service;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.RequestScoped;
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
      "driverClass=com.mysql.cj.jdbc.Driver"
    }
)
@RequestScoped
public class GestionnaireCompte {
    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;
    
     public List<CompteBancaire> getAllComptes() {
        TypedQuery query = em.createNamedQuery("CompteBancaire.findAll", CompteBancaire.class);
        return query.getResultList();
    }

    @Transactional
    public CompteBancaire update(CompteBancaire comptebancaire) {
       return em.merge(comptebancaire);
    }

    @Transactional
    public void ajouter(CompteBancaire comptebancaire) {
       em.persist(comptebancaire);
    }
    
}
