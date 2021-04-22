package com.rest.resume;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Item;

@Path("/list")
public class ListResume {
	
	@GET
	@Produces("application/xml")
	public String getList(){
		Iterable<Result<Item>> items = getListOfResumesFromS3();
		return makeStringFromItems(items);
	}
	
	private Iterable<Result<Item>> getListOfResumesFromS3() {
		MinioClient minioClient =  MinioClient.builder()
	              .endpoint("https://play.min.io")
	              .credentials("Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG")
	              .build();
		Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket("resumes").build());
		return results;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String makeStringFromItems(Iterable<Result<Item>> itemsIterable) {
		int resumeNumber = 1;
		String listInString = "";
		Iterator items = itemsIterable.iterator();
		Result<Item> item = (Result<Item>) items.next();
		while(item != null) {
			listInString += "Resume " + resumeNumber + ": " + item.toString() + "\n";
			resumeNumber++;
			item = (Result<Item>) items.next();
		}
		
		return listInString;
	}

}
