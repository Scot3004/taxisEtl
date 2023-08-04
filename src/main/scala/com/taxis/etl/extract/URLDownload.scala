package com.taxis.etl.extract

import sys.process._
import java.net.URL
import java.io.File

import scala.language.postfixOps
class URLDownload(baseUrl: String) extends IDownload {
  def perform(filename: String): String = {
    System.out.println("Downloading " + filename + " from " + baseUrl)
    new URL(baseUrl + "/" + filename) #> new File(filename) !!
  }
}
