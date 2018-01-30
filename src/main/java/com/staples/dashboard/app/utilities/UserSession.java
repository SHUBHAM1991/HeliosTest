/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName				:	UserSession.java
 * Author				:	Staples
 * Date of Creation		:	14-FEB-2014
 * Description			:	This will maintain session for RwdMember
 * Version No			: 	1.0
 * Modification History	:	
 * Date				Version No				Who						Description
 * 
 */

package com.staples.dashboard.app.utilities;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.staples.dashboard.app.vo.RwdMember;

/**
 * The Class UserSession.
 *
 * @author KumBi002
 * @version 1.0
 *  Revision history
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 *  <p>
 * <table>
 * <tr>
 * 	<td>Version</td><td>Date</td><td>Author</td><td>Description</td>
 * </tr>
 * <tr>
 *  <td>1.0</td><td>Dec 4, 2015</td><td>KumBi002</td><td>Initial Draft</td>
 * </tr>
 * </table>
 *  </p>
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 */
public class UserSession {

	/**
	 * This method will put RwdMember object into session
	 * 
	 * @param objMember
	 */
	public static void setUser(RwdMember objMember) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();

		HttpSession session = request.getSession();
		session.setAttribute("userSession", objMember);
	}

	/**
	 * This method will return RwdMember object from session
	 * 
	 * @return RwdMember
	 */
	public static RwdMember getUser() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();

		HttpSession session = request.getSession();
		RwdMember rwdMember = (RwdMember) session.getAttribute("userSession");
		return rwdMember;
	}

	/**
	 * Method to set the session attributes.
	 * @param String objSessVarName
	 * @param Object objSessVarValue
	 */
	public static void setSessionAttr(String objSessVarName,
			Object objSessVarValue) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();

		HttpSession session = request.getSession();
		session.setAttribute(objSessVarName, objSessVarValue);

	}

	/**
	 * Method to get the session attributes.
	 * @param Sting objSessVarName
	 * @return
	 */
	public static Object getSessionAttr(String objSessVarName) {
		HttpSession session = getSession();
		Object objSessVarValue = session.getAttribute(objSessVarName);
		return objSessVarValue;
	}

	/**
	 * Method to remove session attibute.
	 * @param String objSessVarName
	 */
	public static void removeSessionAttr(String objSessVarName) {
		HttpSession session = getSession();
		session.removeAttribute(objSessVarName);

	}

	/**
	 * Method to remove all session attributes.
	 */
	public static void removeAllSessionAttr() {
		HttpSession session = getSession();
		// Could be uncommented when required
		/*
		 * Enumeration<String> objSessionAttrs = session.getAttributeNames();
		 * String strAttrName; while(objSessionAttrs.hasMoreElements()) {
		 * strAttrName = objSessionAttrs.nextElement(); if(null != strAttrName
		 * && !strAttrName.isEmpty()) { session.removeAttribute(strAttrName); }
		 * }
		 */
		session.invalidate();

	}

	/**
	 * Method to get the session.
	 * @return the session
	 */
	public static HttpSession getSession() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		return session;
	}

	/**
	 * Method to get the Http Request.
	 * @return the objHttpRequest.
	 */
	public static HttpServletRequest getHttpRequest() {
		HttpServletRequest objHttpRequest = ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getRequest();
		return objHttpRequest;
	}

	/**
	 * Method to get the request url.
	 * @return the String.
	 */
	public static String getRequestURL() {
		HttpServletRequest objHttpRequest = getHttpRequest();
		return objHttpRequest.getRequestURL().toString();
	}

	/**
	 * Method to return the request parameter. 
	 * @param String paramName
	 * @return the String
	 */
	public static String getReqParameter(String paramName) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request.getParameter(paramName);
	}

	/**
	 * Method to return the context path.
	 * @return the String
	 */
	public static String getContextPath() {
		HttpServletRequest objHttpRequest = ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getRequest();
		return objHttpRequest.getContextPath();
	}

}
