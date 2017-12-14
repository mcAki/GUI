package com.sys.volunteer.interceptor;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sys.volunteer.exception.SystemException;

public class ExceptionInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		try {
			result = invocation.invoke();
		} catch (DataAccessException ex) {
			ex.printStackTrace();
			throw new SystemException(ex,"数据库操作失败！");

		} catch (NullPointerException ex) {
			ex.printStackTrace();
			throw new SystemException(ex,"调用了未经初始化的对象或者是不存在的对象！");

		} catch (IOException ex) {
			ex.printStackTrace();
			throw new SystemException(ex,"IO异常！");

		} catch (ClassNotFoundException ex) {

			ex.printStackTrace();
			throw new SystemException(ex,"指定的类不存在！");

		} catch (ArithmeticException ex) {
			ex.printStackTrace();
			throw new SystemException(ex,"数学运算异常！");

		} catch (ArrayIndexOutOfBoundsException ex) {
			ex.printStackTrace();
			throw new SystemException(ex,"数组下标越界!");

		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			throw new SystemException(ex,"方法的参数错误！");

		} catch (ClassCastException ex) {
			ex.printStackTrace();
			throw new SystemException(ex,"类型强制转换错误！");

		} catch (SecurityException ex) {
			ex.printStackTrace();
			throw new SystemException(ex,"违背安全原则异常！");

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SystemException(ex,"操作数据库异常！");

		} catch (NoSuchMethodError ex) {
			ex.printStackTrace();
			throw new SystemException(ex,"方法末找到异常！");

		} catch (InternalError ex) {
			ex.printStackTrace();
			throw new SystemException(ex,"Java虚拟机发生了内部错误");

		} catch (SystemException ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new SystemException(ex,"程序内部错误，操作失败！");

		}
		
		return result;

	}

}
