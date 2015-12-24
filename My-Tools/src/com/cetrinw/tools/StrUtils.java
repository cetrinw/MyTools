package com.cetrinw.tools;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;

/**
 * String operate
 * 
 * @author Cetrin Wang
 *
 */
public class StrUtils {

	/**
	 * BLOB -> String
	 * 
	 * @param blob
	 * @return
	 */
	private static String blob2String(Blob blob) {
		try {
			if (blob == null || blob.length() == 0) {
				return "";
			} else {
				String content = new String(blob.getBytes((long) 1,
						(int) blob.length()));
				return content.trim();
			}
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * String -> Blob
	 * @param str
	 * @return 
	 */
	public static InputStream string2Blob(String str) {
		InputStream inputStream = null;
		try {
			if (str != null) {
				inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			new RuntimeException(e.getMessage());
		}
		return inputStream;
	}
	
	/**
	 * inputstream -> String 
	 * @param in
	 * @return
	 */
	public static String inStream2string(InputStream in) {
		StringBuffer buffer = new StringBuffer();
		try {
			if (in != null) {
				BufferedReader bf;
				bf = new BufferedReader(new InputStreamReader(in, "UTF-8"));
				String line = "";
				while ((line = bf.readLine()) != null) {
					buffer.append(line);
				}
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			new RuntimeException(e.getMessage());
		}
		return buffer.toString();
	}
	/**
	 * copy str
	 * @param str
	 * @param length
	 * @return
	 */
	public static String copyString(String str, int length) {
		String result = "";
		for (int i = 0; i < length; i++) {
			result += str + ",";
		}
		return result.substring(0, result.length() - 1);
	}
}
