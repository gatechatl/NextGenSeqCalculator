import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;


public class CreateCSVFile {

	public static void main(String[] args) {
		
		double size = new Double(args[1]);
		double sample = new Double(args[2]);
		
		try {
			String fileName = args[0];
			HashMap map = new HashMap();
			
		    FileWriter fwriter = new FileWriter("temp");
		    BufferedWriter out = new BufferedWriter(fwriter);		    
		    
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				double bps = new Double(split[9]);
				
				if (!map.containsKey(split[0] + "_" + split[1] + "_" + split[2])) {
					map.put(split[0] + "_" + split[1] + "_" + split[2], split[0] + "_" + split[1]);
					out.close();
				    fwriter = new FileWriter(split[0] + "_" + split[1] + "_" + split[2].replaceAll("\\+", "Plus"));
				    out = new BufferedWriter(fwriter);
				    out.write("Platform,Model,Available,Cost,Unit,Chemistry & read settings,reads,length,bp,genome size (bp),Total x coverage,# of samples,X coverage per sample\n");
				}
			        String meta = "";
                                for (int i = 1; i < split.length; i++) {
                                    meta += split[i] + ",";
                                }	
				out.write(meta + size + "," + bps / size + "," + sample + "," + (bps / size / sample) + "\n");
				
				
			}
			out.close();
			in.close();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

