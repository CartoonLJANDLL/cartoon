package guomanwang.domain;

import java.util.Date;

public class Opnumupdate {
    private Integer id;

    private Date updatetime;

    private Integer opnum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getOpnum() {
        return opnum;
    }

    public void setOpnum(Integer opnum) {
        this.opnum = opnum;
    }
}