package local;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;


@Entity
@Table(name="RepairProcessing_table")
public class RepairProcessing {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String status;
    private Long acceptid;

    @PostPersist
    public void onPostPersist(){

        RepairStarted repairStarted = new RepairStarted();
        BeanUtils.copyProperties(this, repairStarted);
        repairStarted.publish();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Long getAcceptid() {
        return acceptid;
    }

    public void setAcceptid(Long acceptid) {
        this.acceptid = acceptid;
    }




}
