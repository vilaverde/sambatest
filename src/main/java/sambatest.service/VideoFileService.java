package sambatest.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.springframework.web.multipart.MultipartFile;

public class VideoFileService {

  private URL s3encodedUrl;
  private URL s3decodedUrl;

  public VideoFileService() {}

  public void Decode(MultipartFile mFile) throws IOException {
    TemporaryFileService tfs = new TemporaryFileService(mFile);
    File tmpFile  = tfs.CreateTmpFile();
    s3encodedUrl = UploadFileService.SendToS3(tmpFile, "encoded-" + mFile.getOriginalFilename());

    URL dcdFileUrl = UploadFileService.SendToZencoder(s3encodedUrl);
    File dcdFile   = DownloadDecodedFile(dcdFileUrl);

    s3decodedUrl = UploadFileService.SendToS3(dcdFile, "decoded-" + mFile.getOriginalFilename());
  }

  private File DownloadDecodedFile(URL decodedFileUrl) throws IOException {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet(decodedFileUrl.toString());

    HttpResponse response = httpClient.execute(httpGet);
    HttpEntity entity = response.getEntity();

    TemporaryFileService tfs = new TemporaryFileService(null);
    File tmpFile = tfs.CreateTmpFile();

    try (FileOutputStream outstream = new FileOutputStream(tmpFile)) {
      entity.writeTo(outstream);
    }

    return tmpFile;
  }

  public String getEncodedUrl(){
    return this.s3encodedUrl.toString();
  }

  public String getDecodedUrl(){
    return this.s3decodedUrl.toString();
  }
}
