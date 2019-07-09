# @local_staging_service
# Feature: StagingService
    Scenario: [DSTG-01] Make a local hsi envi image available for discovery
        Given a local hsi envi image is not already indexed
        When a local hsi envi image is indexed into OMAR
        Then a local hsi envi image should be available
