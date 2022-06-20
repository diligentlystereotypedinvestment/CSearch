import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Collections;

public class Tag {
	public static void main(String[] args) throws FileNotFoundException {
		File dir = new File("/home/vtt/School/College App/tags/data/CollegeEX");
		File[] directoryListing = dir.listFiles();
		ArrayList<Scanner> data = new ArrayList<Scanner>();

		//Lists Importation
		if (directoryListing != null) {
			for(int i = 0; i < directoryListing.length; i++) {
				data.add(new Scanner(directoryListing[i]));
			}
		} else {
		}

		//General Data Importation


		ArrayList<School> schools = new ArrayList<School>();
		for(int i = 0; i < data.size(); i++) {
			while(data.get(i).hasNextLine()){
				String line = data.get(i).nextLine();
				String[] valueName = parse(line);
				if(!contains(schools, valueName[1])){
					int index = schools.size();
					schools.add(new School(valueName[1], data.size()));
					schools.get(index).setTag(i, Integer.valueOf(valueName[0]));
					// System.out.println(schools.indexOf(valueName[1]));
				} else{
					schools.get(indexOf(schools, valueName[1])).setTag(i, Integer.valueOf(valueName[0]));
				}
			}
		}

		Collections.sort(schools, (uni1, uni2) -> Integer.compare(uni1.getValue(), uni2.getValue()));
		for(int i = 0; i < 15; i++) {
			System.out.println("Score: " + schools.get(i).getValue() + " | " + schools.get(i).getName());
		}
		for(int i = 0; i < 5; i++) {
		 	System.out.println(schools.get(0).getTag(i));
		}
	}

	public static String[] parse(String line){
		String[] valueName;
		//if the list has rankings, then split them into rank and name
		try{
		if(line.charAt(0) <= 57 && line.indexOf('.') != -1){
			valueName = line.split("\\.",2);
			valueName[1] = valueName[1].substring(1);
		} else{
			valueName = new String[] {"5", null};
		}
		if(valueName[1] != null && valueName[1].contains(":")){
			valueName[1] = valueName[1].split(":")[0];
		} else if(valueName[1] == null){
			valueName[1] = line.split(":")[0];
		}
		valueName[1] = valueName[1].replace("the ","");
		valueName[1] = valueName[1].replace("The ","");
		return valueName;
		} catch(Exception e){
			System.out.println(line);
			return new String[]{null};
		}
	}

	public static boolean contains(ArrayList<School> schools, String comp){
		for(School s: schools) {
			if(s.equals(comp)){
				return true;
			}
		}
		return false;
	}

	public static int indexOf(ArrayList<School> schools, String search){
		for(int i = 0; i < schools.size(); i++){
			if(schools.get(i).equals(search)){
				return i;
			}
		}
		return -1;
	}
}
