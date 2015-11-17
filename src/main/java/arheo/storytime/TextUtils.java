package arheo.storytime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {
	private static final Pattern pattern = Pattern.compile("#([\\w\\.]*)#");
	
	public static String stringtrans(String text, Story story) {
		Matcher matcher = pattern.matcher(text);
		int iter = 0;
		while (matcher.lookingAt() && iter <= 100000) {
			iter++;
			matcher = matcher.reset();
			
			StringBuilder replaced = new StringBuilder();
			int lastend = 0;
			
			while (matcher.find()) {
				int start = matcher.start()+1;
				int end = matcher.end();
				
				String raw = text.substring(start,end-1);
				String[] words = raw.split(Symbol.delimiter);
				String word = words[0];
				String replacement = "["+word+"]";
				
				if (words.length > 1) {
					
				} else {
					try {
						Symbol comp = Symbol.valueOf(word);
						replacement = comp.get();
					} catch (Exception e) {
						replacement = "[MISSING]";
						Storytime.logger.warn(e);
					}
				}
				
				
				replaced.append(text.substring(lastend,start-1));
				replaced.append(replacement);
				lastend = end;
			} 
			
			if ( lastend < text.length()) {
				replaced.append(text.substring(lastend, text.length()));
			}
			
			
			
			text = replaced.toString();
			matcher = matcher.reset(text);
		}
		
		return text;
	}
	
	

}
