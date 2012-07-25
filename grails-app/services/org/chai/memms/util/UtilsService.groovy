package org.chai.memms.util

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

class UtilsService {

    public static Set<String> split(String string, String delimiter) {
		Set<String> result = new HashSet<String>();
		if (string != null) result.addAll(Arrays.asList(StringUtils.split(string, delimiter)));
		return result;
	}

	public static String unsplit(Object list, String delimiter) {
		List<String> result = new ArrayList<String>();
		
		if (list instanceof String) result.add((String) list);
		if (list instanceof Collection) result.addAll((Collection<String>)list);
		else result.addAll(Arrays.asList((String[]) list));
		
		for (String string : new ArrayList<String>(result)) {
			if (string.isEmpty()) result.remove(string);
		}
		return StringUtils.join(result, delimiter);
	}
	
	public static boolean matches(String text, String value) {
		if (value == null) return false;
		return value.matches("(?i).*"+text+".*");
	}
	
	public static String stripHtml(String htmlString) {
		String noHtmlString;
	
		if (htmlString != null) {
			noHtmlString = htmlString.replace("&nbsp;", " ");
			noHtmlString = noHtmlString.replaceAll("<.*?>", " ");
			noHtmlString = StringEscapeUtils.unescapeHtml(noHtmlString);
			noHtmlString = noHtmlString.trim();
		}
		else noHtmlString = htmlString;
	
		return noHtmlString;
	}
}