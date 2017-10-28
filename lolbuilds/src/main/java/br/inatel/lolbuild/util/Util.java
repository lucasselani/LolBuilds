package br.inatel.lolbuild.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class Util {
	public static String convertStringToMd5(String value) {
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("MD5");

			byte[] md5 = mDigest.digest(value.getBytes("UTF-8"));
			StringBuffer sb = new StringBuffer();
			for (byte b : md5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void redirectWithGet() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

		StringBuffer requestURL = request.getRequestURL();
		String queryString = request.getQueryString();

		if (queryString != null) {
			requestURL.append('?').append(queryString).toString();
		}

		String url = requestURL.toString();
		try {
			externalContext.redirect(requestURL.toString());
		} catch (IOException e) {
			throw new RuntimeException("Unable to rerirect to " + url);
		}

		facesContext.responseComplete();
	}
}
