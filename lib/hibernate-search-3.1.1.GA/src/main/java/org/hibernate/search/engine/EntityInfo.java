//$Id: EntityInfo.java 14713 2008-05-29 15:18:15Z sannegrinovero $
package org.hibernate.search.engine;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Emmanuel Bernard
 */
//TODO Move to egine?
public class EntityInfo {
	
	public final Class clazz;
	public final Serializable id;
	public final Object[] projection;
	public final List<Integer> indexesOfThis = new LinkedList<Integer>();
	
	public EntityInfo(Class clazz, Serializable id, Object[] projection) {
		this.clazz = clazz;
		this.id = id;
		this.projection = projection;
	}
	
}
