package guomanwang.domain;

import java.util.Date;

public class Opera {
    private Integer opId;

    private String opName;

    private String opUrl;

    private String opDesc;

    private String opPhotourl;

    private String opUpdateto;

    private String opType;

    private String opIframeurl;

    private Date opTime;

    private Integer opStatus;

    private Integer opCollectnum;

    private Integer opSharenum;

    private Integer opPlaynum;

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName == null ? null : opName.trim();
    }

    public String getOpUrl() {
        return opUrl;
    }

    public void setOpUrl(String opUrl) {
        this.opUrl = opUrl == null ? null : opUrl.trim();
    }

    public String getOpDesc() {
        return opDesc;
    }

    public void setOpDesc(String opDesc) {
        this.opDesc = opDesc == null ? null : opDesc.trim();
    }

    public String getOpPhotourl() {
        return opPhotourl;
    }

    public void setOpPhotourl(String opPhotourl) {
        this.opPhotourl = opPhotourl == null ? null : opPhotourl.trim();
    }

    public String getOpUpdateto() {
        return opUpdateto;
    }

    public void setOpUpdateto(String opUpdateto) {
        this.opUpdateto = opUpdateto == null ? null : opUpdateto.trim();
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType == null ? null : opType.trim();
    }

    public String getOpIframeurl() {
        return opIframeurl;
    }

    public void setOpIframeurl(String opIframeurl) {
        this.opIframeurl = opIframeurl == null ? null : opIframeurl.trim();
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public Integer getOpStatus() {
        return opStatus;
    }

    public void setOpStatus(Integer opStatus) {
        this.opStatus = opStatus;
    }

    public Integer getOpCollectnum() {
        return opCollectnum;
    }

    public void setOpCollectnum(Integer opCollectnum) {
        this.opCollectnum = opCollectnum;
    }

    public Integer getOpSharenum() {
        return opSharenum;
    }

    public void setOpSharenum(Integer opSharenum) {
        this.opSharenum = opSharenum;
    }

    public Integer getOpPlaynum() {
        return opPlaynum;
    }

    public void setOpPlaynum(Integer opPlaynum) {
        this.opPlaynum = opPlaynum;
    }

	public void setCollecte(int i) {
		// TODO Auto-generated method stub
		
	}
}