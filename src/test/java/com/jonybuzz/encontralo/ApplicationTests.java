package com.jonybuzz.encontralo;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@SpringBootTest
@Transactional
public abstract class ApplicationTests {

  protected String getContentFromFile(String fileName) throws IOException {
    File file = new ClassPathResource(fileName).getFile();
    return FileCopyUtils.copyToString(new FileReader(file));
  }


}
