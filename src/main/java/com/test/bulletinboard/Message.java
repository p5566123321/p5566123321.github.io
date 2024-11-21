package com.test.bulletinboard;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

import jakarta.persistence.Lob;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Message {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String title;

  private LocalDate publicDate;

  private LocalDate expirationDate;

  private String publisher;

  private String content;

  private String fileName;

  private String fileContentType;

  @Lob
  private byte[] fileContent;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPublicDate() {
    return publicDate.toString();
  }

  public void setPublicDate(String publicDate) {
    this.publicDate = LocalDate.parse(publicDate);
  }

  public String getExpirationDate() {
    return expirationDate.toString();
  }

  public void setExpirationDate(String expirationDate) {
    this.expirationDate = LocalDate.parse(expirationDate);
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getFileName() {
    return fileName;
  }
  public void setFileName(String name) {
    fileName = name;
  }

  public byte[] getFileContent() {
    return fileContent;
  }

  public void setFileContent(byte[] content) {
    if (content != null) {
      this.fileContent = content;
    }
  }

  public String getFileContentType() {
    return fileContentType;
  }

  public void setFileContentType(String type) {
    this.fileContentType = type;
  }
}