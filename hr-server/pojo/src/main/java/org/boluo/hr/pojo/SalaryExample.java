package org.boluo.hr.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalaryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SalaryExample() {
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

        public Criteria andBasicsalaryIsNull() {
            addCriterion("basicSalary is null");
            return (Criteria) this;
        }

        public Criteria andBasicsalaryIsNotNull() {
            addCriterion("basicSalary is not null");
            return (Criteria) this;
        }

        public Criteria andBasicsalaryEqualTo(Integer value) {
            addCriterion("basicSalary =", value, "basicsalary");
            return (Criteria) this;
        }

        public Criteria andBasicsalaryNotEqualTo(Integer value) {
            addCriterion("basicSalary <>", value, "basicsalary");
            return (Criteria) this;
        }

        public Criteria andBasicsalaryGreaterThan(Integer value) {
            addCriterion("basicSalary >", value, "basicsalary");
            return (Criteria) this;
        }

        public Criteria andBasicsalaryGreaterThanOrEqualTo(Integer value) {
            addCriterion("basicSalary >=", value, "basicsalary");
            return (Criteria) this;
        }

        public Criteria andBasicsalaryLessThan(Integer value) {
            addCriterion("basicSalary <", value, "basicsalary");
            return (Criteria) this;
        }

        public Criteria andBasicsalaryLessThanOrEqualTo(Integer value) {
            addCriterion("basicSalary <=", value, "basicsalary");
            return (Criteria) this;
        }

        public Criteria andBasicsalaryIn(List<Integer> values) {
            addCriterion("basicSalary in", values, "basicsalary");
            return (Criteria) this;
        }

        public Criteria andBasicsalaryNotIn(List<Integer> values) {
            addCriterion("basicSalary not in", values, "basicsalary");
            return (Criteria) this;
        }

        public Criteria andBasicsalaryBetween(Integer value1, Integer value2) {
            addCriterion("basicSalary between", value1, value2, "basicsalary");
            return (Criteria) this;
        }

        public Criteria andBasicsalaryNotBetween(Integer value1, Integer value2) {
            addCriterion("basicSalary not between", value1, value2, "basicsalary");
            return (Criteria) this;
        }

        public Criteria andBonusIsNull() {
            addCriterion("bonus is null");
            return (Criteria) this;
        }

        public Criteria andBonusIsNotNull() {
            addCriterion("bonus is not null");
            return (Criteria) this;
        }

        public Criteria andBonusEqualTo(Integer value) {
            addCriterion("bonus =", value, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusNotEqualTo(Integer value) {
            addCriterion("bonus <>", value, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusGreaterThan(Integer value) {
            addCriterion("bonus >", value, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusGreaterThanOrEqualTo(Integer value) {
            addCriterion("bonus >=", value, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusLessThan(Integer value) {
            addCriterion("bonus <", value, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusLessThanOrEqualTo(Integer value) {
            addCriterion("bonus <=", value, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusIn(List<Integer> values) {
            addCriterion("bonus in", values, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusNotIn(List<Integer> values) {
            addCriterion("bonus not in", values, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusBetween(Integer value1, Integer value2) {
            addCriterion("bonus between", value1, value2, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusNotBetween(Integer value1, Integer value2) {
            addCriterion("bonus not between", value1, value2, "bonus");
            return (Criteria) this;
        }

        public Criteria andLunchsalaryIsNull() {
            addCriterion("lunchSalary is null");
            return (Criteria) this;
        }

        public Criteria andLunchsalaryIsNotNull() {
            addCriterion("lunchSalary is not null");
            return (Criteria) this;
        }

        public Criteria andLunchsalaryEqualTo(Integer value) {
            addCriterion("lunchSalary =", value, "lunchsalary");
            return (Criteria) this;
        }

        public Criteria andLunchsalaryNotEqualTo(Integer value) {
            addCriterion("lunchSalary <>", value, "lunchsalary");
            return (Criteria) this;
        }

        public Criteria andLunchsalaryGreaterThan(Integer value) {
            addCriterion("lunchSalary >", value, "lunchsalary");
            return (Criteria) this;
        }

        public Criteria andLunchsalaryGreaterThanOrEqualTo(Integer value) {
            addCriterion("lunchSalary >=", value, "lunchsalary");
            return (Criteria) this;
        }

        public Criteria andLunchsalaryLessThan(Integer value) {
            addCriterion("lunchSalary <", value, "lunchsalary");
            return (Criteria) this;
        }

        public Criteria andLunchsalaryLessThanOrEqualTo(Integer value) {
            addCriterion("lunchSalary <=", value, "lunchsalary");
            return (Criteria) this;
        }

        public Criteria andLunchsalaryIn(List<Integer> values) {
            addCriterion("lunchSalary in", values, "lunchsalary");
            return (Criteria) this;
        }

        public Criteria andLunchsalaryNotIn(List<Integer> values) {
            addCriterion("lunchSalary not in", values, "lunchsalary");
            return (Criteria) this;
        }

        public Criteria andLunchsalaryBetween(Integer value1, Integer value2) {
            addCriterion("lunchSalary between", value1, value2, "lunchsalary");
            return (Criteria) this;
        }

        public Criteria andLunchsalaryNotBetween(Integer value1, Integer value2) {
            addCriterion("lunchSalary not between", value1, value2, "lunchsalary");
            return (Criteria) this;
        }

        public Criteria andTrafficsalaryIsNull() {
            addCriterion("trafficSalary is null");
            return (Criteria) this;
        }

        public Criteria andTrafficsalaryIsNotNull() {
            addCriterion("trafficSalary is not null");
            return (Criteria) this;
        }

        public Criteria andTrafficsalaryEqualTo(Integer value) {
            addCriterion("trafficSalary =", value, "trafficsalary");
            return (Criteria) this;
        }

        public Criteria andTrafficsalaryNotEqualTo(Integer value) {
            addCriterion("trafficSalary <>", value, "trafficsalary");
            return (Criteria) this;
        }

        public Criteria andTrafficsalaryGreaterThan(Integer value) {
            addCriterion("trafficSalary >", value, "trafficsalary");
            return (Criteria) this;
        }

        public Criteria andTrafficsalaryGreaterThanOrEqualTo(Integer value) {
            addCriterion("trafficSalary >=", value, "trafficsalary");
            return (Criteria) this;
        }

        public Criteria andTrafficsalaryLessThan(Integer value) {
            addCriterion("trafficSalary <", value, "trafficsalary");
            return (Criteria) this;
        }

        public Criteria andTrafficsalaryLessThanOrEqualTo(Integer value) {
            addCriterion("trafficSalary <=", value, "trafficsalary");
            return (Criteria) this;
        }

        public Criteria andTrafficsalaryIn(List<Integer> values) {
            addCriterion("trafficSalary in", values, "trafficsalary");
            return (Criteria) this;
        }

        public Criteria andTrafficsalaryNotIn(List<Integer> values) {
            addCriterion("trafficSalary not in", values, "trafficsalary");
            return (Criteria) this;
        }

        public Criteria andTrafficsalaryBetween(Integer value1, Integer value2) {
            addCriterion("trafficSalary between", value1, value2, "trafficsalary");
            return (Criteria) this;
        }

        public Criteria andTrafficsalaryNotBetween(Integer value1, Integer value2) {
            addCriterion("trafficSalary not between", value1, value2, "trafficsalary");
            return (Criteria) this;
        }

        public Criteria andAllsalaryIsNull() {
            addCriterion("allSalary is null");
            return (Criteria) this;
        }

        public Criteria andAllsalaryIsNotNull() {
            addCriterion("allSalary is not null");
            return (Criteria) this;
        }

        public Criteria andAllsalaryEqualTo(Integer value) {
            addCriterion("allSalary =", value, "allsalary");
            return (Criteria) this;
        }

        public Criteria andAllsalaryNotEqualTo(Integer value) {
            addCriterion("allSalary <>", value, "allsalary");
            return (Criteria) this;
        }

        public Criteria andAllsalaryGreaterThan(Integer value) {
            addCriterion("allSalary >", value, "allsalary");
            return (Criteria) this;
        }

        public Criteria andAllsalaryGreaterThanOrEqualTo(Integer value) {
            addCriterion("allSalary >=", value, "allsalary");
            return (Criteria) this;
        }

        public Criteria andAllsalaryLessThan(Integer value) {
            addCriterion("allSalary <", value, "allsalary");
            return (Criteria) this;
        }

        public Criteria andAllsalaryLessThanOrEqualTo(Integer value) {
            addCriterion("allSalary <=", value, "allsalary");
            return (Criteria) this;
        }

        public Criteria andAllsalaryIn(List<Integer> values) {
            addCriterion("allSalary in", values, "allsalary");
            return (Criteria) this;
        }

        public Criteria andAllsalaryNotIn(List<Integer> values) {
            addCriterion("allSalary not in", values, "allsalary");
            return (Criteria) this;
        }

        public Criteria andAllsalaryBetween(Integer value1, Integer value2) {
            addCriterion("allSalary between", value1, value2, "allsalary");
            return (Criteria) this;
        }

        public Criteria andAllsalaryNotBetween(Integer value1, Integer value2) {
            addCriterion("allSalary not between", value1, value2, "allsalary");
            return (Criteria) this;
        }

        public Criteria andPensionbaseIsNull() {
            addCriterion("pensionBase is null");
            return (Criteria) this;
        }

        public Criteria andPensionbaseIsNotNull() {
            addCriterion("pensionBase is not null");
            return (Criteria) this;
        }

        public Criteria andPensionbaseEqualTo(Integer value) {
            addCriterion("pensionBase =", value, "pensionbase");
            return (Criteria) this;
        }

        public Criteria andPensionbaseNotEqualTo(Integer value) {
            addCriterion("pensionBase <>", value, "pensionbase");
            return (Criteria) this;
        }

        public Criteria andPensionbaseGreaterThan(Integer value) {
            addCriterion("pensionBase >", value, "pensionbase");
            return (Criteria) this;
        }

        public Criteria andPensionbaseGreaterThanOrEqualTo(Integer value) {
            addCriterion("pensionBase >=", value, "pensionbase");
            return (Criteria) this;
        }

        public Criteria andPensionbaseLessThan(Integer value) {
            addCriterion("pensionBase <", value, "pensionbase");
            return (Criteria) this;
        }

        public Criteria andPensionbaseLessThanOrEqualTo(Integer value) {
            addCriterion("pensionBase <=", value, "pensionbase");
            return (Criteria) this;
        }

        public Criteria andPensionbaseIn(List<Integer> values) {
            addCriterion("pensionBase in", values, "pensionbase");
            return (Criteria) this;
        }

        public Criteria andPensionbaseNotIn(List<Integer> values) {
            addCriterion("pensionBase not in", values, "pensionbase");
            return (Criteria) this;
        }

        public Criteria andPensionbaseBetween(Integer value1, Integer value2) {
            addCriterion("pensionBase between", value1, value2, "pensionbase");
            return (Criteria) this;
        }

        public Criteria andPensionbaseNotBetween(Integer value1, Integer value2) {
            addCriterion("pensionBase not between", value1, value2, "pensionbase");
            return (Criteria) this;
        }

        public Criteria andPensionperIsNull() {
            addCriterion("pensionPer is null");
            return (Criteria) this;
        }

        public Criteria andPensionperIsNotNull() {
            addCriterion("pensionPer is not null");
            return (Criteria) this;
        }

        public Criteria andPensionperEqualTo(Float value) {
            addCriterion("pensionPer =", value, "pensionper");
            return (Criteria) this;
        }

        public Criteria andPensionperNotEqualTo(Float value) {
            addCriterion("pensionPer <>", value, "pensionper");
            return (Criteria) this;
        }

        public Criteria andPensionperGreaterThan(Float value) {
            addCriterion("pensionPer >", value, "pensionper");
            return (Criteria) this;
        }

        public Criteria andPensionperGreaterThanOrEqualTo(Float value) {
            addCriterion("pensionPer >=", value, "pensionper");
            return (Criteria) this;
        }

        public Criteria andPensionperLessThan(Float value) {
            addCriterion("pensionPer <", value, "pensionper");
            return (Criteria) this;
        }

        public Criteria andPensionperLessThanOrEqualTo(Float value) {
            addCriterion("pensionPer <=", value, "pensionper");
            return (Criteria) this;
        }

        public Criteria andPensionperIn(List<Float> values) {
            addCriterion("pensionPer in", values, "pensionper");
            return (Criteria) this;
        }

        public Criteria andPensionperNotIn(List<Float> values) {
            addCriterion("pensionPer not in", values, "pensionper");
            return (Criteria) this;
        }

        public Criteria andPensionperBetween(Float value1, Float value2) {
            addCriterion("pensionPer between", value1, value2, "pensionper");
            return (Criteria) this;
        }

        public Criteria andPensionperNotBetween(Float value1, Float value2) {
            addCriterion("pensionPer not between", value1, value2, "pensionper");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNull() {
            addCriterion("createDate is null");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNotNull() {
            addCriterion("createDate is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedateEqualTo(Date value) {
            addCriterion("createDate =", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotEqualTo(Date value) {
            addCriterion("createDate <>", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThan(Date value) {
            addCriterion("createDate >", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("createDate >=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThan(Date value) {
            addCriterion("createDate <", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThanOrEqualTo(Date value) {
            addCriterion("createDate <=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateIn(List<Date> values) {
            addCriterion("createDate in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotIn(List<Date> values) {
            addCriterion("createDate not in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateBetween(Date value1, Date value2) {
            addCriterion("createDate between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotBetween(Date value1, Date value2) {
            addCriterion("createDate not between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andMedicalbaseIsNull() {
            addCriterion("medicalBase is null");
            return (Criteria) this;
        }

        public Criteria andMedicalbaseIsNotNull() {
            addCriterion("medicalBase is not null");
            return (Criteria) this;
        }

        public Criteria andMedicalbaseEqualTo(Integer value) {
            addCriterion("medicalBase =", value, "medicalbase");
            return (Criteria) this;
        }

        public Criteria andMedicalbaseNotEqualTo(Integer value) {
            addCriterion("medicalBase <>", value, "medicalbase");
            return (Criteria) this;
        }

        public Criteria andMedicalbaseGreaterThan(Integer value) {
            addCriterion("medicalBase >", value, "medicalbase");
            return (Criteria) this;
        }

        public Criteria andMedicalbaseGreaterThanOrEqualTo(Integer value) {
            addCriterion("medicalBase >=", value, "medicalbase");
            return (Criteria) this;
        }

        public Criteria andMedicalbaseLessThan(Integer value) {
            addCriterion("medicalBase <", value, "medicalbase");
            return (Criteria) this;
        }

        public Criteria andMedicalbaseLessThanOrEqualTo(Integer value) {
            addCriterion("medicalBase <=", value, "medicalbase");
            return (Criteria) this;
        }

        public Criteria andMedicalbaseIn(List<Integer> values) {
            addCriterion("medicalBase in", values, "medicalbase");
            return (Criteria) this;
        }

        public Criteria andMedicalbaseNotIn(List<Integer> values) {
            addCriterion("medicalBase not in", values, "medicalbase");
            return (Criteria) this;
        }

        public Criteria andMedicalbaseBetween(Integer value1, Integer value2) {
            addCriterion("medicalBase between", value1, value2, "medicalbase");
            return (Criteria) this;
        }

        public Criteria andMedicalbaseNotBetween(Integer value1, Integer value2) {
            addCriterion("medicalBase not between", value1, value2, "medicalbase");
            return (Criteria) this;
        }

        public Criteria andMedicalperIsNull() {
            addCriterion("medicalPer is null");
            return (Criteria) this;
        }

        public Criteria andMedicalperIsNotNull() {
            addCriterion("medicalPer is not null");
            return (Criteria) this;
        }

        public Criteria andMedicalperEqualTo(Float value) {
            addCriterion("medicalPer =", value, "medicalper");
            return (Criteria) this;
        }

        public Criteria andMedicalperNotEqualTo(Float value) {
            addCriterion("medicalPer <>", value, "medicalper");
            return (Criteria) this;
        }

        public Criteria andMedicalperGreaterThan(Float value) {
            addCriterion("medicalPer >", value, "medicalper");
            return (Criteria) this;
        }

        public Criteria andMedicalperGreaterThanOrEqualTo(Float value) {
            addCriterion("medicalPer >=", value, "medicalper");
            return (Criteria) this;
        }

        public Criteria andMedicalperLessThan(Float value) {
            addCriterion("medicalPer <", value, "medicalper");
            return (Criteria) this;
        }

        public Criteria andMedicalperLessThanOrEqualTo(Float value) {
            addCriterion("medicalPer <=", value, "medicalper");
            return (Criteria) this;
        }

        public Criteria andMedicalperIn(List<Float> values) {
            addCriterion("medicalPer in", values, "medicalper");
            return (Criteria) this;
        }

        public Criteria andMedicalperNotIn(List<Float> values) {
            addCriterion("medicalPer not in", values, "medicalper");
            return (Criteria) this;
        }

        public Criteria andMedicalperBetween(Float value1, Float value2) {
            addCriterion("medicalPer between", value1, value2, "medicalper");
            return (Criteria) this;
        }

        public Criteria andMedicalperNotBetween(Float value1, Float value2) {
            addCriterion("medicalPer not between", value1, value2, "medicalper");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundbaseIsNull() {
            addCriterion("accumulationFundBase is null");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundbaseIsNotNull() {
            addCriterion("accumulationFundBase is not null");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundbaseEqualTo(Integer value) {
            addCriterion("accumulationFundBase =", value, "accumulationfundbase");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundbaseNotEqualTo(Integer value) {
            addCriterion("accumulationFundBase <>", value, "accumulationfundbase");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundbaseGreaterThan(Integer value) {
            addCriterion("accumulationFundBase >", value, "accumulationfundbase");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundbaseGreaterThanOrEqualTo(Integer value) {
            addCriterion("accumulationFundBase >=", value, "accumulationfundbase");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundbaseLessThan(Integer value) {
            addCriterion("accumulationFundBase <", value, "accumulationfundbase");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundbaseLessThanOrEqualTo(Integer value) {
            addCriterion("accumulationFundBase <=", value, "accumulationfundbase");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundbaseIn(List<Integer> values) {
            addCriterion("accumulationFundBase in", values, "accumulationfundbase");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundbaseNotIn(List<Integer> values) {
            addCriterion("accumulationFundBase not in", values, "accumulationfundbase");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundbaseBetween(Integer value1, Integer value2) {
            addCriterion("accumulationFundBase between", value1, value2, "accumulationfundbase");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundbaseNotBetween(Integer value1, Integer value2) {
            addCriterion("accumulationFundBase not between", value1, value2, "accumulationfundbase");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundperIsNull() {
            addCriterion("accumulationFundPer is null");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundperIsNotNull() {
            addCriterion("accumulationFundPer is not null");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundperEqualTo(Float value) {
            addCriterion("accumulationFundPer =", value, "accumulationfundper");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundperNotEqualTo(Float value) {
            addCriterion("accumulationFundPer <>", value, "accumulationfundper");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundperGreaterThan(Float value) {
            addCriterion("accumulationFundPer >", value, "accumulationfundper");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundperGreaterThanOrEqualTo(Float value) {
            addCriterion("accumulationFundPer >=", value, "accumulationfundper");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundperLessThan(Float value) {
            addCriterion("accumulationFundPer <", value, "accumulationfundper");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundperLessThanOrEqualTo(Float value) {
            addCriterion("accumulationFundPer <=", value, "accumulationfundper");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundperIn(List<Float> values) {
            addCriterion("accumulationFundPer in", values, "accumulationfundper");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundperNotIn(List<Float> values) {
            addCriterion("accumulationFundPer not in", values, "accumulationfundper");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundperBetween(Float value1, Float value2) {
            addCriterion("accumulationFundPer between", value1, value2, "accumulationfundper");
            return (Criteria) this;
        }

        public Criteria andAccumulationfundperNotBetween(Float value1, Float value2) {
            addCriterion("accumulationFundPer not between", value1, value2, "accumulationfundper");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
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