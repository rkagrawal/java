import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class slf4jtry {
	final static Logger logger = LoggerFactory.getLogger(slf4jtry.class);

	public static void main (String args []) {
		logger.trace( "This is trace " );
		logger.debug( "This is debug " );
		logger.info( "This is info " );
		logger.warn( "This is warn " );
		logger.error( "This is  errro " );
	}
}
