package scripts;

import java.io.*;
import java.util.ArrayList;

public class SplitMatches {
	private static String header = "";
	
	public static void splitMatches() {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		String line = "";
		File targetFile;

		
		try {
			reader = new BufferedReader(new FileReader("../data/master-distance.csv"));	
			header = reader.readLine(); 
			String matchId = "";
			
			while ((line = reader.readLine()) != null) {
				String id = line.split(",")[0];
				 
				if (!matchId.equals(id)) {
					matchId = id;
					targetFile = new File("deliverables/match-" + matchId + "-distance.csv");
					targetFile.createNewFile();
					
					if (writer != null) {
						writer.flush();
						writer.close();
					}
					
					writer = new BufferedWriter(new FileWriter(targetFile));
					
					System.out.println("Extracting match " + matchId);
					System.out.println("Writing to match-" + matchId + "-distance.csv");
					
					writer.write(header);
					writer.newLine();
				}					

				writer.write(line);
				writer.newLine();
			}
		
			reader.close();
			
			reader = new BufferedReader(new FileReader("../data/master-zones.csv"));	
			header = reader.readLine(); 
			matchId = "";
			
			while ((line = reader.readLine()) != null) {
				String id = line.split(",")[3];
				 
				if (!matchId.equals(id)) {
					matchId = id;
					targetFile = new File("deliverables/match-" + matchId + "-zones.csv");
					targetFile.createNewFile();
					
					writer.flush();
					writer.close();
					
					writer = new BufferedWriter(new FileWriter(targetFile));
					
					System.out.println("Extracting match " + matchId);
					System.out.println("Writing to match-" + matchId + "-zones.csv");
					
					writer.write(header);
					writer.newLine();
				}					

				writer.write(line);
				writer.newLine();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				
				writer.flush();
				writer.close();
				
				System.out.println("...done extracting.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
