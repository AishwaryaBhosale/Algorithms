package trees;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static void main(String args[]) {
		RedBlackTreeMap<String, Integer> redBlack = new RedBlackTreeMap<String, Integer>();
		try (BufferedReader br = new BufferedReader(new FileReader("D:\\CSULB\\demo\\RedBlackTree\\src\\trees\\players_homeruns.csv"))) {
			String line;
			System.out.println("First 5 entries:\n");
			for (int i = 0; i < 5; i++) {
				
				if ((line = br.readLine()) != null) {
					String[] values = line.split(",");
					redBlack.add(values[0], Integer.parseInt(values[1]));
				}
			}
			redBlack.printStructure();
			System.out.println("\nAdding remaing nodes:\n");
			for (int i = 0; i < 5; i++) {
				
				if ((line = br.readLine()) != null) {
					String[] values = line.split(",");
					redBlack.add(values[0], Integer.parseInt(values[1]));
				}
			}
			redBlack.printStructure();
			System.out.println("\nTest case for finding the nodes:\n");
			System.out.println("One that has one nil and one non-nil child Player:Stan Musial Value:"+redBlack.find("Stan Musial"));
			System.out.println("One that is a leaf Player:Willie Mays Value:"+redBlack.find("Willie Mays"));
			System.out.println("One that is a root Player:Honus Wagner Value:"+redBlack.find("Honus Wagner"));
			System.out.println("One that has one nil and one non-nil child Player:Rogers Hornsby Value:"+redBlack.find("Rogers Hornsby"));
			
			System.out.println("\nAdding remaing lines\n");
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				redBlack.add(values[0], Integer.parseInt(values[1]));
			}
			
			System.out.println("There are "+redBlack.getCount()+" keys in the red black tree\n");
			
			System.out.println("Testing for the same old keys:");
			System.out.println("One that has one nil and one non-nil child Player:Stan Musial Value:"+redBlack.find("Stan Musial"));
			System.out.println("One that is a leaf Player:Willie Mays Value:"+redBlack.find("Willie Mays"));
			System.out.println("One that is a root Player:Honus Wagner Value:"+redBlack.find("Honus Wagner"));
			System.out.println("One that has one nil and one non-nil child Player:Rogers Hornsby Value:"+redBlack.find("Rogers Hornsby"));
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
