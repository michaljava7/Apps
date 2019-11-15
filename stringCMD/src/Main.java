import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		   List  list = new ArrayList(); 
		   list.add("a1");
		   list.add("a2");
		   String [] nowa= (String[]) list.toArray(new String[100]);
		   
		   System.out.println(nowa[0]);
		   
	}

}
