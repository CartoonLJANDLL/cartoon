package guomanwang.domain;

import java.util.Date;

public class UserOpera {
    private Integer id;

    private Integer userId;

    private Integer operaId;

    private Integer collection;

    private Integer share;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOperaId() {
        return operaId;
    }

    public void setOperaId(Integer operaId) {
        this.operaId = operaId;
    }

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }

    public Integer getShare() {
        return share;
    }

    public void setShare(Integer share) {
        this.share = share;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}