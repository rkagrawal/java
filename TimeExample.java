import java.sql.Time;
import java.util.Date;

public class TimeExample {
    public static void main ( String[] argv ) {
	Date now = new Date();
	Time t = new Time( now.getTime() );
	System.out.println ( "The time now is " + t );
    }
}