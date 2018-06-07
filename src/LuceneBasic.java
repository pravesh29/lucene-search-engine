import lucene.*;

class LuceneBasic 
{
	public static void main(String[] args)
	{
		// Index Docs
		LuceneIndexer index = new LuceneIndexer();
		index.indexDocs();
	}
	
}