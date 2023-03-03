/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author aouad
 */
public class LignePanier {


    private int IdLignePanier;
    private Panier panier;
    private Produits produit;
    private String nomProd;
    private double prix_unitaire;
    private Date dateAjout;

    

    public LignePanier(int IdLignePanier,Panier panier, Produits produit,String nomProd, double prix_unitaire ,  Date dateAjout) {
        this.IdLignePanier = IdLignePanier;
        this.panier = panier;
        this.produit = produit; 
        this.nomProd = nomProd;
        this.prix_unitaire = prix_unitaire;
   
        this.dateAjout = dateAjout;
   
    }

    public LignePanier() {
    }

    public LignePanier(int idLignePanier, Produits produit, int quantite, double prixUnitaire) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
//getters
    
    public int getIdLignePanier() {
        return IdLignePanier;
    }

    public Panier getPanier() {
        return panier;
    }

    public String getNomProd() {
        return nomProd;
    }
    
    public double getPrix_unitaire() {
        return prix_unitaire;
    }
    public Produits getProduit() {
        return produit;
    }


  
    public Date getDateAjout() {
        return dateAjout;
    }
  

 //setters 

    public void setIdLignePanier(int IdLignePanier) {
        this.IdLignePanier = IdLignePanier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public void setProduit(Produits produit) {
        this.produit = produit;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }
    
   public void setPrix_unitaire(double prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

   

 
    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }
    
 


    //toString

  

    @Override
    public String toString() {
        return "LignePanier{" + "IdLignePanier=" + IdLignePanier + ", panier=" + panier + ", produit=" + produit + ", nomProd=" + nomProd + ", prix_unitaire=" + prix_unitaire + ", dateAjout=" + dateAjout + '}';
    }

    public void add(LignePanier lp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

   

  
   

 
    
    
    

}
