package ebookshop.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;

import ebookshop.beans.BookRDF;

public class BookRDFPeer {
	
	// Search book from dbpedia with keyword
	public static ArrayList<BookRDF> searchBookSW (String keyWord){
		
		ArrayList<BookRDF> books = new ArrayList<>();
		
		String queryString = "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>" +
				"PREFIX dbpprop: <http://dbpedia.org/property/>" +
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
				"SELECT ?name ?genreName ?isbn ?authorName ?abstract " +
		       "WHERE { " +
		       "?book a dbpedia-owl:Book . " +
		       
		       "?book rdfs:label ?name . " +
		       "FILTER (lang(?name)=\"en\") . " +
		       "FILTER (regex (?name, \"" + keyWord + "\", \"i\") ) . " +
		       
		       "?book dbpedia-owl:literaryGenre ?genre . " +
		       "?genre rdfs:label ?genreName . " +
		       "FILTER (lang(?genreName)=\"en\") . " +
		       
		       "?book dbpedia-owl:author ?author . " +
		       "?author rdfs:label ?authorName . " +
		       "FILTER (lang(?authorName)=\"en\") . " +
									
				"?book dbpedia-owl:abstract ?abstract . " +
				"FILTER (lang(?abstract)=\"en\") . " +
		       
		       	"?book dbpedia-owl:isbn ?isbn . " +		       
		       	"} ";

		Query query;
		QueryExecution qexec = null;
		ResultSet rs = null;
		try {
			query = QueryFactory.create(queryString);
			qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);

			rs = qexec.execSelect();
			
			while (rs.hasNext()) {
				
				BookRDF book = new BookRDF();
				
	    		QuerySolution binding = rs.next();
	    		
	    		book.setBookName(binding.get("name").toString().trim());
	    		book.setBookCategory(binding.get("genreName").toString().trim());
	    		book.setISBN(binding.get("isbn").toString().trim());
	    		book.setBookAuthor(binding.get("authorName").toString().trim());
	    		book.setBookDescription(binding.get("abstract").toString().trim());
	    		
	    		books.add(book);
			}
			
		} catch (Exception e) {System.out.println(e.getMessage());
		} finally{qexec.close();}
		
		String queryString1 = "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>" +
				"PREFIX dbpprop: <http://dbpedia.org/property/>" +
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
				"SELECT ?name ?publisherName1 ?publisherName2 " +
		       "WHERE { " +
		       "?book a dbpedia-owl:Book . " +
		       
		       "?book rdfs:label ?name . " +
		       "FILTER (lang(?name)=\"en\") . " +
		       "FILTER (regex (?name, \"" + keyWord + "\", \"i\") ) . " +
		       
		       "optional { " +
				"?book dbpedia-owl:publisher ?publisher . " +
				"?publisher rdfs:label ?publisherName1 . " +
				"FILTER (lang(?publisherName1)=\"en\") . " + " } " +
				
				"optional { " +
				"?book dbpprop:publisher ?publisherName2 . " +
				"FILTER (lang(?publisherName2)=\"en\") . " + " } " +
		       	"} ";
		
		Query query1;
		QueryExecution qexec1 = null;
		ResultSet rs1 = null;
		try {
			query1 = QueryFactory.create(queryString1);
			qexec1 = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query1);

			rs1 = qexec1.execSelect();
			
