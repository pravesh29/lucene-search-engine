package lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

public class LuceneIndexer {
	
	public void indexDocs(){
		try
		{
			//	Specify the analyzer for tokenizing text.
		    //	The same analyzer should be used for indexing and searching

			StandardAnalyzer analyzer = new StandardAnalyzer();
			
			//	Code to create the index
			FSDirectory index = FSDirectory.open(Paths.get("test.lucene"));
			
			IndexWriterConfig config = new IndexWriterConfig(analyzer);
			
			IndexWriter w = new IndexWriter(index, config);
			
			// Scan the files from files directory 
			File folder = new File("files/");
			File[] listOfFiles = folder.listFiles();

			for (File file : listOfFiles) {
			    if (file.isFile()) {
			        FileInputStream fis = new FileInputStream("files/" + file.getName());
					BufferedReader br = new BufferedReader(new InputStreamReader(fis));
					
					String strLine, docContent = "";
					//Read File Line By Line and save all the content in one string
					while ((strLine = br.readLine()) != null)   {
						docContent += strLine;
					}
					
					// Index this document
					addDoc(w, docContent, file.getName().replace(".txt", ""));

					br.close();
			    }
			}
			
//			addDoc(w, "Search me baby träumen mögen", "1");
//			addDoc(w, "computerlinguistil", "2");
//			addDoc(w, "Hell of an adventure", "3");
//			addDoc(w, "You're a bitch", "4");
//			addDoc(w, "Kutta Kamina", "5");
//			addDoc(w, "Kamine kutte", "6");
			System.out.println("Indexing job complete");
			w.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private static void addDoc(IndexWriter w, String title, String isbn) throws IOException 
	{
		  Document doc = new Document();
		  // A text field will be tokenized
		  doc.add(new TextField("title", title, Field.Store.YES));
		  // We use a string field for isbn because we don\'t want it tokenized
		  doc.add(new StringField("isbn", isbn, Field.Store.YES));
		  w.addDocument(doc);
	}

}
