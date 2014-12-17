package scripts;

import java.io.*;
import java.util.ArrayList;

public class Main {
	private static String csvZones = "../data/master-zones.csv";
	private static String csvDistance = "../data/master-distance.csv";
	
	public static void main(String[] args) {
		//splitFiles();
		
		saveMatchIds();
		
		//splitMatches();		
	}
	
	private static void splitFiles() {
		SplitFile.countLines(csvZones);
		
		SplitFile.splitFile(csvZones, 25);
	}
	
	private static void splitMatches() {
		SplitMatches.splitMatches();
	}
	
	private static void saveMatchIds() {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		String line = "";
		File targetFile = new File("deliverables/matches.csv");
		ArrayList<String> matches = new ArrayList<String>();
		
		try {
			reader = new BufferedReader(new FileReader(csvDistance));
			targetFile.createNewFile();
			writer = new BufferedWriter(new FileWriter(targetFile));
			System.out.println("Extracting match id's...");
			
			while ((line = reader.readLine()) != null) {
				String[] split = line.split(",");
				String matchId = split[0];
				String tier = split[4];
				
				if (!matches.contains(matchId)) {
					matches.add(matchId);
					writer.write(matchId + "," + tier);
					writer.newLine();				
				}
			}
			
			reader.close();
			
			writer.flush();
			writer.close();
			
			System.out.println("...done extracting match id's.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
