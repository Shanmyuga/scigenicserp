package com.sci.bpm.flow;

import javax.servlet.http.HttpServletRequest;

import org.springframework.webflow.context.servlet.FilenameFlowUrlHandler;
import org.springframework.webflow.context.servlet.FlowUrlHandler;
import org.springframework.webflow.core.collection.AttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.mvc.servlet.AbstractFlowHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class ScigenicsFlowHandler extends FilenameFlowUrlHandler {

	@Override
	public String createFlowExecutionUrl(String flowId, String flowExecutionKey, HttpServletRequest request) {
		String flowexec = super.createFlowExecutionUrl(flowId, flowExecutionKey, request);
		String pageNumber = request.getParameter("d-16544-p");
		StringBuilder url = new StringBuilder();
		url.append(flowexec);
		if(pageNumber!=null) {
			appendQueryParameter(url, "d-16544-p", pageNumber, "UTF-8");
		}
		String orderNumber = request.getParameter("d-16544-o");
		if(orderNumber!=null) {
			appendQueryParameter(url, "d-16544-o", orderNumber, "UTF-8");
		}
		String sortNumber = request.getParameter("d-16544-s");
		if(sortNumber!=null) {
			appendQueryParameter(url, "d-16544-s", sortNumber, "UTF-8");
		}
		return url.toString();
	}

	public String getFlowId(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		return arg0.getParameter("_flowId");
	}

	private void appendQueryParameter(StringBuilder url, Object key, Object value, String encodingScheme) {
		String encodedKey = encode(key, encodingScheme);
		String encodedValue = encode(value, encodingScheme);
		url.append("&");
		url.append(encodedKey).append('=').append(encodedValue);
	}

	private String encode(Object value, String encodingScheme) {
		return value != null ? urlEncode(value.toString(), encodingScheme) : "";
	}

	private String urlEncode(String value, String encodingScheme) {
		try {
			return URLEncoder.encode(value, encodingScheme);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Cannot url encode " + value);
		}
	}



}
