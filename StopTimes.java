import java.sql.Time;

public class StopTimes implements Comparable {

	private String stop_id;
	private Time arrival;
	private Time departure;

	public StopTimes( String sid, Time arr, Time dep ) {
		stop_id = sid; arrival = arr; departure = dep;
	}

	public String getStopId() { return stop_id; }	
	public Time getArrival() { return arrival; }	
	public Time getDeparture() { return departure; }	

	@Override	
	public int compareTo( Object st ) {
		StopTimes s = (StopTimes ) st;
		if ( arrival ==  s.getArrival() ) return 0;
		else if ( arrival.after( s.getArrival() ) ) return 1;
		else return -1;
	}

	@Override
	public boolean equals(Object anObject ) {
		if( this == anObject ) return true;
		if( !( anObject instanceof StopTimes ) )return false;

		StopTimes aStopTime = (StopTimes) anObject;
		if( this.stop_id == aStopTime.getStopId() && this.arrival.equals(aStopTime.getArrival() ) )return true;
		
		return false;
	}


	@Override
	public int hashCode() {
		int hashcode = 17;
		hashcode = 31*hashcode + stop_id.hashCode();
		hashcode = 31*hashcode + arrival.hashCode();	

		return hashcode;
	}
	

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("Stop id:"+stop_id);
		res.append(",arrival:"+ arrival);
		res.append(",departure:"+ departure);
		res.append("\n");

		return res.toString();
	}
};
