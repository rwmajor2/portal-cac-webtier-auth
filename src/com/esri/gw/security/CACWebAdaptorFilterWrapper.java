package com.esri.gw.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CACWebAdaptorFilterWrapper extends HttpServletRequestWrapper{
	
	private static final Logger log = Logger.getLogger("CACWebAdaptorFilterWrapper");
	
	public CACWebAdaptorFilterWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getRemoteUser() {
		HttpServletRequest req = (HttpServletRequest) super.getRequest();
		String dn = req.getHeader("SSL_CLIENT_S_DN");
		String cn = getValByAttributeTypeFromIssuerDN(dn,"CN=");
		cn = cn.replace(" ", "_");
		if (cn.length() > 26)
		{
			cn = cn.substring(0, 26);
		}
		String username = cn;
		log.log(Level.INFO, "**** CAC Filter:  " + cn);
		return username;
	}
	
	private String getValByAttributeTypeFromIssuerDN(String dn, String attributeType)
	{
	    String[] dnSplits = dn.split(","); 
	    for (String dnSplit : dnSplits) 
	    {
	        if (dnSplit.contains(attributeType)) 
	        {
	            String[] cnSplits = dnSplit.trim().split("=");
	            if(cnSplits[1]!= null)
	            {
	                return cnSplits[1].trim();
	            }
	        }
	    }
	    return "";
	}
}
