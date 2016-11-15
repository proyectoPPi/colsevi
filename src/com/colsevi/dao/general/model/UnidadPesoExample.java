package com.colsevi.dao.general.model;

import java.util.ArrayList;
import java.util.List;

public class UnidadPesoExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table unidad_peso
	 * @mbggenerated  Thu Apr 21 12:37:10 COT 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table unidad_peso
	 * @mbggenerated  Thu Apr 21 12:37:10 COT 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table unidad_peso
	 * @mbggenerated  Thu Apr 21 12:37:10 COT 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table unidad_peso
	 * @mbggenerated  Thu Apr 21 12:37:10 COT 2016
	 */
	public UnidadPesoExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table unidad_peso
	 * @mbggenerated  Thu Apr 21 12:37:10 COT 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table unidad_peso
	 * @mbggenerated  Thu Apr 21 12:37:10 COT 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table unidad_peso
	 * @mbggenerated  Thu Apr 21 12:37:10 COT 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table unidad_peso
	 * @mbggenerated  Thu Apr 21 12:37:10 COT 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table unidad_peso
	 * @mbggenerated  Thu Apr 21 12:37:10 COT 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table unidad_peso
	 * @mbggenerated  Thu Apr 21 12:37:10 COT 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table unidad_peso
	 * @mbggenerated  Thu Apr 21 12:37:10 COT 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table unidad_peso
	 * @mbggenerated  Thu Apr 21 12:37:10 COT 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table unidad_peso
	 * @mbggenerated  Thu Apr 21 12:37:10 COT 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table unidad_peso
	 * @mbggenerated  Thu Apr 21 12:37:10 COT 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table unidad_peso
	 * @mbggenerated  Thu Apr 21 12:37:10 COT 2016
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

		public Criteria andNombreIsNull() {
			addCriterion("nombre is null");
			return (Criteria) this;
		}

		public Criteria andNombreIsNotNull() {
			addCriterion("nombre is not null");
			return (Criteria) this;
		}

		public Criteria andNombreEqualTo(String value) {
			addCriterion("nombre =", value, "nombre");
			return (Criteria) this;
		}

		public Criteria andNombreNotEqualTo(String value) {
			addCriterion("nombre <>", value, "nombre");
			return (Criteria) this;
		}

		public Criteria andNombreGreaterThan(String value) {
			addCriterion("nombre >", value, "nombre");
			return (Criteria) this;
		}

		public Criteria andNombreGreaterThanOrEqualTo(String value) {
			addCriterion("nombre >=", value, "nombre");
			return (Criteria) this;
		}

		public Criteria andNombreLessThan(String value) {
			addCriterion("nombre <", value, "nombre");
			return (Criteria) this;
		}

		public Criteria andNombreLessThanOrEqualTo(String value) {
			addCriterion("nombre <=", value, "nombre");
			return (Criteria) this;
		}

		public Criteria andNombreLike(String value) {
			addCriterion("nombre like", value, "nombre");
			return (Criteria) this;
		}

		public Criteria andNombreNotLike(String value) {
			addCriterion("nombre not like", value, "nombre");
			return (Criteria) this;
		}

		public Criteria andNombreIn(List<String> values) {
			addCriterion("nombre in", values, "nombre");
			return (Criteria) this;
		}

		public Criteria andNombreNotIn(List<String> values) {
			addCriterion("nombre not in", values, "nombre");
			return (Criteria) this;
		}

		public Criteria andNombreBetween(String value1, String value2) {
			addCriterion("nombre between", value1, value2, "nombre");
			return (Criteria) this;
		}

		public Criteria andNombreNotBetween(String value1, String value2) {
			addCriterion("nombre not between", value1, value2, "nombre");
			return (Criteria) this;
		}

		public Criteria andDescripcionIsNull() {
			addCriterion("descripcion is null");
			return (Criteria) this;
		}

		public Criteria andDescripcionIsNotNull() {
			addCriterion("descripcion is not null");
			return (Criteria) this;
		}

		public Criteria andDescripcionEqualTo(String value) {
			addCriterion("descripcion =", value, "descripcion");
			return (Criteria) this;
		}

		public Criteria andDescripcionNotEqualTo(String value) {
			addCriterion("descripcion <>", value, "descripcion");
			return (Criteria) this;
		}

		public Criteria andDescripcionGreaterThan(String value) {
			addCriterion("descripcion >", value, "descripcion");
			return (Criteria) this;
		}

		public Criteria andDescripcionGreaterThanOrEqualTo(String value) {
			addCriterion("descripcion >=", value, "descripcion");
			return (Criteria) this;
		}

		public Criteria andDescripcionLessThan(String value) {
			addCriterion("descripcion <", value, "descripcion");
			return (Criteria) this;
		}

		public Criteria andDescripcionLessThanOrEqualTo(String value) {
			addCriterion("descripcion <=", value, "descripcion");
			return (Criteria) this;
		}

		public Criteria andDescripcionLike(String value) {
			addCriterion("descripcion like", value, "descripcion");
			return (Criteria) this;
		}

		public Criteria andDescripcionNotLike(String value) {
			addCriterion("descripcion not like", value, "descripcion");
			return (Criteria) this;
		}

		public Criteria andDescripcionIn(List<String> values) {
			addCriterion("descripcion in", values, "descripcion");
			return (Criteria) this;
		}

		public Criteria andDescripcionNotIn(List<String> values) {
			addCriterion("descripcion not in", values, "descripcion");
			return (Criteria) this;
		}

		public Criteria andDescripcionBetween(String value1, String value2) {
			addCriterion("descripcion between", value1, value2, "descripcion");
			return (Criteria) this;
		}

		public Criteria andDescripcionNotBetween(String value1, String value2) {
			addCriterion("descripcion not between", value1, value2, "descripcion");
			return (Criteria) this;
		}

		public Criteria andCodigoIsNull() {
			addCriterion("codigo is null");
			return (Criteria) this;
		}

		public Criteria andCodigoIsNotNull() {
			addCriterion("codigo is not null");
			return (Criteria) this;
		}

		public Criteria andCodigoEqualTo(String value) {
			addCriterion("codigo =", value, "codigo");
			return (Criteria) this;
		}

		public Criteria andCodigoNotEqualTo(String value) {
			addCriterion("codigo <>", value, "codigo");
			return (Criteria) this;
		}

		public Criteria andCodigoGreaterThan(String value) {
			addCriterion("codigo >", value, "codigo");
			return (Criteria) this;
		}

		public Criteria andCodigoGreaterThanOrEqualTo(String value) {
			addCriterion("codigo >=", value, "codigo");
			return (Criteria) this;
		}

		public Criteria andCodigoLessThan(String value) {
			addCriterion("codigo <", value, "codigo");
			return (Criteria) this;
		}

		public Criteria andCodigoLessThanOrEqualTo(String value) {
			addCriterion("codigo <=", value, "codigo");
			return (Criteria) this;
		}

		public Criteria andCodigoLike(String value) {
			addCriterion("codigo like", value, "codigo");
			return (Criteria) this;
		}

		public Criteria andCodigoNotLike(String value) {
			addCriterion("codigo not like", value, "codigo");
			return (Criteria) this;
		}

		public Criteria andCodigoIn(List<String> values) {
			addCriterion("codigo in", values, "codigo");
			return (Criteria) this;
		}

		public Criteria andCodigoNotIn(List<String> values) {
			addCriterion("codigo not in", values, "codigo");
			return (Criteria) this;
		}

		public Criteria andCodigoBetween(String value1, String value2) {
			addCriterion("codigo between", value1, value2, "codigo");
			return (Criteria) this;
		}

		public Criteria andCodigoNotBetween(String value1, String value2) {
			addCriterion("codigo not between", value1, value2, "codigo");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table unidad_peso
	 * @mbggenerated  Thu Apr 21 12:37:10 COT 2016
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
     * This class corresponds to the database table unidad_peso
     *
     * @mbggenerated do_not_delete_during_merge Tue Apr 12 16:26:48 COT 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}