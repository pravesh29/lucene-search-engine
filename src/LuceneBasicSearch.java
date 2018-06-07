import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

import lucene.LuceneSearch;

public class LuceneBasicSearch {

	// This function needs a command line argument to proceed
	public static void main(String[] args) throws InvalidTokenOffsetsException {
		
		// Call lucene search
		LuceneSearch bs = new LuceneSearch();
		//System.out.println(args[0]);
		try {
			
			// Capture command line argument
			String querystr = args!=null && args.length>0 ? args[0] :  "amazon kindle";
			bs.search(querystr);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
