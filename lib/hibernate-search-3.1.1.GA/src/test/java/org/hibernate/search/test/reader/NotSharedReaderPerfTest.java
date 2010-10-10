//$Id: NotSharedReaderPerfTest.java 14766 2008-06-14 07:43:17Z sannegrinovero $
package org.hibernate.search.test.reader;

import org.hibernate.cfg.Configuration;
import org.hibernate.search.Environment;

/**
 * @author Emmanuel Bernard
 */
public class NotSharedReaderPerfTest extends ReaderPerfTestCase {
	protected void configure(Configuration cfg) {
		super.configure( cfg );
		cfg.setProperty( Environment.READER_STRATEGY, "not-shared" );
	}
}
