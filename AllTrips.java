import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.ImmutableSortedSet.Builder;
import com.google.common.collect.Iterables;
import com.google.common.base.Predicate;
import java.io.InputStreamReader;
import java.io.DataInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;
import java.lang.Iterable;
import java.util.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.TreeSet;



public class AllTrips {

	public static void main(String argv[] ) {
	    try{
		FileInputStream fstream = new FileInputStream("stop_times.txt");
  		// Get the object of DataInputStream
  		DataInputStream in = new DataInputStream(fstream);
  		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;

		TripSorter ts = new TripSorter();
		ImmutableSortedSet.Builder<Trips> builder = new ImmutableSortedSet.Builder<Trips>(ts);

		
		//Read File Line By Line
		int i=0;
		while ((strLine = br.readLine()) != null)   {
		    if ( ++i == 1 ) continue;
		    ArrayList<String> arr = (ArrayList<String>)Lists.newArrayList(Splitter.on(',').split(strLine));
		    try {
			Trips t = new Trips( Integer.parseInt(arr.get(0) ), 
					     Time.valueOf(arr.get(1) ), 
					     Time.valueOf( arr.get(2)), 
					     Integer.parseInt(arr.get(3)) );
			builder.add(t);
		    } catch( Exception e ) {
			e.printStackTrace();
		    }
		
		}

		System.out.println("There are total of " + i + " elements" );
		ImmutableSortedSet<Trips> tripSet =  builder.build();
		
		final Time now = new Time( (new Date()).getTime() );
		
		System.out.println ("the time now is " + now );

		Predicate<Trips> laterThan = new Predicate<Trips>() {
		    public boolean apply(Trips t ) {
			System.out.println ("The T time is " + t.getArrival() + " and now is " + now );
			System.out.println ("... returning " + t.getArrival().after(now) );
			return t.getArrival().after(now);
		    }
		};
		
		TreeSet<Trips> laterTrips = Sets.newTreeSet(Iterables.filter( tripSet, laterThan ) );

		System.out.println("The full set contains " + tripSet.size() );
		System.out.println("The filtered set contains " + laterTrips.size() );
	    }catch(Exception e) {
		System.out.println("Exception...");
		e.printStackTrace();
	    }
	}
	

}
