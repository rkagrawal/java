import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Iterator;
import java.io.InputStreamReader;
import java.io.DataInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.sql.Time;

public class Transit {
    private Map< String, Trip > allRoutes;
    
    public Transit() {
	allRoutes = new HashMap<String, Trip >();
    }
    public void build( String fileName ) {
	try {
	    FileInputStream fstream = new FileInputStream( fileName );
	    DataInputStream in = new DataInputStream( fstream );
	    BufferedReader br = new BufferedReader ( new InputStreamReader(in) );
	    String strLine;
	    int i=0;
	    while ((strLine = br.readLine()) != null)   {
		if ( ++i == 1 ) continue;
		ArrayList<String> arr = (ArrayList<String>)Lists.newArrayList(Splitter.on(',').split(strLine));
		String tripId = arr.get(0);
		StopTimes st = new StopTimes( arr.get(3), Time.valueOf(arr.get(1)), Time.valueOf(arr.get(2)));
		if( allRoutes.get(tripId ) == null ) 
		    allRoutes.put(tripId, new Trip(tripId) );
	
		//System.out.println("Adding " + st + " to trip " + tripId );
		allRoutes.get(tripId).addAStop(st);
	    }	
	} catch( Exception e ) {
	    e.printStackTrace();
	}
	
    }

    @Override
    public String toString() {
	StringBuilder result = new StringBuilder();
	System.out.println( "Inside toSring\n");
	Iterator it = allRoutes.entrySet().iterator();
	while(it.hasNext()) {
	    Map.Entry pairs = (Map.Entry) it.next();
	    result.append("\nTrip ID: " + pairs.getKey() );
	    result.append("---------------------------\n");
	    result.append( pairs.getValue() );
	}
	return result.toString();
    }
    
}
