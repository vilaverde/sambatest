package sambatest.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class UploadFileService {

	public UploadFileService() { }

  public static URL SendToS3(File file, String filename) {
    AWSService aws = new AWSService();
    URL url = aws.UploadFile(file, filename);
    return url;
  }

  public static URL SendToZencoder(URL videoFileUrl) throws IOException {
    ZencoderService zcs = new ZencoderService();
    URL url = zcs.ProcessVideo(videoFileUrl);
    return url;
  }
}
