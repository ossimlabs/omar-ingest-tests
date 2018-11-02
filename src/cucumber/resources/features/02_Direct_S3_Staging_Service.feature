@direct_s3_staging_service
Feature: StagingService

Scenario Outline: [DSTG-01] Make a given IMAGE available for discovery
  Given the image <image-name> is not already staged - direct s3 ingest
  When the image <image-name> avro message is placed on the SQS - direct s3 ingest
  Then the image <image-name> should be discoverable - direct s3 ingest

  Examples:
    | image-name |
    | s3://o2-test-data/direct-test/celtic/007/po_105215_pan_0000000.ntf |
    | /data/direct-test/celtic/staged/007/po_105215_pan_0000000.ntf |