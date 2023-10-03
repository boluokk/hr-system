package org.boluo.hr.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class EmployeeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EmployeeExample() {
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

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(String value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(String value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(String value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(String value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(String value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(String value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLike(String value) {
            addCriterion("gender like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotLike(String value) {
            addCriterion("gender not like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<String> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<String> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(String value1, String value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(String value1, String value2) {
            addCriterion("gender not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterionForJDBCDate("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterionForJDBCDate("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterionForJDBCDate("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterionForJDBCDate("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterionForJDBCDate("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNull() {
            addCriterion("idCard is null");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNotNull() {
            addCriterion("idCard is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardEqualTo(String value) {
            addCriterion("idCard =", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotEqualTo(String value) {
            addCriterion("idCard <>", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThan(String value) {
            addCriterion("idCard >", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThanOrEqualTo(String value) {
            addCriterion("idCard >=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThan(String value) {
            addCriterion("idCard <", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThanOrEqualTo(String value) {
            addCriterion("idCard <=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLike(String value) {
            addCriterion("idCard like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotLike(String value) {
            addCriterion("idCard not like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardIn(List<String> values) {
            addCriterion("idCard in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotIn(List<String> values) {
            addCriterion("idCard not in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardBetween(String value1, String value2) {
            addCriterion("idCard between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotBetween(String value1, String value2) {
            addCriterion("idCard not between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andWedlockIsNull() {
            addCriterion("wedlock is null");
            return (Criteria) this;
        }

        public Criteria andWedlockIsNotNull() {
            addCriterion("wedlock is not null");
            return (Criteria) this;
        }

        public Criteria andWedlockEqualTo(String value) {
            addCriterion("wedlock =", value, "wedlock");
            return (Criteria) this;
        }

        public Criteria andWedlockNotEqualTo(String value) {
            addCriterion("wedlock <>", value, "wedlock");
            return (Criteria) this;
        }

        public Criteria andWedlockGreaterThan(String value) {
            addCriterion("wedlock >", value, "wedlock");
            return (Criteria) this;
        }

        public Criteria andWedlockGreaterThanOrEqualTo(String value) {
            addCriterion("wedlock >=", value, "wedlock");
            return (Criteria) this;
        }

        public Criteria andWedlockLessThan(String value) {
            addCriterion("wedlock <", value, "wedlock");
            return (Criteria) this;
        }

        public Criteria andWedlockLessThanOrEqualTo(String value) {
            addCriterion("wedlock <=", value, "wedlock");
            return (Criteria) this;
        }

        public Criteria andWedlockLike(String value) {
            addCriterion("wedlock like", value, "wedlock");
            return (Criteria) this;
        }

        public Criteria andWedlockNotLike(String value) {
            addCriterion("wedlock not like", value, "wedlock");
            return (Criteria) this;
        }

        public Criteria andWedlockIn(List<String> values) {
            addCriterion("wedlock in", values, "wedlock");
            return (Criteria) this;
        }

        public Criteria andWedlockNotIn(List<String> values) {
            addCriterion("wedlock not in", values, "wedlock");
            return (Criteria) this;
        }

        public Criteria andWedlockBetween(String value1, String value2) {
            addCriterion("wedlock between", value1, value2, "wedlock");
            return (Criteria) this;
        }

        public Criteria andWedlockNotBetween(String value1, String value2) {
            addCriterion("wedlock not between", value1, value2, "wedlock");
            return (Criteria) this;
        }

        public Criteria andNationidIsNull() {
            addCriterion("nationId is null");
            return (Criteria) this;
        }

        public Criteria andNationidIsNotNull() {
            addCriterion("nationId is not null");
            return (Criteria) this;
        }

        public Criteria andNationidEqualTo(Integer value) {
            addCriterion("nationId =", value, "nationid");
            return (Criteria) this;
        }

        public Criteria andNationidNotEqualTo(Integer value) {
            addCriterion("nationId <>", value, "nationid");
            return (Criteria) this;
        }

        public Criteria andNationidGreaterThan(Integer value) {
            addCriterion("nationId >", value, "nationid");
            return (Criteria) this;
        }

        public Criteria andNationidGreaterThanOrEqualTo(Integer value) {
            addCriterion("nationId >=", value, "nationid");
            return (Criteria) this;
        }

        public Criteria andNationidLessThan(Integer value) {
            addCriterion("nationId <", value, "nationid");
            return (Criteria) this;
        }

        public Criteria andNationidLessThanOrEqualTo(Integer value) {
            addCriterion("nationId <=", value, "nationid");
            return (Criteria) this;
        }

        public Criteria andNationidIn(List<Integer> values) {
            addCriterion("nationId in", values, "nationid");
            return (Criteria) this;
        }

        public Criteria andNationidNotIn(List<Integer> values) {
            addCriterion("nationId not in", values, "nationid");
            return (Criteria) this;
        }

        public Criteria andNationidBetween(Integer value1, Integer value2) {
            addCriterion("nationId between", value1, value2, "nationid");
            return (Criteria) this;
        }

        public Criteria andNationidNotBetween(Integer value1, Integer value2) {
            addCriterion("nationId not between", value1, value2, "nationid");
            return (Criteria) this;
        }

        public Criteria andNativeplaceIsNull() {
            addCriterion("nativePlace is null");
            return (Criteria) this;
        }

        public Criteria andNativeplaceIsNotNull() {
            addCriterion("nativePlace is not null");
            return (Criteria) this;
        }

        public Criteria andNativeplaceEqualTo(String value) {
            addCriterion("nativePlace =", value, "nativeplace");
            return (Criteria) this;
        }

        public Criteria andNativeplaceNotEqualTo(String value) {
            addCriterion("nativePlace <>", value, "nativeplace");
            return (Criteria) this;
        }

        public Criteria andNativeplaceGreaterThan(String value) {
            addCriterion("nativePlace >", value, "nativeplace");
            return (Criteria) this;
        }

        public Criteria andNativeplaceGreaterThanOrEqualTo(String value) {
            addCriterion("nativePlace >=", value, "nativeplace");
            return (Criteria) this;
        }

        public Criteria andNativeplaceLessThan(String value) {
            addCriterion("nativePlace <", value, "nativeplace");
            return (Criteria) this;
        }

        public Criteria andNativeplaceLessThanOrEqualTo(String value) {
            addCriterion("nativePlace <=", value, "nativeplace");
            return (Criteria) this;
        }

        public Criteria andNativeplaceLike(String value) {
            addCriterion("nativePlace like", value, "nativeplace");
            return (Criteria) this;
        }

        public Criteria andNativeplaceNotLike(String value) {
            addCriterion("nativePlace not like", value, "nativeplace");
            return (Criteria) this;
        }

        public Criteria andNativeplaceIn(List<String> values) {
            addCriterion("nativePlace in", values, "nativeplace");
            return (Criteria) this;
        }

        public Criteria andNativeplaceNotIn(List<String> values) {
            addCriterion("nativePlace not in", values, "nativeplace");
            return (Criteria) this;
        }

        public Criteria andNativeplaceBetween(String value1, String value2) {
            addCriterion("nativePlace between", value1, value2, "nativeplace");
            return (Criteria) this;
        }

        public Criteria andNativeplaceNotBetween(String value1, String value2) {
            addCriterion("nativePlace not between", value1, value2, "nativeplace");
            return (Criteria) this;
        }

        public Criteria andPoliticidIsNull() {
            addCriterion("politicId is null");
            return (Criteria) this;
        }

        public Criteria andPoliticidIsNotNull() {
            addCriterion("politicId is not null");
            return (Criteria) this;
        }

        public Criteria andPoliticidEqualTo(Integer value) {
            addCriterion("politicId =", value, "politicid");
            return (Criteria) this;
        }

        public Criteria andPoliticidNotEqualTo(Integer value) {
            addCriterion("politicId <>", value, "politicid");
            return (Criteria) this;
        }

        public Criteria andPoliticidGreaterThan(Integer value) {
            addCriterion("politicId >", value, "politicid");
            return (Criteria) this;
        }

        public Criteria andPoliticidGreaterThanOrEqualTo(Integer value) {
            addCriterion("politicId >=", value, "politicid");
            return (Criteria) this;
        }

        public Criteria andPoliticidLessThan(Integer value) {
            addCriterion("politicId <", value, "politicid");
            return (Criteria) this;
        }

        public Criteria andPoliticidLessThanOrEqualTo(Integer value) {
            addCriterion("politicId <=", value, "politicid");
            return (Criteria) this;
        }

        public Criteria andPoliticidIn(List<Integer> values) {
            addCriterion("politicId in", values, "politicid");
            return (Criteria) this;
        }

        public Criteria andPoliticidNotIn(List<Integer> values) {
            addCriterion("politicId not in", values, "politicid");
            return (Criteria) this;
        }

        public Criteria andPoliticidBetween(Integer value1, Integer value2) {
            addCriterion("politicId between", value1, value2, "politicid");
            return (Criteria) this;
        }

        public Criteria andPoliticidNotBetween(Integer value1, Integer value2) {
            addCriterion("politicId not between", value1, value2, "politicid");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andDepartmentidIsNull() {
            addCriterion("departmentId is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentidIsNotNull() {
            addCriterion("departmentId is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentidEqualTo(Integer value) {
            addCriterion("departmentId =", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidNotEqualTo(Integer value) {
            addCriterion("departmentId <>", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidGreaterThan(Integer value) {
            addCriterion("departmentId >", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidGreaterThanOrEqualTo(Integer value) {
            addCriterion("departmentId >=", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidLessThan(Integer value) {
            addCriterion("departmentId <", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidLessThanOrEqualTo(Integer value) {
            addCriterion("departmentId <=", value, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidIn(List<Integer> values) {
            addCriterion("departmentId in", values, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidNotIn(List<Integer> values) {
            addCriterion("departmentId not in", values, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidBetween(Integer value1, Integer value2) {
            addCriterion("departmentId between", value1, value2, "departmentid");
            return (Criteria) this;
        }

        public Criteria andDepartmentidNotBetween(Integer value1, Integer value2) {
            addCriterion("departmentId not between", value1, value2, "departmentid");
            return (Criteria) this;
        }

        public Criteria andJoblevelidIsNull() {
            addCriterion("jobLevelId is null");
            return (Criteria) this;
        }

        public Criteria andJoblevelidIsNotNull() {
            addCriterion("jobLevelId is not null");
            return (Criteria) this;
        }

        public Criteria andJoblevelidEqualTo(Integer value) {
            addCriterion("jobLevelId =", value, "joblevelid");
            return (Criteria) this;
        }

        public Criteria andJoblevelidNotEqualTo(Integer value) {
            addCriterion("jobLevelId <>", value, "joblevelid");
            return (Criteria) this;
        }

        public Criteria andJoblevelidGreaterThan(Integer value) {
            addCriterion("jobLevelId >", value, "joblevelid");
            return (Criteria) this;
        }

        public Criteria andJoblevelidGreaterThanOrEqualTo(Integer value) {
            addCriterion("jobLevelId >=", value, "joblevelid");
            return (Criteria) this;
        }

        public Criteria andJoblevelidLessThan(Integer value) {
            addCriterion("jobLevelId <", value, "joblevelid");
            return (Criteria) this;
        }

        public Criteria andJoblevelidLessThanOrEqualTo(Integer value) {
            addCriterion("jobLevelId <=", value, "joblevelid");
            return (Criteria) this;
        }

        public Criteria andJoblevelidIn(List<Integer> values) {
            addCriterion("jobLevelId in", values, "joblevelid");
            return (Criteria) this;
        }

        public Criteria andJoblevelidNotIn(List<Integer> values) {
            addCriterion("jobLevelId not in", values, "joblevelid");
            return (Criteria) this;
        }

        public Criteria andJoblevelidBetween(Integer value1, Integer value2) {
            addCriterion("jobLevelId between", value1, value2, "joblevelid");
            return (Criteria) this;
        }

        public Criteria andJoblevelidNotBetween(Integer value1, Integer value2) {
            addCriterion("jobLevelId not between", value1, value2, "joblevelid");
            return (Criteria) this;
        }

        public Criteria andPosidIsNull() {
            addCriterion("posId is null");
            return (Criteria) this;
        }

        public Criteria andPosidIsNotNull() {
            addCriterion("posId is not null");
            return (Criteria) this;
        }

        public Criteria andPosidEqualTo(Integer value) {
            addCriterion("posId =", value, "posid");
            return (Criteria) this;
        }

        public Criteria andPosidNotEqualTo(Integer value) {
            addCriterion("posId <>", value, "posid");
            return (Criteria) this;
        }

        public Criteria andPosidGreaterThan(Integer value) {
            addCriterion("posId >", value, "posid");
            return (Criteria) this;
        }

        public Criteria andPosidGreaterThanOrEqualTo(Integer value) {
            addCriterion("posId >=", value, "posid");
            return (Criteria) this;
        }

        public Criteria andPosidLessThan(Integer value) {
            addCriterion("posId <", value, "posid");
            return (Criteria) this;
        }

        public Criteria andPosidLessThanOrEqualTo(Integer value) {
            addCriterion("posId <=", value, "posid");
            return (Criteria) this;
        }

        public Criteria andPosidIn(List<Integer> values) {
            addCriterion("posId in", values, "posid");
            return (Criteria) this;
        }

        public Criteria andPosidNotIn(List<Integer> values) {
            addCriterion("posId not in", values, "posid");
            return (Criteria) this;
        }

        public Criteria andPosidBetween(Integer value1, Integer value2) {
            addCriterion("posId between", value1, value2, "posid");
            return (Criteria) this;
        }

        public Criteria andPosidNotBetween(Integer value1, Integer value2) {
            addCriterion("posId not between", value1, value2, "posid");
            return (Criteria) this;
        }

        public Criteria andEngageformIsNull() {
            addCriterion("engageForm is null");
            return (Criteria) this;
        }

        public Criteria andEngageformIsNotNull() {
            addCriterion("engageForm is not null");
            return (Criteria) this;
        }

        public Criteria andEngageformEqualTo(String value) {
            addCriterion("engageForm =", value, "engageform");
            return (Criteria) this;
        }

        public Criteria andEngageformNotEqualTo(String value) {
            addCriterion("engageForm <>", value, "engageform");
            return (Criteria) this;
        }

        public Criteria andEngageformGreaterThan(String value) {
            addCriterion("engageForm >", value, "engageform");
            return (Criteria) this;
        }

        public Criteria andEngageformGreaterThanOrEqualTo(String value) {
            addCriterion("engageForm >=", value, "engageform");
            return (Criteria) this;
        }

        public Criteria andEngageformLessThan(String value) {
            addCriterion("engageForm <", value, "engageform");
            return (Criteria) this;
        }

        public Criteria andEngageformLessThanOrEqualTo(String value) {
            addCriterion("engageForm <=", value, "engageform");
            return (Criteria) this;
        }

        public Criteria andEngageformLike(String value) {
            addCriterion("engageForm like", value, "engageform");
            return (Criteria) this;
        }

        public Criteria andEngageformNotLike(String value) {
            addCriterion("engageForm not like", value, "engageform");
            return (Criteria) this;
        }

        public Criteria andEngageformIn(List<String> values) {
            addCriterion("engageForm in", values, "engageform");
            return (Criteria) this;
        }

        public Criteria andEngageformNotIn(List<String> values) {
            addCriterion("engageForm not in", values, "engageform");
            return (Criteria) this;
        }

        public Criteria andEngageformBetween(String value1, String value2) {
            addCriterion("engageForm between", value1, value2, "engageform");
            return (Criteria) this;
        }

        public Criteria andEngageformNotBetween(String value1, String value2) {
            addCriterion("engageForm not between", value1, value2, "engageform");
            return (Criteria) this;
        }

        public Criteria andTiptopdegreeIsNull() {
            addCriterion("tiptopDegree is null");
            return (Criteria) this;
        }

        public Criteria andTiptopdegreeIsNotNull() {
            addCriterion("tiptopDegree is not null");
            return (Criteria) this;
        }

        public Criteria andTiptopdegreeEqualTo(String value) {
            addCriterion("tiptopDegree =", value, "tiptopdegree");
            return (Criteria) this;
        }

        public Criteria andTiptopdegreeNotEqualTo(String value) {
            addCriterion("tiptopDegree <>", value, "tiptopdegree");
            return (Criteria) this;
        }

        public Criteria andTiptopdegreeGreaterThan(String value) {
            addCriterion("tiptopDegree >", value, "tiptopdegree");
            return (Criteria) this;
        }

        public Criteria andTiptopdegreeGreaterThanOrEqualTo(String value) {
            addCriterion("tiptopDegree >=", value, "tiptopdegree");
            return (Criteria) this;
        }

        public Criteria andTiptopdegreeLessThan(String value) {
            addCriterion("tiptopDegree <", value, "tiptopdegree");
            return (Criteria) this;
        }

        public Criteria andTiptopdegreeLessThanOrEqualTo(String value) {
            addCriterion("tiptopDegree <=", value, "tiptopdegree");
            return (Criteria) this;
        }

        public Criteria andTiptopdegreeLike(String value) {
            addCriterion("tiptopDegree like", value, "tiptopdegree");
            return (Criteria) this;
        }

        public Criteria andTiptopdegreeNotLike(String value) {
            addCriterion("tiptopDegree not like", value, "tiptopdegree");
            return (Criteria) this;
        }

        public Criteria andTiptopdegreeIn(List<String> values) {
            addCriterion("tiptopDegree in", values, "tiptopdegree");
            return (Criteria) this;
        }

        public Criteria andTiptopdegreeNotIn(List<String> values) {
            addCriterion("tiptopDegree not in", values, "tiptopdegree");
            return (Criteria) this;
        }

        public Criteria andTiptopdegreeBetween(String value1, String value2) {
            addCriterion("tiptopDegree between", value1, value2, "tiptopdegree");
            return (Criteria) this;
        }

        public Criteria andTiptopdegreeNotBetween(String value1, String value2) {
            addCriterion("tiptopDegree not between", value1, value2, "tiptopdegree");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIsNull() {
            addCriterion("specialty is null");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIsNotNull() {
            addCriterion("specialty is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialtyEqualTo(String value) {
            addCriterion("specialty =", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotEqualTo(String value) {
            addCriterion("specialty <>", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyGreaterThan(String value) {
            addCriterion("specialty >", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyGreaterThanOrEqualTo(String value) {
            addCriterion("specialty >=", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyLessThan(String value) {
            addCriterion("specialty <", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyLessThanOrEqualTo(String value) {
            addCriterion("specialty <=", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyLike(String value) {
            addCriterion("specialty like", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotLike(String value) {
            addCriterion("specialty not like", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIn(List<String> values) {
            addCriterion("specialty in", values, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotIn(List<String> values) {
            addCriterion("specialty not in", values, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyBetween(String value1, String value2) {
            addCriterion("specialty between", value1, value2, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotBetween(String value1, String value2) {
            addCriterion("specialty not between", value1, value2, "specialty");
            return (Criteria) this;
        }

        public Criteria andSchoolIsNull() {
            addCriterion("school is null");
            return (Criteria) this;
        }

        public Criteria andSchoolIsNotNull() {
            addCriterion("school is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolEqualTo(String value) {
            addCriterion("school =", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolNotEqualTo(String value) {
            addCriterion("school <>", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolGreaterThan(String value) {
            addCriterion("school >", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolGreaterThanOrEqualTo(String value) {
            addCriterion("school >=", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolLessThan(String value) {
            addCriterion("school <", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolLessThanOrEqualTo(String value) {
            addCriterion("school <=", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolLike(String value) {
            addCriterion("school like", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolNotLike(String value) {
            addCriterion("school not like", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolIn(List<String> values) {
            addCriterion("school in", values, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolNotIn(List<String> values) {
            addCriterion("school not in", values, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolBetween(String value1, String value2) {
            addCriterion("school between", value1, value2, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolNotBetween(String value1, String value2) {
            addCriterion("school not between", value1, value2, "school");
            return (Criteria) this;
        }

        public Criteria andBegindateIsNull() {
            addCriterion("beginDate is null");
            return (Criteria) this;
        }

        public Criteria andBegindateIsNotNull() {
            addCriterion("beginDate is not null");
            return (Criteria) this;
        }

        public Criteria andBegindateEqualTo(Date value) {
            addCriterionForJDBCDate("beginDate =", value, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateNotEqualTo(Date value) {
            addCriterionForJDBCDate("beginDate <>", value, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateGreaterThan(Date value) {
            addCriterionForJDBCDate("beginDate >", value, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("beginDate >=", value, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateLessThan(Date value) {
            addCriterionForJDBCDate("beginDate <", value, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("beginDate <=", value, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateIn(List<Date> values) {
            addCriterionForJDBCDate("beginDate in", values, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateNotIn(List<Date> values) {
            addCriterionForJDBCDate("beginDate not in", values, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("beginDate between", value1, value2, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("beginDate not between", value1, value2, "begindate");
            return (Criteria) this;
        }

        public Criteria andWorkstateIsNull() {
            addCriterion("workState is null");
            return (Criteria) this;
        }

        public Criteria andWorkstateIsNotNull() {
            addCriterion("workState is not null");
            return (Criteria) this;
        }

        public Criteria andWorkstateEqualTo(String value) {
            addCriterion("workState =", value, "workstate");
            return (Criteria) this;
        }

        public Criteria andWorkstateNotEqualTo(String value) {
            addCriterion("workState <>", value, "workstate");
            return (Criteria) this;
        }

        public Criteria andWorkstateGreaterThan(String value) {
            addCriterion("workState >", value, "workstate");
            return (Criteria) this;
        }

        public Criteria andWorkstateGreaterThanOrEqualTo(String value) {
            addCriterion("workState >=", value, "workstate");
            return (Criteria) this;
        }

        public Criteria andWorkstateLessThan(String value) {
            addCriterion("workState <", value, "workstate");
            return (Criteria) this;
        }

        public Criteria andWorkstateLessThanOrEqualTo(String value) {
            addCriterion("workState <=", value, "workstate");
            return (Criteria) this;
        }

        public Criteria andWorkstateLike(String value) {
            addCriterion("workState like", value, "workstate");
            return (Criteria) this;
        }

        public Criteria andWorkstateNotLike(String value) {
            addCriterion("workState not like", value, "workstate");
            return (Criteria) this;
        }

        public Criteria andWorkstateIn(List<String> values) {
            addCriterion("workState in", values, "workstate");
            return (Criteria) this;
        }

        public Criteria andWorkstateNotIn(List<String> values) {
            addCriterion("workState not in", values, "workstate");
            return (Criteria) this;
        }

        public Criteria andWorkstateBetween(String value1, String value2) {
            addCriterion("workState between", value1, value2, "workstate");
            return (Criteria) this;
        }

        public Criteria andWorkstateNotBetween(String value1, String value2) {
            addCriterion("workState not between", value1, value2, "workstate");
            return (Criteria) this;
        }

        public Criteria andWorkidIsNull() {
            addCriterion("workID is null");
            return (Criteria) this;
        }

        public Criteria andWorkidIsNotNull() {
            addCriterion("workID is not null");
            return (Criteria) this;
        }

        public Criteria andWorkidEqualTo(String value) {
            addCriterion("workID =", value, "workid");
            return (Criteria) this;
        }

        public Criteria andWorkidNotEqualTo(String value) {
            addCriterion("workID <>", value, "workid");
            return (Criteria) this;
        }

        public Criteria andWorkidGreaterThan(String value) {
            addCriterion("workID >", value, "workid");
            return (Criteria) this;
        }

        public Criteria andWorkidGreaterThanOrEqualTo(String value) {
            addCriterion("workID >=", value, "workid");
            return (Criteria) this;
        }

        public Criteria andWorkidLessThan(String value) {
            addCriterion("workID <", value, "workid");
            return (Criteria) this;
        }

        public Criteria andWorkidLessThanOrEqualTo(String value) {
            addCriterion("workID <=", value, "workid");
            return (Criteria) this;
        }

        public Criteria andWorkidLike(String value) {
            addCriterion("workID like", value, "workid");
            return (Criteria) this;
        }

        public Criteria andWorkidNotLike(String value) {
            addCriterion("workID not like", value, "workid");
            return (Criteria) this;
        }

        public Criteria andWorkidIn(List<String> values) {
            addCriterion("workID in", values, "workid");
            return (Criteria) this;
        }

        public Criteria andWorkidNotIn(List<String> values) {
            addCriterion("workID not in", values, "workid");
            return (Criteria) this;
        }

        public Criteria andWorkidBetween(String value1, String value2) {
            addCriterion("workID between", value1, value2, "workid");
            return (Criteria) this;
        }

        public Criteria andWorkidNotBetween(String value1, String value2) {
            addCriterion("workID not between", value1, value2, "workid");
            return (Criteria) this;
        }

        public Criteria andContracttermIsNull() {
            addCriterion("contractTerm is null");
            return (Criteria) this;
        }

        public Criteria andContracttermIsNotNull() {
            addCriterion("contractTerm is not null");
            return (Criteria) this;
        }

        public Criteria andContracttermEqualTo(Double value) {
            addCriterion("contractTerm =", value, "contractterm");
            return (Criteria) this;
        }

        public Criteria andContracttermNotEqualTo(Double value) {
            addCriterion("contractTerm <>", value, "contractterm");
            return (Criteria) this;
        }

        public Criteria andContracttermGreaterThan(Double value) {
            addCriterion("contractTerm >", value, "contractterm");
            return (Criteria) this;
        }

        public Criteria andContracttermGreaterThanOrEqualTo(Double value) {
            addCriterion("contractTerm >=", value, "contractterm");
            return (Criteria) this;
        }

        public Criteria andContracttermLessThan(Double value) {
            addCriterion("contractTerm <", value, "contractterm");
            return (Criteria) this;
        }

        public Criteria andContracttermLessThanOrEqualTo(Double value) {
            addCriterion("contractTerm <=", value, "contractterm");
            return (Criteria) this;
        }

        public Criteria andContracttermIn(List<Double> values) {
            addCriterion("contractTerm in", values, "contractterm");
            return (Criteria) this;
        }

        public Criteria andContracttermNotIn(List<Double> values) {
            addCriterion("contractTerm not in", values, "contractterm");
            return (Criteria) this;
        }

        public Criteria andContracttermBetween(Double value1, Double value2) {
            addCriterion("contractTerm between", value1, value2, "contractterm");
            return (Criteria) this;
        }

        public Criteria andContracttermNotBetween(Double value1, Double value2) {
            addCriterion("contractTerm not between", value1, value2, "contractterm");
            return (Criteria) this;
        }

        public Criteria andConversiontimeIsNull() {
            addCriterion("conversionTime is null");
            return (Criteria) this;
        }

        public Criteria andConversiontimeIsNotNull() {
            addCriterion("conversionTime is not null");
            return (Criteria) this;
        }

        public Criteria andConversiontimeEqualTo(Date value) {
            addCriterionForJDBCDate("conversionTime =", value, "conversiontime");
            return (Criteria) this;
        }

        public Criteria andConversiontimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("conversionTime <>", value, "conversiontime");
            return (Criteria) this;
        }

        public Criteria andConversiontimeGreaterThan(Date value) {
            addCriterionForJDBCDate("conversionTime >", value, "conversiontime");
            return (Criteria) this;
        }

        public Criteria andConversiontimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("conversionTime >=", value, "conversiontime");
            return (Criteria) this;
        }

        public Criteria andConversiontimeLessThan(Date value) {
            addCriterionForJDBCDate("conversionTime <", value, "conversiontime");
            return (Criteria) this;
        }

        public Criteria andConversiontimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("conversionTime <=", value, "conversiontime");
            return (Criteria) this;
        }

        public Criteria andConversiontimeIn(List<Date> values) {
            addCriterionForJDBCDate("conversionTime in", values, "conversiontime");
            return (Criteria) this;
        }

        public Criteria andConversiontimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("conversionTime not in", values, "conversiontime");
            return (Criteria) this;
        }

        public Criteria andConversiontimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("conversionTime between", value1, value2, "conversiontime");
            return (Criteria) this;
        }

        public Criteria andConversiontimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("conversionTime not between", value1, value2, "conversiontime");
            return (Criteria) this;
        }

        public Criteria andNotworkdateIsNull() {
            addCriterion("notWorkDate is null");
            return (Criteria) this;
        }

        public Criteria andNotworkdateIsNotNull() {
            addCriterion("notWorkDate is not null");
            return (Criteria) this;
        }

        public Criteria andNotworkdateEqualTo(Date value) {
            addCriterionForJDBCDate("notWorkDate =", value, "notworkdate");
            return (Criteria) this;
        }

        public Criteria andNotworkdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("notWorkDate <>", value, "notworkdate");
            return (Criteria) this;
        }

        public Criteria andNotworkdateGreaterThan(Date value) {
            addCriterionForJDBCDate("notWorkDate >", value, "notworkdate");
            return (Criteria) this;
        }

        public Criteria andNotworkdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("notWorkDate >=", value, "notworkdate");
            return (Criteria) this;
        }

        public Criteria andNotworkdateLessThan(Date value) {
            addCriterionForJDBCDate("notWorkDate <", value, "notworkdate");
            return (Criteria) this;
        }

        public Criteria andNotworkdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("notWorkDate <=", value, "notworkdate");
            return (Criteria) this;
        }

        public Criteria andNotworkdateIn(List<Date> values) {
            addCriterionForJDBCDate("notWorkDate in", values, "notworkdate");
            return (Criteria) this;
        }

        public Criteria andNotworkdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("notWorkDate not in", values, "notworkdate");
            return (Criteria) this;
        }

        public Criteria andNotworkdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("notWorkDate between", value1, value2, "notworkdate");
            return (Criteria) this;
        }

        public Criteria andNotworkdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("notWorkDate not between", value1, value2, "notworkdate");
            return (Criteria) this;
        }

        public Criteria andBegincontractIsNull() {
            addCriterion("beginContract is null");
            return (Criteria) this;
        }

        public Criteria andBegincontractIsNotNull() {
            addCriterion("beginContract is not null");
            return (Criteria) this;
        }

        public Criteria andBegincontractEqualTo(Date value) {
            addCriterionForJDBCDate("beginContract =", value, "begincontract");
            return (Criteria) this;
        }

        public Criteria andBegincontractNotEqualTo(Date value) {
            addCriterionForJDBCDate("beginContract <>", value, "begincontract");
            return (Criteria) this;
        }

        public Criteria andBegincontractGreaterThan(Date value) {
            addCriterionForJDBCDate("beginContract >", value, "begincontract");
            return (Criteria) this;
        }

        public Criteria andBegincontractGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("beginContract >=", value, "begincontract");
            return (Criteria) this;
        }

        public Criteria andBegincontractLessThan(Date value) {
            addCriterionForJDBCDate("beginContract <", value, "begincontract");
            return (Criteria) this;
        }

        public Criteria andBegincontractLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("beginContract <=", value, "begincontract");
            return (Criteria) this;
        }

        public Criteria andBegincontractIn(List<Date> values) {
            addCriterionForJDBCDate("beginContract in", values, "begincontract");
            return (Criteria) this;
        }

        public Criteria andBegincontractNotIn(List<Date> values) {
            addCriterionForJDBCDate("beginContract not in", values, "begincontract");
            return (Criteria) this;
        }

        public Criteria andBegincontractBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("beginContract between", value1, value2, "begincontract");
            return (Criteria) this;
        }

        public Criteria andBegincontractNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("beginContract not between", value1, value2, "begincontract");
            return (Criteria) this;
        }

        public Criteria andEndcontractIsNull() {
            addCriterion("endContract is null");
            return (Criteria) this;
        }

        public Criteria andEndcontractIsNotNull() {
            addCriterion("endContract is not null");
            return (Criteria) this;
        }

        public Criteria andEndcontractEqualTo(Date value) {
            addCriterionForJDBCDate("endContract =", value, "endcontract");
            return (Criteria) this;
        }

        public Criteria andEndcontractNotEqualTo(Date value) {
            addCriterionForJDBCDate("endContract <>", value, "endcontract");
            return (Criteria) this;
        }

        public Criteria andEndcontractGreaterThan(Date value) {
            addCriterionForJDBCDate("endContract >", value, "endcontract");
            return (Criteria) this;
        }

        public Criteria andEndcontractGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("endContract >=", value, "endcontract");
            return (Criteria) this;
        }

        public Criteria andEndcontractLessThan(Date value) {
            addCriterionForJDBCDate("endContract <", value, "endcontract");
            return (Criteria) this;
        }

        public Criteria andEndcontractLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("endContract <=", value, "endcontract");
            return (Criteria) this;
        }

        public Criteria andEndcontractIn(List<Date> values) {
            addCriterionForJDBCDate("endContract in", values, "endcontract");
            return (Criteria) this;
        }

        public Criteria andEndcontractNotIn(List<Date> values) {
            addCriterionForJDBCDate("endContract not in", values, "endcontract");
            return (Criteria) this;
        }

        public Criteria andEndcontractBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("endContract between", value1, value2, "endcontract");
            return (Criteria) this;
        }

        public Criteria andEndcontractNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("endContract not between", value1, value2, "endcontract");
            return (Criteria) this;
        }

        public Criteria andWorkageIsNull() {
            addCriterion("workAge is null");
            return (Criteria) this;
        }

        public Criteria andWorkageIsNotNull() {
            addCriterion("workAge is not null");
            return (Criteria) this;
        }

        public Criteria andWorkageEqualTo(Integer value) {
            addCriterion("workAge =", value, "workage");
            return (Criteria) this;
        }

        public Criteria andWorkageNotEqualTo(Integer value) {
            addCriterion("workAge <>", value, "workage");
            return (Criteria) this;
        }

        public Criteria andWorkageGreaterThan(Integer value) {
            addCriterion("workAge >", value, "workage");
            return (Criteria) this;
        }

        public Criteria andWorkageGreaterThanOrEqualTo(Integer value) {
            addCriterion("workAge >=", value, "workage");
            return (Criteria) this;
        }

        public Criteria andWorkageLessThan(Integer value) {
            addCriterion("workAge <", value, "workage");
            return (Criteria) this;
        }

        public Criteria andWorkageLessThanOrEqualTo(Integer value) {
            addCriterion("workAge <=", value, "workage");
            return (Criteria) this;
        }

        public Criteria andWorkageIn(List<Integer> values) {
            addCriterion("workAge in", values, "workage");
            return (Criteria) this;
        }

        public Criteria andWorkageNotIn(List<Integer> values) {
            addCriterion("workAge not in", values, "workage");
            return (Criteria) this;
        }

        public Criteria andWorkageBetween(Integer value1, Integer value2) {
            addCriterion("workAge between", value1, value2, "workage");
            return (Criteria) this;
        }

        public Criteria andWorkageNotBetween(Integer value1, Integer value2) {
            addCriterion("workAge not between", value1, value2, "workage");
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