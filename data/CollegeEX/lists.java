import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class lists {
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<String> urls = new ArrayList<String>();
		Scanner one = new Scanner(new File("1.txt"));
		Scanner two = new Scanner(new File("2.txt"));
		Scanner three = new Scanner(new File("3.txt"));
		Scanner four = new Scanner(new File("4.txt"));
		Scanner five = new Scanner(new File("5.txt"));
		Scanner six = new Scanner(new File("6.txt"));
		Scanner seven = new Scanner(new File("7.txt"));
		Scanner eight = new Scanner(new File("8.txt"));
		Scanner nine = new Scanner(new File("9.txt"));
		Scanner[] scans = {one, two, three, four, five, six, seven, eight, nine};
		for(Scanner scan: scans) {
			while(scan.hasNextLine()){
				String line = scan.nextLine();
				while(scan.hasNextLine() && !line.contains("<div class=\"back\">")){
					line = scan.nextLine();
				}
				if(!scan.hasNextLine()){
					break;
				}
				line = scan.nextLine();
				int start = line.indexOf("f=\"");
				int fin = line.indexOf("\">");
				line = line.substring(start+3, fin);
				urls.add("\"https://www.collegexpress.com" + line + "\">\"" + getName(line) + ".txt\"");
			}
		}
		for(String e: urls) {
			System.out.println("curl " + e);
		}
	}

	public static String getName(String line){
		line = line.substring(12);
		line = line.substring(0, line.indexOf("/"));
		line = line.replace("-", " ");
		return line;
	}
}
