package ebookshop.model;

import java.util.ArrayList;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

import ebookshop.beans.ExtraComment;

public class ExtraCommentPeer {

	public static ArrayList<ExtraComment> getExtraComments(String keyWord, String site){
		ArrayList<ExtraComment> extraComments = new ArrayList<>();
		
		String queryString = "PREFIX rev: <http://purl.org/stuff/rev#> " 
						+	"PREFIX gr: <http://purl.org/goodrelations/v1#> " 
						+	"SELECT ?text ?point "
						+	"WHERE { "
						+	"?book rev:hasComment ?comment. "
						+	"?comment rev:text ?text. "
						+	"?comment rev:rating ?point. "
						+	"?book gr:hasEAN_UCC_13 ?isbn. "
						+	"FILTER (regex(?isbn, \"" + keyWord + "\")) "
						+ 	"}";

		Query query;
		QueryExecution qexec = null;
		ResultSet rs = null;
		int count = 0;
		try {
			query = QueryFactory.create(queryString);
			if(site.equals("1")){ // Website 1
				qexec = QueryExecutionFactory.sparqlService("http://localhost:2020/sparql", query);
			}
			else if(site.equals("2")){ // Magento
				qexec = QueryExecutionFactory.sparqlService("http://localhost:3030/sparql", query);
			}else{
				qexec = QueryExecutionFactory.sparqlService("", query);
			}

			rs = qexec.execSelect();
			
			
			while (rs.hasNext()) {
				count++;
				
				ExtraComment extraComment = new ExtraComment();
				
	    		QuerySolution binding = rs.next();
	    		
	    		extraComment.setContentComment(binding.get("text").toString().trim());
	    		
	    		if(site.equals("1")){// Website 1
	    			extraComment.setPoint(Float.parseFloat(binding.get("point").toString().trim().substring(0, 3)));
	    		}
	    		else if(site.equals("2")){// Magento
	    			extraComment.setPoint(Float.parseFloat(binding.get("point").toString().trim().substring(0, 1)));
	    		}else{
	    			extraComment.setPoint(Float.parseFloat(binding.get("point").toString().trim().substring(0, 3)));
	    		}
	    		
	    		extraComments.add(extraComment);
			}
			
		} catch (Exception e) {System.out.println(e.getMessage());
		} finally{
			if(count == 0){
				extraComments = null;
			}
			qexec.close();
		}
		
		return extraComments;
	}
	
}
