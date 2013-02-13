import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;


public class CreateCSVFile {

	public static void main(String[] args) {
		
		double size = new Double(args[1].replaceAll(",", ""));
		double sample = new Double(args[2].replaceAll(",", ""));
		
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
				double bps = expandValue(split[9]);
                                split[9] = Minimize0sbp(split[9]);
                                split[7] = Minimize0s(split[7]);
				if (!map.containsKey(split[0])) {
					map.put(split[0], split[0] + "_" + split[1]);
					out.close();
				    fwriter = new FileWriter(split[0] + "_" + split[1] + "_" + split[2].replaceAll("\\+", "Plus"));
				    out = new BufferedWriter(fwriter);
				    out.write("Platform,Model,Available,Cost,Unit,Chemistry & read settings,reads,length,bp,genome size (bp),Total x coverage,# of samples,X coverage per sample\n");
				}
			        String meta = "";
                                for (int i = 1; i < split.length; i++) {
                                    
                                    meta += split[i].replaceAll("_", " ") + ",";
                                }	
				out.write(meta + Minimize0sbp(size + "") + "," + bps / size + "," + sample + "," + (bps / size / sample) + "\n");
				
				
			}
			out.close();
			in.close();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

        public static double expandValue(String number) {
            if (number.contains("Gbp")) {
                return new Double(number.replaceAll("Gbp", "")) * 1000000000;
            } else if (number.contains("Mbp")) {
                return new Double(number.replaceAll("Mbp", "")) * 1000000;

            } else if (number.contains("Kbp")) {
                return new Double(number.replaceAll("Kbp", "")) * 1000;

            }
            return new Double(number);
        }
        public static String Minimize0sbp(String number) {
             String finalText = "";
             try {
                double num = new Double(number);
                if (num / 1000000000 >= 1) {                    
                    number = (num / 1000000000) + "Gbp";
                } else if (num / 1000000 >= 1) {
                    number = (num / 1000000) + "Mbp";
                } else if (num / 1000 >= 1) {
                    number = (num / 1000) + "Kbp";
                }
                return number;
             } catch (Exception e) {
                e.printStackTrace();
                return number;
             }
        }
        public static String Minimize0s(String number) {
             String finalText = "";
             try {
                double num = new Double(number);
                if (num / 1000000000 >= 1) {
                    number = (num / 1000000000) + "G";
                } else if (num / 1000000 >= 1) {
                    number = (num / 1000000) + "M";
                } else if (num / 1000 >= 1) {
                    number = (num / 1000) + "K";
                }
                return number;
             } catch (Exception e) {
                e.printStackTrace();
                return number;
             }
        }


}

