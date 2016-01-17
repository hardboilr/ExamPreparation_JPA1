package interfaces;

import entity.Project;
import entity.ProjectUser;
import java.util.Date;
import java.util.List;

/**
 * @author Tobias Jacobsen
 */
public interface CRUDFacade {
    public void createProjectUser(String userName, String email);
    public void createProject(String name, String description, Date created, Date lastModified);
    public ProjectUser getProjectUser(long id);
    public Project findProject(long id);
    public List<ProjectUser> getAllProjectUsers();
    public void assignProjectUserToProject(long productUserId, long projectId);
    public void createTaskAndAssignToProject(String name, String description, int hoursAssigned, long id);
}
