package com.sys.volunteer.otherMes;

import java.util.List;

import com.sys.volunteer.pojo.OtherMes;

public interface OtherMesService {

	public OtherMes addOtherMes(OtherMes om);
	
	public OtherMes updateOtherMes(OtherMes om);
	
	public List<OtherMes> findAll();
	
	public List<OtherMes> findByType(int typeId);
	
	public OtherMes findById(int id);
	
	public List<OtherMes> findNewTop();
	
	public List<OtherMes> findCommonTop();
	
	public OtherMes delOtherMes(OtherMes om);
}
