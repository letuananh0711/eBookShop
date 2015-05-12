package ebookshop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ebookshop.model.DataManager;

public class ShopAdminServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ShopAdminServletController() {
        super();}


    public void init(ServletConfig config) throws ServletException {
		System.out.println("Initiating controller servlet!");
		super.init(config);
		
		DataManager dataManager = new DataManager();
		dataManager.setDbURL(config.getInitParameter("dbURL"));
		dataManager.setDbUserName(config.getInitParameter("dbUserName"));
		dataManager.setDbPassword(config.getInitParameter("dbPassword"));

		ServletContext context = config.getServletContext();
		context.setAttribute("base", config.getInitParameter("base"));
		context.setAttribute("imageURL", config.getInitParameter("imageURL"));
		context.setAttribute("dataManager", dataManager);
		
		// load jdbc driver
		try {
			Class.forName(config.getInitParameter("jdbcDriver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String base = "/jsp-admin/";
		String url = "/jsp-admin/AdminLogin.jsp";
		String action = request.getParameter("action");
		
		if(action != null){
			switch(action){
			case "checkAdminLogin":
				url = base + "CheckAdminLogin.jsp";
				break;
			case "adminInterface":
				url = base + "Administration.jsp";
				break;
			case "userTab":
				url = base + "UserTab.jsp";
				break;
			case "bookTab":
				url = base + "BookTab.jsp";
				break;
			case "orderTab":
				url = base + "OrderTab.jsp";
				break;
			case "commentTab":
				url = base + "CommentTab.jsp";
				break;
			case "categoryTab":
				url = base + "CategoryTab.jsp";
				break;
			case "searchBook":
				url = base + "AdminSearchBook.jsp";
				break;
			case "newBookRDF":
				url = base + "AddNewBookFromDBPedia.jsp";
				break;
			case "createBook":
				url = base + "AddNewBookConfirmation.jsp";
				break;
			default:
				break;
			}
		}
		
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}

}
