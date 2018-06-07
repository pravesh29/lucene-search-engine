package lucene;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.TextFragment;
import org.apache.lucene.search.highlight.TokenSources;

public class LuceneSearch {

	public void search(String qs) throws IOException, ParseException, InvalidTokenOffsetsException{
		
		// Read the index
		FSDirectory index = FSDirectory.open(Paths.get("test.lucene"));

		StandardAnalyzer analyzer = new StandardAnalyzer();
		
		//Text to search
		//	The "title" arg specifies the default field to use when no field is explicitly specified in the query
		Query q = new QueryParser("title", analyzer).parse(qs);
		
		// Searching code
		int hitsPerPage = 20;
	    IndexReader reader = DirectoryReader.open(index);
	    IndexSearcher searcher = new IndexSearcher(reader);
	    TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage);
	    searcher.search(q, collector);
	    //searcher.setDefaultFieldSortScoring(true, false);
	    //searcher.set
	    //searcher.explain(qs, doc)
	    ScoreDoc[] hits = collector.topDocs().scoreDocs;

	    // Highlighter
	    SimpleHTMLFormatter htmlFormatter = new SimpleHTMLFormatter();
	    Highlighter highlighter = new Highlighter(htmlFormatter, new QueryScorer(q));
	    
	    //	Code to display the results of search
	    for(int i=0;i<hits.length;++i) 
	    {
	      int docId = hits[i].doc;
	      Document d = searcher.doc(docId);
	      
	      @SuppressWarnings("deprecation")
		  TokenStream tokenStream = TokenSources.getAnyTokenStream(searcher.getIndexReader(), docId, "title", analyzer);	      
	      TextFragment[] frag = highlighter.getBestTextFragments(tokenStream, d.get("title"), false, 10);
	      
	      // create string from document fragments
	      StringBuilder strBuilder = new StringBuilder();
	      for (int j = 0; j < frag.length; j++) {
	          if ((frag[j] != null) && (frag[j].getScore() > 0)) {
	 	         strBuilder.append(frag[j] + "...");
	          }
	        }
	      
	      String newString = strBuilder.toString();
	      
	      // return search results
          System.out.println(d.get("isbn") + "//////" + newString.trim());

	    }
	    
	    
	    // reader can only be closed when there is no need to access the documents any more
	    reader.close();
	}
	
	
}
