import os
from dotenv import load_dotenv
from pyspark.sql import SparkSession
from pyspark import SparkConf


load_dotenv()

conf = SparkConf()
conf.set('spark.jars.packages', 'org.apache.hadoop:hadoop-aws:3.3.4,com.amazonaws:aws-java-sdk-bundle:1.12.262')
spark = SparkSession.builder.appName("DemoS3").config(conf=conf).getOrCreate()

bucket = os.getenv("AWS_S3_BUCKET")
print("Bucket {}".format(bucket))
filenames = os.getenv("TAXIS_ETL_FILES").split(",")
for key in filenames:
    print("s3a://{}/{}".format(bucket, key))
    df = spark.read.parquet("s3a://{}/{}".format(bucket, key))

spark.stop() 