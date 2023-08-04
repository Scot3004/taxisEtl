package com.taxis.etl.extract

import software.amazon.awssdk.core.ResponseInputStream
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.{GetObjectRequest, GetObjectResponse}

import java.io.File
import java.nio.file.{Files, StandardCopyOption}
import scala.language.postfixOps

class S3Download(s3Client: S3Client, bucketName: String) extends IDownload {
  def perform(filename: String): String = {
    System.out.println("Downloading " + filename + " from s3 bucket " + bucketName)
    val getS3Objects = GetObjectRequest
      .builder()
      .bucket(bucketName)
      .key(filename)

      .build()
    val initialStream: ResponseInputStream[GetObjectResponse] = s3Client.getObject(getS3Objects)
    val targetFile = new File(filename)
    Files.copy(initialStream, targetFile.toPath, StandardCopyOption.REPLACE_EXISTING)
    filename
  }
}
