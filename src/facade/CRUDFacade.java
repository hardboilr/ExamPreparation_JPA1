package facade;

import entity.Project;
import entity.ProjectUser;
import java.util.List;

/**
 * @author Tobias Jacobsen
 */
public interface CRUDFacade {
    public void createProjectUser(String userName, String email);
    public ProjectUser findUser(long id);
    public List<ProjectUser> getAllProjectUsers();
    public Project createProject(String name, String description);
    public void assignProjectUserToProject(long id);
    public Project findProject(long id);
    public void createTaskAndAssignToProject(String name, String description, int hoursAssigned, long id);
}
