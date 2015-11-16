package arheo.storytime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {
	private static final Pattern pattern = Pattern.compile("#([\\w\\.]*)");
	
	public static String stringtrans(String text, Story story) {
		Matcher matcher = pattern.matcher(text);
		int iter = 0;
		while (matcher.lookingAt() && iter <= 100000) {
			matcher = matcher.reset();
			
			StringBuilder replaced = new StringBuilder();
			int lastend = 0;
			
			while (matcher.find()) {
				int start = matcher.start()+1;
				int end = matcher.end();
				
				String raw = text.substring(start,end);
				String[] words = raw.split(Comp.delimiter);
				String word = words[0];
				String replacement = "["+word+"]";
				
				if (words.length > 1) {
					
				} else {
					try {
						Comp comp = Comp.valueOf(word);
						replacement = comp.get();
					} catch (Exception e) {
						replacement = "[MISSING]";
					}
				}
				
				replaced.append(text.substring(lastend,start-1));
				replaced.append(replacement);
				lastend = end;
			} 
			
			text = replaced.toString();
			matcher = matcher.reset(text);
		}
		
		return text;
	}
	
	

}
