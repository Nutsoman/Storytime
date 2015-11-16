package arheo.storytime;

import java.util.Random;

public enum Comp {

	NAMES_MALE_FIRST(new String[]{"Bill","Dave","Harry"}),
	
	NAMES_FEMALE_FIRST(new String[]{"Billis","Davina","Harriet"}),
	
	NAMES_LAST(new String[]{"Billo","Davo","Haricot Verts"}),
	
	TITLE_MALE(new String[]{"Baron","Duke","Lord"}),
	
	TITLE_FEMALE(new String[]{"Baroness","Duchess","Lady"}),
	
	LAST_TITLE(new String[]{
			"the Unpleasant",
			"the Pleasant",
			"the Hard",
			"the Chin",
			"the Legs"
	}),
	
	NAMETYPE(new String[]{
			"#NAMES_MALE_FIRST #NAMES_LAST",
			"#NAMES_FEMALE_FIRST #NAMES_LAST",
			"#TITLE_MALE #NAMES_MALE_FIRST",
			"#TITLE_FEMALE #NAMES_FEMALE_FIRST",
			"#TITLE_MALE #NAMES_LAST",
			"#TITLE_FEMALE #NAMES_LAST",
			"#TITLE_MALE #NAMES_MALE_FIRST #NAMES_LAST",
			"#TITLE_FEMALE #NAMES_FEMALE_FIRST #NAMES_LAST",
			"#TITLE_MALE #NAMES_LAST #LAST_TITLE",
			"#TITLE_FEMALE #NAMES_LAST #LAST_TITLE"
			
			
	
	}),
	
	;
	
	public static final String delimiter = "#";
	public static final String seperator = ".";
	
	public final String[] words;
	private static Random rand = new Random();
	
	private Comp(String[] words) {
		this.words = words;
	}
	
	public String get(){
		return this.words[rand.nextInt(words.length)];
		
	}
}
