package test;

import control.CRUD;
import entity.Project;
import entity.ProjectUser;
import facade.CRUDFacade;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author tobias
 */
public class CRUDTester {

    /*
     public void createTaskAndAssignToProject(String name, String description, int hoursAssigned, long id);
     */
    @After
    public void cleanup() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
        em.createNativeQuery("truncate table project_projectuser").executeUpdate();
        em.createNativeQuery("truncate table projectuser").executeUpdate();
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
        em.remove(p1);
    }

    @Test
    public void testCreateProject() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        CRUDFacade crud = new CRUD();
        crud.createProject("FirstProject", "Some description");
        crud.createProject("SecondProject", "Another description");
        crud.createProject("ThirdProject", "A third description");
        Project p1 = em.find(Project.class, 1l);
        Project p2 = em.find(Project.class, 2l);
        Project p3 = em.find(Project.class, 3l);
        assertEquals("FirstProject", p1.getName());
        assertEquals("Some description", p1.getDescription());
        assertEquals("SecondProject", p2.getName());
        assertEquals("Another description", p2.getDescription());
        assertEquals("ThirdProject", p3.getName());
        assertEquals("A third description", p3.getDescription());
    }

    @Test
    public void testFindUser() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        CRUDFacade crud = new CRUD();
        crud.createProjectUser("Tobias", "tobias.cbs@gmail.com");
        crud.createProjectUser("Niels", "niels@nielsen.com");
        crud.createProjectUser("Jens", "jens@jensen.com");
        ProjectUser p1 = crud.findUser(1l);
        ProjectUser p2 = crud.findUser(2l);
        ProjectUser p3 = crud.findUser(3l);
        assertEquals("Tobias", p1.getUserName());
        assertEquals("tobias.cbs@gmail.com", p1.getEmail());
        assertEquals("Niels", p2.getUserName());
        assertEquals("niels@nielsen.com", p2.getEmail());
        assertEquals("Jens", p3.getUserName());
        assertEquals("jens@jensen.com", p3.getEmail());
    }

    @Test
    public void testFindProject() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        CRUDFacade crud = new CRUD();
        crud.createProject("FirstProject", "Some description");
        crud.createProject("SecondProject", "Another description");
        crud.createProject("ThirdProject", "Last description");
        Project p1 = crud.findProject(1l);
        Project p2 = crud.findProject(2l);
        Project p3 = crud.findProject(3l);
        assertEquals("FirstProject", p1.getName());
        assertEquals("Some description", p1.getDescription());
        assertEquals("SecondProject", p2.getName());
        assertEquals("Another description", p2.getDescription());
        assertEquals("ThirdProject", p3.getName());
        assertEquals("Last description", p3.getDescription());
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
        crud.createProject("FirstProject", "Some description");
        crud.createProject("SecondProject", "Another description");
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
        crud.createProject("Project1", "RandomDescription1");
        crud.createProject("Project2", "RandomDescription2");
        crud.createProject("Project3", "RandomDescription3");
        crud.createTaskAndAssignToProject("Task1", "RandomDescription", 500, 1l);
        crud.createTaskAndAssignToProject("Task2", "RandomDescription", 500, 2l);
        crud.createTaskAndAssignToProject("Task3", "RandomDescription", 500, 3l);
        assertEquals("Task1", crud.findProject(1l).getTask(1l).getName());
        assertEquals("Task2", crud.findProject(2l).getTask(2l).getName());
        assertEquals("Task3", crud.findProject(3l).getTask(3l).getName());
    }

}
