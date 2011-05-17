// $Id: Discriminator.java 16554 2009-05-13 12:38:19Z hardy.ferentschik $
package org.hibernate.search.analyzer;

/**
 * Allows to choose a by name defines analyzer at runtime.
 *
 * @author Hardy Ferentschik
 */
public interface Discriminator {

	/**
	 * Allows to specify the analyzer to be used for the given field based on the specified entity state.
	 *
	 * @param value The value of the field the <code>@AnalyzerDiscriminator</code> annotation was placed on. <code>null</code>
	 * if the annotation was placed on class level.
	 * @param entity The entity to be indexed.
	 * @param field The document field.
	 * @return The name of a defined analyzer to be used for the specified <code>field</code> or <code>null</code> if the
	 * default analyzer for this field should be used.
	 * @see org.hibernate.search.annotations.AnalyzerDef
	 */
	String getAnanyzerDefinitionName(Object value, Object entity, String field);
}
