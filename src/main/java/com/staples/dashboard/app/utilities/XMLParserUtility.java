/** 
 * Copyright 2012, Staples, Inc 
 * All Rights Reserved
 * FileName                     :       XMLParserUtility.java
 * Author                       :       Staples
 * Date of Creation             :       02/07/2014
 * Description                  :       This class is a parser utility which will marshal the xml string 
 * 										and returns the corresponding java object.
 * Version No                   :       1.0
 * Modification History 		:       
 * Date      	Version No	Who                 	Description
 * 02/07/2014	1.0			Balamurugan Sriraman	Draft Version					
 */

package com.staples.dashboard.app.utilities;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * The Class XMLParserUtility.
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
public class XMLParserUtility {

	/**
	 * Method o parse the xml.
	 * @param xmlName
	 * @param mappingObject
	 * @return
	 */
	public Object xmlParser(String xmlName, Object mappingObject) {
		JAXBContext jc = null;
		Unmarshaller unmarshaller = null;
		Object unmarshalledXmlObj = null;
		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream(xmlName);
		try {
			jc = JAXBContext.newInstance(mappingObject.getClass());
			unmarshaller = jc.createUnmarshaller();
			unmarshalledXmlObj = unmarshaller.unmarshal(in);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return unmarshalledXmlObj;
	}

}
