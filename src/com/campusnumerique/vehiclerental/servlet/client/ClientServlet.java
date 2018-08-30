package com.campusnumerique.vehiclerental.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.campusnumerique.vehiclerental.dao.ClientDAO;

@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ClientDAO clientDAO=null;
    /**
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public ClientServlet() throws ClassNotFoundException {
        super();
        clientDAO= new ClientDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String wAction = "";
		JSONObject responseData = new JSONObject();
	    PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		if(request.getParameter("action")!=null && !request.getParameter("action").equals("")){
			wAction=request.getParameter("action");
			if(wAction.equals("getClients")){
				
					JSONArray clients = clientDAO.findAllAsJson();
					responseData.put("clients", clients);
					response.setStatus(HttpServletResponse.SC_OK);

			}
			out.println(responseData.toString());
		}
		else{
			response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			out.println("No action given");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
