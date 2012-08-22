import java.util.Set;
import java.util.TreeSet;

public class Trip  {
	private String trip_id;
	Set<StopTimes> stop_times;

	public Trip( String id ) {
		trip_id = id; 
		stop_times = new TreeSet<StopTimes>();
	}

	public void addAStop( StopTimes st ) {
		stop_times.add(st);
	}

	public String getTripId() { return trip_id; }
	

	@Override
	public String toString() {
		StringBuilder  result = new StringBuilder();
		result.append( "Trip Id:" + trip_id );
		result.append( stop_times.toString() );
		return result.toString();
	}
};
