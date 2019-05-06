<%@ page import="omar.cucumber.ingest.test.CucumberTest"%>
<html>
<head>
</head>
<body>
<%!
  public void callIngest() {
    CucumberTest ingest = new CucumberTest();
    ingest.startTest();
  }
%>
<%

  String requestMethod = request.getMethod();
  if(requestMethod.equals("POST")){
    callIngest();
  }
%>
<form name="ingestTestForm" action="#" method="post">
  <input type="submit" id="ingestTestBtn" value="Run Test">
</form>
</body>
</html>