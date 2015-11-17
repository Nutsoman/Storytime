package arheo.storytime;

import java.util.HashMap;
import java.util.Map;

import scala.collection.mutable.StringBuilder;

import arheo.storytime.PhraseList.Phrase;

public class Formatting {
	public static interface IFormatType {
		public String apply(String text);
		public String getIdentifier();
	}
	private static Map<Character,Class<? extends IFormatType>> mapping = new HashMap<Character,Class<? extends IFormatType>>();
	static {
		mapping.put('%', WordAdjustment.class);
	}

	public static Phrase format(Phrase phrase, String command) {
		char ctype = command.charAt(0);
		String cbody = command.substring(1);
		
		if (!mapping.containsKey(ctype)) {
			Storytime.logger.warn("No format type mapping '"+ctype+"' found.");
			return phrase;
		}
		
		IFormatType[] ftypes = mapping.get(ctype).getEnumConstants();
		
		if (ftypes == null) {
			Storytime.logger.warn("No enum values found for format type '"+ctype+"'.");
			return phrase;
		}
		
		IFormatType ftype = null;
		for (IFormatType ft : ftypes) {
			if (ft.getIdentifier().equals(cbody)) {
				ftype = ft;
				break;
			}
		}
		
		if (ftype == null) {
			Storytime.logger.warn("No sub-type '"+cbody+"' in format type '"+ctype+"'.");
			return phrase;
		}
		
		return phrase.format(ftype);
	}
	
	private static enum WordAdjustment implements IFormatType {
		DEFAULT,
		CAP{
			@Override
			public String apply(String text) {
				String[] words = text.split(" ");
				StringBuilder out = new StringBuilder(text.length());
				
				for (int i=0; i<words.length; i++) {
					String word = words[i];
					out.append(word.substring(0, 1).toUpperCase());
					if (word.length() > 1) {
						out.append(word.substring(1));
					}
					if (i < words.length-1) {
						out.append(" ");
					}
				}
				
				return out.toString();
			}
		},
		UP{
			@Override
			public String apply(String text) {
				return text.toUpperCase();
			}
		},
		LOW{
			@Override
			public String apply(String text) {
				return text.toLowerCase();
			}
		},
		;
		
		@Override
		public String apply(String text) {
			return text;
		}

		@Override
		public String getIdentifier() {
			return this.name();
		}
	}
}
