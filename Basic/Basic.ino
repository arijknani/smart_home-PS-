

#include <ESP8266WiFi.h>
#include <Firebase_ESP_Client.h>

#define WIFI_SSID "TT_6598"
#define WIFI_PASSWORD "kl5941k7i6"
#define DATABASE_URL "gaz-esp-default-rtdb.firebaseio.com" 
FirebaseData fbdo;
FirebaseAuth auth;
FirebaseConfig config;


FirebaseJson json;
int sensor=A0;
int gas_value;

void setup()
{

    Serial.begin(38400);

    WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
    Serial.print("Connecting to Wi-Fi");
    while (WiFi.status() != WL_CONNECTED)
    {
        Serial.print(".");
        delay(300);
    }
    Serial.println();
    Serial.print("Connected with IP: ");
    Serial.println(WiFi.localIP());
    Serial.println();

    Serial.printf("Firebase Client v%s\n\n", FIREBASE_CLIENT_VERSION);

    config.database_url = DATABASE_URL;

    config.signer.test_mode = true;
    Firebase.reconnectWiFi(true);
    Firebase.begin(&config, &auth);

    pinMode(sensor,INPUT);
    
}

void loop()
{
  FirebaseJson json;
  
  gas_value=analogRead(sensor);
  Serial.println(gas_value);

 
  json.add("gaz", gas_value);

  Firebase.RTDB.pushJSON(&fbdo, "/",&json.set("gaz", gas_value));  

}