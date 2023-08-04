package com.taxis.etl.extract

import com.taxis.etl.extract.Downloader.downloadFiles
import com.taxis.etl.extract.s3.DownloadS3Object
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client

import java.nio.file.{Files, Paths}

object Extract {
  def getFilenames(): Array[String] = {
    def folder: String = ReadFromEnv.readDownloadFolder()
    ReadFromEnv.readTaxisETLFileName().map((x: String) => folder + "/" + x)
  }

  def getDownloadedFilenames(): Array[String] = {
    performDownload()
    getFilenames()
  }

  def performDownload(): Unit = {
    if (ReadFromEnv.readDownloadFiles()) {
      def baseUrl: Option[String] = ReadFromEnv.readBaseUrl()
      def folder: String = ReadFromEnv.readDownloadFolder()
      def filenames : Array[String] = ReadFromEnv.readTaxisETLFileName()

      val s3Client = S3Client.builder.region(Region.US_EAST_2).build

      def path = Paths.get(folder)

      if (!(Files.exists(path) && Files.isDirectory(path))) {
        Files.createDirectory(path)
      }
      new DownloadS3Object(s3Client, "prft-etl-testing", path).perform(filenames)
    }
  }

    def main(args: Array[String]): Unit = {
      performDownload()
    }
}
