package test;

import control.CRUD;
import facade.CRUDFacade;
import javax.persistence.*;

/**
 * @author Tobias Jacobsen
 */
public class Tester {
    
    public static void main(String[] args) {
        Tester tester = new Tester();
        tester.test();
        CRUDFacade crud = new CRUD();
    }

    public void test() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
    }
    
}
