package guomanwang.domain;

import java.util.Date;

public class Peoplenum {
    private Integer id;

    private Date accesstime;

    private Date lefttime;

    private Double holdtime;

    private Integer userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAccesstime() {
        return accesstime;
    }

    public void setAccesstime(Date accesstime) {
        this.accesstime = accesstime;
    }

    public Date getLefttime() {
        return lefttime;
    }

    public void setLefttime(Date lefttime) {
        this.lefttime = lefttime;
    }

    public Double getHoldtime() {
        return holdtime;
    }

    public void setHoldtime(Double holdtime) {
        this.holdtime = holdtime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}