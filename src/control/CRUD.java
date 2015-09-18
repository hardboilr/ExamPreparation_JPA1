package control;

import entity.Project;
import entity.ProjectUser;
import entity.Task;
import facade.CRUDFacade;
import java.util.List;
import javax.persistence.*;

/**
 * @author Tobias Jacobsen
 */
public class CRUD implements CRUDFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    EntityManager em = emf.createEntityManager();

    @Override
    public void createProjectUser(String userName, String email) {
        ProjectUser p = new ProjectUser(userName, email);
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    @Override
    public ProjectUser findUser(long id) {
        ProjectUser p = em.find(ProjectUser.class, id);
        return p;
    }

    @Override
    public Project findProject(long id) {
        Project p = em.find(Project.class, id);
        return p;
    }

    @Override
    public List<ProjectUser> getAllProjectUsers() {
        Query query = em.createQuery("SELECT e FROM ProjectUser e");
        List <ProjectUser> list = query.getResultList();
        return list;
    }

    @Override
    public void createProject(String name, String description) {
        Project p = new Project(name, description);
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
