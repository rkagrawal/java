import java.sql.Time;

public class Trips implements Comparable<Trips>{
	private int id;
	private Time arrival;
	private Time departure;
	private int stop;

	public Trips( int i, Time a, Time d, int s ) {
		id = i; arrival = a; departure = d; stop = s;
	}

	public int compareTo( Trips t ) {
		return arrival.compareTo(t.getArrival());
	}

	Time getArrival() { return arrival; }
	Time getDeparture() { return departure; }
	int getId() { return id; }
	int getStop() { return stop; }
}
