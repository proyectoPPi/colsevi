package com.colsevi.dao.pedido.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	public DetallePedidoExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
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

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andId_detalle_pedidoIsNull() {
			addCriterion("id_detalle_pedido is null");
			return (Criteria) this;
		}

		public Criteria andId_detalle_pedidoIsNotNull() {
			addCriterion("id_detalle_pedido is not null");
			return (Criteria) this;
		}

		public Criteria andId_detalle_pedidoEqualTo(Integer value) {
			addCriterion("id_detalle_pedido =", value, "id_detalle_pedido");
			return (Criteria) this;
		}

		public Criteria andId_detalle_pedidoNotEqualTo(Integer value) {
			addCriterion("id_detalle_pedido <>", value, "id_detalle_pedido");
			return (Criteria) this;
		}

		public Criteria andId_detalle_pedidoGreaterThan(Integer value) {
			addCriterion("id_detalle_pedido >", value, "id_detalle_pedido");
			return (Criteria) this;
		}

		public Criteria andId_detalle_pedidoGreaterThanOrEqualTo(Integer value) {
			addCriterion("id_detalle_pedido >=", value, "id_detalle_pedido");
			return (Criteria) this;
		}

		public Criteria andId_detalle_pedidoLessThan(Integer value) {
			addCriterion("id_detalle_pedido <", value, "id_detalle_pedido");
			return (Criteria) this;
		}

		public Criteria andId_detalle_pedidoLessThanOrEqualTo(Integer value) {
			addCriterion("id_detalle_pedido <=", value, "id_detalle_pedido");
			return (Criteria) this;
		}

		public Criteria andId_detalle_pedidoIn(List<Integer> values) {
			addCriterion("id_detalle_pedido in", values, "id_detalle_pedido");
			return (Criteria) this;
		}

		public Criteria andId_detalle_pedidoNotIn(List<Integer> values) {
			addCriterion("id_detalle_pedido not in", values,
					"id_detalle_pedido");
			return (Criteria) this;
		}

		public Criteria andId_detalle_pedidoBetween(Integer value1,
				Integer value2) {
			addCriterion("id_detalle_pedido between", value1, value2,
					"id_detalle_pedido");
			return (Criteria) this;
		}

		public Criteria andId_detalle_pedidoNotBetween(Integer value1,
				Integer value2) {
			addCriterion("id_detalle_pedido not between", value1, value2,
					"id_detalle_pedido");
			return (Criteria) this;
		}

		public Criteria andId_pedidoIsNull() {
			addCriterion("id_pedido is null");
			return (Criteria) this;
		}

		public Criteria andId_pedidoIsNotNull() {
			addCriterion("id_pedido is not null");
			return (Criteria) this;
		}

		public Criteria andId_pedidoEqualTo(Integer value) {
			addCriterion("id_pedido =", value, "id_pedido");
			return (Criteria) this;
		}

		public Criteria andId_pedidoNotEqualTo(Integer value) {
			addCriterion("id_pedido <>", value, "id_pedido");
			return (Criteria) this;
		}

		public Criteria andId_pedidoGreaterThan(Integer value) {
			addCriterion("id_pedido >", value, "id_pedido");
			return (Criteria) this;
		}

		public Criteria andId_pedidoGreaterThanOrEqualTo(Integer value) {
			addCriterion("id_pedido >=", value, "id_pedido");
			return (Criteria) this;
		}

		public Criteria andId_pedidoLessThan(Integer value) {
			addCriterion("id_pedido <", value, "id_pedido");
			return (Criteria) this;
		}

		public Criteria andId_pedidoLessThanOrEqualTo(Integer value) {
			addCriterion("id_pedido <=", value, "id_pedido");
			return (Criteria) this;
		}

		public Criteria andId_pedidoIn(List<Integer> values) {
			addCriterion("id_pedido in", values, "id_pedido");
			return (Criteria) this;
		}

		public Criteria andId_pedidoNotIn(List<Integer> values) {
			addCriterion("id_pedido not in", values, "id_pedido");
			return (Criteria) this;
		}

		public Criteria andId_pedidoBetween(Integer value1, Integer value2) {
			addCriterion("id_pedido between", value1, value2, "id_pedido");
			return (Criteria) this;
		}

		public Criteria andId_pedidoNotBetween(Integer value1, Integer value2) {
			addCriterion("id_pedido not between", value1, value2, "id_pedido");
			return (Criteria) this;
		}

		public Criteria andId_productoIsNull() {
			addCriterion("id_producto is null");
			return (Criteria) this;
		}

		public Criteria andId_productoIsNotNull() {
			addCriterion("id_producto is not null");
			return (Criteria) this;
		}

		public Criteria andId_productoEqualTo(Integer value) {
			addCriterion("id_producto =", value, "id_producto");
			return (Criteria) this;
		}

		public Criteria andId_productoNotEqualTo(Integer value) {
			addCriterion("id_producto <>", value, "id_producto");
			return (Criteria) this;
		}

		public Criteria andId_productoGreaterThan(Integer value) {
			addCriterion("id_producto >", value, "id_producto");
			return (Criteria) this;
		}

		public Criteria andId_productoGreaterThanOrEqualTo(Integer value) {
			addCriterion("id_producto >=", value, "id_producto");
			return (Criteria) this;
		}

		public Criteria andId_productoLessThan(Integer value) {
			addCriterion("id_producto <", value, "id_producto");
			return (Criteria) this;
		}

		public Criteria andId_productoLessThanOrEqualTo(Integer value) {
			addCriterion("id_producto <=", value, "id_producto");
			return (Criteria) this;
		}

		public Criteria andId_productoIn(List<Integer> values) {
			addCriterion("id_producto in", values, "id_producto");
			return (Criteria) this;
		}

		public Criteria andId_productoNotIn(List<Integer> values) {
			addCriterion("id_producto not in", values, "id_producto");
			return (Criteria) this;
		}

		public Criteria andId_productoBetween(Integer value1, Integer value2) {
			addCriterion("id_producto between", value1, value2, "id_producto");
			return (Criteria) this;
		}

		public Criteria andId_productoNotBetween(Integer value1, Integer value2) {
			addCriterion("id_producto not between", value1, value2,
					"id_producto");
			return (Criteria) this;
		}

		public Criteria andCantidadIsNull() {
			addCriterion("cantidad is null");
			return (Criteria) this;
		}

		public Criteria andCantidadIsNotNull() {
			addCriterion("cantidad is not null");
			return (Criteria) this;
		}

		public Criteria andCantidadEqualTo(Integer value) {
			addCriterion("cantidad =", value, "cantidad");
			return (Criteria) this;
		}

		public Criteria andCantidadNotEqualTo(Integer value) {
			addCriterion("cantidad <>", value, "cantidad");
			return (Criteria) this;
		}

		public Criteria andCantidadGreaterThan(Integer value) {
			addCriterion("cantidad >", value, "cantidad");
			return (Criteria) this;
		}

		public Criteria andCantidadGreaterThanOrEqualTo(Integer value) {
			addCriterion("cantidad >=", value, "cantidad");
			return (Criteria) this;
		}

		public Criteria andCantidadLessThan(Integer value) {
			addCriterion("cantidad <", value, "cantidad");
			return (Criteria) this;
		}

		public Criteria andCantidadLessThanOrEqualTo(Integer value) {
			addCriterion("cantidad <=", value, "cantidad");
			return (Criteria) this;
		}

		public Criteria andCantidadIn(List<Integer> values) {
			addCriterion("cantidad in", values, "cantidad");
			return (Criteria) this;
		}

		public Criteria andCantidadNotIn(List<Integer> values) {
			addCriterion("cantidad not in", values, "cantidad");
			return (Criteria) this;
		}

		public Criteria andCantidadBetween(Integer value1, Integer value2) {
			addCriterion("cantidad between", value1, value2, "cantidad");
			return (Criteria) this;
		}

		public Criteria andCantidadNotBetween(Integer value1, Integer value2) {
			addCriterion("cantidad not between", value1, value2, "cantidad");
			return (Criteria) this;
		}

		public Criteria andSub_totalIsNull() {
			addCriterion("sub_total is null");
			return (Criteria) this;
		}

		public Criteria andSub_totalIsNotNull() {
			addCriterion("sub_total is not null");
			return (Criteria) this;
		}

		public Criteria andSub_totalEqualTo(BigDecimal value) {
			addCriterion("sub_total =", value, "sub_total");
			return (Criteria) this;
		}

		public Criteria andSub_totalNotEqualTo(BigDecimal value) {
			addCriterion("sub_total <>", value, "sub_total");
			return (Criteria) this;
		}

		public Criteria andSub_totalGreaterThan(BigDecimal value) {
			addCriterion("sub_total >", value, "sub_total");
			return (Criteria) this;
		}

		public Criteria andSub_totalGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("sub_total >=", value, "sub_total");
			return (Criteria) this;
		}

		public Criteria andSub_totalLessThan(BigDecimal value) {
			addCriterion("sub_total <", value, "sub_total");
			return (Criteria) this;
		}

		public Criteria andSub_totalLessThanOrEqualTo(BigDecimal value) {
			addCriterion("sub_total <=", value, "sub_total");
			return (Criteria) this;
		}

		public Criteria andSub_totalIn(List<BigDecimal> values) {
			addCriterion("sub_total in", values, "sub_total");
			return (Criteria) this;
		}

		public Criteria andSub_totalNotIn(List<BigDecimal> values) {
			addCriterion("sub_total not in", values, "sub_total");
			return (Criteria) this;
		}

		public Criteria andSub_totalBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("sub_total between", value1, value2, "sub_total");
			return (Criteria) this;
		}

		public Criteria andSub_totalNotBetween(BigDecimal value1,
				BigDecimal value2) {
			addCriterion("sub_total not between", value1, value2, "sub_total");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table detalle_pedido
	 * @mbggenerated  Tue Apr 12 16:28:01 COT 2016
	 */
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

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
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

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table detalle_pedido
     *
     * @mbggenerated do_not_delete_during_merge Thu Mar 10 16:33:10 COT 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}