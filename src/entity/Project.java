package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "project")
public class Project implements Serializable {

    @Column(name = "project_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    @JoinTable(name = "project_users", joinColumns = {
        @JoinColumn(name = "project_id", referencedColumnName = "project_id")}, inverseJoinColumns = {
        @JoinColumn(name = "project_user_id")})
    @ManyToMany
    private List<ProjectUser> projectUserList = new ArrayList();

    @JoinColumn(name = "task_list")
    @OneToMany(mappedBy = "project")
    private List<Task> taskList = new ArrayList();

    public Project() {
    }

    public Project(String name, String description, Date created, Date lastModified) {
        this.name = name;
        this.description = description;
        this.created = created;
        this.lastModified = lastModified;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public long getId() {
        return id;
    }

    public void addProjectUser(ProjectUser p) {
        projectUserList.add(p);
    }

    public ProjectUser getProjectUser(long id) {
        for (ProjectUser p : projectUserList) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public Task getTask(long id) {
        for (Task t : taskList) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public void addTask(Task t) {
        taskList.add(t);
    }
}
