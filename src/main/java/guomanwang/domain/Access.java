package guomanwang.domain;

public class Access {
    private Integer id;

    private String accesspwd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccesspwd() {
        return accesspwd;
    }

    public void setAccesspwd(String accesspwd) {
        this.accesspwd = accesspwd == null ? null : accesspwd.trim();
    }
}