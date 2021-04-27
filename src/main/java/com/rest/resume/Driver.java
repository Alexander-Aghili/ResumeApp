import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import com.rest.resume.ResumeS3;

public class Driver {
    private static final Scanner scan = new Scanner(System.in);
    private static ResumeS3 resume;
    private static final String httpPath = "http://localhost:8081/ResumeApp/Resume/list";
    
    public static void main(String[] args) {
    	resume = new ResumeS3();
    	
        System.out.println();
        System.out.println("--- Main Menu ---");
        
        while(true) {
        	System.out.println("Would you like to upload a file or download a file? (Enter \"up\" or \"down\") You can also list all files by typing \"print\". Type \"stop\" to stop the program.");
            String input = scan.nextLine();
	        if (input.equals("up")) {
	        	System.out.println("What is the file path?");
	        	String tempInput = scan.nextLine();
	            resume.uploadResume(tempInput);
	        } else if (input.equals("down")) {
	        	System.out.println("Enter Object name to Download:");
	        	String tempInput = scan.nextLine();
	            resume.downloadResume(tempInput);
	        } else if (input.equals("print")) {
	        	URL url;
				try {
					url = new URL(httpPath);
		        	HttpURLConnection con = (HttpURLConnection) url.openConnection();
		        	con.setRequestMethod("GET");
		        	System.out.println(con.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } else if (input.equals("end")) {
	        	System.out.println("Ending program...");
	            System.exit(0);
	        }
        }
    }
}

