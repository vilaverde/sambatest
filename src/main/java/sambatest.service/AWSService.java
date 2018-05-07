package sambatest.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;
import java.net.URL;

public class AWSService {

	private final String bucketName = "musa-sambatest-s3";
  private final String accessKey  = System.getenv("S3_ACCESS_KEY");
  private final String secretKey  = System.getenv("S3_SECRET_KEY");

  private BasicAWSCredentials credentials;
  private AmazonS3 s3Client;

	public AWSService() {
    this.credentials = new BasicAWSCredentials(accessKey, secretKey);
    this.s3Client = AmazonS3ClientBuilder
      .standard()
      .withCredentials(new AWSStaticCredentialsProvider(credentials))
      .withRegion(Regions.SA_EAST_1)
      .build();
  }

  public URL UploadFile(File file, String filename) throws AmazonServiceException, AmazonClientException{
    String keyName = "files/" + (filename.isEmpty() ? file.getName() : filename);

    s3Client
      .putObject(new PutObjectRequest(bucketName, keyName, file)
      .withCannedAcl(CannedAccessControlList.PublicRead));

    return this.s3Client.getUrl(bucketName, keyName);
  }
}
