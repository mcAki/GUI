package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "goods_type", catalog = "mprs")
public class GoodsType {

	private Integer goodsTypeId;
	private String goodsTypeName;
	private Integer goodsFlag;
	private String description;
	private String values;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "goods_type_id", unique = true, nullable = false)
	public Integer getGoodsTypeId() {
		return goodsTypeId;
	}
	
	public void setGoodsTypeId(Integer goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}
	
	@Column(name = "goods_type_name", length = 32)
	public String getGoodsTypeName() {
		return goodsTypeName;
	}
	
	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}
	
	@Column(name="goods_flag" ,length=11)
	public Integer getGoodsFlag() {
		return goodsFlag;
	}

	public void setGoodsFlag(Integer goodsFlag) {
		this.goodsFlag = goodsFlag;
	}
	
	@Column(name = "description", length = 200)
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "[values]", length = 200)
	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	
	
	
}
