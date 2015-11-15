package arheo.storytime;

import java.util.Random;

public enum Theme {
	
	DEFAULT(new String[]{"Harold the Robotic"},new String[]{"An Epic Monstrosity"}),
	
	GRAVEYARD(
			
		new String[]{"Insidious Irene","Count Cloggis","Louis the Liche","Skeleton Sue","Barry the Bat","The Fleshblimp","Paulus Blartastico","Dylan the Gravedigger","Discopansy the Flatulent"},
		new String[]{"A Treatise on the Dead", "A Treatise on the Deader", "A Treatise on the Deadest"}
			
			
			
			
			
			
	)
	,
	;
	
	private static Random rand = new Random();
	private String[] authors;
	private String[] titles;
	
	private Theme(String[] authors,String[] titles) {
		
		this.authors = authors;
		this.titles = titles;
	}
	
	public String getAuthor() {
		return this.authors[rand.nextInt(this.authors.length)];
	}
	
	public String getTitle() {
		return this.titles[rand.nextInt(this.titles.length)];
	}
}
