@staging_service
Feature: StagingService

    Scenario: [STG--02] Make a GeoEye MSI GeoTIFF image available for discovery
        Given a GeoEye MSI GeoTIFF is not already staged
        When its AVRO message is placed on the SQS
        Then it should be discoverable
        And it should have a thumbnail
        And a WMS call should produce an image

    Scenario: [STG-04] Make a GeoEye MSI NITF21 image available for discovery
        Given a GeoEye MSI NITF21 is not already staged
        When its AVRO message is placed on the SQS
        Then it should be discoverable
        And it should have a thumbnail
        And a WMS call should produce an image

    Scenario: [STG-01] Make a GeoEye PAN GeoTIFF image available for discovery
        Given a GeoEye PAN GeoTIFF is not already staged
        When its AVRO message is placed on the SQS
        Then it should be discoverable
        And it should have a thumbnail
        And a WMS call should produce an image

    Scenario: [STG-03] Make a GeoEye PAN NITF21 image available for discovery
        Given a GeoEye PAN NITF21 is not already staged
        When its AVRO message is placed on the SQS
        Then it should be discoverable
        And it should have a thumbnail
        And a WMS call should produce an image

    Scenario: [STG-17] Make an IKONOS PAN NITF image with error model available for discovery
        Given an IKONOS PAN NITF is not already staged
        When its AVRO message is placed on the SQS
        Then it should be discoverable
        And it should have a thumbnail
        And a WMS call should produce an image

    @C2S
    Scenario: Make an NTM IR NITF image with error model available for discovery
        Given an NTM IR NITF is not already staged
        When its AVRO message is placed on the SQS
        Then it should be discoverable
        And it should have a thumbnail
        And a WMS call should produce an image

    @C2S
    Scenario: Make an NTM PAN NITF image with error model available for discovery
        Given an NTM PAN NITF is not already staged
        When its AVRO message is placed on the SQS
        Then it should be discoverable
        And it should have a thumbnail
        And a WMS call should produce an image

    @C2S
    Scenario: Make an NTM SAR NITF image with error model available for discovery
        Given an NTM SAR NITF is not already staged
        When its AVRO message is placed on the SQS
        Then it should be discoverable
        And it should have a thumbnail
        And a WMS call should produce an image

    Scenario: [STG-06] Make a QuickBird MSI GeoTIFF image available for discovery
        Given a QuickBird MSI GeoTIFF is not already staged
        When its AVRO message is placed on the SQS
        Then it should be discoverable
        And it should have a thumbnail
        And a WMS call should produce an image

    Scenario: [STG-05] Make a QuickBird PAN GeoTIFF image available for discovery
        Given a QuickBird PAN GeoTIFF is not already staged
        When its AVRO message is placed on the SQS
        Then it should be discoverable
        And it should have a thumbnail
        And a WMS call should produce an image

    # Scenario: [STG-09] Make a RapidEye MSI GeoTIFF image available for discovery
    #     Given a RapidEye MSI GeoTIFF is not already staged
    #     When its AVRO message is placed on the SQS
    #     Then it should be discoverable
    #     And it should have a thumbnail
    #     And a WMS call should produce an image

    Scenario: [STG-10] Make a TerraSAR-X SAR NITF20 image available for discovery
        Given a TerraSAR-X SAR NITF20 is not already staged
        When its AVRO message is placed on the SQS
        Then it should be discoverable
        And it should have a thumbnail
        And a WMS call should produce an image

    Scenario: [STG-13] Make a WorldView2 MSI GeoTIFF image available for discovery
        Given a WorldView2 MSI GeoTIFF is not already staged
        When its AVRO message is placed on the SQS
        Then it should be discoverable
        And it should have a thumbnail
        And a WMS call should produce an image

    Scenario: [STG-12] Make a WorldView2 PAN GeoTIFF image available for discovery
        Given a WorldView2 PAN GeoTIFF is not already staged
        When its AVRO message is placed on the SQS
        Then it should be discoverable
        And it should have a thumbnail
        And a WMS call should produce an image

    Scenario: Make another WorldView2 PAN GeoTIFF image available for discovery
        Given another WorldView2 PAN GeoTIFF is not already staged
        When its AVRO message is placed on the SQS
        Then it should be discoverable
        And it should have a thumbnail
        And a WMS call should produce an image

    Scenario: [STG-14] Make a WorldView2 PAN NITF20 image available for discovery
        Given a WorldView2 PAN NITF20 is not already staged
        When its AVRO message is placed on the SQS
        Then it should be discoverable
        And it should have a thumbnail
        And a WMS call should produce an image
