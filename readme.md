This directory contains code for basic search engine built on top of Lucene. Complete code is https://bitbucket.org/mahakg/lucene-search-engine/ 

# Objective
Make data searchable. You can find document against some queries you're interested in. The result will be the excerpt of those queries in the documents. 
# Data 
An excerpt of Amazon reviews dataset; 10000 docs. We're using a subset of ARD (Amazon Kindle reviews) to index.

# Features included: 
* Full text search (case insensitive)
* wild card search; ? , * --- Amazo? Kind*
* Highlight option1. --- get text fragments the way google searches <b>Kindle</b>
* Fuzzy search --- Kinle~1 -> search for Kindle
* Phrase Queries --- Amazon Kindle


# Index Statistics
* Size of Corpus used = ~50MB
* Size of Index = ~5.5 MB (Binary files compressed)

# Ranking
Lucene Default Ranking (Combination of tf (t in d), Idf score, some more lucene's intelligent parameters like document normalization etc)

# Technologies Used
* Lucene
* LAMP stack for web interface, Twitter bootstrap for UI, Jquery Ajax


# Directory Structure
* src/ - main code base of lucene
     1. LuceneBasic.java - Indexer of documents
     2. LuceneBasicSearch.java - Search process is written here. This file accepts a commandline argument which is a query for the search operation 
* files - corpus directory
* web.php - Web interface file
* Search.jar -  This file has all dependencies included and is a runnable jar of our code.

# Set up
* Make sure that root directory contains files/ with all the documents needed for indexing.
* Run LuceneBasic.java to index documents. This will create an index directory
* Run LuceneBasicSearch.java to search for a query in your documents.
* Redirect your apache root directory or create a virtual host where web.php file exists. This directory should have your index directory.

# WebInterface 
![Screen Shot 2016-06-29 at 03.51.36.png](https://bitbucket.org/repo/kLLkAq/images/2644792017-Screen%20Shot%202016-06-29%20at%2003.51.36.png)


# Future
* This can be a good starting point of clustering reviews with some opinion words for e.g you can find a bunch of positive reviews (given some opinion words) and some negative reviews and then further mine them.

* Ranking of records can be further improved (Distance between words, proximity based)