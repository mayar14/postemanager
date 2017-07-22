/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import model.Boitelettres;
import model.Tournee;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import orm.HibernateUtil;

/**
 *
 * @author Rabab
 */
@RequestScoped
@Named("blService")
public class BoitelettreService   implements Serializable{
    
    private static final long serialVersionUID = 1L;
	Session session=null;

    public BoitelettreService() {
    }
        
        
        
    public ArrayList<Boitelettres> getAllBL(){
        ArrayList<Boitelettres> allBL = new ArrayList<>();
        try {
		this.session = HibernateUtil.getSessionFactory().openSession(); 
		Transaction tx = session.beginTransaction();
		Query req = session.createQuery("from Boitelettres b order by b.type");
            allBL = (ArrayList<Boitelettres>) req.list();
            tx.commit();
            //session.clear();
		} catch (HibernateException e) {
		  System.out.println("Erreur dans getAllBL\n"+e);
		}
        return allBL;
    }
    
    
    
    
    public ArrayList<Boitelettres> getBLByTournee(Tournee tournee){
        ArrayList<Boitelettres> allBL = new ArrayList<>();
        
        return allBL;
    }
    
    
    
    public int deleteBl(Boitelettres BlToDelete){
		int result=0;
		try {
			this.session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			String strReq="delete from Boitelettres b where b.id=?";
            Query req = session.createQuery(strReq).setInteger(0, BlToDelete.getId());
            result= req.executeUpdate();
            tx.commit();
            if(result>0){
            	System.out.println("Boite lettre  supprimée avec succès");
            }
            //session.clear();
		} catch (Exception e) {
			System.out.println("Erreur dans deleteBL\n"+e);
                        e.printStackTrace();
		}
		return result;
	}
	
	public int updateBl(Boitelettres BlToUpdate){
		int result=0;
		try {
			this.session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			if(!session.isOpen()){
				session=HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();
			}
			session.update(BlToUpdate);
            tx.commit();
            if(result>0){
            	System.out.println("MatÃ©riel Mis Ã  jour avec succÃ¨s avec succÃ¨s");
            }
            //session.clear();
		} catch (Exception e) {
			System.out.println("Erreur dans updateMat\n"+e);
            e.printStackTrace();
		}
		return result;
	}
	
	public int addBl(Boitelettres BlToUpdate){
		int result=0;
		try {
			this.session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			//MatToUpdate.setDepartement(getDepartementByID(idDep));
			if(!session.isOpen()){
				session=HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();
			}
			session.save(BlToUpdate);
//			String strReq="insert into User(nom,prenom,login,password,profile,iddepartement)";
//            Query req = session.createQuery(strReq).setString(0, userToUpdate.getNom()).setString(1, userToUpdate.getPrenom()).setString(2, userToUpdate.getLogin()).setString(3, userToUpdate.getPassword()).setString(4, userToUpdate.getProfile()).setInteger(5, idDep);
//            result= req.executeUpdate();
            tx.commit();
            if(result>0){
            	System.out.println("MatÃ©iel AjoutÃ© avec succÃ¨s");
            }
            //session.clear();
		} catch (Exception e) {
			System.out.println("Erreur dans addUser\n"+e);
            e.printStackTrace();
            session.close();
		}
		return result;
	}
}
