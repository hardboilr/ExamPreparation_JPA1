package entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * @author Tobias Jacobsen
 */
@Entity
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String name;
    private String description;
    private String created;
    private String lastModified;
    
    @ManyToMany
    private List<ProjectUser> projectUserList = new ArrayList();
    
    @OneToMany(mappedBy="project")
    private List<Task> taskList = new ArrayList();
    
    public Project() {
    }
    
    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }
}
