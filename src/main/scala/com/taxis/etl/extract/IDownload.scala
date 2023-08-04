package com.taxis.etl.extract

import java.nio.file.{Files, Paths}
import scala.language.postfixOps
trait IDownload {
    def perform(filename: String): String
}
