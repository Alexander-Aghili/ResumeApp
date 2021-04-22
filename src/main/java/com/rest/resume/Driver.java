package com.rest.resume;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Driver {
    private String input;
    private final Scanner scan = new Scanner(System.in);
    private ResumeS3 resume;
    private final String httpPath = "http://localhost:8081/ResumeApp/Resume/list";
    
    public void main(String[] args) {
    	resume = new ResumeS3();
    	
        System.out.println();
        System.out.println("--- Main Menu ---");
        System.out.println("Would you like to upload a file or download a file? (Enter \"up\" or \"down\") You can also list all files by typing \"print\". Type \"stop\" to stop the program.");
        input = scan.nextLine();

        while(!input.equalsIgnoreCase("up") && !input.equalsIgnoreCase("down") && !input.equalsIgnoreCase("end") && !input.equalsIgnoreCase("list")) {
            System.out.println("Invalid input. Enter \"up\", \"down\", or \"list\".");
            input = scan.nextLine();
        }
        switch(input.toLowerCase()) {
            case "up": System.out.println("What is the file path?");
            input = scan.nextLine();
            resume.uploadResume(input);
            break;
        case "down":
            System.out.println("What is the file path?");
            input = scan.nextLine();
            resume.downloadResume(input);
            break;
        case "print": //print
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
            break;
        case "end":
            System.out.println("Ending program...");
            System.exit(0);
	        break;
	    }
	}
}

