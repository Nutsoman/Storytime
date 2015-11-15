package arheo.storytime;

import java.util.Random;

public class Story {

	private static Random rand = new Random();
	
	public String title;
	public String author;
	public Theme theme;
	
	
	public Story(Theme theme) {
		this.theme = theme;
		this.title = TextUtils.stringtrans(theme.getTitle(),this);
		this.author = TextUtils.stringtrans(theme.getAuthor(),this);
		
	}
	
	
}
