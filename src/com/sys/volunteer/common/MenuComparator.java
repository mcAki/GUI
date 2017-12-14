package com.sys.volunteer.common;

import java.util.Comparator;

import com.ages.model.Menutree;

public class MenuComparator implements Comparator<Menutree> {

	@Override
	public int compare(Menutree o1, Menutree o2) {
		
		return o1.getOrderNo().compareTo(o2.getOrderNo());
	}

}
