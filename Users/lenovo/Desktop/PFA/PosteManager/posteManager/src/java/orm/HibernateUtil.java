/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orm;

import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    private static Configuration configuration;
    
//    static {
//        try {
//            // Create the SessionFactory from standard (hibernate.cfg.xml) 
//            // config file.
//            sessionFactory = new Configuration().configure().buildSessionFactory();
//        } catch (Throwable ex) {
//            // Log the exception. 
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
    
    public static SessionFactory getSessionFactory() {
    	if(configuration==null){
    		configuration = new Configuration();
            configuration.configure();
    	}
        if(serviceRegistry==null)
        	serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()). buildServiceRegistry();
       if(sessionFactory==null)
    	   sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
}
