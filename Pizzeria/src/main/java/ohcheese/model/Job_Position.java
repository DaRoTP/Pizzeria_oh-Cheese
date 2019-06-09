package ohcheese.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "Job_Position")
public class Job_Position {
    @Id
    @Column(name = "Job_Position_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Position_Name")
    private String Position_Name;

    public Job_Position(String position_Name) {
        Position_Name = position_Name;
    }
    public Job_Position() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getPosition_Name() { return Position_Name; }
    public void setPosition_Name(String position_Name) { Position_Name = position_Name; }
    
}
