package com.esri.gw.security;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//import java.util.Enumeration;
//import java.util.logging.Level;
//import java.util.logging.Logger;

import com.esri.gw.security.CACWebAdaptorFilterWrapper;

public class CACWebAdaptorFilter implements Filter{
	
	//private static final Logger log = Logger.getLogger("CACWebAdaptorFilter");

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) arg0;
		HttpServletResponse httpRes = (HttpServletResponse) arg1;
		chain.doFilter(new CACWebAdaptorFilterWrapper(httpReq), httpRes);
		//HttpServletRequest httpReq = (HttpServletRequest) arg0;
		//Enumeration<String> names = httpReq.getHeaderNames();
		//String headers = "";
		//while (names.hasMoreElements()) {
		//    String name = (String) names.nextElement();
		//    String value = httpReq.getHeader(name);
		//    headers = headers + "***" + name + "  :  "  +  value  +  "\n";
		//}
		//log.log(Level.SEVERE, headers);
		//chain.doFilter(arg0, arg1);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
}
