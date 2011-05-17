// $Id: Being.java 15592 2008-11-19 14:17:48Z hardy.ferentschik $
package org.hibernate.search.test.inheritance;

import javax.persistence.MappedSuperclass;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.test.bridge.PaddedIntegerBridge;

/**
 * @author Emmanuel Bernard
 */
@MappedSuperclass
public class Being {
	@Field(index = Index.UN_TOKENIZED)
	@FieldBridge(impl = PaddedIntegerBridge.class)
	private int weight;

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
