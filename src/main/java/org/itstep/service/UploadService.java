package org.itstep.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Service
@Slf4j
public class UploadService implements ServletContextAware {
  private ServletContext servletContext;

  public String saveImage(MultipartFile multipartFile) throws IOException {
    String filename = multipartFile.getOriginalFilename();
    String dir = servletContext.getRealPath("/resources/img/blog-img");
    log.info("Image dir: " + dir);
    multipartFile.transferTo(Paths.get(dir, filename));
    return filename;
  }

  @Override
  public void setServletContext(ServletContext servletContext) {
    this.servletContext = servletContext;
  }
}
