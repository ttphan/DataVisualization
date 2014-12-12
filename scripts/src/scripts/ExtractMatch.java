package scripts;

import java.io.*;

public class ExtractMatch {
	public static void getMatch(int matchId) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		String line = "";
		File targetFile = new File("deliverables/match-" + matchId + "-distance.csv");
		boolean found = false;
		
		try {
			reader = new BufferedReader(new FileReader("../data/master-distance.csv"));
			targetFile.createNewFile();
			writer = new BufferedWriter(new FileWriter(targetFile));
			System.out.println("Extracting match " + matchId);
			System.out.println("Writing to match-" + matchId + "-distance.csv");
			
			if ((line = reader.readLine()) != null) {
				writer.write(line);
				writer.newLine();
				while ((line = reader.readLine()) != null) {
					int id = Integer.parseInt(line.split(",")[0]);
					
					if (id == matchId) {
						writer.write(line);
						writer.newLine();
						found = true;
					}
					else if (found) {
						break;
					}

				}
			}
			
			found = false;
			targetFile = new File("deliverables/match-" + matchId + "-zones.csv");		
			reader = new BufferedReader(new FileReader("../data/master-zones.csv"));
			writer = new BufferedWriter(new FileWriter(targetFile));
			System.out.println("Writing to match-" + matchId + "-zones.csv");
			
			if ((line = reader.readLine()) != null) {
				writer.write(line);
				writer.newLine();
				while ((line = reader.readLine()) != null) {
					int id = Integer.parseInt(line.split(",")[3]);
					
					if (id == matchId) {
						writer.write(line);
						writer.newLine();
						found = true;
					}
					else if (found) {
						break;
					}
				}
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
