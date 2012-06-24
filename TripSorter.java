import java.util.Comparator;

public class TripSorter implements Comparator<Trips> {
    @Override
    public int compare( Trips t1, Trips t2 ) {
	return t1.getArrival().compareTo(t2.getArrival());
    }
}