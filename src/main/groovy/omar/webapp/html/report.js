$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/01_Staging_Service.feature");
formatter.feature({
  "line": 2,
  "name": "StagingService",
  "description": "",
  "id": "stagingservice",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@staging_service"
    }
  ]
});
formatter.scenarioOutline({
  "line": 4,
  "name": "[STG-01] Make a given IMAGE available for discovery",
  "description": "",
  "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 5,
  "name": "the image \u003cimage-name\u003e is not already staged",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "the image \u003cimage-name\u003e avro message is placed on the SQS",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "the image \u003cimage-name\u003e should be discoverable",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "it should have a thumbnail",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "a WMS call should produce an image",
  "keyword": "And "
});
formatter.examples({
  "line": 11,
  "name": "",
  "description": "",
  "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery;",
  "rows": [
    {
      "cells": [
        "image-name"
      ],
      "line": 12,
      "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery;;1"
    },
    {
      "cells": [
        "2010-12-05T221358_RE2_3A-NAC_6683383_113276"
      ],
      "line": 13,
      "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery;;2"
    },
    {
      "cells": [
        "28APR16RS0207001_111844_SL0080R_28N082W_001C___SHH_0101_OBS_IMAG"
      ],
      "line": 14,
      "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery;;3"
    },
    {
      "cells": [
        "26JAN16TS0109001_130755_DS0110L_33N106W_001X___SVV_0101_OBS_IMAG"
      ],
      "line": 15,
      "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery;;4"
    },
    {
      "cells": [
        "16MAY02111607-P1BS-055998375010_01_P014"
      ],
      "line": 16,
      "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery;;5"
    },
    {
      "cells": [
        "16MAY02111606-P1BS-055998375010_01_P013"
      ],
      "line": 17,
      "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery;;6"
    },
    {
      "cells": [
        "14SEP12113301-M1BS-053951940020_01_P001"
      ],
      "line": 18,
      "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery;;7"
    },
    {
      "cells": [
        "14SEP15TS0107001_100021_SL0023L_25N121E_001X___SVV_0101_OBS_IMAG"
      ],
      "line": 19,
      "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery;;8"
    },
    {
      "cells": [
        "14OCT15165809-P1BS-053951940010_01_P001"
      ],
      "line": 20,
      "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery;;9"
    },
    {
      "cells": [
        "14OCT15165809-M1BS-053951940010_01_P001"
      ],
      "line": 21,
      "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery;;10"
    },
    {
      "cells": [
        "14AUG20010406-P1BS-053852449040_01_P001"
      ],
      "line": 22,
      "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery;;11"
    },
    {
      "cells": [
        "14AUG20010406-M1BS-053852449040_01_P001"
      ],
      "line": 23,
      "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery;;12"
    },
    {
      "cells": [
        "11MAR08WV010500008MAR11071429-P1BS-005707719010_04_P003"
      ],
      "line": 24,
      "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery;;13"
    },
    {
      "cells": [
        "05FEB09OV05010005V090205P0001912264B220000100282M_001508507"
      ],
      "line": 25,
      "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery;;14"
    },
    {
      "cells": [
        "05FEB09OV05010005V090205M0001912264B220000100072M_001508507"
      ],
      "line": 26,
      "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery;;15"
    },
    {
      "cells": [
        "04MAY16RS0208001_132348_SM0280R_31N111W_001C___SHH_0101_OBS_IMAG"
      ],
      "line": 27,
      "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery;;16"
    },
    {
      "cells": [
        "04DEC11050020-P2AS_R1C1-000000185964_01_P001"
      ],
      "line": 28,
      "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery;;17"
    },
    {
      "cells": [
        "04DEC11050020-M2AS_R1C1-000000185964_01_P001"
      ],
      "line": 29,
      "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery;;18"
    },
    {
      "cells": [
        "04APR16CS0207001_110646_SM0262R_29N081W_001X___SHH_0101_OBS_IMAG"
      ],
      "line": 30,
      "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery;;19"
    }
  ],
  "keyword": "Examples"
});
formatter.scenario({
  "line": 13,
  "name": "[STG-01] Make a given IMAGE available for discovery",
  "description": "",
  "id": "stagingservice;[stg-01]-make-a-given-image-available-for-discovery;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@staging_service"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "the image 2010-12-05T221358_RE2_3A-NAC_6683383_113276 is not already staged",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "the image 2010-12-05T221358_RE2_3A-NAC_6683383_113276 avro message is placed on the SQS",
  "matchedColumns": [
    0
  ],
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "the image 2010-12-05T221358_RE2_3A-NAC_6683383_113276 should be discoverable",
  "matchedColumns": [
    0
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "it should have a thumbnail",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "a WMS call should produce an image",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "2010-12-05T221358_RE2_3A-NAC_6683383_113276",
      "offset": 10
    }
  ],
  "location": "Staging_StepDef.groovy:77"
});
