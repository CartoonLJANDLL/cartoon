package guomanwang.domain;

import java.util.Date;

public class Opera {
    private Integer opId;
    //番剧名称
    private String opName;
    //番剧地址
    private String opUrl;
    //番剧的一句话描述
    private String opDesc;
    //番剧图片的地址
    private String opPhotourl;
    //番剧更新至何时
    private String opUpdateto;
    //番剧类型 玄幻 竞技 爱情之类
    private String opType;
    //番剧分享地址，点击即可直接观看
    private String opIframeurl;
    //番剧在本网站更新时间
    private Date opTime;
    //番剧状态 0未完结   1完结
    private Integer opStatus;
    //番剧的收藏量
    private Integer opCollectnum;
    //番剧的分享量
    private Integer opSharenum;
    //标识是否被收藏
    private Integer collecte = 0;
    
    
    public Integer getCollecte() {
		return collecte;
	}

	public void setCollecte(Integer collecte) {
		this.collecte = collecte;
	}

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
		this.opUpdateto = opUpdateto;
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
}