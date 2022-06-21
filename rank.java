import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

//
// Decompiled by Procyon v0.6.0
//

public class rank
{
	public static void main(String[] array) throws FileNotFoundException {
		File[] listFiles = new File("/home/vtt/School/College App/CSearch/data/CollegeEX").listFiles();
		ArrayList<Scanner> list = new ArrayList<Scanner>();
		if (listFiles != null) {
			for (int i = 0; i < listFiles.length; i++) {
				if (listFiles[i].getName().indexOf('.') != -1) {
					list.add(new Scanner(listFiles[i]));
				} else{
					list.add(null);
				}
			}
		}

		// System.out.println(listFiles.length);
		// for(File f: listFiles) {
		// 	System.out.println(f.getName());
		// }
		for (int j = 0; j < listFiles.length; j++) {
			ArrayList<String> schools = new ArrayList<String>();
			if(list.get(j) == null){
				// System.out.println(listFiles[j].getName());
				// if(listFiles[j].getName().equals("the secrets no more list.txt:")){
				// 	System.out.println("found");
				// }
				continue;
			}
			Scanner scanner = list.get(j);
			boolean isParsed = false;
			boolean isFirst = true;
			//System.out.println(listFiles[j].getName());
			while (scanner.hasNextLine()) {
				String s = scanner.nextLine();
				if (isFirst && !s.contains("html")) {
					isParsed = true;
					break;
				}
				isFirst = false;
				while (scanner.hasNextLine() && !s.contains("<li class=\" clearfix\">") && !s.contains("<li class=\"highlighted clearfix\">")) {
					s = scanner.nextLine();
				}
				if (!scanner.hasNextLine()) {
					break;
				}
				String nextLine = scanner.nextLine();
				if(!nextLine.contains("href")){
					//<span class="name">Interdenominational Theological Center (GA)</span>
					nextLine = nextLine.substring(19, nextLine.length() - 7);
				} else{
					nextLine = nextLine.substring(nextLine.indexOf(">") + 1);
					if (nextLine.matches("[0-9]\\.")) {
						nextLine = nextLine.substring(0, nextLine.indexOf("<")) + nextLine.substring(nextLine.indexOf("\">") + 2, nextLine.indexOf("</s"));
					}
					else {
						nextLine = nextLine.substring(nextLine.indexOf("\">") + 2, nextLine.indexOf("</s"));
					}
					// System.out.println(listFiles[j].getName());
					//<span class="name"><a  href="/college/auburn-university/2400026/details/">Auburn University</a> (Auburn, AL)</span>
					nextLine = nextLine.replace("</a>", "");
				}
				schools.add(nextLine);
			}
			if (!isParsed && !isFirst) {
				try {
					String s2 = "";
					for (int k = 0; k < schools.size() - 1; k++) {
						s2 += (String)schools.get(k) + "\n";
					}
					FileWriter fileWriter = new FileWriter(listFiles[j]);
					fileWriter.write(s2 + (String)schools.get(schools.size() - 1));
					fileWriter.close();
				}
				catch (IOException ex) {}
			}
		}
	}
}
