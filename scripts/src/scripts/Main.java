package scripts;

public class Main {
	private static String csvFile = "../data/master-zones.csv";
	
	public static void main(String[] args) {
		//splitFiles();
		
		ExtractMatch(569649581);		
	}
	
	private static void splitFiles() {
		SplitFile.countLines(csvFile);
		
		SplitFile.splitFile(csvFile, 25);
	}
	
	private static void ExtractMatch(int matchId) {
		ExtractMatch.getMatch(matchId);
	}

}
