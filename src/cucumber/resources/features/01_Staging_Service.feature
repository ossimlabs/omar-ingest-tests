@staging_service
Feature: StagingService

    Scenario Outline: [STG-0] Make an image available for discovery
        Given <index> <platform> <sensor> <format> is not already staged
        When <index> <platform> <sensor> <format> AVRO message is placed on the SQS
        Then <index> <platform> <sensor> <format> should be discoverable
        Examples:
        | index   | platform   | sensor | format   |
        | a       | GeoEye     | MSI    | GeoTIFF  |
        | a       | GeoEye     | MSI    | NITF21   |
        | a       | GeoEye     | PAN    | GeoTIFF  |
        | a       | GeoEye     | PAN    | NITF21   |
        | a       | GeoEye     | PAN    | NITF     |
        | a       | IKONOS     | PAN    | NITF     |
        | a       | NTM        | IR     | NITF     |
        | a       | NTM        | PAN    | NITF     |
        | a       | NTM        | SAR    | NITF     |
        | a       | QuickBird  | MSI    | GeoTIFF  |
        | a       | QuickBird  | PAN    | GeoTIFF  |
#        | a       | RapidEye   | MSI    | GeoTIFF  |
#        | a       | TerraSAR   | SAR    | NITF20   |
        | a       | WorldView2 | MSI    | GeoTIFF  |
        | a       | WorldView2 | PAN    | GeoTIFF  |
        | another | WorldView2 | PAN    | GeoTIFF  |
        | a       | WorldView2 | PAN    | NITF20   |
