package org.ares.app.common.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;

public class BeanCopy {
	private static final PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();

	/**
	 * @param sour
	 * @param dest
	 */
	public static void copyProperties(Object sour, Object dest) {
		copyProperties(sour, dest, false);
	}

	/**
	 * 
	 * @param sour
	 * @param dest
	 * @param nullOver
	 * if (nullOver) overwrite 2007-02-21 13:40 ly
	 */
	public static void copyProperties(Object sour, Object dest, boolean nullOver) {
		PropertyDescriptor sourDescriptors[];
		if (dest == null)
			throw new IllegalArgumentException("No destination bean specified");
		if (sour == null)
			throw new IllegalArgumentException("No sourin bean specified");
		sourDescriptors = propertyUtilsBean.getPropertyDescriptors(sour);

		for (int i = 0; i < sourDescriptors.length; i++) {
			String name;
			name = sourDescriptors[i].getName();
			if (!"class".equals(name) && propertyUtilsBean.isReadable(sour, name)
					&& propertyUtilsBean.isWriteable(dest, name)) {
				{
					try {
						Object value = propertyUtilsBean.getSimpleProperty(sour, name);
						if (value != null) {
							if ("".equals(value)) {
								value = null;
							}
						}
						if (value != null) {
							BeanUtils.copyProperty(dest, name, value);
						} else {
							if (nullOver)
								propertyUtilsBean.setSimpleProperty(dest, name, null);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param request
	 * @param bean
	 */
	public static void fillBean(HttpServletRequest request, Object bean) {
		PropertyDescriptor beanDescriptors[];
		if (bean == null)
			throw new IllegalArgumentException("dest bean is null!");
		if (request == null)
			throw new IllegalArgumentException("request is null!");
		beanDescriptors = propertyUtilsBean.getPropertyDescriptors(bean);

		for (int i = 0; i < beanDescriptors.length; i++) {
			String name;
			name = beanDescriptors[i].getName();
			if (!"class".equals(name) && propertyUtilsBean.isWriteable(bean, name)) {
				{
					try {
						Object value = request.getParameter(name);
						if (value != null) {
							if ("".equals(value)) {
								value = null;
							}
						}

						if (value != null)
							BeanUtils.copyProperty(bean, name, value);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param bean
	 * @param property
	 * @return
	 */
	public static Object getProperty(Object bean, String property) {
		if (bean == null)
			throw new IllegalArgumentException("source bean is null!");
		Object value = null;
		try {
			if (!propertyUtilsBean.isReadable(bean, property)) {
				Field f = findField(bean, property);
				if (f != null) {
					f.setAccessible(true);
					value = f.get(bean);
				}
			} else
				value = propertyUtilsBean.getSimpleProperty(bean, property);
		} catch (Exception e) {
			e.printStackTrace();
			value = null;
		}
		return value;
	}

	/**
	 * 
	 * @param bean
	 * @param property
	 * @param value
	 */
	public static void setProperty(Object bean, String property, Object value) {
		if (bean == null)
			throw new IllegalArgumentException("dest bean is null!");
		try {
			if (!propertyUtilsBean.isWriteable(bean, property)) {
				Field f = findField(bean, property);
				if (f != null) {
					f.setAccessible(true);
					f.set(bean, value);
				}
			} else
				propertyUtilsBean.setSimpleProperty(bean, property, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param bean
	 * @param property
	 * @param value
	 */
	public static void copySpecProperty(Object bean, String property, Object value) {
		copySpecProperty(bean, property, value, false);
	}

	/**
	 * @param bean
	 * @param property
	 * @param value
	 * @param nullOver
	 */
	public static void copySpecProperty(Object bean, String property, Object value, boolean nullOver) {
		if (bean == null)
			throw new IllegalArgumentException("dest bean is null!");
		if (value != null && "".equals(value))
			value = null;
		if (value == null && !nullOver)
			return;
		try {
			if (propertyUtilsBean.isWriteable(bean, property)) {
				BeanUtils.copyProperty(bean, property, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Field findField(Object bean, String property) {
		for (Class<?> superClass = bean.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(property);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

}
