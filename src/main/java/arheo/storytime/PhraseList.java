package arheo.storytime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import arheo.storytime.Formatting.IFormatType;

public class PhraseList {
	private List<Phrase> list;
	private double totalWeight = 0;
	private boolean recalcWeights = true;
	
	public PhraseList(Object... args) {
		this.list = new ArrayList<Phrase>();
		
		for (Object arg : args) {
			if (arg instanceof Phrase) {
				this.list.add((Phrase)arg);
			} 
			else if (arg instanceof String) {
				this.list.add(new Phrase((String)arg, 1.0));
			}
		}
		
		this.calculateWeights();
	}
	
	private void calculateWeights() {
		this.recalcWeights = false;
		
		this.totalWeight = 0;
		for (Phrase p : this.list) {
			this.totalWeight += p.weight;
			p.totalWeight = this.totalWeight;
		}
	}
	
	public void add(Phrase p) {
		this.list.add(p);
		this.recalcWeights = true;
	}
	
	public void add(String s) {
		this.list.add(new Phrase(s, 1.0));
		this.recalcWeights = true;
	}
	
	public Phrase get(Random rand) {
		if (this.recalcWeights) {
			this.calculateWeights();
		}
		
		if (this.list.size() == 0) {
			return Phrase.MISSING;
		}
		
		Phrase out = this.list.get(0);
		double rweight = rand.nextDouble() * this.totalWeight;
		for (Phrase p : this.list) {
			if (p.totalWeight > rweight) {
				break;
			}
			out = p;
		}
		
		return out;
	}
	
	public static class Phrase {
		public String text;
		public double weight;
		protected double totalWeight = 0;
		
		public static final Phrase MISSING = new Phrase("[MISSING]", 1.0);
		public static final Phrase ERROR = new Phrase("[ERROR]", 1.0);
		
		protected Phrase(String text, double weight) {
			this.text = text;
			this.weight = weight;
		}
		protected Phrase(String text) {
			this(text,1.0);
		}
		
		public static Phrase w(String text, double weight) {
			return new Phrase(text, weight);
		}
		
		public Phrase copy() {
			return new Phrase(this.text, this.weight);
		}
		
		public Phrase format(IFormatType formatting) {
			return new Phrase(formatting.apply(this.text), this.weight);
		}
	}
}
