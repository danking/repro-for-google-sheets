import com.google.api.services.sheets.v4.model.{Sheet, Spreadsheet}
import com.google.api.services.sheets.v4.SheetsScopes
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.auth.oauth2.GoogleCredentials
import com.google.auth.http.HttpCredentialsAdapter
import com.google.api.services.sheets.v4.Sheets
import collection.JavaConverters._
import java.io.FileInputStream
import com.google.api.client.json.gson.GsonFactory

object Reproduction {
  def main(args: Array[String]): Unit = {
    val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
    val gsonFactory = GsonFactory.getDefaultInstance()
    // THIS FAILS:
    val credentials = GoogleCredentials.getApplicationDefault().createScoped(
      List(SheetsScopes.SPREADSHEETS_READONLY).asJava)
    // THIS WORKS:
    // val credentials: GoogleCredentials = 
    //   GoogleCredentials.fromStream(new FileInputStream(sys.env("GOOGLE_APPLICATION_DEFAULT"))).createScoped(
    //     List(SheetsScopes.SPREADSHEETS).asJava
    //   )
    val sheetsService = new Sheets.Builder(httpTransport, gsonFactory, new HttpCredentialsAdapter(credentials))
      .setApplicationName("Name")
      .build()
    val spreadsheet: Spreadsheet = sheetsService.spreadsheets().get("1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms").execute()
  }
}
