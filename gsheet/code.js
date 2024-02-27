function doPost(e) {
  const sheet = SpreadsheetApp.getActiveSpreadsheet().getActiveSheet();
  const data = JSON.parse(e.postData.contents);

  sheet.appendRow([
    data.id,
    data.key,
    data.postTime,
    data.packageName,

    data.extras?.title,
    data.extras?.text,
    data.extras?.bigText,
    data.extras?.infoText,
    data.extras?.subText,
    data.extras?.summaryText,
  ]);

  return ContentService.createTextOutput(
    JSON.stringify({ result: "success" }),
  ).setMimeType(ContentService.MimeType.JSON);
}
