package com.taxis.etl.extract

import software.amazon.awssdk.services.s3.S3Client
import scala.language.postfixOps

class S3Download(s3Client: S3Client, bucketName: String) extends IDownload {
  def perform(filenames: Array[String]): Unit = {
    filenames.foreach((filename: String) => {
      System.out.println("Downloading " + filename + " from s3 bucket " + bucketName)
    })
  }
}
