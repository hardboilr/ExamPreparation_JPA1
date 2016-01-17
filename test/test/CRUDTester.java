package test;

import control.CRUD;
import entity.Project;
import entity.ProjectUser;
import interfaces.CRUDFacade;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class CRUDTester {

    @After
    public void cleanup() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
        em.createNativeQuery("truncate table project_users").executeUpdate();
        em.createNativeQuery("truncate table project_user").executeUpdate();
        em.createNativeQuery("truncate table project").executeUpdate();
        em.createNativeQuery("truncate table task").executeUpdate();
        em.getTransaction().commit();
    }

    @Test
    public void testCreateProjectUser() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        CRUDFacade crud = new CRUD();
        crud.createProjectUser("Tobias", "tobias.cbs@gmail.com");
        crud.createProjectUser("Niels", "niels@nielsen.com");
        crud.createProjectUser("Jens", "jens.cbs@jensen.com");
        ProjectUser p1 = em.find(ProjectUser.class, 1l);
        ProjectUser p2 = em.find(ProjectUser.class, 2l);
        ProjectUser p3 = em.find(ProjectUser.class, 3l);
        assertEquals("Tobias", p1.getUserName());
        assertEquals("tobias.cbs@gmail.com", p1.getEmail());
        assertEquals("Niels", p2.getUserName());
        assertEquals("niels@nielsen.com", p2.getEmail());
        assertEquals("Jens", p3.getUserName());
        assertEquals("jens.cbs@jensen.com", p3.getEmail());
    }

    @Test
    public void testCreateProject() {
        //setup
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        CRUDFacade crud = new CRUD();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        
        //data
        Date created1 = new Date();
        Date lastModified1 = new Date();
        Date created2 = new Date();
        Date lastModified2 = new Date();
        Date created3 = new Date();
        Date lastModified3 = new Date();
        try {
            created1 = sdf1.parse("12-01-2016 06:00:00");
            created2 = sdf1.parse("13-01-2016 07:45:05");
            created3 = sdf1.parse("14-01-2016 02:15:23");
            lastModified1 = sdf1.parse("20-01-2016 06:00:00");
            lastModified2 = sdf1.parse("20-01-2016 06:13:36");
            lastModified3 = sdf1.parse("20-01-2016 06:40:11");
        } catch (ParseException ex) {
            Logger.getLogger(CRUDTester.class.getName()).log(Level.SEVERE, null, ex);
        }

        String p1Name = "FirstProject";
        String p2Name = "SecondProject";
        String p3Name = "ThirdProject";
        String p1Description = "Some description";
        String p2Description = "Another description";
        String p3Description = "A third description";
        
        crud.createProject(p1Name, p1Description, created1, lastModified1);
        crud.createProject(p2Name, p2Description, created2, lastModified2);
        crud.createProject(p3Name, p3Description, created3, lastModified3);
        
        //test
        Project p1 = em.find(Project.class, 1l);
        Project p2 = em.find(Project.class, 2l);
        Project p3 = em.find(Project.class, 3l);

        assertEquals(p1Name, p1.getName());
        assertEquals(p1Description, p1.getDescription());
        assertEquals(created1, p1.getCreated());
        assertEquals(lastModified1, p1.getLastModified());

        assertEquals(p2Name, p2.getName());
        assertEquals(p2Description, p2.getDescription());
        assertEquals(created2, p2.getCreated());
        assertEquals(lastModified2, p2.getLastModified());

        assertEquals(p3Name, p3.getName());
        assertEquals(p3Description, p3.getDescription());
        assertEquals(created3, p3.getCreated());
        assertEquals(lastModified3, p3.getLastModified());
    }

    @Test
    public void testFindUser() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        CRUDFacade crud = new CRUD();
        crud.createProjectUser("Tobias", "tobias.cbs@gmail.com");
        crud.createProjectUser("Niels", "niels@nielsen.com");
        crud.createProjectUser("Jens", "jens@jensen.com");
        ProjectUser p1 = crud.getProjectUser(1l);
        ProjectUser p2 = crud.getProjectUser(2l);
        ProjectUser p3 = crud.getProjectUser(3l);
        assertEquals("Tobias", p1.getUserName());
        assertEquals("tobias.cbs@gmail.com", p1.getEmail());
        assertEquals("Niels", p2.getUserName());
        assertEquals("niels@nielsen.com", p2.getEmail());
        assertEquals("Jens", p3.getUserName());
        assertEquals("jens@jensen.com", p3.getEmail());
    }

    @Test
    public void testFindProject() {
        //setup
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        CRUDFacade crud = new CRUD();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        
        //data
        String p1Name = "FirstProject";
        String p2Name = "SecondProject";
        String p3Name = "ThirdProject";
        String p1Description = "Some description";
        String p2Description = "Another description";
        String p3Description = "A third description";
        
        Date created1 = new Date();
        Date lastModified1 = new Date();
        Date created2 = new Date();
        Date lastModified2 = new Date();
        Date created3 = new Date();
        Date lastModified3 = new Date();
        try {
            created1 = sdf1.parse("12-01-2016 06:00:00");
            created2 = sdf1.parse("13-01-2016 07:45:05");
            created3 = sdf1.parse("14-01-2016 02:15:23");
            lastModified1 = sdf1.parse("20-01-2016 06:00:00");
            lastModified2 = sdf1.parse("20-01-2016 06:13:36");
            lastModified3 = sdf1.parse("20-01-2016 06:40:11");
        } catch (ParseException ex) {
            Logger.getLogger(CRUDTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        crud.createProject(p1Name, p1Description, created1, lastModified1);
        crud.createProject(p2Name, p2Description, created2, lastModified2);
        crud.createProject(p3Name, p3Description, created3, lastModified3);
        
        Project p1 = crud.findProject(1l);
        Project p2 = crud.findProject(2l);
        Project p3 = crud.findProject(3l);
        
        assertEquals(p1Name, p1.getName());
        assertEquals(p1Description, p1.getDescription());
        assertEquals(created1, p1.getCreated());
        assertEquals(lastModified1, p1.getLastModified());
        
        assertEquals(p2Name, p2.getName());
        assertEquals(p2Description, p2.getDescription());
        assertEquals(created2, p2.getCreated());
        assertEquals(lastModified2, p2.getLastModified());
        
        assertEquals(p3Name, p3.getName());
        assertEquals(p3Description, p3.getDescription());
        assertEquals(created3, p3.getCreated());
        assertEquals(lastModified3, p3.getLastModified());
    }

    @Test
    public void testGetAllProjectUsers() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        CRUDFacade crud = new CRUD();
        crud.createProjectUser("Tobias", "tobias.cbs@gmail.com");
        crud.createProjectUser("Niels", "niels@nielsen.com");
        crud.createProjectUser("Jens", "jens@jensen.com");
        List<ProjectUser> list = crud.getAllProjectUsers();
        assertEquals(3, list.size());
        assertEquals("Tobias", list.get(0).getUserName());
        assertEquals("Niels", list.get(1).getUserName());
        assertEquals("Jens", list.get(2).getUserName());
    }

    @Test
    public void testAssignProjectUserToProject() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        CRUDFacade crud = new CRUD();
        
        crud.createProjectUser("Tobias", "tobias.cbs@gmail.com");
        crud.createProjectUser("Niels", "niels@nielsen.com");
        crud.createProject("FirstProject", "Some description", new Date(), new Date());
        crud.createProject("SecondProject", "Another description", new Date(), new Date());
        
        crud.assignProjectUserToProject(1l, 2l);
        crud.assignProjectUserToProject(2l, 1l);
        Project pro1 = crud.findProject(1l);
        Project pro2 = crud.findProject(2l);
        
        assertEquals("Tobias", pro2.getProjectUser(1l).getUserName());
        assertEquals("Niels", pro1.getProjectUser(2l).getUserName());
    }

    @Test
    public void testCreateTaskAndAssignToProject() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        CRUDFacade crud = new CRUD();
        crud.createProject("Project1", "RandomDescription1", new Date(), new Date());
        crud.createProject("Project2", "RandomDescription2", new Date(), new Date());
        crud.createProject("Project3", "RandomDescription3", new Date(), new Date());
        crud.createTaskAndAssignToProject("Task1", "RandomDescription", 500, 1l);
        crud.createTaskAndAssignToProject("Task2", "RandomDescription", 500, 2l);
        crud.createTaskAndAssignToProject("Task3", "RandomDescription", 500, 3l);
        assertEquals("Task1", crud.findProject(1l).getTask(1l).getName());
        assertEquals("Task2", crud.findProject(2l).getTask(2l).getName());
        assertEquals("Task3", crud.findProject(3l).getTask(3l).getName());
    }

}
