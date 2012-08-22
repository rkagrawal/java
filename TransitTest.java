public class TransitTest {

    public static void main( String args [] ) {

	Transit t = new Transit();
	t.build("stop_times.txt");
	System.out.println(t);
    }
}