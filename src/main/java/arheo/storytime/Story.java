package arheo.storytime;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Story {

	private static Random rand = new Random();
	
	public String title;
	public String author;
	public Theme theme;
	public String content;
	public Map<String,Thing> things;
	
	
	public Story(Theme theme) {
		this.theme = theme;
		this.things = new HashMap<String,Thing>();
		this.title = TextUtils.stringtrans(theme.getTitle(),this);
		this.author = TextUtils.stringtrans(theme.getAuthor(),this);
		this.content = TextUtils.stringtrans(Symbol.TESTPAGE.get(), this);
		 
		
	}
	
	public static class Thing {
		public String name;
		
		public Thing(String name) {
			this.name = name;
		}
	}
	
}
