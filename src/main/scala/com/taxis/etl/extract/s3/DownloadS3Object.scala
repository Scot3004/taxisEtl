package com.taxis.etl.extract.s3

import software.amazon.awssdk.core.ResponseInputStream
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.{GetObjectRequest, GetObjectResponse}

import java.io.File
import java.nio.file.{Files, StandardCopyOption}

class DownloadS3Object(s3Client: S3Client, bucketName: String) {
    def perform(filename: String): Unit = {
        val getS3Objects = GetObjectRequest
            .builder()
            .bucket(bucketName)
            .key(filename)
            .build()
        val initialStream: ResponseInputStream[GetObjectResponse] = s3Client.getObject(getS3Objects)
        val targetFile = new File(filename)
        Files.copy(initialStream, targetFile.toPath, StandardCopyOption.REPLACE_EXISTING)
    }
}
