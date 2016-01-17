package control;

import entity.Project;
import entity.ProjectUser;
import entity.Task;
import interfaces.CRUDFacade;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

public class CRUD implements CRUDFacade {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public CRUD() {
        emf = Persistence.createEntityManagerFactory("PU");
        em = emf.createEntityManager();
    }
    
    @Override
    public void createProjectUser(String userName, String email) {
        ProjectUser p = new ProjectUser(userName, email);
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    @Override
    public ProjectUser getProjectUser(long id) {
        ProjectUser p = em.find(ProjectUser.class, id);
        return p;
    }
    
    @Override
    public List<ProjectUser> getAllProjectUsers() {
        Query query = em.createQuery("SELECT e FROM ProjectUser e");
        List <ProjectUser> list = query.getResultList();
        return list;
    }
    
    @Override
    public Project findProject(long id) {
        Project p = em.find(Project.class, id);
        return p;
    }


    @Override
    public void createProject(String name, String description, Date created, Date lastModified) {
        Project p = new Project(name, description, created, lastModified);
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    @Override
    public void assignProjectUserToProject(long productUserId, long projectId) {
        Project project = em.find(Project.class, projectId);
        ProjectUser projectUser = em.find(ProjectUser.class, productUserId);
        em.getTransaction().begin();
        project.addProjectUser(projectUser);
        em.getTransaction().commit();
    }

    @Override
    public void createTaskAndAssignToProject(String name, String description, int hoursAssigned, long id) {
        Task task = new Task(name, description, hoursAssigned);
        Project p = em.find(Project.class, id);
        em.getTransaction().begin();
        em.persist(task);
        p.addTask(task);
        em.getTransaction().commit();
    }
}
