package com.rest.resume;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import io.minio.BucketExistsArgs;
import io.minio.DeleteBucketTagsArgs;
import io.minio.DownloadObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

public class ResumeS3 {
	
	MinioClient minioClient;
	
	public ResumeS3() {
		try {
			minioClient =
	          MinioClient.builder()
	              .endpoint("https://play.min.io")
	              .credentials("Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG")
	              .build();
			boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("resumes").build());
			if (!found) {
				minioClient.makeBucket(MakeBucketArgs.builder().bucket("resumes").build());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error creating S3 database.");
		}
	}
	
	public void uploadResume(String filepath) {
		try {
			minioClient.uploadObject(
			  UploadObjectArgs.builder()
			      .bucket("resumes")
			      .object(getFileName(filepath))
			      .filename(filepath)
			      .build());
		} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
				| IllegalArgumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error uploading file.");
		}
	}
	
	//Do more if filename is the same from another
	private String getFileName(String filepath) {
		return filepath.substring(filepath.lastIndexOf("\\") + 1);
	}
	
	public void downloadResume(String filepath) {
		try {
			minioClient.downloadObject(
					  DownloadObjectArgs.builder()
					  .bucket("resumes")
					  .object(getFileName(filepath))
					  .filename(filepath)
					  .build());
		} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
				| IllegalArgumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error downloading file.");
		}
	}
	
}
