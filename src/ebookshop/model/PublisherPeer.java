package ebookshop.model;

import java.util.ArrayList;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

import ebookshop.beans.Publisher;

public class PublisherPeer {
	
	//
	public static Publisher getPublisherInfo(String pName){
		Publisher publisher = new Publisher();
		String queryString = "PREFIX gr: <http://purl.org/goodrelations/v1#> "
				+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
				+ "SELECT ?sameAs ?seeAlso "
				+ "WHERE { "
				+ "?publisher owl:sameAs ?sameAs. "
				+ "?publisher rdfs:seeAlso ?seeAlso. "
				+ "?publisher gr:name ?name. "
				+ "FILTER (regex(?name, \"" + pName +"\")) "
				+ "}";

		Query query;
		QueryExecution qexec = null;
		ResultSet rs = null;
		try {
			query = QueryFactory.create(queryString);
			qexec = QueryExecutionFactory.sparqlService("http://localhost:2020/sparql", query);

			rs = qexec.execSelect();
			
    		QuerySolution binding = rs.next();
    		
    		publisher.setNamePublisher(pName);
    		publisher.setSameAs(binding.get("sameAs").toString().trim());
    		publisher.setSeeAlso(binding.get("seeAlso").toString().trim());
	    			
		} catch (Exception e) {System.out.println(e.getMessage());
		} finally{qexec.close();}
		
		return getPublisherInfoExtra(publisher);
	}
	
	// Get extra informations for publisher
	public static Publisher getPublisherInfoExtra(Publisher p){
		
		Publisher publisher = p;
		ArrayList<String> books = new ArrayList<>();
		ArrayList<String> linkBooks = new ArrayList<>();
		
		String queryString = "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/> " 
				+ "PREFIX dbpprop: <http://dbpedia.org/property/> "
				+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
				+ "SELECT ?thumbnail ?abstract ?yearfound ?founder ?headquartersname ?publications ?homepage ?bookname ?linkbook "
				+ "WHERE { "
				+ "optional {<" + publisher.getSameAs() + ">" + " dbpedia-owl:thumbnail ?thumbnail. }"
				+ "<" + publisher.getSameAs() + ">" + " dbpedia-owl:abstract ?abstract. "
				+ "FILTER (lang(?abstract)=\"en\") "
				+ "<" + publisher.getSameAs() + ">" + " foaf:homepage ?homepage. "
				
				+ "optional {<" + publisher.getSameAs() + ">" + " dbpprop:founded ?yearfound. } "
				+ "optional {<" + publisher.getSameAs() + ">" + " dbpprop:founder ?founder. } "
				+ "optional {<" + publisher.getSameAs() + ">" + " dbpprop:publications ?publications. } "
				+ "optional {<" + publisher.getSameAs() + ">" + " dbpprop:headquarters ?headquarters. "
				+ "?headquarters rdfs:label ?headquartersname. "
				+ "FILTER (lang(?headquartersname)=\"en\") } "
				
				+ "?somebook a owl:Thing. "
				+ "?somebook dbpprop:publisher " + "<" + publisher.getSameAs() + ">" + ". "
				+ "?somebook rdfs:label ?bookname. "
				+ "FILTER (lang(?bookname)=\"en\") "
				+ "?linkbook foaf:primaryTopic ?somebook. "
				+ "} "
				+ "LIMIT 10";

		Query query;
		QueryExecution qexec = null;
		ResultSet rs = null;
		int count = 0;
		try {
			query = QueryFactory.create(queryString);
			qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);

			rs = qexec.execSelect();
			
			while (rs.hasNext()) {
				
				count++;
				
	    		QuerySolution binding = rs.next();
	    		
	    		publisher.setDescription(binding.get("abstract").toString().trim());
	    		publisher.setHomepage(binding.get("homepage").toString().trim());
	    
	    		try{
		    		publisher.setFounder(binding.get("founder").toString().trim());
	    		}
	    		catch (Exception e){
	    			publisher.setFounder("---@en");
	    		}
	    		
	    		try{		    	
		    		publisher.setHeadquaterName(binding.get("headquartersname").toString().trim());
	    		}
	    		catch (Exception e){
	    			publisher.setHeadquaterName("---@en");
	    		}
	    		
	    		try{
		    		publisher.setPublication(binding.get("publications").toString().trim());
	    		}
	    		catch (Exception e){
	    			publisher.setPublication("---@en");
	    		}
	    		
	    		try{
		    		publisher.setYearFounded(binding.get("yearfound").toString().trim());
	    		}
	    		catch (Exception e){
	    			publisher.setYearFounded("---");
	    		}
	    		
	    		try{
		    		publisher.setImage(binding.get("thumbnail").toString().trim());
	    		}
	    		catch (Exception e){
	    			publisher.setImage("");
	    		}
	    		
	    		books.add(binding.get("bookname").toString().trim());
	    		publisher.setBooks(books);
	    		
	    		linkBooks.add(binding.get("linkbook").toString().trim());
	    		publisher.setLinkBooks(linkBooks);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			publisher = null;
		} finally{
			if(count == 0){
				publisher = null;
			}
			qexec.close();}
		
		return publisher;
	} 
}
