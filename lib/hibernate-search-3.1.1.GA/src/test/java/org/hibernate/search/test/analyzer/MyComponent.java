//$Id: MyComponent.java 14954 2008-07-17 20:43:10Z sannegrinovero $
package org.hibernate.search.test.analyzer;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Analyzer;

/**
 * @author Emmanuel Bernard
 */
public class MyComponent {
	@Field(index = Index.TOKENIZED)
	@Analyzer(impl = Test4Analyzer.class)
	private String componentProperty;

	public String getComponentProperty() {
		return componentProperty;
	}

	public void setComponentProperty(String componentProperty) {
		this.componentProperty = componentProperty;
	}
}
