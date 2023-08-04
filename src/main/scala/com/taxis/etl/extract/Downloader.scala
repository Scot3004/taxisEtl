package com.taxis.etl.extract

import java.nio.file.{Files, Paths}

object Downloader {
  def downloadFiles(download: IDownload): Unit = {
    def folder: String = ReadFromEnv.readDownloadFolder()
    def path = Paths.get(folder)
    if (!(Files.exists(path) && Files.isDirectory(path)))
      Files.createDirectory(path)
    ReadFromEnv.readTaxisETLFileName().foreach((filename: String) => {
      download.perform(filename)
    })
  }
}