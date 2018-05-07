package sambatest.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class TemporaryFileService {

  private File tmpFile;
  private MultipartFile mFile;

  public TemporaryFileService(MultipartFile mFile) {
    this.mFile = mFile;
  }

  public File CreateTmpFile() throws IOException {
    String prefix = String.valueOf(System.currentTimeMillis());
    String suffix = ".tmp";

    tmpFile = File.createTempFile(prefix, suffix);
    tmpFile.deleteOnExit();

    if(this.mFile != null)
      mFile.transferTo(tmpFile);

    return tmpFile;
  }

  public File getTmpFile() {
    return tmpFile;
  }
}
