/**
 * 
 */
package org.divy.common.bo.command.impl;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.command.AbstractDatabaseUpdateCommand;
import org.divy.common.bo.command.IDBCommandContext;

/**
 * @author Divyakumar
 *
 */
public class DefaultDatabaseUpdateCommand<ENTITY extends IBusinessObject<ID>, ID>
		extends
		AbstractDatabaseUpdateCommand<ENTITY, ID> {

	/**
	 * @param typeParameterClass
	 * @param context
	 */
	public DefaultDatabaseUpdateCommand(
			Class<? extends ENTITY> typeParameterClass,
			IDBCommandContext context) {
		super(typeParameterClass, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.divy.common.bo.command.AbstractDatabaseUpdateCommand#copyFields(org
	 * .divy.common.bo.IBusinessObject, org.divy.common.bo.IBusinessObject)
	 */
	@Override
	protected void copyFields(ENTITY source, ENTITY target) {
		try {
			BeanInfo fromBean = Introspector.getBeanInfo(source.getClass());
			BeanInfo toBean = Introspector.getBeanInfo(target.getClass());

			PropertyDescriptor[] toPd = toBean.getPropertyDescriptors();
			List<PropertyDescriptor> fromPd = Arrays.asList(fromBean
					.getPropertyDescriptors());

			for (PropertyDescriptor propertyDescriptor : toPd) {

				propertyDescriptor.getDisplayName();

				PropertyDescriptor pd = fromPd.get(fromPd.indexOf(propertyDescriptor));

				if (pd.getDisplayName().equals(propertyDescriptor.getDisplayName())
						&& !pd.getDisplayName().equals("class")) {

					if (propertyDescriptor.getWriteMethod() != null) {
						propertyDescriptor.getWriteMethod().invoke(target,
								pd.getReadMethod().invoke(source, new Object[]{}));
					}
				}

			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

}
