package br.com.immersionalura.me.omdbapi.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonFilter {

	// Create a pattern(PTBR:Padrão) to use in search
	private static final Pattern REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");
	
	public static Map<String, String> parse(String json){
        
        Map<String, String> Attributes = new HashMap<>();

        // Create a matcher to search patterns in archive
        Matcher matcherAttributes = REGEX_ATRIBUTOS_JSON.matcher(json);
           
        /* While the matcher finds patterns,
           the groups will be cast in variables:String (attribute, value) */
        while (matcherAttributes.find()) {
             String attribute = matcherAttributes.group(1);
             String value = matcherAttributes.group(2);
             Attributes.put(attribute, value);
        }
        
        // Return the map with 'key=value'
        return Attributes;
		
	
	}
}
