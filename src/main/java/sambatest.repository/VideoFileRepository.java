package sambatest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sambatest.model.VideoFile;

public interface VideoFileRepository extends CrudRepository<VideoFile, Long> {

}
