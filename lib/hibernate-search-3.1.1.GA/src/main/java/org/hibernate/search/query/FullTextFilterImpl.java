// $Id: FullTextFilterImpl.java 15541 2008-11-10 20:14:05Z hardy.ferentschik $
package org.hibernate.search.query;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.search.FullTextFilter;

/**
 * @author Emmanuel Bernard
 */
public class FullTextFilterImpl implements FullTextFilter {
	private final Map<String, Object> parameters = new HashMap<String, Object>();
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public FullTextFilter setParameter(String name, Object value) {
		parameters.put( name, value );
		return this;
	}

	public Object getParameter(String name) {
		return parameters.get( name );
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}
}
