package com.staples.dashboard.app.utilities;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.staples.dashboard.app.constants.OAuthConstants;
import com.staples.dashboard.app.sdc.vo.PriceLog;
import com.staples.dashboard.app.vo.NephosConfig;
import com.staples.dashboard.app.vo.NephosResponseVO;
import com.staples.dashboard.app.vo.OAuth2Details;
import com.staples.dashboard.exception.DashboardException;

public class OAuthUtility {

	public static OAuth2Details createOAuthDetails(NephosConfig config) {
		OAuth2Details oauthDetails = new OAuth2Details();
		oauthDetails.setAccessToken(config.getAccessToken());
		oauthDetails.setGrantType(OAuthConstants.GRANT_TYPE_CLIENT_CREDENTIALS);
		oauthDetails.setAuthenticationServerUrl(config.getAuthUrl());
		oauthDetails.setClientId(config.getUserName());
		oauthDetails.setClientSecret(config.getPassword());
		oauthDetails.setResourceServerUrl(config.getResourceUrl());

		return oauthDetails;
	}

	public static Map handleResponse(HttpResponse response) {
		String contentType = OAuthConstants.JSON_CONTENT;
		if (response.getEntity().getContentType() != null) {
			contentType = response.getEntity().getContentType().getValue();
		}
		if (contentType.contains(OAuthConstants.JSON_CONTENT)) {
			return handleJsonResponse(response);
		} else if (contentType.contains(OAuthConstants.URL_ENCODED_CONTENT)) {
			return handleURLEncodedResponse(response);
		} else if (contentType.contains(OAuthConstants.XML_CONTENT)) {
			return handleXMLResponse(response);
		} else {
			// Unsupported Content type
		return null;
		}

	}

	public static Map handleJsonResponse(HttpResponse response) {
		Map<String, String> oauthLoginResponse = null;

		String contentType = response.getEntity().getContentType().getValue();
		try {
			String result = EntityUtils.toString(response.getEntity());
			oauthLoginResponse = (Map<String, String>) new JSONParser()
					.parse(result);

		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			/*e.printStackTrace();
			throw new RuntimeException();*/

		} catch (IOException e) {
			// TODO Auto-generated catch block

			throw new DashboardException(e);
		} catch (RuntimeException e) {
			/*System.out.println("Could not parse JSON response");*/
			/*throw new DashboardException(e);*/
		}

		return oauthLoginResponse;
	}

	public static Map handleURLEncodedResponse(HttpResponse response) {
		Map<String, Charset> map = Charset.availableCharsets();
		Map<String, String> oauthResponse = new HashMap<String, String>();
		Set<Map.Entry<String, Charset>> set = map.entrySet();
		Charset charset = null;
		HttpEntity entity = response.getEntity();

		for (Map.Entry<String, Charset> entry : set) {
			if (entry.getKey().equalsIgnoreCase(
					String.valueOf(StandardCharsets.UTF_8))) {
				charset = entry.getValue();
			}
		}

		try {
			List<NameValuePair> list = URLEncodedUtils.parse(
					EntityUtils.toString(entity), StandardCharsets.UTF_8);
			for (NameValuePair pair : list) {
				oauthResponse.put(pair.getName(), pair.getValue());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block

			throw new DashboardException("Could not parse URLEncoded Response "
					+ e);
		}

		return oauthResponse;
	}

	public static Map handleXMLResponse(HttpResponse response) {
		Map<String, String> oauthResponse = new HashMap<String, String>();
		try {

			String xmlString = EntityUtils.toString(response.getEntity());
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			InputSource inStream = new InputSource();
			inStream.setCharacterStream(new StringReader(xmlString));
			Document doc = db.parse(inStream);
			parseXMLDoc(null, doc, oauthResponse);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DashboardException(
					"Exception occurred while parsing XML response");
		}
		return oauthResponse;
	}

	public static void parseXMLDoc(Element element, Document doc,
			Map<String, String> oauthResponse) {
		NodeList child = null;
		if (element == null) {
			child = doc.getChildNodes();

		} else {
			child = element.getChildNodes();
		}
		for (int j = 0; j < child.getLength(); j++) {
			if (child.item(j).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
				org.w3c.dom.Element childElement = (org.w3c.dom.Element) child
						.item(j);
				if (childElement.hasChildNodes()) {
					/*System.out.println(childElement.getTagName() + " : "
							+ childElement.getTextContent());*/
					oauthResponse.put(childElement.getTagName(),
							childElement.getTextContent());
					parseXMLDoc(childElement, null, oauthResponse);
				}

			}
		}
	}

	public static String getAuthorizationHeaderForAccessToken(String accessToken) {
		return OAuthConstants.BEARER + " " + accessToken;
	}

	public static String getBasicAuthorizationHeader(String username,
			String password) {
		return OAuthConstants.BASIC + " "
				+ encodeCredentials(username, password);
	}

	public static String encodeCredentials(String username, String password) {
		String cred = username + ":" + password;
		String encodedValue = null;
		byte[] encodedBytes = Base64.encodeBase64(cred.getBytes());
		encodedValue = new String(encodedBytes);

		return encodedValue;

	}

	public static boolean isValidInput(OAuth2Details input) {

		if (input == null) {
			return false;
		}

		String grantType = input.getGrantType();

		if (!isValid(grantType)) {
			return false;
		}

		if (!isValid(input.getAuthenticationServerUrl())) {
			return false;
		}

		if (grantType.equals(OAuthConstants.GRANT_TYPE_PASSWORD)) {
			if (!isValid(input.getUsername()) || !isValid(input.getPassword())) {
				return false;
			}
		}

		if (grantType.equals(OAuthConstants.GRANT_TYPE_CLIENT_CREDENTIALS)) {
			if (!isValid(input.getClientId())
					|| !isValid(input.getClientSecret())) {
				return false;
			}
		}

		return true;

	}

	public static boolean isValid(String str) {
		return (str != null && str.trim().length() > 0);
	}

	public static NephosResponseVO formNephosResponseVO(HttpResponse response, PriceLog priceLog) {
		String result;

		ObjectMapper objMap = new ObjectMapper();
		NephosResponseVO obj = null;

		try {

			result = EntityUtils.toString(response.getEntity());
			priceLog.setPriceResponse(result);
			obj = objMap.readValue(result, NephosResponseVO.class);
			obj.setResponseCode(200);
			obj.setResponseMessage("Success");
		} catch (ParseException e1) {
			obj= new NephosResponseVO();
			obj.setResponseCode(500);
			obj.setResponseMessage("Service Not Available");
			priceLog.setPriceResponse(obj.getResponseCode() + ": " + obj.getResponseMessage());
			return obj;
		} catch (JsonParseException e) {
			obj= new NephosResponseVO();
			obj.setResponseCode(500);
			obj.setResponseMessage("Service Not Available");
			priceLog.setPriceResponse(obj.getResponseCode() + ": " + obj.getResponseMessage());
			return obj;
		} catch (JsonMappingException e) {
			obj= new NephosResponseVO();
			obj.setResponseCode(500);
			obj.setResponseMessage("Service Not Available");
			priceLog.setPriceResponse(obj.getResponseCode() + ": " + obj.getResponseMessage());
			return obj;

		} catch (IOException e) {
			obj= new NephosResponseVO();
			obj.setResponseCode(500);
			obj.setResponseMessage("Service Not Available");
			priceLog.setPriceResponse(obj.getResponseCode() + ": " + obj.getResponseMessage());
			return obj;
		}
		return obj;

	}

}
