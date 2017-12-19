package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * MissionType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "goods", catalog = "mprs")
public class Goods implements java.io.Serializable {

	// Fields

	private Integer goodsId;
	private String goodsName;
	private GoodsType goodsType;
	private Integer goodsFlag;
	private String description;
	private Double value;
	private Integer kaCardId;
	private Integer isUsed;
	private Integer provinceCode;
	private String province;
	private Integer IsLocal;
	private Integer kaBaseNum;

	// Constructors

	@Column(name="is_local")
	public Integer getIsLocal() {
		return IsLocal;
	}

	public void setIsLocal(Integer isLocal) {
		IsLocal = isLocal;
	}

	/** default constructor */
	public Goods() {
	}

	/** full constructor */
	public Goods(String goodsName, String description) {
		this.goodsName = goodsName;
		this.description = description;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "goods_id", unique = true, nullable = false)
	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	@Column(name = "goods_name", length = 32)
	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="goods_type")
	public GoodsType getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(GoodsType goodsType) {
		this.goodsType = goodsType;
	}

	@Column(name="goods_flag" ,length=11)
	public Integer getGoodsFlag() {
		return goodsFlag;
	}

	public void setGoodsFlag(Integer goodsFlag) {
		this.goodsFlag = goodsFlag;
	}

	@Column(name="[value]")
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Column(name="ka_card_id")
	public Integer getKaCardId() {
		return kaCardId;
	}

	public void setKaCardId(Integer kaCardId) {
		this.kaCardId = kaCardId;
	}

	@Column(name="is_used")
	public Integer getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}

	@Column(name="province_code")
	public Integer getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(Integer provinceCode) {
		this.provinceCode = provinceCode;
	}

	@Column(name="province")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name="ka_base_num")
	public Integer getKaBaseNum() {
		return kaBaseNum;
	}

	public void setKaBaseNum(Integer kaBaseNum) {
		this.kaBaseNum = kaBaseNum;
	}

}