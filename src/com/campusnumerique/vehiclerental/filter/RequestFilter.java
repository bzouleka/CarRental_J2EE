package com.campusnumerique.vehiclerental.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import com.campusnumerique.vehiclerental.bean.ClientBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpSession session = ((HttpServletRequest) request).getSession();
		ClientBean clientBean = (ClientBean)session.getAttribute("client");
		
		
		if(clientBean.getRole().equals("ROLE_ADMIN")){
			filterChain.doFilter(request, response);
		}else {
			
			 RequestDispatcher requestDispatcher = 
			            request.getRequestDispatcher("/reservation") ;
			         requestDispatcher.forward(request, response) ;
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
