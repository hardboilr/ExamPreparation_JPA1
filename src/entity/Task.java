package entity;

import javax.persistence.*;

/**
 * @author Tobias Jacobsen
 */
@Entity
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String name;
    private String description;
    private int hoursAssigned;
    private int hoursUsed;
    
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
}
