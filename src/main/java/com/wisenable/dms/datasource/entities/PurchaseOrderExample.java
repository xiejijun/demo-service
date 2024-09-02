package com.wisenable.dms.datasource.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurchaseOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PurchaseOrderExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andSalesOrderNoIsNull() {
            addCriterion("sales_order_no is null");
            return (Criteria) this;
        }

        public Criteria andSalesOrderNoIsNotNull() {
            addCriterion("sales_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andSalesOrderNoEqualTo(String value) {
            addCriterion("sales_order_no =", value, "salesOrderNo");
            return (Criteria) this;
        }

        public Criteria andSalesOrderNoNotEqualTo(String value) {
            addCriterion("sales_order_no <>", value, "salesOrderNo");
            return (Criteria) this;
        }

        public Criteria andSalesOrderNoGreaterThan(String value) {
            addCriterion("sales_order_no >", value, "salesOrderNo");
            return (Criteria) this;
        }

        public Criteria andSalesOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("sales_order_no >=", value, "salesOrderNo");
            return (Criteria) this;
        }

        public Criteria andSalesOrderNoLessThan(String value) {
            addCriterion("sales_order_no <", value, "salesOrderNo");
            return (Criteria) this;
        }

        public Criteria andSalesOrderNoLessThanOrEqualTo(String value) {
            addCriterion("sales_order_no <=", value, "salesOrderNo");
            return (Criteria) this;
        }

        public Criteria andSalesOrderNoLike(String value) {
            addCriterion("sales_order_no like", value, "salesOrderNo");
            return (Criteria) this;
        }

        public Criteria andSalesOrderNoNotLike(String value) {
            addCriterion("sales_order_no not like", value, "salesOrderNo");
            return (Criteria) this;
        }

        public Criteria andSalesOrderNoIn(List<String> values) {
            addCriterion("sales_order_no in", values, "salesOrderNo");
            return (Criteria) this;
        }

        public Criteria andSalesOrderNoNotIn(List<String> values) {
            addCriterion("sales_order_no not in", values, "salesOrderNo");
            return (Criteria) this;
        }

        public Criteria andSalesOrderNoBetween(String value1, String value2) {
            addCriterion("sales_order_no between", value1, value2, "salesOrderNo");
            return (Criteria) this;
        }

        public Criteria andSalesOrderNoNotBetween(String value1, String value2) {
            addCriterion("sales_order_no not between", value1, value2, "salesOrderNo");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNull() {
            addCriterion("order_time is null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNotNull() {
            addCriterion("order_time is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeEqualTo(Date value) {
            addCriterion("order_time =", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotEqualTo(Date value) {
            addCriterion("order_time <>", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThan(Date value) {
            addCriterion("order_time >", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("order_time >=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThan(Date value) {
            addCriterion("order_time <", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThanOrEqualTo(Date value) {
            addCriterion("order_time <=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIn(List<Date> values) {
            addCriterion("order_time in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotIn(List<Date> values) {
            addCriterion("order_time not in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeBetween(Date value1, Date value2) {
            addCriterion("order_time between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotBetween(Date value1, Date value2) {
            addCriterion("order_time not between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andSalesNameIsNull() {
            addCriterion("sales_name is null");
            return (Criteria) this;
        }

        public Criteria andSalesNameIsNotNull() {
            addCriterion("sales_name is not null");
            return (Criteria) this;
        }

        public Criteria andSalesNameEqualTo(String value) {
            addCriterion("sales_name =", value, "salesName");
            return (Criteria) this;
        }

        public Criteria andSalesNameNotEqualTo(String value) {
            addCriterion("sales_name <>", value, "salesName");
            return (Criteria) this;
        }

        public Criteria andSalesNameGreaterThan(String value) {
            addCriterion("sales_name >", value, "salesName");
            return (Criteria) this;
        }

        public Criteria andSalesNameGreaterThanOrEqualTo(String value) {
            addCriterion("sales_name >=", value, "salesName");
            return (Criteria) this;
        }

        public Criteria andSalesNameLessThan(String value) {
            addCriterion("sales_name <", value, "salesName");
            return (Criteria) this;
        }

        public Criteria andSalesNameLessThanOrEqualTo(String value) {
            addCriterion("sales_name <=", value, "salesName");
            return (Criteria) this;
        }

        public Criteria andSalesNameLike(String value) {
            addCriterion("sales_name like", value, "salesName");
            return (Criteria) this;
        }

        public Criteria andSalesNameNotLike(String value) {
            addCriterion("sales_name not like", value, "salesName");
            return (Criteria) this;
        }

        public Criteria andSalesNameIn(List<String> values) {
            addCriterion("sales_name in", values, "salesName");
            return (Criteria) this;
        }

        public Criteria andSalesNameNotIn(List<String> values) {
            addCriterion("sales_name not in", values, "salesName");
            return (Criteria) this;
        }

        public Criteria andSalesNameBetween(String value1, String value2) {
            addCriterion("sales_name between", value1, value2, "salesName");
            return (Criteria) this;
        }

        public Criteria andSalesNameNotBetween(String value1, String value2) {
            addCriterion("sales_name not between", value1, value2, "salesName");
            return (Criteria) this;
        }

        public Criteria andSalesOrderTimeIsNull() {
            addCriterion("sales_order_time is null");
            return (Criteria) this;
        }

        public Criteria andSalesOrderTimeIsNotNull() {
            addCriterion("sales_order_time is not null");
            return (Criteria) this;
        }

        public Criteria andSalesOrderTimeEqualTo(Date value) {
            addCriterion("sales_order_time =", value, "salesOrderTime");
            return (Criteria) this;
        }

        public Criteria andSalesOrderTimeNotEqualTo(Date value) {
            addCriterion("sales_order_time <>", value, "salesOrderTime");
            return (Criteria) this;
        }

        public Criteria andSalesOrderTimeGreaterThan(Date value) {
            addCriterion("sales_order_time >", value, "salesOrderTime");
            return (Criteria) this;
        }

        public Criteria andSalesOrderTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sales_order_time >=", value, "salesOrderTime");
            return (Criteria) this;
        }

        public Criteria andSalesOrderTimeLessThan(Date value) {
            addCriterion("sales_order_time <", value, "salesOrderTime");
            return (Criteria) this;
        }

        public Criteria andSalesOrderTimeLessThanOrEqualTo(Date value) {
            addCriterion("sales_order_time <=", value, "salesOrderTime");
            return (Criteria) this;
        }

        public Criteria andSalesOrderTimeIn(List<Date> values) {
            addCriterion("sales_order_time in", values, "salesOrderTime");
            return (Criteria) this;
        }

        public Criteria andSalesOrderTimeNotIn(List<Date> values) {
            addCriterion("sales_order_time not in", values, "salesOrderTime");
            return (Criteria) this;
        }

        public Criteria andSalesOrderTimeBetween(Date value1, Date value2) {
            addCriterion("sales_order_time between", value1, value2, "salesOrderTime");
            return (Criteria) this;
        }

        public Criteria andSalesOrderTimeNotBetween(Date value1, Date value2) {
            addCriterion("sales_order_time not between", value1, value2, "salesOrderTime");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNull() {
            addCriterion("order_status is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNotNull() {
            addCriterion("order_status is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusEqualTo(Integer value) {
            addCriterion("order_status =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(Integer value) {
            addCriterion("order_status <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(Integer value) {
            addCriterion("order_status >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_status >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(Integer value) {
            addCriterion("order_status <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(Integer value) {
            addCriterion("order_status <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<Integer> values) {
            addCriterion("order_status in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<Integer> values) {
            addCriterion("order_status not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(Integer value1, Integer value2) {
            addCriterion("order_status between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("order_status not between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andAuditUserIsNull() {
            addCriterion("audit_user is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserIsNotNull() {
            addCriterion("audit_user is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserEqualTo(Long value) {
            addCriterion("audit_user =", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserNotEqualTo(Long value) {
            addCriterion("audit_user <>", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserGreaterThan(Long value) {
            addCriterion("audit_user >", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserGreaterThanOrEqualTo(Long value) {
            addCriterion("audit_user >=", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserLessThan(Long value) {
            addCriterion("audit_user <", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserLessThanOrEqualTo(Long value) {
            addCriterion("audit_user <=", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserIn(List<Long> values) {
            addCriterion("audit_user in", values, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserNotIn(List<Long> values) {
            addCriterion("audit_user not in", values, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserBetween(Long value1, Long value2) {
            addCriterion("audit_user between", value1, value2, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserNotBetween(Long value1, Long value2) {
            addCriterion("audit_user not between", value1, value2, "auditUser");
            return (Criteria) this;
        }

        public Criteria andShipperIdIsNull() {
            addCriterion("shipper_id is null");
            return (Criteria) this;
        }

        public Criteria andShipperIdIsNotNull() {
            addCriterion("shipper_id is not null");
            return (Criteria) this;
        }

        public Criteria andShipperIdEqualTo(Long value) {
            addCriterion("shipper_id =", value, "shipperId");
            return (Criteria) this;
        }

        public Criteria andShipperIdNotEqualTo(Long value) {
            addCriterion("shipper_id <>", value, "shipperId");
            return (Criteria) this;
        }

        public Criteria andShipperIdGreaterThan(Long value) {
            addCriterion("shipper_id >", value, "shipperId");
            return (Criteria) this;
        }

        public Criteria andShipperIdGreaterThanOrEqualTo(Long value) {
            addCriterion("shipper_id >=", value, "shipperId");
            return (Criteria) this;
        }

        public Criteria andShipperIdLessThan(Long value) {
            addCriterion("shipper_id <", value, "shipperId");
            return (Criteria) this;
        }

        public Criteria andShipperIdLessThanOrEqualTo(Long value) {
            addCriterion("shipper_id <=", value, "shipperId");
            return (Criteria) this;
        }

        public Criteria andShipperIdIn(List<Long> values) {
            addCriterion("shipper_id in", values, "shipperId");
            return (Criteria) this;
        }

        public Criteria andShipperIdNotIn(List<Long> values) {
            addCriterion("shipper_id not in", values, "shipperId");
            return (Criteria) this;
        }

        public Criteria andShipperIdBetween(Long value1, Long value2) {
            addCriterion("shipper_id between", value1, value2, "shipperId");
            return (Criteria) this;
        }

        public Criteria andShipperIdNotBetween(Long value1, Long value2) {
            addCriterion("shipper_id not between", value1, value2, "shipperId");
            return (Criteria) this;
        }

        public Criteria andThirdNoIsNull() {
            addCriterion("third_no is null");
            return (Criteria) this;
        }

        public Criteria andThirdNoIsNotNull() {
            addCriterion("third_no is not null");
            return (Criteria) this;
        }

        public Criteria andThirdNoEqualTo(String value) {
            addCriterion("third_no =", value, "thirdNo");
            return (Criteria) this;
        }

        public Criteria andThirdNoNotEqualTo(String value) {
            addCriterion("third_no <>", value, "thirdNo");
            return (Criteria) this;
        }

        public Criteria andThirdNoGreaterThan(String value) {
            addCriterion("third_no >", value, "thirdNo");
            return (Criteria) this;
        }

        public Criteria andThirdNoGreaterThanOrEqualTo(String value) {
            addCriterion("third_no >=", value, "thirdNo");
            return (Criteria) this;
        }

        public Criteria andThirdNoLessThan(String value) {
            addCriterion("third_no <", value, "thirdNo");
            return (Criteria) this;
        }

        public Criteria andThirdNoLessThanOrEqualTo(String value) {
            addCriterion("third_no <=", value, "thirdNo");
            return (Criteria) this;
        }

        public Criteria andThirdNoLike(String value) {
            addCriterion("third_no like", value, "thirdNo");
            return (Criteria) this;
        }

        public Criteria andThirdNoNotLike(String value) {
            addCriterion("third_no not like", value, "thirdNo");
            return (Criteria) this;
        }

        public Criteria andThirdNoIn(List<String> values) {
            addCriterion("third_no in", values, "thirdNo");
            return (Criteria) this;
        }

        public Criteria andThirdNoNotIn(List<String> values) {
            addCriterion("third_no not in", values, "thirdNo");
            return (Criteria) this;
        }

        public Criteria andThirdNoBetween(String value1, String value2) {
            addCriterion("third_no between", value1, value2, "thirdNo");
            return (Criteria) this;
        }

        public Criteria andThirdNoNotBetween(String value1, String value2) {
            addCriterion("third_no not between", value1, value2, "thirdNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoIsNull() {
            addCriterion("tracking_no is null");
            return (Criteria) this;
        }

        public Criteria andTrackingNoIsNotNull() {
            addCriterion("tracking_no is not null");
            return (Criteria) this;
        }

        public Criteria andTrackingNoEqualTo(String value) {
            addCriterion("tracking_no =", value, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoNotEqualTo(String value) {
            addCriterion("tracking_no <>", value, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoGreaterThan(String value) {
            addCriterion("tracking_no >", value, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoGreaterThanOrEqualTo(String value) {
            addCriterion("tracking_no >=", value, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoLessThan(String value) {
            addCriterion("tracking_no <", value, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoLessThanOrEqualTo(String value) {
            addCriterion("tracking_no <=", value, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoLike(String value) {
            addCriterion("tracking_no like", value, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoNotLike(String value) {
            addCriterion("tracking_no not like", value, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoIn(List<String> values) {
            addCriterion("tracking_no in", values, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoNotIn(List<String> values) {
            addCriterion("tracking_no not in", values, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoBetween(String value1, String value2) {
            addCriterion("tracking_no between", value1, value2, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andTrackingNoNotBetween(String value1, String value2) {
            addCriterion("tracking_no not between", value1, value2, "trackingNo");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIsNull() {
            addCriterion("work_type is null");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIsNotNull() {
            addCriterion("work_type is not null");
            return (Criteria) this;
        }

        public Criteria andWorkTypeEqualTo(Integer value) {
            addCriterion("work_type =", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNotEqualTo(Integer value) {
            addCriterion("work_type <>", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeGreaterThan(Integer value) {
            addCriterion("work_type >", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("work_type >=", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLessThan(Integer value) {
            addCriterion("work_type <", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLessThanOrEqualTo(Integer value) {
            addCriterion("work_type <=", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIn(List<Integer> values) {
            addCriterion("work_type in", values, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNotIn(List<Integer> values) {
            addCriterion("work_type not in", values, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeBetween(Integer value1, Integer value2) {
            addCriterion("work_type between", value1, value2, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("work_type not between", value1, value2, "workType");
            return (Criteria) this;
        }

        public Criteria andTotalSalePriceIsNull() {
            addCriterion("total_sale_price is null");
            return (Criteria) this;
        }

        public Criteria andTotalSalePriceIsNotNull() {
            addCriterion("total_sale_price is not null");
            return (Criteria) this;
        }

        public Criteria andTotalSalePriceEqualTo(BigDecimal value) {
            addCriterion("total_sale_price =", value, "totalSalePrice");
            return (Criteria) this;
        }

        public Criteria andTotalSalePriceNotEqualTo(BigDecimal value) {
            addCriterion("total_sale_price <>", value, "totalSalePrice");
            return (Criteria) this;
        }

        public Criteria andTotalSalePriceGreaterThan(BigDecimal value) {
            addCriterion("total_sale_price >", value, "totalSalePrice");
            return (Criteria) this;
        }

        public Criteria andTotalSalePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_sale_price >=", value, "totalSalePrice");
            return (Criteria) this;
        }

        public Criteria andTotalSalePriceLessThan(BigDecimal value) {
            addCriterion("total_sale_price <", value, "totalSalePrice");
            return (Criteria) this;
        }

        public Criteria andTotalSalePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_sale_price <=", value, "totalSalePrice");
            return (Criteria) this;
        }

        public Criteria andTotalSalePriceIn(List<BigDecimal> values) {
            addCriterion("total_sale_price in", values, "totalSalePrice");
            return (Criteria) this;
        }

        public Criteria andTotalSalePriceNotIn(List<BigDecimal> values) {
            addCriterion("total_sale_price not in", values, "totalSalePrice");
            return (Criteria) this;
        }

        public Criteria andTotalSalePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_sale_price between", value1, value2, "totalSalePrice");
            return (Criteria) this;
        }

        public Criteria andTotalSalePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_sale_price not between", value1, value2, "totalSalePrice");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNull() {
            addCriterion("total_amount is null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNotNull() {
            addCriterion("total_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountEqualTo(BigDecimal value) {
            addCriterion("total_amount =", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotEqualTo(BigDecimal value) {
            addCriterion("total_amount <>", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThan(BigDecimal value) {
            addCriterion("total_amount >", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_amount >=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThan(BigDecimal value) {
            addCriterion("total_amount <", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_amount <=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIn(List<BigDecimal> values) {
            addCriterion("total_amount in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotIn(List<BigDecimal> values) {
            addCriterion("total_amount not in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_amount between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_amount not between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNull() {
            addCriterion("discount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("discount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(BigDecimal value) {
            addCriterion("discount =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(BigDecimal value) {
            addCriterion("discount <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(BigDecimal value) {
            addCriterion("discount >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discount >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(BigDecimal value) {
            addCriterion("discount <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discount <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<BigDecimal> values) {
            addCriterion("discount in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<BigDecimal> values) {
            addCriterion("discount not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount not between", value1, value2, "discount");
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

        public Criteria andBankInfoIsNull() {
            addCriterion("bank_info is null");
            return (Criteria) this;
        }

        public Criteria andBankInfoIsNotNull() {
            addCriterion("bank_info is not null");
            return (Criteria) this;
        }

        public Criteria andBankInfoEqualTo(String value) {
            addCriterion("bank_info =", value, "bankInfo");
            return (Criteria) this;
        }

        public Criteria andBankInfoNotEqualTo(String value) {
            addCriterion("bank_info <>", value, "bankInfo");
            return (Criteria) this;
        }

        public Criteria andBankInfoGreaterThan(String value) {
            addCriterion("bank_info >", value, "bankInfo");
            return (Criteria) this;
        }

        public Criteria andBankInfoGreaterThanOrEqualTo(String value) {
            addCriterion("bank_info >=", value, "bankInfo");
            return (Criteria) this;
        }

        public Criteria andBankInfoLessThan(String value) {
            addCriterion("bank_info <", value, "bankInfo");
            return (Criteria) this;
        }

        public Criteria andBankInfoLessThanOrEqualTo(String value) {
            addCriterion("bank_info <=", value, "bankInfo");
            return (Criteria) this;
        }

        public Criteria andBankInfoLike(String value) {
            addCriterion("bank_info like", value, "bankInfo");
            return (Criteria) this;
        }

        public Criteria andBankInfoNotLike(String value) {
            addCriterion("bank_info not like", value, "bankInfo");
            return (Criteria) this;
        }

        public Criteria andBankInfoIn(List<String> values) {
            addCriterion("bank_info in", values, "bankInfo");
            return (Criteria) this;
        }

        public Criteria andBankInfoNotIn(List<String> values) {
            addCriterion("bank_info not in", values, "bankInfo");
            return (Criteria) this;
        }

        public Criteria andBankInfoBetween(String value1, String value2) {
            addCriterion("bank_info between", value1, value2, "bankInfo");
            return (Criteria) this;
        }

        public Criteria andBankInfoNotBetween(String value1, String value2) {
            addCriterion("bank_info not between", value1, value2, "bankInfo");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNull() {
            addCriterion("tenant_id is null");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNotNull() {
            addCriterion("tenant_id is not null");
            return (Criteria) this;
        }

        public Criteria andTenantIdEqualTo(Long value) {
            addCriterion("tenant_id =", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotEqualTo(Long value) {
            addCriterion("tenant_id <>", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThan(Long value) {
            addCriterion("tenant_id >", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThanOrEqualTo(Long value) {
            addCriterion("tenant_id >=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThan(Long value) {
            addCriterion("tenant_id <", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThanOrEqualTo(Long value) {
            addCriterion("tenant_id <=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIn(List<Long> values) {
            addCriterion("tenant_id in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotIn(List<Long> values) {
            addCriterion("tenant_id not in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdBetween(Long value1, Long value2) {
            addCriterion("tenant_id between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotBetween(Long value1, Long value2) {
            addCriterion("tenant_id not between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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