/**
 * 
 */
package org.divy.common.bo.command.impl;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.command.AbstractDatabaseUpdateCommand;
import org.divy.common.bo.command.IDBCommandContext;
import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.impl.DefaultBOMapper;

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
		
		mapper = new DefaultBOMapper(typeParameterClass,typeParameterClass);
	}
	
	IBOMapper<ENTITY,ENTITY> mapper;
	
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.divy.common.bo.command.AbstractDatabaseUpdateCommand#copyFields(org
	 * .divy.common.bo.IBusinessObject, org.divy.common.bo.IBusinessObject)
	 */
	@Override
	protected void copyFields(ENTITY source, ENTITY target) {
		mapper.mapToBO(source, target);
	}
	
//	@Override
//	protected void copyFields(ENTITY source, ENTITY target) {
//		try {
//			BeanInfo fromBean = Introspector.getBeanInfo(source.getClass());
//			BeanInfo toBean = Introspector.getBeanInfo(target.getClass());
//
//			PropertyDescriptor[] toPd = toBean.getPropertyDescriptors();
//			List<PropertyDescriptor> fromPd = Arrays.asList(fromBean
//					.getPropertyDescriptors());
//
//			for (PropertyDescriptor propertyDescriptor : toPd) {
//
//				propertyDescriptor.getDisplayName();
//
//				PropertyDescriptor pd = fromPd.get(fromPd.indexOf(propertyDescriptor));
//
//				if (pd.getDisplayName().equals(propertyDescriptor.getDisplayName())
//						&& !pd.getDisplayName().equals("class")) {
//
//					if (propertyDescriptor.getWriteMethod() != null) {
//						propertyDescriptor.getWriteMethod().invoke(target,
//								pd.getReadMethod().invoke(source, new Object[]{}));
//					}
//				}
//
//			}
//		} catch (IntrospectionException e) {
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		}
//
//	}

}
