package ebookshop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ebookshop.model.DataManager;


	public class ShopServletController extends javax.servlet.http.HttpServlet
	implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;

	public ShopServletController() {
		super();
	}
	
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
		
		String base = "/jsp/";
		String url = "/index.jsp";
		String action = request.getParameter("action");
		
		if(action != null){
			switch(action){
			case "goHomePage":
				break;
			case "search":
				url = base + "SearchOutcome.jsp";
				break;
			case "selectCatalog":
				url = base + "SelectCatalog.jsp";
				break;
			case "bookDetails":
				url = base + "BookDetails.jsp";
				break;
			case "register":
				url = base + "Register.jsp";
				break;
			case "registerCustomer":
				url = base + "RegisterConfirmation.jsp";
				break;
			case "login":
				url = base + "Login.jsp";
				break;
			case "checkLogin":
				url = base + "LoginCheck.jsp";
				break;
			case "logout":
				url = base + "Logout.jsp";
				break;
			case "checkOut":
				url = base + "Checkout.jsp";
				break;
			case "orderConfirmation":
				url = base + "OrderConfirmation.jsp";
				break;
			case "searchAdvanced":
				url = base + "SearchOutcomeAdvanced.jsp";
				break;
			case "contact":
				url = base + "Contact.jsp";
				break;
			case "about":
				url = base + "About.jsp";
				break;
			default:
				if (action.matches("(showCart|(add|update|delete)Item)"))
		            url = base + "ShoppingCart.jsp";
				break;
			}
		}
		
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}

}
