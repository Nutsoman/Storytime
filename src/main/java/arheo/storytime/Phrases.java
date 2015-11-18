package arheo.storytime;

import arheo.storytime.PhraseList.Phrase;

public abstract class Phrases {
	public static class Verb extends Phrase {
		public Verb(String text, double weight) {
			super(text, weight);
		}
		
	}
	
	public static class Pronoun extends Phrase {
		String obj;
		String possadj;
		String posspro;
		String refpro;
		public Pronoun(String subj,String obj,String possadj,String posspro,String refpro, double weight) {
			super(subj, weight);
			this.obj = obj;
			this.possadj = possadj;
			this.posspro = posspro;
			this.refpro = refpro;
			
		}
	}
	
	//####
	public static Phrase pn(String subj,String obj,String possadj,String posspro,String refpro, double weight) {
		return new Pronoun(subj, obj, possadj, posspro, refpro, weight);
		
	}
}
