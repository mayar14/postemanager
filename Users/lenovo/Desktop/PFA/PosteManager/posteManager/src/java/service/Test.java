/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.Iterator;
import model.Boitelettres;

/**
 *
 * @author Rabab
 */
public class Test {
    public static void main(String[] args) {
        BoitelettreService bls = new BoitelettreService(); 
        ArrayList<Boitelettres> Bl = new ArrayList();
        Bl = bls.getAllBL();
        for (Iterator<Boitelettres> iterator = Bl.iterator(); iterator.hasNext();) {
            Boitelettres bl = iterator.next();
            System.out.println(bl.getId()+"\t"+bl.getAdresse()+"\t"+bl.getTournee().getNumero());
            
        }
    }
    
    
}
