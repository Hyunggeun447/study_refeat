package solo.studyRefeat.domain.common.uploadS3;

import java.awt.image.BufferedImage;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

  String uploadImg(MultipartFile multipartFile);

  String uploadImgToS3(BufferedImage image, String Filename, String ext);

  void delete(String path);

  private String getUUID() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }
}
