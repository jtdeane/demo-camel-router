package ws.cogito.security;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.XPathBuilder;

/**
 * Batch Queue Routing
 * @author jeremydeane
 */
public class BatchEventRouteBuilder extends RouteBuilder {

	/**
	 * Configure Batch Event Route - Only works with XML
	 */
	public void configure() throws Exception {
		
		XPathBuilder xPathBuilder = new XPathBuilder
				("//audit-events/audit-event");

		from("activemq:batch.events").
			split(xPathBuilder).
			parallelProcessing().
			to("activemq:insurance.events");
	}
}