import java.util.ArrayList;

public class School{
	private String name;
	private int[] tags;

	//the way to make a school object; tags is for the number of tags you want
	public School(String name, int tags){
		this.name = name;
		this.tags = new int[tags];
	}

	public void setTag(int index, int value){
		tags[index] = value;
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof String){
			return name.equals((String) obj);
		} else if(obj instanceof School){
			return name.equals(((School) obj).getName());
		} else{
			return false;
		}
	}

	@Override
	public int hashCode() {
		return name.length();
	}

	public String getName(){
		return name;
	}

	public int getTag(int i){
		return tags[i];
	}

	public int getValue(){
		int sum = 0;
		for(int e: tags) {
			if(e == 0){
				sum+=30;
			}
			sum+=e;
		}
		return sum;
	}
}
