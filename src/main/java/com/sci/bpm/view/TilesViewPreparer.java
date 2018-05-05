package com.sci.bpm.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class TilesViewPreparer implements ViewPreparer {

	@Override
	public void execute(Request arg0, AttributeContext arg1) {
		// TODO Auto-generated method stub
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		HttpSession  session = request.getSession();

String rolename = (String)session.getAttribute("ROLE");
		
		Attribute menu = arg1.getAttribute("menu");
		
		String path = "/WEB-INF/jsp/layout/"+rolename+"/"+ "usermenu.jsp";
		menu.setValue(path);
		
	}

	
}
