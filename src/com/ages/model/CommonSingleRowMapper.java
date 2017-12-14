package com.ages.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

@SuppressWarnings("unchecked")
public class CommonSingleRowMapper<T> implements ResultSetHandler {

	private Class<?> model;

	public CommonSingleRowMapper(Class<?> model) {
		this.model = model;

	}

	@Override
	public Object handle(ResultSet rs) throws SQLException {
		// if (!rs.next()) {
		// return null;
		// }
		List list = new ArrayList();
		Object paramValue = null;
		while (rs.next()) {

			try {
				if (rs.getString(1)!=null) {
					if (model == String.class) {
						paramValue = rs.getString(1);
						if (paramValue != null) {
							paramValue = ((String) paramValue).trim();
						} else if (paramValue == null) {
							paramValue = "";
						}
					} else if (model == short.class || model == Short.class) {
						paramValue = new Short(rs.getShort(1));
					} else if (model == long.class || model == Long.class) {
						paramValue = new Long(rs.getLong(1));
					} else if (model == int.class || model == Integer.class) {
						paramValue = new Integer(rs.getInt(1));
					} else if (model == float.class || model == Float.class) {
						paramValue = new Float(rs.getFloat(1));
					} else if (model == double.class || model == Double.class) {
						paramValue = new Double(rs.getDouble(1));
					} else if (model == java.util.Date.class) {
						String tmp = rs.getDate(1).toString() + " "
								+ rs.getTime(1).toString();
						if (tmp.trim() != "") {
							SimpleDateFormat formatter = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");
							paramValue = formatter.parse(tmp);
						} else {
							paramValue = "";
						}
					} else if (model == java.sql.Date.class) {
						String tmp = rs.getDate(1).toString() + " "
								+ rs.getTime(1).toString();
						if (tmp.trim() != "") {
							SimpleDateFormat formatter = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");
							paramValue = new java.sql.Date(formatter.parse(tmp)
									.getTime());
						} else {
							paramValue = "";
						}
					} else if (model == java.sql.Timestamp.class) {
						String tmp = rs.getTimestamp(1).toString() + " "
								+ rs.getTime(1).toString();
						if (tmp.trim() != "") {
							SimpleDateFormat formatter = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");
							paramValue = new java.sql.Timestamp(formatter
									.parse(tmp).getTime());
						} else {
							paramValue = "";
						}
					} else {
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			list.add(paramValue);
		}
		return list;
	}

}