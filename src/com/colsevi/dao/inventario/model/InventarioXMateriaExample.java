package com.colsevi.dao.inventario.model;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class InventarioXMateriaExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table inventario_x_materia
	 * @mbggenerated  Thu Apr 21 17:19:48 COT 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table inventario_x_materia
	 * @mbggenerated  Thu Apr 21 17:19:48 COT 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table inventario_x_materia
	 * @mbggenerated  Thu Apr 21 17:19:48 COT 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario_x_materia
	 * @mbggenerated  Thu Apr 21 17:19:48 COT 2016
	 */
	public InventarioXMateriaExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario_x_materia
	 * @mbggenerated  Thu Apr 21 17:19:48 COT 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario_x_materia
	 * @mbggenerated  Thu Apr 21 17:19:48 COT 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario_x_materia
	 * @mbggenerated  Thu Apr 21 17:19:48 COT 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario_x_materia
	 * @mbggenerated  Thu Apr 21 17:19:48 COT 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario_x_materia
	 * @mbggenerated  Thu Apr 21 17:19:48 COT 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario_x_materia
	 * @mbggenerated  Thu Apr 21 17:19:48 COT 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario_x_materia
	 * @mbggenerated  Thu Apr 21 17:19:48 COT 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario_x_materia
	 * @mbggenerated  Thu Apr 21 17:19:48 COT 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario_x_materia
	 * @mbggenerated  Thu Apr 21 17:19:48 COT 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table inventario_x_materia
	 * @mbggenerated  Thu Apr 21 17:19:48 COT 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table inventario_x_materia
	 * @mbggenerated  Thu Apr 21 17:19:48 COT 2016
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

		public Criteria andId_inventarioIsNull() {
			addCriterion("id_inventario is null");
			return (Criteria) this;
		}

		public Criteria andId_inventarioIsNotNull() {
			addCriterion("id_inventario is not null");
			return (Criteria) this;
		}

		public Criteria andId_inventarioEqualTo(Integer value) {
			addCriterion("id_inventario =", value, "id_inventario");
			return (Criteria) this;
		}

		public Criteria andId_inventarioNotEqualTo(Integer value) {
			addCriterion("id_inventario <>", value, "id_inventario");
			return (Criteria) this;
		}

		public Criteria andId_inventarioGreaterThan(Integer value) {
			addCriterion("id_inventario >", value, "id_inventario");
			return (Criteria) this;
		}

		public Criteria andId_inventarioGreaterThanOrEqualTo(Integer value) {
			addCriterion("id_inventario >=", value, "id_inventario");
			return (Criteria) this;
		}

		public Criteria andId_inventarioLessThan(Integer value) {
			addCriterion("id_inventario <", value, "id_inventario");
			return (Criteria) this;
		}

		public Criteria andId_inventarioLessThanOrEqualTo(Integer value) {
			addCriterion("id_inventario <=", value, "id_inventario");
			return (Criteria) this;
		}

		public Criteria andId_inventarioIn(List<Integer> values) {
			addCriterion("id_inventario in", values, "id_inventario");
			return (Criteria) this;
		}

		public Criteria andId_inventarioNotIn(List<Integer> values) {
			addCriterion("id_inventario not in", values, "id_inventario");
			return (Criteria) this;
		}

		public Criteria andId_inventarioBetween(Integer value1, Integer value2) {
			addCriterion("id_inventario between", value1, value2, "id_inventario");
			return (Criteria) this;
		}

		public Criteria andId_inventarioNotBetween(Integer value1, Integer value2) {
			addCriterion("id_inventario not between", value1, value2, "id_inventario");
			return (Criteria) this;
		}

		public Criteria andId_ingredienteIsNull() {
			addCriterion("id_ingrediente is null");
			return (Criteria) this;
		}

		public Criteria andId_ingredienteIsNotNull() {
			addCriterion("id_ingrediente is not null");
			return (Criteria) this;
		}

		public Criteria andId_ingredienteEqualTo(Integer value) {
			addCriterion("id_ingrediente =", value, "id_ingrediente");
			return (Criteria) this;
		}

		public Criteria andId_ingredienteNotEqualTo(Integer value) {
			addCriterion("id_ingrediente <>", value, "id_ingrediente");
			return (Criteria) this;
		}

		public Criteria andId_ingredienteGreaterThan(Integer value) {
			addCriterion("id_ingrediente >", value, "id_ingrediente");
			return (Criteria) this;
		}

		public Criteria andId_ingredienteGreaterThanOrEqualTo(Integer value) {
			addCriterion("id_ingrediente >=", value, "id_ingrediente");
			return (Criteria) this;
		}

		public Criteria andId_ingredienteLessThan(Integer value) {
			addCriterion("id_ingrediente <", value, "id_ingrediente");
			return (Criteria) this;
		}

		public Criteria andId_ingredienteLessThanOrEqualTo(Integer value) {
			addCriterion("id_ingrediente <=", value, "id_ingrediente");
			return (Criteria) this;
		}

		public Criteria andId_ingredienteIn(List<Integer> values) {
			addCriterion("id_ingrediente in", values, "id_ingrediente");
			return (Criteria) this;
		}

		public Criteria andId_ingredienteNotIn(List<Integer> values) {
			addCriterion("id_ingrediente not in", values, "id_ingrediente");
			return (Criteria) this;
		}

		public Criteria andId_ingredienteBetween(Integer value1, Integer value2) {
			addCriterion("id_ingrediente between", value1, value2, "id_ingrediente");
			return (Criteria) this;
		}

		public Criteria andId_ingredienteNotBetween(Integer value1, Integer value2) {
			addCriterion("id_ingrediente not between", value1, value2, "id_ingrediente");
			return (Criteria) this;
		}

		public Criteria andLoteIsNull() {
			addCriterion("lote is null");
			return (Criteria) this;
		}

		public Criteria andLoteIsNotNull() {
			addCriterion("lote is not null");
			return (Criteria) this;
		}

		public Criteria andLoteEqualTo(Integer value) {
			addCriterion("lote =", value, "lote");
			return (Criteria) this;
		}

		public Criteria andLoteNotEqualTo(Integer value) {
			addCriterion("lote <>", value, "lote");
			return (Criteria) this;
		}

		public Criteria andLoteGreaterThan(Integer value) {
			addCriterion("lote >", value, "lote");
			return (Criteria) this;
		}

		public Criteria andLoteGreaterThanOrEqualTo(Integer value) {
			addCriterion("lote >=", value, "lote");
			return (Criteria) this;
		}

		public Criteria andLoteLessThan(Integer value) {
			addCriterion("lote <", value, "lote");
			return (Criteria) this;
		}

		public Criteria andLoteLessThanOrEqualTo(Integer value) {
			addCriterion("lote <=", value, "lote");
			return (Criteria) this;
		}

		public Criteria andLoteIn(List<Integer> values) {
			addCriterion("lote in", values, "lote");
			return (Criteria) this;
		}

		public Criteria andLoteNotIn(List<Integer> values) {
			addCriterion("lote not in", values, "lote");
			return (Criteria) this;
		}

		public Criteria andLoteBetween(Integer value1, Integer value2) {
			addCriterion("lote between", value1, value2, "lote");
			return (Criteria) this;
		}

		public Criteria andLoteNotBetween(Integer value1, Integer value2) {
			addCriterion("lote not between", value1, value2, "lote");
			return (Criteria) this;
		}

		public Criteria andId_unidad_pesoIsNull() {
			addCriterion("id_unidad_peso is null");
			return (Criteria) this;
		}

		public Criteria andId_unidad_pesoIsNotNull() {
			addCriterion("id_unidad_peso is not null");
			return (Criteria) this;
		}

		public Criteria andId_unidad_pesoEqualTo(Integer value) {
			addCriterion("id_unidad_peso =", value, "id_unidad_peso");
			return (Criteria) this;
		}

		public Criteria andId_unidad_pesoNotEqualTo(Integer value) {
			addCriterion("id_unidad_peso <>", value, "id_unidad_peso");
			return (Criteria) this;
		}

		public Criteria andId_unidad_pesoGreaterThan(Integer value) {
			addCriterion("id_unidad_peso >", value, "id_unidad_peso");
			return (Criteria) this;
		}

		public Criteria andId_unidad_pesoGreaterThanOrEqualTo(Integer value) {
			addCriterion("id_unidad_peso >=", value, "id_unidad_peso");
			return (Criteria) this;
		}

		public Criteria andId_unidad_pesoLessThan(Integer value) {
			addCriterion("id_unidad_peso <", value, "id_unidad_peso");
			return (Criteria) this;
		}

		public Criteria andId_unidad_pesoLessThanOrEqualTo(Integer value) {
			addCriterion("id_unidad_peso <=", value, "id_unidad_peso");
			return (Criteria) this;
		}

		public Criteria andId_unidad_pesoIn(List<Integer> values) {
			addCriterion("id_unidad_peso in", values, "id_unidad_peso");
			return (Criteria) this;
		}

		public Criteria andId_unidad_pesoNotIn(List<Integer> values) {
			addCriterion("id_unidad_peso not in", values, "id_unidad_peso");
			return (Criteria) this;
		}

		public Criteria andId_unidad_pesoBetween(Integer value1, Integer value2) {
			addCriterion("id_unidad_peso between", value1, value2, "id_unidad_peso");
			return (Criteria) this;
		}

		public Criteria andId_unidad_pesoNotBetween(Integer value1, Integer value2) {
			addCriterion("id_unidad_peso not between", value1, value2, "id_unidad_peso");
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

		public Criteria andCantidadEqualTo(Double value) {
			addCriterion("cantidad =", value, "cantidad");
			return (Criteria) this;
		}

		public Criteria andCantidadNotEqualTo(Double value) {
			addCriterion("cantidad <>", value, "cantidad");
			return (Criteria) this;
		}

		public Criteria andCantidadGreaterThan(Double value) {
			addCriterion("cantidad >", value, "cantidad");
			return (Criteria) this;
		}

		public Criteria andCantidadGreaterThanOrEqualTo(Double value) {
			addCriterion("cantidad >=", value, "cantidad");
			return (Criteria) this;
		}

		public Criteria andCantidadLessThan(Double value) {
			addCriterion("cantidad <", value, "cantidad");
			return (Criteria) this;
		}

		public Criteria andCantidadLessThanOrEqualTo(Double value) {
			addCriterion("cantidad <=", value, "cantidad");
			return (Criteria) this;
		}

		public Criteria andCantidadIn(List<Double> values) {
			addCriterion("cantidad in", values, "cantidad");
			return (Criteria) this;
		}

		public Criteria andCantidadNotIn(List<Double> values) {
			addCriterion("cantidad not in", values, "cantidad");
			return (Criteria) this;
		}

		public Criteria andCantidadBetween(Double value1, Double value2) {
			addCriterion("cantidad between", value1, value2, "cantidad");
			return (Criteria) this;
		}

		public Criteria andCantidadNotBetween(Double value1, Double value2) {
			addCriterion("cantidad not between", value1, value2, "cantidad");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table inventario_x_materia
	 * @mbggenerated  Thu Apr 21 17:19:48 COT 2016
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

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table inventario_x_materia
     *
     * @mbggenerated do_not_delete_during_merge Thu Apr 21 16:32:29 COT 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}