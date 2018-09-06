package com.campusnumerique.vehiclerental.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.campusnumerique.vehiclerental.bean.ClientBean;
import com.campusnumerique.vehiclerental.dao.ClientDAO;
import com.campusnumerique.vehiclerental.entity.Client;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private ClientDAO clientDAO = null; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
       clientDAO = new ClientDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.getRequestDispatcher("pages/login.jsp").forward(request, response);
		response.setStatus(HttpServletResponse.SC_OK);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		ClientBean clientBean = (ClientBean)session.getAttribute("client");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			Client user = clientDAO.findByEmail(email);
			
			if(user == null){
				
				String error = "Aucun utilisateur n'est enregistré!";
				request.setAttribute("error", error);
				doGet(request, response);
			}
			else if (!user.isAdmin()){
				String error = "Vous n'avez pas les droits ! ";
				request.setAttribute("error", error);
				doGet(request, response);
			}
			else{
				if(user.getMail().equals(email) && user.getPassword().equals(password)) {
					clientBean.setLogin(user.getMail());
					clientBean.setRole(user.getRole());
					RequestDispatcher req = request.getRequestDispatcher("/reservation");
					req.forward(request, response);
				} else {
					String error = "Mot de passe invalide";
					request.setAttribute("error", error);
					doGet(request, response);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		doGet(request, response);
	}

}
