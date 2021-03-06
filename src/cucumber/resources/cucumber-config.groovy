targetDeployment = System.getenv("TARGET_DEPLOYMENT")
if (!targetDeployment) {
   targetDeployment = "dev"
}
domainName = System.getenv("DOMAIN_NAME")
if (!domainName) {
   domainName = "ossim.io"
}

s3Bucket = "o2-test-data/Standard_test_imagery_set"
s3BucketUrl = "https://s3.amazonaws.com"

sqsStagingQueue = "NOT_ASSIGNED"
rbtcloudRootDir = "https://omar-${targetDeployment}.${domainName}"
switch(targetDeployment) {
   case "dev":
      sqsStagingQueue = "https://sqs.us-east-1.amazonaws.com/320588532383/avro-dev"
      break
   case "stage":
      sqsStagingQueue = "https://sqs.us-east-1.amazonaws.com/320588532383/avro-stage"
      break
   case "prod":
      sqsStagingQueue = "https://sqs.us-east-1.amazonaws.com/320588532383/avro-prod"
      break
   case "blue":
      sqsStagingQueue = "https://sqs.us-east-1.amazonaws.com/320588532383/avro-prod"
      break
   case "green":
      sqsStagingQueue = "https://sqs.us-east-1.amazonaws.com/320588532383/avro-prod"
      break
   case "rel":
      sqsStagingQueue = "https://sqs.us-east-1.amazonaws.com/320588532383/avro-release"
      break
   default:
      break
}
stagingService = "${rbtcloudRootDir}/omar-stager/dataManager"
wmsServerProperty = "${rbtcloudRootDir}/omar-wms/wms"
wfsServerProperty = "${rbtcloudRootDir}/omar-wfs/wfs"
wfsUrl = "${rbtcloudRootDir}/omar-wfs"
imageSpaceUrl = "${rbtcloudRootDir}/omar-oms/imageSpace"
sqsTimestampName = "Timestamp"
uname = System.getenv("CURL_USER_NAME")
pword = System.getenv("CURL_PASSWORD")
curlUname="${uname}:${pword}"

println("\nOMAR URL being tested: ${rbtcloudRootDir}\n")

waitForStage = 5 // minutes to wait
waitForStageMultiIngest = 30 // minutes to wait for all multi ingest images to stage
images = [
        geoeye: [
                msi: [
                        geotiff: [ "14AUG20010406-M1BS-053852449040_01_P001" ],
                        nitf21: [ "05FEB09OV05010005V090205M0001912264B220000100072M_001508507" ]
                ],
                pan: [
                        geotiff: [ "14AUG20010406-P1BS-053852449040_01_P001" ],
                        nitf21: [ "05FEB09OV05010005V090205P0001912264B220000100282M_001508507" ]
                ]
        ],
        ikonos: [
                pan: [
                        nitf: [ "po_106005_pan_0000000" ]
                ]
        ],
        ntm: [
                ir: [
                        nitf: [ "" ]
                ],
                pan: [
                        nitf: [ "" ]
                ],
                sar: [
                        nitf: [ "" ]
                ]
        ],
        quickbird: [
                msi: [
                        geotiff: [ "04DEC11050020-M2AS_R1C1-000000185964_01_P001" ]
                ],
                pan: [
                        geotiff: [ "04DEC11050020-P2AS_R1C1-000000185964_01_P001" ]
                ]
        ],
        "terrasar-x": [
                sar: [
                        nitf20: [ "14SEP15TS0107001_100021_SL0023L_25N121E_001X___SVV_0101_OBS_IMAG" ]
                ]
        ],
        worldview2: [
                msi: [
                        geotiff: [ "14SEP12113301-M1BS-053951940020_01_P001" ]
                ],
                pan: [
                        geotiff: [
                                "16MAY02111606-P1BS-055998375010_01_P013",
                                "16MAY02111607-P1BS-055998375010_01_P014"
                        ],
                        nitf20: [ "11MAR08WV010500008MAR11071429-P1BS-005707719010_04_P003" ]
                ]
        ],
        local: [
                hsi:[
                        envi:"/data/hsi/2012-06-11/AM/ALPHA/2012-06-11_18-20-11/HSI/Scan_00007/2012-06-11_18-20-11.HSI.Scan_00007.scene.corrected.hsi"
                ]
        ],
        remote: [
                quickbirdpan: [
                        nitf:[
                                s3: "s3://o2-test-data/direct-test/celtic/007/po_105215_pan_0000000.ntf",
                                mount: "/data/direct-test/celtic/staged/007/po_105215_pan_0000000.ntf",
                        ]
                ]
        ]
]
