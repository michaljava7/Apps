package international;
import java.util.*;

public class Messages_pl extends ListResourceBundle {
     public Object[][] getContents() {
         return contents;
     }

    static final Object[][] contents = {
       { "hello", "Dzie� dobry!" },
       { "now", "Teraz b�dzie, a w�a�ciwie ju� jest" },
       { "charset", "ISO-8859-2" }
    };
}