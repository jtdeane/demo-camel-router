package ws.cogito.security;

import org.apache.camel.builder.RouteBuilder;

/**
 * Insurance Queue Routing
 * @author jeremydeane
 */
public class InsuranceEventRouteBuilder extends RouteBuilder {

	/**
	 * Configure Insurance Event Route
	 */
    public void configure() {
    	
    	/* Only works with XML - use Simple Language instead
    	from("activemq:insurance.events").choice()
    	  .when().xpath("/audit-event/application = 'Claims'").to("activemq:audit.events")
    	  .otherwise().to("activemq:billing.events");*/    	

    	//Accomodate JSON and XML payloads
    	from("activemq:insurance.events").choice().when().
    		simple("${in.body} contains 'Claims'").to("activemq:audit.events")
    		.otherwise().to("activemq:billing.events");
    }
}