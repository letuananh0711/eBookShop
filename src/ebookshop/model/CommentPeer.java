package ebookshop.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ebookshop.beans.Comment;

public class CommentPeer {

	public static ArrayList<Comment> getAllComments(DataManager dataManager){
		
		ArrayList<Comment> listComment = new ArrayList<>();
		
		Connection connection = dataManager.getConnection();
		
		if(connection != null){
			try {
				Statement s = connection.createStatement();
				String query = "select * from Commentaire";
				try{
					ResultSet rs = s.executeQuery(query);
					try{
						while(rs.next()){
							Comment comment = new Comment();
							comment.setIdCommentaire(rs.getInt(1));
							comment.setPersonneCommentaire(rs.getString(2));
							comment.setEmailCommentaire(rs.getString(3));
							comment.setContenu(rs.getString(4));
							comment.setEtatConmmentaire(rs.getInt(5));
							comment.setPoint(rs.getFloat(6));
							comment.setIdLivreCommentaire(rs.getInt(7));
							listComment.add(comment);
						}
					}finally {rs.close();}
				}
				finally{s.close();}
			} catch (SQLException e) {
				System.out.println("Could not get comment list: " + e.getMessage());
			}finally{
				dataManager.putConnection(connection);
			}
		}
		
		return listComment;
	}

	public static void insertComment(Statement s, Comment c) throws SQLException {
		
		String query = "insert into Commentaire (personneCommentaire, emailCommentaire, contenu, etatCommentaire, point, idLivre) " +
				"values ('" + c.getPersonneCommentaire().replace("'", "\\'") + "', '" +
						c.getEmailCommentaire() + "', '" +
								c.getContenu().replace("'", "\\'") + "', 1, " +
												c.getPoint() + ", " +
														c.getIdLivreCommentaire() + ")";
		s.executeUpdate(query);
	}
	
	public static ArrayList<Comment> getValidComments(DataManager dataManager, int bookId){
		ArrayList<Comment> comments = new ArrayList<>();
		
		Connection connection = dataManager.getConnection();
		
		if(connection != null){
			try {
				Statement s = connection.createStatement();
				String query = "select * from Commentaire where idLivre = " + bookId + " and etatCommentaire = 1";
				try{
					ResultSet rs = s.executeQuery(query);
					try{
						while(rs.next()){
							Comment comment = new Comment();
							comment.setIdCommentaire(rs.getInt(1));
							comment.setPersonneCommentaire(rs.getString(2));
							comment.setEmailCommentaire(rs.getString(3));
							comment.setContenu(rs.getString(4));
							comment.setEtatConmmentaire(rs.getInt(5));
							comment.setPoint(rs.getFloat(6));
							comment.setIdLivreCommentaire(rs.getInt(7));
							comments.add(comment);
						}
					}finally {rs.close();}
				}
				finally{s.close();}
			} catch (SQLException e) {
				System.out.println("Could not get comment list: " + e.getMessage());
			}finally{
				dataManager.putConnection(connection);
			}
		}
		return comments;
	}
	
	public static float getBookAvgRate(DataManager dataManager, int bookId){
		float avgRate = 0;
		
		Connection connection = dataManager.getConnection();
		
		if(connection != null){
			try {
				Statement s = connection.createStatement();
				String query = "select avg(point) from Commentaire where etatCommentaire = 1 and idLivre = " + bookId;
				try{
					ResultSet rs = s.executeQuery(query);
					try{
						while(rs.next()){
							avgRate = rs.getFloat(1);
						}
					}finally {rs.close();}
				}
				finally{s.close();}
			} catch (SQLException e) {
				System.out.println("Could not get average point: " + e.getMessage());
			}finally{
				dataManager.putConnection(connection);
			}
		}
		return avgRate;
	}
	
	public static int getNumCommentByBookId(DataManager dataManager, int bookId){
		int num = 0;
		Connection connection = dataManager.getConnection();
		
		if(connection != null){
			try {
				Statement s = connection.createStatement();
				String query = "select count(idCommentaire) from Commentaire where etatCommentaire = 1 and idLivre = " + bookId;
				try{
					ResultSet rs = s.executeQuery(query);
					try{
						while(rs.next()){
							num = rs.getInt(1);
						}
					}finally {rs.close();}
				}
				finally{s.close();}
			} catch (SQLException e) {
				System.out.println("Could not get number of comment: " + e.getMessage());
			}finally{
				dataManager.putConnection(connection);
			}
		}
		return num;
	}
}
