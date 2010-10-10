// $Id: UrlBridge.java 15560 2008-11-13 15:38:44Z hardy.ferentschik $
package org.hibernate.search.bridge.builtin;

import java.net.URL;
import java.net.MalformedURLException;

import org.hibernate.search.bridge.TwoWayStringBridge;
import org.hibernate.search.SearchException;
import org.hibernate.annotations.common.util.StringHelper;

/**
 * Bridge for <code>URL</code>s.
 *
 * @author Emmanuel Bernard
 */
public class UrlBridge implements TwoWayStringBridge {
	public Object stringToObject(String stringValue) {
		if ( StringHelper.isEmpty( stringValue ) ) {
			return null;
		}
		else {
			try {
				return new URL( stringValue );
			}
			catch ( MalformedURLException e ) {
				throw new SearchException( "Unable to build URL: " + stringValue, e );
			}
		}
	}

	public String objectToString(Object object) {
		return object == null ?
				null :
				object.toString();
	}
}
