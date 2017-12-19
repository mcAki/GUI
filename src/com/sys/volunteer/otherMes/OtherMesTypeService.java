package com.sys.volunteer.otherMes;

import java.util.List;

import com.sys.volunteer.pojo.OtherMesType;

public interface OtherMesTypeService {

	public OtherMesType addOtherMesType(OtherMesType om);
	
	public OtherMesType updateOtherMesType(OtherMesType om);
	
	public List<OtherMesType> findAll();
	
	public OtherMesType findById(int id);
}
