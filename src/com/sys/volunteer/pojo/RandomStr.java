package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "s_random", catalog = "mprs")
public class RandomStr {

	private Integer id;
	
	private String codestr;
//	private String code;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	@Column(name = "codestr",unique = true, nullable = false)
	public String getCodestr() {
		return codestr;
	}
	
	public void setCodestr(String codestr) {
		this.codestr = codestr;
	}
//	public String getCode() {
//		return code;
//	}
//	
//	public void setCode(String code) {
//		this.code = code;
//	}
	
	
	
	
}
