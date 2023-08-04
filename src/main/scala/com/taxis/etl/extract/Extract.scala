package com.taxis.etl.extract

import com.taxis.etl.extract.Downloader.downloadFiles

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
            if (baseUrl.isDefined) {
                downloadFiles(new URLDownload(baseUrl.get))
            } else {
                throw new IllegalArgumentException("Base url was not defined")
            }
        }
    }

    def main(args: Array[String]): Unit = {
        System.out.println(getDownloadedFilenames().mkString("Array(", ", ", ")"))
    }
}
