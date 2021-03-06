package com.colsevi.dao.proveedor.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public class CompraXIngredienteExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table compra_x_ingrediente
	 * @mbggenerated  Wed Jun 08 11:31:29 COT 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table compra_x_ingrediente
	 * @mbggenerated  Wed Jun 08 11:31:29 COT 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table compra_x_ingrediente
	 * @mbggenerated  Wed Jun 08 11:31:29 COT 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table compra_x_ingrediente
	 * @mbggenerated  Wed Jun 08 11:31:29 COT 2016
	 */
	public CompraXIngredienteExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table compra_x_ingrediente
	 * @mbggenerated  Wed Jun 08 11:31:29 COT 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table compra_x_ingrediente
	 * @mbggenerated  Wed Jun 08 11:31:29 COT 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table compra_x_ingrediente
	 * @mbggenerated  Wed Jun 08 11:31:29 COT 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table compra_x_ingrediente
	 * @mbggenerated  Wed Jun 08 11:31:29 COT 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table compra_x_ingrediente
	 * @mbggenerated  Wed Jun 08 11:31:29 COT 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table compra_x_ingrediente
	 * @mbggenerated  Wed Jun 08 11:31:29 COT 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table compra_x_ingrediente
	 * @mbggenerated  Wed Jun 08 11:31:29 COT 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table compra_x_ingrediente
	 * @mbggenerated  Wed Jun 08 11:31:29 COT 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table compra_x_ingrediente
	 * @mbggenerated  Wed Jun 08 11:31:29 COT 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table compra_x_ingrediente
	 * @mbggenerated  Wed Jun 08 11:31:29 COT 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table compra_x_ingrediente
	 * @mbggenerated  Wed Jun 08 11:31:29 COT 2016
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

		public Criteria andId_compraIsNull() {
			addCriterion("id_compra is null");
			return (Criteria) this;
		}

		public Criteria andId_compraIsNotNull() {
			addCriterion("id_compra is not null");
			return (Criteria) this;
		}

		public Criteria andId_compraEqualTo(Integer value) {
			addCriterion("id_compra =", value, "id_compra");
			return (Criteria) this;
		}

		public Criteria andId_compraNotEqualTo(Integer value) {
			addCriterion("id_compra <>", value, "id_compra");
			return (Criteria) this;
		}

		public Criteria andId_compraGreaterThan(Integer value) {
			addCriterion("id_compra >", value, "id_compra");
			return (Criteria) this;
		}

		public Criteria andId_compraGreaterThanOrEqualTo(Integer value) {
			addCriterion("id_compra >=", value, "id_compra");
			return (Criteria) this;
		}

		public Criteria andId_compraLessThan(Integer value) {
			addCriterion("id_compra <", value, "id_compra");
			return (Criteria) this;
		}

		public Criteria andId_compraLessThanOrEqualTo(Integer value) {
			addCriterion("id_compra <=", value, "id_compra");
			return (Criteria) this;
		}

		public Criteria andId_compraIn(List<Integer> values) {
			addCriterion("id_compra in", values, "id_compra");
			return (Criteria) this;
		}

		public Criteria andId_compraNotIn(List<Integer> values) {
			addCriterion("id_compra not in", values, "id_compra");
			return (Criteria) this;
		}

		public Criteria andId_compraBetween(Integer value1, Integer value2) {
			addCriterion("id_compra between", value1, value2, "id_compra");
			return (Criteria) this;
		}

		public Criteria andId_compraNotBetween(Integer value1, Integer value2) {
			addCriterion("id_compra not between", value1, value2, "id_compra");
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

		public Criteria andVunitarioIsNull() {
			addCriterion("vunitario is null");
			return (Criteria) this;
		}

		public Criteria andVunitarioIsNotNull() {
			addCriterion("vunitario is not null");
			return (Criteria) this;
		}

		public Criteria andVunitarioEqualTo(BigDecimal value) {
			addCriterion("vunitario =", value, "vunitario");
			return (Criteria) this;
		}

		public Criteria andVunitarioNotEqualTo(BigDecimal value) {
			addCriterion("vunitario <>", value, "vunitario");
			return (Criteria) this;
		}

		public Criteria andVunitarioGreaterThan(BigDecimal value) {
			addCriterion("vunitario >", value, "vunitario");
			return (Criteria) this;
		}

		public Criteria andVunitarioGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("vunitario >=", value, "vunitario");
			return (Criteria) this;
		}

		public Criteria andVunitarioLessThan(BigDecimal value) {
			addCriterion("vunitario <", value, "vunitario");
			return (Criteria) this;
		}

		public Criteria andVunitarioLessThanOrEqualTo(BigDecimal value) {
			addCriterion("vunitario <=", value, "vunitario");
			return (Criteria) this;
		}

		public Criteria andVunitarioIn(List<BigDecimal> values) {
			addCriterion("vunitario in", values, "vunitario");
			return (Criteria) this;
		}

		public Criteria andVunitarioNotIn(List<BigDecimal> values) {
			addCriterion("vunitario not in", values, "vunitario");
			return (Criteria) this;
		}

		public Criteria andVunitarioBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("vunitario between", value1, value2, "vunitario");
			return (Criteria) this;
		}

		public Criteria andVunitarioNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("vunitario not between", value1, value2, "vunitario");
			return (Criteria) this;
		}

		public Criteria andFecha_vencimientoIsNull() {
			addCriterion("fecha_vencimiento is null");
			return (Criteria) this;
		}

		public Criteria andFecha_vencimientoIsNotNull() {
			addCriterion("fecha_vencimiento is not null");
			return (Criteria) this;
		}

		public Criteria andFecha_vencimientoEqualTo(Date value) {
			addCriterion("fecha_vencimiento =", value, "fecha_vencimiento");
			return (Criteria) this;
		}

		public Criteria andFecha_vencimientoNotEqualTo(Date value) {
			addCriterion("fecha_vencimiento <>", value, "fecha_vencimiento");
			return (Criteria) this;
		}

		public Criteria andFecha_vencimientoGreaterThan(Date value) {
			addCriterion("fecha_vencimiento >", value, "fecha_vencimiento");
			return (Criteria) this;
		}

		public Criteria andFecha_vencimientoGreaterThanOrEqualTo(Date value) {
			addCriterion("fecha_vencimiento >=", value, "fecha_vencimiento");
			return (Criteria) this;
		}

		public Criteria andFecha_vencimientoLessThan(Date value) {
			addCriterion("fecha_vencimiento <", value, "fecha_vencimiento");
			return (Criteria) this;
		}

		public Criteria andFecha_vencimientoLessThanOrEqualTo(Date value) {
			addCriterion("fecha_vencimiento <=", value, "fecha_vencimiento");
			return (Criteria) this;
		}

		public Criteria andFecha_vencimientoIn(List<Date> values) {
			addCriterion("fecha_vencimiento in", values, "fecha_vencimiento");
			return (Criteria) this;
		}

		public Criteria andFecha_vencimientoNotIn(List<Date> values) {
			addCriterion("fecha_vencimiento not in", values, "fecha_vencimiento");
			return (Criteria) this;
		}

		public Criteria andFecha_vencimientoBetween(Date value1, Date value2) {
			addCriterion("fecha_vencimiento between", value1, value2, "fecha_vencimiento");
			return (Criteria) this;
		}

		public Criteria andFecha_vencimientoNotBetween(Date value1, Date value2) {
			addCriterion("fecha_vencimiento not between", value1, value2, "fecha_vencimiento");
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

		public Criteria andIvaIsNull() {
			addCriterion("iva is null");
			return (Criteria) this;
		}

		public Criteria andIvaIsNotNull() {
			addCriterion("iva is not null");
			return (Criteria) this;
		}

		public Criteria andIvaEqualTo(Integer value) {
			addCriterion("iva =", value, "iva");
			return (Criteria) this;
		}

		public Criteria andIvaNotEqualTo(Integer value) {
			addCriterion("iva <>", value, "iva");
			return (Criteria) this;
		}

		public Criteria andIvaGreaterThan(Integer value) {
			addCriterion("iva >", value, "iva");
			return (Criteria) this;
		}

		public Criteria andIvaGreaterThanOrEqualTo(Integer value) {
			addCriterion("iva >=", value, "iva");
			return (Criteria) this;
		}

		public Criteria andIvaLessThan(Integer value) {
			addCriterion("iva <", value, "iva");
			return (Criteria) this;
		}

		public Criteria andIvaLessThanOrEqualTo(Integer value) {
			addCriterion("iva <=", value, "iva");
			return (Criteria) this;
		}

		public Criteria andIvaIn(List<Integer> values) {
			addCriterion("iva in", values, "iva");
			return (Criteria) this;
		}

		public Criteria andIvaNotIn(List<Integer> values) {
			addCriterion("iva not in", values, "iva");
			return (Criteria) this;
		}

		public Criteria andIvaBetween(Integer value1, Integer value2) {
			addCriterion("iva between", value1, value2, "iva");
			return (Criteria) this;
		}

		public Criteria andIvaNotBetween(Integer value1, Integer value2) {
			addCriterion("iva not between", value1, value2, "iva");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table compra_x_ingrediente
	 * @mbggenerated  Wed Jun 08 11:31:29 COT 2016
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
     * This class corresponds to the database table compra_x_ingrediente
     *
     * @mbggenerated do_not_delete_during_merge Thu Mar 10 16:27:51 COT 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}