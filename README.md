1. Create a service account in google cloud platform.
2. Create a service account key for that service account.
3. Save the key to your computer.
4. Enable the Google Sheets API in google cloud platform.
5. Execute:

```
GOOGLE_APPLICATION_DEFAULT=/path/to/key.json ./gradlew runApp
```

The application will fail and print an error message. Navigate to `src/main/scala/Reproduction.scala` and switch the commented out code. Re run the command above. It will not fail and not print anything.
