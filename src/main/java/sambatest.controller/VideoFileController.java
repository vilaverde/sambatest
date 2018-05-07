package sambatest.controller;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.json.JSONObject;

import sambatest.service.VideoFileService;

@RestController
public class VideoFileController {

  @PostMapping("/decode")
  public ResponseEntity<?> decode(
    @RequestParam("file") MultipartFile mFileParam,
    RedirectAttributes redirectAttributes) {

    if (mFileParam == null || redirectAttributes == null)
      return ResponseEntity.badRequest().build();

    try {
      VideoFileService vfs = new VideoFileService();
      vfs.Decode(mFileParam);

      JSONObject body = new JSONObject();
      body.put("encodedUrl", vfs.getEncodedUrl());
      body.put("decodedUrl", vfs.getDecodedUrl());

      return new ResponseEntity<>(body.toString(), HttpStatus.OK);
    }
    catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

  }
}
