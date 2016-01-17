package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Table(name="project_user")
@Entity
public class ProjectUser implements Serializable {
    
    @Column(name="project_user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name="user_name")
    private String userName;
    
    @Column(name="email")
    private String email;
    
    @Column(name="created")
    private String created;
    
    @JoinColumn(name="project_id_hallo")
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
