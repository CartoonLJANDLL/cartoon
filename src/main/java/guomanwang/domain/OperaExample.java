package guomanwang.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OperaExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andOpIdIsNull() {
            addCriterion("op_id is null");
            return (Criteria) this;
        }

        public Criteria andOpIdIsNotNull() {
            addCriterion("op_id is not null");
            return (Criteria) this;
        }

        public Criteria andOpIdEqualTo(Integer value) {
            addCriterion("op_id =", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotEqualTo(Integer value) {
            addCriterion("op_id <>", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdGreaterThan(Integer value) {
            addCriterion("op_id >", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("op_id >=", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdLessThan(Integer value) {
            addCriterion("op_id <", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdLessThanOrEqualTo(Integer value) {
            addCriterion("op_id <=", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdIn(List<Integer> values) {
            addCriterion("op_id in", values, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotIn(List<Integer> values) {
            addCriterion("op_id not in", values, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdBetween(Integer value1, Integer value2) {
            addCriterion("op_id between", value1, value2, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotBetween(Integer value1, Integer value2) {
            addCriterion("op_id not between", value1, value2, "opId");
            return (Criteria) this;
        }

        public Criteria andOpNameIsNull() {
            addCriterion("op_name is null");
            return (Criteria) this;
        }

        public Criteria andOpNameIsNotNull() {
            addCriterion("op_name is not null");
            return (Criteria) this;
        }

        public Criteria andOpNameEqualTo(String value) {
            addCriterion("op_name =", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameNotEqualTo(String value) {
            addCriterion("op_name <>", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameGreaterThan(String value) {
            addCriterion("op_name >", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameGreaterThanOrEqualTo(String value) {
            addCriterion("op_name >=", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameLessThan(String value) {
            addCriterion("op_name <", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameLessThanOrEqualTo(String value) {
            addCriterion("op_name <=", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameLike(String value) {
            addCriterion("op_name like", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameNotLike(String value) {
            addCriterion("op_name not like", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameIn(List<String> values) {
            addCriterion("op_name in", values, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameNotIn(List<String> values) {
            addCriterion("op_name not in", values, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameBetween(String value1, String value2) {
            addCriterion("op_name between", value1, value2, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameNotBetween(String value1, String value2) {
            addCriterion("op_name not between", value1, value2, "opName");
            return (Criteria) this;
        }

        public Criteria andOpUrlIsNull() {
            addCriterion("op_url is null");
            return (Criteria) this;
        }

        public Criteria andOpUrlIsNotNull() {
            addCriterion("op_url is not null");
            return (Criteria) this;
        }

        public Criteria andOpUrlEqualTo(String value) {
            addCriterion("op_url =", value, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlNotEqualTo(String value) {
            addCriterion("op_url <>", value, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlGreaterThan(String value) {
            addCriterion("op_url >", value, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlGreaterThanOrEqualTo(String value) {
            addCriterion("op_url >=", value, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlLessThan(String value) {
            addCriterion("op_url <", value, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlLessThanOrEqualTo(String value) {
            addCriterion("op_url <=", value, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlLike(String value) {
            addCriterion("op_url like", value, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlNotLike(String value) {
            addCriterion("op_url not like", value, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlIn(List<String> values) {
            addCriterion("op_url in", values, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlNotIn(List<String> values) {
            addCriterion("op_url not in", values, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlBetween(String value1, String value2) {
            addCriterion("op_url between", value1, value2, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlNotBetween(String value1, String value2) {
            addCriterion("op_url not between", value1, value2, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpDescIsNull() {
            addCriterion("op_desc is null");
            return (Criteria) this;
        }

        public Criteria andOpDescIsNotNull() {
            addCriterion("op_desc is not null");
            return (Criteria) this;
        }

        public Criteria andOpDescEqualTo(String value) {
            addCriterion("op_desc =", value, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescNotEqualTo(String value) {
            addCriterion("op_desc <>", value, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescGreaterThan(String value) {
            addCriterion("op_desc >", value, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescGreaterThanOrEqualTo(String value) {
            addCriterion("op_desc >=", value, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescLessThan(String value) {
            addCriterion("op_desc <", value, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescLessThanOrEqualTo(String value) {
            addCriterion("op_desc <=", value, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescLike(String value) {
            addCriterion("op_desc like", value, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescNotLike(String value) {
            addCriterion("op_desc not like", value, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescIn(List<String> values) {
            addCriterion("op_desc in", values, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescNotIn(List<String> values) {
            addCriterion("op_desc not in", values, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescBetween(String value1, String value2) {
            addCriterion("op_desc between", value1, value2, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescNotBetween(String value1, String value2) {
            addCriterion("op_desc not between", value1, value2, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpPhotourlIsNull() {
            addCriterion("op_photourl is null");
            return (Criteria) this;
        }

        public Criteria andOpPhotourlIsNotNull() {
            addCriterion("op_photourl is not null");
            return (Criteria) this;
        }

        public Criteria andOpPhotourlEqualTo(String value) {
            addCriterion("op_photourl =", value, "opPhotourl");
            return (Criteria) this;
        }

        public Criteria andOpPhotourlNotEqualTo(String value) {
            addCriterion("op_photourl <>", value, "opPhotourl");
            return (Criteria) this;
        }

        public Criteria andOpPhotourlGreaterThan(String value) {
            addCriterion("op_photourl >", value, "opPhotourl");
            return (Criteria) this;
        }

        public Criteria andOpPhotourlGreaterThanOrEqualTo(String value) {
            addCriterion("op_photourl >=", value, "opPhotourl");
            return (Criteria) this;
        }

        public Criteria andOpPhotourlLessThan(String value) {
            addCriterion("op_photourl <", value, "opPhotourl");
            return (Criteria) this;
        }

        public Criteria andOpPhotourlLessThanOrEqualTo(String value) {
            addCriterion("op_photourl <=", value, "opPhotourl");
            return (Criteria) this;
        }

        public Criteria andOpPhotourlLike(String value) {
            addCriterion("op_photourl like", value, "opPhotourl");
            return (Criteria) this;
        }

        public Criteria andOpPhotourlNotLike(String value) {
            addCriterion("op_photourl not like", value, "opPhotourl");
            return (Criteria) this;
        }

        public Criteria andOpPhotourlIn(List<String> values) {
            addCriterion("op_photourl in", values, "opPhotourl");
            return (Criteria) this;
        }

        public Criteria andOpPhotourlNotIn(List<String> values) {
            addCriterion("op_photourl not in", values, "opPhotourl");
            return (Criteria) this;
        }

        public Criteria andOpPhotourlBetween(String value1, String value2) {
            addCriterion("op_photourl between", value1, value2, "opPhotourl");
            return (Criteria) this;
        }

        public Criteria andOpPhotourlNotBetween(String value1, String value2) {
            addCriterion("op_photourl not between", value1, value2, "opPhotourl");
            return (Criteria) this;
        }

        public Criteria andOpUpdatetoIsNull() {
            addCriterion("op_updateto is null");
            return (Criteria) this;
        }

        public Criteria andOpUpdatetoIsNotNull() {
            addCriterion("op_updateto is not null");
            return (Criteria) this;
        }

        public Criteria andOpUpdatetoEqualTo(String value) {
            addCriterion("op_updateto =", value, "opUpdateto");
            return (Criteria) this;
        }

        public Criteria andOpUpdatetoNotEqualTo(String value) {
            addCriterion("op_updateto <>", value, "opUpdateto");
            return (Criteria) this;
        }

        public Criteria andOpUpdatetoGreaterThan(String value) {
            addCriterion("op_updateto >", value, "opUpdateto");
            return (Criteria) this;
        }

        public Criteria andOpUpdatetoGreaterThanOrEqualTo(String value) {
            addCriterion("op_updateto >=", value, "opUpdateto");
            return (Criteria) this;
        }

        public Criteria andOpUpdatetoLessThan(String value) {
            addCriterion("op_updateto <", value, "opUpdateto");
            return (Criteria) this;
        }

        public Criteria andOpUpdatetoLessThanOrEqualTo(String value) {
            addCriterion("op_updateto <=", value, "opUpdateto");
            return (Criteria) this;
        }

        public Criteria andOpUpdatetoLike(String value) {
            addCriterion("op_updateto like", value, "opUpdateto");
            return (Criteria) this;
        }

        public Criteria andOpUpdatetoNotLike(String value) {
            addCriterion("op_updateto not like", value, "opUpdateto");
            return (Criteria) this;
        }

        public Criteria andOpUpdatetoIn(List<String> values) {
            addCriterion("op_updateto in", values, "opUpdateto");
            return (Criteria) this;
        }

        public Criteria andOpUpdatetoNotIn(List<String> values) {
            addCriterion("op_updateto not in", values, "opUpdateto");
            return (Criteria) this;
        }

        public Criteria andOpUpdatetoBetween(String value1, String value2) {
            addCriterion("op_updateto between", value1, value2, "opUpdateto");
            return (Criteria) this;
        }

        public Criteria andOpUpdatetoNotBetween(String value1, String value2) {
            addCriterion("op_updateto not between", value1, value2, "opUpdateto");
            return (Criteria) this;
        }

        public Criteria andOpTypeIsNull() {
            addCriterion("op_type is null");
            return (Criteria) this;
        }

        public Criteria andOpTypeIsNotNull() {
            addCriterion("op_type is not null");
            return (Criteria) this;
        }

        public Criteria andOpTypeEqualTo(String value) {
            addCriterion("op_type =", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeNotEqualTo(String value) {
            addCriterion("op_type <>", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeGreaterThan(String value) {
            addCriterion("op_type >", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeGreaterThanOrEqualTo(String value) {
            addCriterion("op_type >=", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeLessThan(String value) {
            addCriterion("op_type <", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeLessThanOrEqualTo(String value) {
            addCriterion("op_type <=", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeLike(String value) {
            addCriterion("op_type like", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeNotLike(String value) {
            addCriterion("op_type not like", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeIn(List<String> values) {
            addCriterion("op_type in", values, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeNotIn(List<String> values) {
            addCriterion("op_type not in", values, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeBetween(String value1, String value2) {
            addCriterion("op_type between", value1, value2, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeNotBetween(String value1, String value2) {
            addCriterion("op_type not between", value1, value2, "opType");
            return (Criteria) this;
        }

        public Criteria andOpIframeurlIsNull() {
            addCriterion("op_iframeurl is null");
            return (Criteria) this;
        }

        public Criteria andOpIframeurlIsNotNull() {
            addCriterion("op_iframeurl is not null");
            return (Criteria) this;
        }

        public Criteria andOpIframeurlEqualTo(String value) {
            addCriterion("op_iframeurl =", value, "opIframeurl");
            return (Criteria) this;
        }

        public Criteria andOpIframeurlNotEqualTo(String value) {
            addCriterion("op_iframeurl <>", value, "opIframeurl");
            return (Criteria) this;
        }

        public Criteria andOpIframeurlGreaterThan(String value) {
            addCriterion("op_iframeurl >", value, "opIframeurl");
            return (Criteria) this;
        }

        public Criteria andOpIframeurlGreaterThanOrEqualTo(String value) {
            addCriterion("op_iframeurl >=", value, "opIframeurl");
            return (Criteria) this;
        }

        public Criteria andOpIframeurlLessThan(String value) {
            addCriterion("op_iframeurl <", value, "opIframeurl");
            return (Criteria) this;
        }

        public Criteria andOpIframeurlLessThanOrEqualTo(String value) {
            addCriterion("op_iframeurl <=", value, "opIframeurl");
            return (Criteria) this;
        }

        public Criteria andOpIframeurlLike(String value) {
            addCriterion("op_iframeurl like", value, "opIframeurl");
            return (Criteria) this;
        }

        public Criteria andOpIframeurlNotLike(String value) {
            addCriterion("op_iframeurl not like", value, "opIframeurl");
            return (Criteria) this;
        }

        public Criteria andOpIframeurlIn(List<String> values) {
            addCriterion("op_iframeurl in", values, "opIframeurl");
            return (Criteria) this;
        }

        public Criteria andOpIframeurlNotIn(List<String> values) {
            addCriterion("op_iframeurl not in", values, "opIframeurl");
            return (Criteria) this;
        }

        public Criteria andOpIframeurlBetween(String value1, String value2) {
            addCriterion("op_iframeurl between", value1, value2, "opIframeurl");
            return (Criteria) this;
        }

        public Criteria andOpIframeurlNotBetween(String value1, String value2) {
            addCriterion("op_iframeurl not between", value1, value2, "opIframeurl");
            return (Criteria) this;
        }

        public Criteria andOpTimeIsNull() {
            addCriterion("op_time is null");
            return (Criteria) this;
        }

        public Criteria andOpTimeIsNotNull() {
            addCriterion("op_time is not null");
            return (Criteria) this;
        }

        public Criteria andOpTimeEqualTo(Date value) {
            addCriterion("op_time =", value, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeNotEqualTo(Date value) {
            addCriterion("op_time <>", value, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeGreaterThan(Date value) {
            addCriterion("op_time >", value, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("op_time >=", value, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeLessThan(Date value) {
            addCriterion("op_time <", value, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeLessThanOrEqualTo(Date value) {
            addCriterion("op_time <=", value, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeIn(List<Date> values) {
            addCriterion("op_time in", values, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeNotIn(List<Date> values) {
            addCriterion("op_time not in", values, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeBetween(Date value1, Date value2) {
            addCriterion("op_time between", value1, value2, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeNotBetween(Date value1, Date value2) {
            addCriterion("op_time not between", value1, value2, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpStatusIsNull() {
            addCriterion("op_status is null");
            return (Criteria) this;
        }

        public Criteria andOpStatusIsNotNull() {
            addCriterion("op_status is not null");
            return (Criteria) this;
        }

        public Criteria andOpStatusEqualTo(Integer value) {
            addCriterion("op_status =", value, "opStatus");
            return (Criteria) this;
        }

        public Criteria andOpStatusNotEqualTo(Integer value) {
            addCriterion("op_status <>", value, "opStatus");
            return (Criteria) this;
        }

        public Criteria andOpStatusGreaterThan(Integer value) {
            addCriterion("op_status >", value, "opStatus");
            return (Criteria) this;
        }

        public Criteria andOpStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("op_status >=", value, "opStatus");
            return (Criteria) this;
        }

        public Criteria andOpStatusLessThan(Integer value) {
            addCriterion("op_status <", value, "opStatus");
            return (Criteria) this;
        }

        public Criteria andOpStatusLessThanOrEqualTo(Integer value) {
            addCriterion("op_status <=", value, "opStatus");
            return (Criteria) this;
        }

        public Criteria andOpStatusIn(List<Integer> values) {
            addCriterion("op_status in", values, "opStatus");
            return (Criteria) this;
        }

        public Criteria andOpStatusNotIn(List<Integer> values) {
            addCriterion("op_status not in", values, "opStatus");
            return (Criteria) this;
        }

        public Criteria andOpStatusBetween(Integer value1, Integer value2) {
            addCriterion("op_status between", value1, value2, "opStatus");
            return (Criteria) this;
        }

        public Criteria andOpStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("op_status not between", value1, value2, "opStatus");
            return (Criteria) this;
        }

        public Criteria andOpCollectnumIsNull() {
            addCriterion("op_collectnum is null");
            return (Criteria) this;
        }

        public Criteria andOpCollectnumIsNotNull() {
            addCriterion("op_collectnum is not null");
            return (Criteria) this;
        }

        public Criteria andOpCollectnumEqualTo(Integer value) {
            addCriterion("op_collectnum =", value, "opCollectnum");
            return (Criteria) this;
        }

        public Criteria andOpCollectnumNotEqualTo(Integer value) {
            addCriterion("op_collectnum <>", value, "opCollectnum");
            return (Criteria) this;
        }

        public Criteria andOpCollectnumGreaterThan(Integer value) {
            addCriterion("op_collectnum >", value, "opCollectnum");
            return (Criteria) this;
        }

        public Criteria andOpCollectnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("op_collectnum >=", value, "opCollectnum");
            return (Criteria) this;
        }

        public Criteria andOpCollectnumLessThan(Integer value) {
            addCriterion("op_collectnum <", value, "opCollectnum");
            return (Criteria) this;
        }

        public Criteria andOpCollectnumLessThanOrEqualTo(Integer value) {
            addCriterion("op_collectnum <=", value, "opCollectnum");
            return (Criteria) this;
        }

        public Criteria andOpCollectnumIn(List<Integer> values) {
            addCriterion("op_collectnum in", values, "opCollectnum");
            return (Criteria) this;
        }

        public Criteria andOpCollectnumNotIn(List<Integer> values) {
            addCriterion("op_collectnum not in", values, "opCollectnum");
            return (Criteria) this;
        }

        public Criteria andOpCollectnumBetween(Integer value1, Integer value2) {
            addCriterion("op_collectnum between", value1, value2, "opCollectnum");
            return (Criteria) this;
        }

        public Criteria andOpCollectnumNotBetween(Integer value1, Integer value2) {
            addCriterion("op_collectnum not between", value1, value2, "opCollectnum");
            return (Criteria) this;
        }

        public Criteria andOpSharenumIsNull() {
            addCriterion("op_sharenum is null");
            return (Criteria) this;
        }

        public Criteria andOpSharenumIsNotNull() {
            addCriterion("op_sharenum is not null");
            return (Criteria) this;
        }

        public Criteria andOpSharenumEqualTo(Integer value) {
            addCriterion("op_sharenum =", value, "opSharenum");
            return (Criteria) this;
        }

        public Criteria andOpSharenumNotEqualTo(Integer value) {
            addCriterion("op_sharenum <>", value, "opSharenum");
            return (Criteria) this;
        }

        public Criteria andOpSharenumGreaterThan(Integer value) {
            addCriterion("op_sharenum >", value, "opSharenum");
            return (Criteria) this;
        }

        public Criteria andOpSharenumGreaterThanOrEqualTo(Integer value) {
            addCriterion("op_sharenum >=", value, "opSharenum");
            return (Criteria) this;
        }

        public Criteria andOpSharenumLessThan(Integer value) {
            addCriterion("op_sharenum <", value, "opSharenum");
            return (Criteria) this;
        }

        public Criteria andOpSharenumLessThanOrEqualTo(Integer value) {
            addCriterion("op_sharenum <=", value, "opSharenum");
            return (Criteria) this;
        }

        public Criteria andOpSharenumIn(List<Integer> values) {
            addCriterion("op_sharenum in", values, "opSharenum");
            return (Criteria) this;
        }

        public Criteria andOpSharenumNotIn(List<Integer> values) {
            addCriterion("op_sharenum not in", values, "opSharenum");
            return (Criteria) this;
        }

        public Criteria andOpSharenumBetween(Integer value1, Integer value2) {
            addCriterion("op_sharenum between", value1, value2, "opSharenum");
            return (Criteria) this;
        }

        public Criteria andOpSharenumNotBetween(Integer value1, Integer value2) {
            addCriterion("op_sharenum not between", value1, value2, "opSharenum");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}