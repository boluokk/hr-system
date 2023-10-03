package org.boluo.hr.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AppraiseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AppraiseExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andEidIsNull() {
            addCriterion("eid is null");
            return (Criteria) this;
        }

        public Criteria andEidIsNotNull() {
            addCriterion("eid is not null");
            return (Criteria) this;
        }

        public Criteria andEidEqualTo(Integer value) {
            addCriterion("eid =", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotEqualTo(Integer value) {
            addCriterion("eid <>", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidGreaterThan(Integer value) {
            addCriterion("eid >", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidGreaterThanOrEqualTo(Integer value) {
            addCriterion("eid >=", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLessThan(Integer value) {
            addCriterion("eid <", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLessThanOrEqualTo(Integer value) {
            addCriterion("eid <=", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidIn(List<Integer> values) {
            addCriterion("eid in", values, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotIn(List<Integer> values) {
            addCriterion("eid not in", values, "eid");
            return (Criteria) this;
        }

        public Criteria andEidBetween(Integer value1, Integer value2) {
            addCriterion("eid between", value1, value2, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotBetween(Integer value1, Integer value2) {
            addCriterion("eid not between", value1, value2, "eid");
            return (Criteria) this;
        }

        public Criteria andAppdateIsNull() {
            addCriterion("appDate is null");
            return (Criteria) this;
        }

        public Criteria andAppdateIsNotNull() {
            addCriterion("appDate is not null");
            return (Criteria) this;
        }

        public Criteria andAppdateEqualTo(Date value) {
            addCriterionForJDBCDate("appDate =", value, "appdate");
            return (Criteria) this;
        }

        public Criteria andAppdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("appDate <>", value, "appdate");
            return (Criteria) this;
        }

        public Criteria andAppdateGreaterThan(Date value) {
            addCriterionForJDBCDate("appDate >", value, "appdate");
            return (Criteria) this;
        }

        public Criteria andAppdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("appDate >=", value, "appdate");
            return (Criteria) this;
        }

        public Criteria andAppdateLessThan(Date value) {
            addCriterionForJDBCDate("appDate <", value, "appdate");
            return (Criteria) this;
        }

        public Criteria andAppdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("appDate <=", value, "appdate");
            return (Criteria) this;
        }

        public Criteria andAppdateIn(List<Date> values) {
            addCriterionForJDBCDate("appDate in", values, "appdate");
            return (Criteria) this;
        }

        public Criteria andAppdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("appDate not in", values, "appdate");
            return (Criteria) this;
        }

        public Criteria andAppdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("appDate between", value1, value2, "appdate");
            return (Criteria) this;
        }

        public Criteria andAppdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("appDate not between", value1, value2, "appdate");
            return (Criteria) this;
        }

        public Criteria andAppresultIsNull() {
            addCriterion("appResult is null");
            return (Criteria) this;
        }

        public Criteria andAppresultIsNotNull() {
            addCriterion("appResult is not null");
            return (Criteria) this;
        }

        public Criteria andAppresultEqualTo(String value) {
            addCriterion("appResult =", value, "appresult");
            return (Criteria) this;
        }

        public Criteria andAppresultNotEqualTo(String value) {
            addCriterion("appResult <>", value, "appresult");
            return (Criteria) this;
        }

        public Criteria andAppresultGreaterThan(String value) {
            addCriterion("appResult >", value, "appresult");
            return (Criteria) this;
        }

        public Criteria andAppresultGreaterThanOrEqualTo(String value) {
            addCriterion("appResult >=", value, "appresult");
            return (Criteria) this;
        }

        public Criteria andAppresultLessThan(String value) {
            addCriterion("appResult <", value, "appresult");
            return (Criteria) this;
        }

        public Criteria andAppresultLessThanOrEqualTo(String value) {
            addCriterion("appResult <=", value, "appresult");
            return (Criteria) this;
        }

        public Criteria andAppresultLike(String value) {
            addCriterion("appResult like", value, "appresult");
            return (Criteria) this;
        }

        public Criteria andAppresultNotLike(String value) {
            addCriterion("appResult not like", value, "appresult");
            return (Criteria) this;
        }

        public Criteria andAppresultIn(List<String> values) {
            addCriterion("appResult in", values, "appresult");
            return (Criteria) this;
        }

        public Criteria andAppresultNotIn(List<String> values) {
            addCriterion("appResult not in", values, "appresult");
            return (Criteria) this;
        }

        public Criteria andAppresultBetween(String value1, String value2) {
            addCriterion("appResult between", value1, value2, "appresult");
            return (Criteria) this;
        }

        public Criteria andAppresultNotBetween(String value1, String value2) {
            addCriterion("appResult not between", value1, value2, "appresult");
            return (Criteria) this;
        }

        public Criteria andAppcontentIsNull() {
            addCriterion("appContent is null");
            return (Criteria) this;
        }

        public Criteria andAppcontentIsNotNull() {
            addCriterion("appContent is not null");
            return (Criteria) this;
        }

        public Criteria andAppcontentEqualTo(String value) {
            addCriterion("appContent =", value, "appcontent");
            return (Criteria) this;
        }

        public Criteria andAppcontentNotEqualTo(String value) {
            addCriterion("appContent <>", value, "appcontent");
            return (Criteria) this;
        }

        public Criteria andAppcontentGreaterThan(String value) {
            addCriterion("appContent >", value, "appcontent");
            return (Criteria) this;
        }

        public Criteria andAppcontentGreaterThanOrEqualTo(String value) {
            addCriterion("appContent >=", value, "appcontent");
            return (Criteria) this;
        }

        public Criteria andAppcontentLessThan(String value) {
            addCriterion("appContent <", value, "appcontent");
            return (Criteria) this;
        }

        public Criteria andAppcontentLessThanOrEqualTo(String value) {
            addCriterion("appContent <=", value, "appcontent");
            return (Criteria) this;
        }

        public Criteria andAppcontentLike(String value) {
            addCriterion("appContent like", value, "appcontent");
            return (Criteria) this;
        }

        public Criteria andAppcontentNotLike(String value) {
            addCriterion("appContent not like", value, "appcontent");
            return (Criteria) this;
        }

        public Criteria andAppcontentIn(List<String> values) {
            addCriterion("appContent in", values, "appcontent");
            return (Criteria) this;
        }

        public Criteria andAppcontentNotIn(List<String> values) {
            addCriterion("appContent not in", values, "appcontent");
            return (Criteria) this;
        }

        public Criteria andAppcontentBetween(String value1, String value2) {
            addCriterion("appContent between", value1, value2, "appcontent");
            return (Criteria) this;
        }

        public Criteria andAppcontentNotBetween(String value1, String value2) {
            addCriterion("appContent not between", value1, value2, "appcontent");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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