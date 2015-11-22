package arheo.storytime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import arheo.storytime.PhraseList.Phrase;
import arheo.storytime.Story.Thing;

public class TextUtils {
	private static final Pattern pattern = Pattern.compile("#(.*?)#");
	//a
	public static int storytype = 0;
	//b
	public static String stringtrans(String text, Story story) {
		Matcher matcher = pattern.matcher(text);
		int iter = 0;
		while (matcher.find() && iter <= 100000) {
			iter++;
			matcher = matcher.reset();
			
			StringBuilder replaced = new StringBuilder();
			int lastend = 0;
			
			while (matcher.find()) {
				int start = matcher.start()+1;
				int end = matcher.end();
				
				
				
				String raw = text.substring(start,end-1);
				String[] words = raw.split(Symbol.seperator);
				String word = words[0];
				Phrase replacement = new Phrase("["+word+"]");
				Boolean format = true;
				
				try {
					Symbol comp = Symbol.valueOf(word);
					replacement = comp.getPhrase();
				} catch (IllegalArgumentException e) {
					format = false;
					replacement = Phrase.MISSING;
				} catch (Exception e) {
					format = false;
					replacement = Phrase.ERROR;
					Storytime.logger.warn(e,e);
				}
				
				if (words.length > 1 && format) {
					for (int i=1; i<words.length; i++) {
						replacement = Formatting.format(replacement, words[i]);
						if (words[i].startsWith("~")) {
							String name = words[i].substring(1);
							Storytime.logger.info("before");
							Storytime.logger.info(story.things);
							Storytime.logger.info("after");
							if (!story.things.containsKey(name)) {
								story.things.put(name,new Thing(stringtrans(replacement.text,story)));
							}
							replacement = new Phrase(story.things.get(name).name);
							Storytime.logger.info(story.things);
		
						}
					}
				}
				
				//a
				if (words.length > 1 && format) {
					for (int i=1; i<words.length; i++) {
						replacement = Formatting.format(replacement, words[i]);
						if (words[i].contains("@")) {
							storytype = 2;
							String name = words[i].substring(1);
							if (!story.things.containsKey(name)) {
								story.things.put(name,new Thing(stringtrans(replacement.text,story)));
							}
							replacement = new Phrase(story.things.get(name).name);
							Storytime.logger.info(story.things);
		
						}
					}
				}
				//b
				replaced.append(text.substring(lastend,start-1));
				replaced.append(replacement.text);
				lastend = end;
			} 
			
			if ( lastend < text.length()) {
				replaced.append(text.substring(lastend, text.length()));
			}
			
			
			
			text = replaced.toString();
			//matcher = matcher.reset(text);
			matcher = pattern.matcher(text);
		}
		
		return text;
	}
	
	

}
