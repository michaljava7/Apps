package international;

import java.util.ListResourceBundle;

public class Messages_en extends ListResourceBundle{
	
	static final Object [][] contents= {
			 { "hello", "Hello!" },
		       { "now", "Now is: " },
		       { "charset", "ISO-8859-1" }
	};
	@Override
	protected Object[][] getContents() {
		
		return contents;
	
	}
}
