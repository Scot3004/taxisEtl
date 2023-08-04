package com.taxis.etl.extract

import java.nio.file.{Files, Paths}

object Downloader {
  def downloadFiles(download: IDownload, folder: String): Unit = {
    def path = Paths.get(folder)
    if (!(Files.exists(path) && Files.isDirectory(path))) {
      Files.createDirectory(path)
      download.perform(ReadFromEnv.readTaxisETLFileName());
    }
  }
}