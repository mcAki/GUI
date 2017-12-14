package com.ages.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortList<T> extends ArrayList<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Comparator<T> orderIsdn;
	
	public SortList(Comparator<T> orderIsdn){
		this.orderIsdn=orderIsdn;
	}
	
	public void put(T t){
		this.add(t);
		Collections.sort(this,orderIsdn);
	}
	
	public T pop(){
		return this.remove(0);
	}

}
