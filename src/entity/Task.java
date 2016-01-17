package entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name="task")
@Entity
public class Task implements Serializable {
    
    @Column(name="task_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="description")
    private String description;
    
    @Column(name="hours_assigned")
    private int hoursAssigned;
    
    @Column(name="hours_used")
    private int hoursUsed;
    
    @JoinColumn(name="project_id")
    @ManyToOne
    private Project project;
    
    public Task() {
    }
    
    public Task(String name, String description, int hoursAssigned) {
        this.name = name;
        this.description = description;
        this.hoursAssigned = hoursAssigned;
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

    public int getHoursAssigned() {
        return hoursAssigned;
    }

    public void setHoursAssigned(int hoursAssigned) {
        this.hoursAssigned = hoursAssigned;
    }

    public int getHoursUsed() {
        return hoursUsed;
    }

    public void setHoursUsed(int hoursUsed) {
        this.hoursUsed = hoursUsed;
    }

    public long getId() {
        return id;
    }
}
