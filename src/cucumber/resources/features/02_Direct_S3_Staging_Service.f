@direct_s3_staging_service
Feature: StagingService

    Scenario: [DSTG-01] Make an AWS Remote QuickBirdPan NITF Direct S3 image available for discovery
        Given an AWS Remote QuickBirdPan NITF S3 image is not already indexed
        When an AWS Remote QuickBirdPan NITF S3 image is indexed into OMAR
        Then an AWS Remote QuickBirdPan NITF S3 image should be available

    Scenario: [DSTG-02] Make an AWS Remote QuickBirdPan NITF Direct mount image available for discovery
        Given an AWS Remote QuickBirdPan NITF mount image is not already indexed
        When an AWS Remote QuickBirdPan NITF mount image is indexed into OMAR
        Then an AWS Remote QuickBirdPan NITF mount image should be available
