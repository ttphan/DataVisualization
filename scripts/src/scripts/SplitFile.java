package scripts;

import java.io.*;

public class SplitFile {
	
	private static String header = "";
	private static int lineCount = 0;
	
	public static void countLines(String csvFile) {
		BufferedReader reader = null;
		String line = "";
		
		try {
			System.out.println("Begin counting lines...");
			reader = new BufferedReader(new FileReader(csvFile));
			while((line = reader.readLine()) != null) {
				if (lineCount == 0) {
					header = line;
				}
				lineCount++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				System.out.println("...done counting lines (" + lineCount +")");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void splitFile(String csvFile, double parts) {
		int fileSize = (int) Math.ceil(lineCount / parts);
		
		BufferedReader reader = null;
		BufferedWriter writer = null;
		String line = "";
		int lineNumber = 0;
		int fileNumber = 0;
		File targetFile = new File("deliverables/master-zones-" + fileNumber + ".csv");
		
		try {
			reader = new BufferedReader(new FileReader(csvFile));
			targetFile.createNewFile();
			writer = new BufferedWriter(new FileWriter(targetFile));
			System.out.println("Start splitting...");
			System.out.println("Writing to master-zones-" + fileNumber + ".csv");
			
			if (reader.readLine() != null) {
				while ((line = reader.readLine()) != null) {
					if (lineNumber == fileSize) {
						System.out.println("Done with master-zones-" + fileNumber + ".csv");
						System.out.println("--------");
						writer.flush();
						writer.close();
						fileNumber++;
						targetFile = new File("deliverables/master-zones-" + fileNumber + ".csv");
						targetFile.createNewFile();
						writer = new BufferedWriter(new FileWriter(targetFile));
						lineNumber = 0;
						System.out.println("Writing to master-zones-" + fileNumber + ".csv");
					}
					
					if (lineNumber == 0) {
						writer.write(header);
						writer.newLine();
					}
					
					line = line.replace("\"", "");
				
					writer.write(line);
					writer.newLine();
					
					lineNumber++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				
				writer.flush();
				writer.close();
				
				System.out.println("...done splitting");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
