package ebookshop.model;

import java.util.ArrayList;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

import ebookshop.beans.Author;

public class AuthorPeer {
	
	//
	public static Author getAuthorInfo(String aName){
		
		Author author = new Author();
		
		String queryString = "PREFIX foaf: <http://xmlns.com/foaf/0.1/> "
							+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
							+ "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
							+ "SELECT ?sameAs ?seeAlso "
							+ "WHERE { "
							+ "?author owl:sameAs ?sameAs. "
							+ "?author rdfs:seeAlso ?seeAlso. "
							+ "?author foaf:name ?name. "
							+ "FILTER (regex(?name, \"" + aName +"\")) "
							+ "}";

		Query query;
		QueryExecution qexec = null;
		ResultSet rs = null;
		try {
			query = QueryFactory.create(queryString);
			qexec = QueryExecutionFactory.sparqlService("http://localhost:2020/sparql", query);

			rs = qexec.execSelect();
			
    		QuerySolution binding = rs.next();
    		
    		author.setNameAuthor(aName);
    		author.setSameAs(binding.get("sameAs").toString().trim());
    		author.setSeeAlso(binding.get("seeAlso").toString().trim());
	    		
			
		} catch (Exception e) {System.out.println(e.getMessage());
		} finally{qexec.close();}
		
		
		return getAuthorInfoExtra(author);
	}
	
	// Get extra informations for author
	public static Author getAuthorInfoExtra(Author a){
		
		Author author = a;
		ArrayList<String> books = new ArrayList<>();
		ArrayList<String> linkBooks = new ArrayList<>();
		
		String queryString = "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/> " 
				+ "PREFIX dbpprop: <http://dbpedia.org/property/> "
				+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
				+ "SELECT ?birthname ?thumbnail ?abstract ?birthday ?cityname ?countryname ?homepage ?bookname ?linkbook "
				+ "WHERE { "
				+ "<" + author.getSameAs() + ">" + " a dbpedia-owl:Writer. "
				+ "optional { <" + author.getSameAs() + ">" + " dbpedia-owl:birthName ?birthname.}"
				+ "<" + author.getSameAs() + ">" + " dbpedia-owl:thumbnail ?thumbnail. "
				+ "<" + author.getSameAs() + ">" + " dbpedia-owl:abstract ?abstract. "
				+ "FILTER (lang(?abstract)=\"en\") "
				+ "<" + author.getSameAs() + ">" + " dbpedia-owl:birthDate ?birthday. "
				+ "<" + author.getSameAs() + ">" + " dbpedia-owl:birthPlace ?birthplace. "
				+ "optional { ?birthplace a dbpedia-owl:City. }"
				+ "?birthplace rdfs:label ?cityname. "
				+ "FILTER (lang(?cityname)=\"en\") "
				+ "?birthplace dbpedia-owl:country ?country. "
				+ "?country rdfs:label ?countryname. "
				+ "FILTER (lang(?countryname)=\"en\") "
				+ "optional {<" + author.getSameAs() + ">" + " foaf:homepage ?homepage. }"
				 
				+ "?somebook a owl:Thing. "
				+ "?somebook dbpprop:author " + "<" + author.getSameAs() + ">" + ". "
				+ "?somebook rdfs:label ?bookname. "
				+ "FILTER (lang(?bookname)=\"en\") "
				+ "?linkbook foaf:primaryTopic ?somebook. "
				+ "}";

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
	    		
	    		author.setBirthDate(binding.get("birthday").toString().trim());
	    		try{
	    			author.setBirthName(binding.get("birthname").toString().trim());
	    		}
	    		catch (Exception e){
	    			author.setBirthName("---@en");
	    		}
	    		
	    		try{
	    			author.setHomePage(binding.get("homepage").toString().trim());
	    		}
	    		catch (Exception e){
	    			author.setHomePage("");	
	    		}
	    		
	    		author.setBirthCity(binding.get("cityname").toString().trim());
	    		author.setBirthCountry(binding.get("countryname").toString().trim());
	    		author.setDescription(binding.get("abstract").toString().trim());
	    		
	    		author.setImage(binding.get("thumbnail").toString().trim());
	    		
	    		books.add(binding.get("bookname").toString().trim());
	    		author.setBooks(books);
	    		
	    		linkBooks.add(binding.get("linkbook").toString().trim());
	    		author.setLinkBooks(linkBooks);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			author = null;
		} finally{
			if(count == 0){
				author = null;
			}
			qexec.close();}
		
		return author;
	} 
	
}
