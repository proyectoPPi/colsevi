package com.colsevi.application;

import java.util.regex.Pattern;

import org.owasp.esapi.ESAPI;

public class validadorGeneral {
	
	/** The patterns. */
	private static Pattern[] patterns = new Pattern[]{
        /*Patrones xss*/
        Pattern.compile("^.*<script>(.*?)</script>.*$", Pattern.CASE_INSENSITIVE),
        Pattern.compile("^.*src[\r\n]*=[\r\n]*\\\'(.*?)\\\'.*$", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        Pattern.compile("^.*src[\r\n]*=[\r\n]*\\\"(.*?)\\\".*$", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        Pattern.compile("^.*</script>.*$", Pattern.CASE_INSENSITIVE),
        Pattern.compile("^.*<script(.*?)>.*$", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        Pattern.compile("^.*eval\\((.*?)\\).*$", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        Pattern.compile("^.*expression\\((.*?)\\).*$", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        Pattern.compile("^.*javascript:.*$", Pattern.CASE_INSENSITIVE),
        Pattern.compile("^.*vbscript:.*$", Pattern.CASE_INSENSITIVE),
        Pattern.compile("^.*onload[(].*$", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        Pattern.compile("^.*alert[(].*$", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),       
        Pattern.compile("&lt;", Pattern.CASE_INSENSITIVE),
        Pattern.compile("&gt;", Pattern.CASE_INSENSITIVE),
        Pattern.compile("<", Pattern.CASE_INSENSITIVE),
        Pattern.compile(">", Pattern.CASE_INSENSITIVE),
        Pattern.compile("^.*Select .*$", Pattern.CASE_INSENSITIVE),
        Pattern.compile("^.*Insert .*$", Pattern.CASE_INSENSITIVE),
        Pattern.compile("^.*Update .*$", Pattern.CASE_INSENSITIVE),
        Pattern.compile("^.*Delete .*$", Pattern.CASE_INSENSITIVE),
        Pattern.compile("^.*Replace .*$", Pattern.CASE_INSENSITIVE),
        Pattern.compile("^.*Database .*$", Pattern.CASE_INSENSITIVE),
        Pattern.compile("^.*Create .*$", Pattern.CASE_INSENSITIVE),
        Pattern.compile("^.*Drop .*$", Pattern.CASE_INSENSITIVE),
        Pattern.compile("^.*Rollback .*$", Pattern.CASE_INSENSITIVE),
        Pattern.compile("^.*Truncate .*$", Pattern.CASE_INSENSITIVE),
        Pattern.compile("^.*Commit .*$", Pattern.CASE_INSENSITIVE),
    };
	

	public static String stripXSS(String value) {
        if (value != null) {
            value = ESAPI.encoder().canonicalize(value);
            value = value.replaceAll("\0", "");
            for (Pattern scriptPattern : patterns){
                value = scriptPattern.matcher(value).replaceAll("");
            }
        }
        return value;
    }
	
	public static boolean validateXSS(String value){
		
		boolean isValid = true;
		
		if (value != null) {
			boolean stringFinded = false;
            value = ESAPI.encoder().canonicalize(value);
            value = value.replaceAll("\0", "");
            for (Pattern scriptPattern : patterns){
            	stringFinded = scriptPattern.matcher(value).find();
            	if(stringFinded){
            		isValid = false;
            		break;
            	}
            }
            
        }
		return isValid;
	}
}