			while (rs1.hasNext()) {
				
	    		QuerySolution binding1 = rs1.next();
	    		
	    		String bookName = binding1.get("name").toString().trim();
	    		String bookPublisher1 = null;
	    		String bookPublisher2 = null;
	    		
	    		try{
	    			bookPublisher1 = binding1.get("publisherName1").toString();
	    			bookPublisher2 = binding1.get("publisherName2").toString();
	    		}
	    		catch (Exception e){
	    			if(bookPublisher1 == null){		
	    				bookPublisher1 = "@en";
	    			}
	    			if(bookPublisher2 == null){		
	    				bookPublisher2 = "@en";
	    			}
	    			Iterator<BookRDF> iterator = books.iterator();
		    		while(iterator.hasNext()){
		    			BookRDF book = iterator.next();
		    			if(bookName.equals(book.getBookName())){
		    				if(!bookPublisher1.equals("@en")){
		    					book.setBookPublisher(bookPublisher1);
		    				}
		    				if(!bookPublisher2.equals("@en")){
		    					book.setBookPublisher(bookPublisher2);
		    				}
		    				if(bookPublisher1.equals("@en") && bookPublisher2.equals("@en")){
		    					book.setBookPublisher("@en");
		    				}
		    			}
		    		}
	    			continue;
	    		}
	    		
	    		Iterator<BookRDF> iterator = books.iterator();
	    		while(iterator.hasNext()){
	    			BookRDF book = iterator.next();
	    			if(bookName.equals(book.getBookName())){
	    				book.setBookPublisher(bookPublisher2);
	    			}
	    		}
			}
		} catch (Exception e) {System.out.println(e.getMessage());
		} finally{qexec1.close();}
		
		return books;
	}

	// Create RDF Document for user to download
	public static int createRDFDocument(DataManager dataManager, String bName){
		
		String fileName = "C:/Users/Tuan Anh/Desktop/Dropbox/KLTN/Coding/Project/eBookShop/WebContent/RDFDocument/" + bName.replace(" ", "_") + ".rdf";
		int result = 0;
		
		String queryString =
				"PREFIX db: <http://localhost:2020/resource/> " +
				"PREFIX rev: <http://purl.org/stuff/rev#> " +
				"PREFIX dbpedia-owl: <http://dbpedia.org/ontology/> " +
				"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
				"PREFIX yago: <http://dbpedia.org/class/yago/> " +
				"PREFIX meta: <http://www4.wiwiss.fu-berlin.de/bizer/d2r-server/metadata#> " +
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
				"PREFIX d2r: <http://sites.wiwiss.fu-berlin.de/suhl/bizer/d2r-server/config.rdf#> " +
				"PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
				"PREFIX dbpedia: <http://dbpedia.org/resource/> " +
				"PREFIX map: <http://localhost:2020/resource/#> " +
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " +
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
				"PREFIX gr: <http://purl.org/goodrelations/v1#> " +
				"PREFIX vocab: <http://localhost:2020/resource/vocab/> " +
				"CONSTRUCT " + 
					"{ " +
					"?book rdf:type ?type. " +
					"?book gr:name ?name. " +
					"?book rdfs:label ?label. " +

					"?book dbpedia-owl:literaryGenre ?genre. " +
					"?genre gr:category ?genreLabel. " +
					"?genre rdf:type ?genreType. " +
					"?genre owl:sameAs ?genreSameAs. " +
					"?genre rdfs:seeAlso ?genreSeeAlso. " +

					"?book gr:hasBrand ?publisher. " +
					"?publisher gr:name ?publisherName. " +
					"?publisher rdf:type ?publisherType. " +
					"?publisher owl:sameAs ?publisherSameAs. " +
					"?publisher rdfs:seeAlso ?publisherSeeAlso. " +

					"?book gr:hasManufacturer ?author. " +
					"?author foaf:name ?authorName. " +
					"?author rdf:type ?authorType. " +
					"?author owl:sameAs ?authorSameAs. " +
					"?author rdfs:seeAlso ?authorSeeAlso. " +

					"?book gr:hasEAN_UCC_13 ?isbn. " +
					"?book foaf:depiction ?depiction. " +
					"?book gr:description ?description. " +
					"?book owl:sameAs ?sameAs. " +
					"?book rdfs:seeAlso ?seeAlso. " +

					"} " +

					"WHERE " +
					"{ " +
					"?book rdf:type ?type. " +
					"?book gr:name ?name. " +
					"?book rdfs:label ?label. " +
					"FILTER (regex(?label, \"" + bName  + "\")) " +
			
					"?book dbpedia-owl:literaryGenre ?genre. " +
					"?genre gr:category ?genreLabel. " +
					"?genre rdf:type ?genreType. " +
					"?genre owl:sameAs ?genreSameAs. " +
					"?genre rdfs:seeAlso ?genreSeeAlso. " +
			
					"?book gr:hasBrand ?publisher. " +
					"?publisher gr:name ?publisherName. " +
					"?publisher rdf:type ?publisherType. " +
					"?publisher owl:sameAs ?publisherSameAs. " +
					"?publisher rdfs:seeAlso ?publisherSeeAlso. " +
					 
					"?book gr:hasManufacturer ?author. " +
					"?author foaf:name ?authorName. " +
					"?author rdf:type ?authorType. " +
					"?author owl:sameAs ?authorSameAs. " +
					"?author rdfs:seeAlso ?authorSeeAlso. " +
			
					"?book gr:hasEAN_UCC_13 ?isbn. " +
					"?book foaf:depiction ?depiction. " +
					"?book gr:description ?description. " +
					"?book owl:sameAs ?sameAs. " +
					"?book rdfs:seeAlso ?seeAlso. " +
					"} ";
		
		Query query;
		QueryExecution qexec = null;
		Model model = null;
		try {
			query = QueryFactory.create(queryString);
			qexec = QueryExecutionFactory.sparqlService("http://localhost:2020/sparql", query);

			model = qexec.execConstruct();
			
			FileOutputStream fos;
			fos = new FileOutputStream(new File(fileName));
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			
			// Jena is not adding this to the XML file, when choosing UTF-8 - encoding
			osw.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
			model.write(osw, "RDF/XML-ABBREV", "");
			
			result = 1;
		} catch (Exception e) {System.out.println(e.getMessage());
		} finally{qexec.close();}
		
		return result;
	}
	
}
