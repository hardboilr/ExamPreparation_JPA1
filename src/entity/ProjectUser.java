package entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Tobias Jacobsen
 */
@Entity
public class ProjectUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private long id;
    
    private String userName;
    private String email;
    private String created;
    
    @ManyToMany(mappedBy="projectUserList")
    private List<Project> projectList = new ArrayList();

    public ProjectUser() {
    }
    
    public ProjectUser(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public long getId() {
        return id;
    }
    
    
}
