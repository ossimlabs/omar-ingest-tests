@staging_service
Feature: StagingService

    Scenario: [STG--02] Make a GeoEye MSI GeoTIFF image available for discovery
        Given a GeoEye MSI GeoTIFF is not already staged
        When a GeoEye MSI GeoTIFF AVRO message is placed on the SQS
        Then a GeoEye MSI GeoTIFF should be discoverable

    Scenario: [STG-04] Make a GeoEye MSI NITF21 image available for discovery
        Given a GeoEye MSI NITF21 is not already staged
        When a GeoEye MSI NITF21 AVRO message is placed on the SQS
        Then a GeoEye MSI NITF21 should be discoverable

    Scenario: [STG-01] Make a GeoEye PAN GeoTIFF image available for discovery
        Given a GeoEye PAN GeoTIFF is not already staged
        When a GeoEye PAN GeoTIFF AVRO message is placed on the SQS
        Then a GeoEye PAN GeoTIFF should be discoverable

    Scenario: [STG-03] Make a GeoEye PAN NITF21 image available for discovery
        Given a GeoEye PAN NITF21 is not already staged
        When a GeoEye PAN NITF21 AVRO message is placed on the SQS
        Then a GeoEye PAN NITF21 should be discoverable

    Scenario: [STG-17] Make an IKONOS PAN NITF image with error model available for discovery
        Given an IKONOS PAN NITF is not already staged
        When an IKONOS PAN NITF AVRO message is placed on the SQS
        Then an IKONOS PAN NITF should be discoverable

    @C2S
    Scenario: Make an NTM IR NITF image with error model available for discovery
        Given an NTM IR NITF is not already staged
        When an NTM IR NITF AVRO message is placed on the SQS
        Then an NTM IR NITF should be discoverable

    @C2S
    Scenario: Make an NTM PAN NITF image with error model available for discovery
        Given an NTM PAN NITF is not already staged
        When an NTM PAN NITF AVRO message is placed on the SQS
        Then an NTM PAN NITF should be discoverable

    @C2S
    Scenario: Make an NTM SAR NITF image with error model available for discovery
        Given an NTM SAR NITF is not already staged
        When an NTM SAR NITF AVRO message is placed on the SQS
        Then an NTM SAR NITF should be discoverable

    Scenario: [STG-06] Make a QuickBird MSI GeoTIFF image available for discovery
        Given a QuickBird MSI GeoTIFF is not already staged
        When a QuickBird MSI GeoTIFF AVRO message is placed on the SQS
        Then a QuickBird MSI GeoTIFF should be discoverable

    Scenario: [STG-05] Make a QuickBird PAN GeoTIFF image available for discovery
        Given a QuickBird PAN GeoTIFF is not already staged
        When a QuickBird PAN GeoTIFF AVRO message is placed on the SQS
        Then a QuickBird PAN GeoTIFF should be discoverable

#    Scenario: [STG-09] Make a RapidEye MSI GeoTIFF image available for discovery
#        Given a RapidEye MSI GeoTIFF is not already staged
#        When a RapidEye MSI GeoTIFF AVRO message is placed on the SQS
#        Then a RapidEye MSI GeoTIFF should be discoverable

    Scenario: [STG-10] Make a TerraSAR-X SAR NITF20 image available for discovery
        Given a TerraSAR-X SAR NITF20 is not already staged
        When a TerraSAR-X SAR NITF20 AVRO message is placed on the SQS
        Then a TerraSAR-X SAR NITF20 should be discoverable

    Scenario: [STG-13] Make a WorldView2 MSI GeoTIFF image available for discovery
        Given a WorldView2 MSI GeoTIFF is not already staged
        When a WorldView2 MSI GeoTIFF AVRO message is placed on the SQS
        Then a WorldView2 MSI GeoTIFF should be discoverable

    Scenario: [STG-12] Make a WorldView2 PAN GeoTIFF image available for discovery
        Given a WorldView2 PAN GeoTIFF is not already staged
        When a WorldView2 PAN GeoTIFF AVRO message is placed on the SQS
        Then a WorldView2 PAN GeoTIFF should be discoverable

    Scenario: Make another WorldView2 PAN GeoTIFF image available for discovery
        Given another WorldView2 PAN GeoTIFF is not already staged
        When another WorldView2 PAN GeoTIFF AVRO message is placed on the SQS
        Then another WorldView2 PAN GeoTIFF should be discoverable

    Scenario: [STG-14] Make a WorldView2 PAN NITF20 image available for discovery
        Given a WorldView2 PAN NITF20 is not already staged
        When a WorldView2 PAN NITF20 AVRO message is placed on the SQS
        Then a WorldView2 PAN NITF20 should be discoverable
