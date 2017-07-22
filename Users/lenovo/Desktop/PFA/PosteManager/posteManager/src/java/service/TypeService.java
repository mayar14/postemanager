/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import model.Type;
import model.Ville;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import orm.HibernateUtil;

/**
 *
 * @author Rabab
 */
@Named(value = "typeService")
@Dependent
public class TypeService implements Serializable {
    

    private static final long serialVersionUID = 1L;
	Session session=null;
        
        public TypeService() {
    
        }
        
        
        public ArrayList<Type> getAllType(){
        ArrayList<Type> allType = new ArrayList<>();
        try {
		this.session = HibernateUtil.getSessionFactory().openSession(); 
		Transaction tx = session.beginTransaction();
		Query req = session.createQuery("from Type t order by t.libelle");
            allType = (ArrayList<Type>) req.list();
            tx.commit();
            //session.clear();
		} catch (HibernateException e) {
		  System.out.println("Erreur dans getAllType\n"+e);
		}
        return allType;
    }
        
        
       public int deleteType(Type TypeToDelete){
		int result=0;
		try {
			this.session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			String strReq="delete from Type t where t.id=?";
            Query req = session.createQuery(strReq).setInteger(0, TypeToDelete.getId());
            result= req.executeUpdate();
            tx.commit();
            if(result>0){
            	System.out.println("Type  supprimé avec succès");
            }
            //session.clear();
		} catch (HibernateException e) {
			System.out.println("Erreur dans deleteType\n"+e);
		}
		return result;
	}
    
   public int updateType(Type TypeToUpdate){
		int result=0;
		try {
			this.session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			if(!session.isOpen()){
				session=HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();
			}
			session.update(TypeToUpdate);
            tx.commit();
            if(result>0){
            	System.out.println("Type Mis Ã  jour avec succÃ¨s avec succÃ¨s");
            }
            //session.clear();
		} catch (HibernateException e) {
			System.out.println("Erreur dans updateType\n"+e);
           		}
		return result;
	}
    
   public int addType(Type TypeToUpdate){
		int result=0;
		try {
			this.session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			//MatToUpdate.setDepartement(getDepartementByID(idDep));
			if(!session.isOpen()){
				session=HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();
			}
			session.save(TypeToUpdate);
//			String strReq="insert into User(nom,prenom,login,password,profile,iddepartement)";
//            Query req = session.createQuery(strReq).setString(0, userToUpdate.getNom()).setString(1, userToUpdate.getPrenom()).setString(2, userToUpdate.getLogin()).setString(3, userToUpdate.getPassword()).setString(4, userToUpdate.getProfile()).setInteger(5, idDep);
//            result= req.executeUpdate();
            tx.commit();
            if(result>0){
            	System.out.println("Type Ajoute avec succés");
            }
            //session.clear();
		} catch (Exception e) {
			System.out.println("Erreur dans addType\n"+e);
            e.printStackTrace();
            session.close();
		}
		return result;
	}
     
        
}
