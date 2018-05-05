package sambatest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VideoFile {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String name;
  private String raw_file_path;
  private String decoded_file_path;

  protected VideoFile() {}

  public VideoFile(String raw_file_path, String decoded_file_path) {
    this.raw_file_path = raw_file_path;
    this.decoded_file_path = decoded_file_path;
    this.name = raw_file_path.substring(raw_file_path.lastIndexOf('/')+1);
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRawFilePath() {
    return raw_file_path;
  }

  public void setRawFilePath(String raw_file_path) {
    this.raw_file_path = raw_file_path;
  }

  public String getDecodedFilePath() {
    return decoded_file_path;
  }

  public void getDecodedFilePath(String decoded_file_path) {
    this.decoded_file_path = decoded_file_path;
  }
}
