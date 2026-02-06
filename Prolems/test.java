#include <EEPROM.h>
#include "GravityTDS.h"
#include <LiquidCrystal.h>
#include <OneWire.h>
#include <DallasTemperature.h>
#include <LedControl.h>

LiquidCrystal lcd(10, 9, 5, 4, 3, 2);

// ------------------- TDS -------------------
#define TDS_PIN A1
GravityTDS gravityTds;
float tdsValue = 0;

// ------------------- DS18B20 -------------------
#define ONE_WIRE_BUS 7
OneWire oneWire(ONE_WIRE_BUS);
DallasTemperature sensors( & oneWire);

// ------------------- LED MATRIX -------------------
LedControl lc = LedControl(11, 13, 12, 4);

// ------------------- BITMAP CHỮ -------------------
byte T[8] = { B11111111, B11111111, B00011000, B00011000, B00011000, B00011000, B00011000, B00011000 };
byte E[8] = { B11111111, B11000000, B11000000, B11111111, B11111111, B11000000, B11111111, B11111111 };
byte M[8] = { B11000011, B11100111, B11111111, B11111111, B11011011, B11000011, B11000011, B11000011 };
byte P[8] = { B11111100, B11000011, B11000011, B11111100, B11000000, B11000000, B11000000, B11000000 };
byte D[8] = { B11111110, B11000011, B11000011, B11000011, B11000011, B11000011, B11000011, B11111110 };
byte S[8] = { B01111111, B11000000, B11000000, B11111110, B01111111, B00000011, B00000011, B11111110 };
byte C[8] = { B01111111, B11000000, B11000000, B11000000, B11000000, B11000000, B11000000, B01111111 };

// ------------------- KÝ TỰ ĐẶC BIỆT -------------------
byte degree_char[8] = { B00111000, B00111000, B00000000, B00000000, B00000000, B00000000, B00000000, B00000000 };

// ------------------- BITMAP SỐ -------------------
byte num0[8] = { B01111110, B11000011, B11000011, B11000011, B11000011, B11000011, B11000011, B01111110 };
byte num1[8] = { B00011000, B00111000, B01111000, B00011000, B00011000, B00011000, B00011000, B11111111 };
byte num2[8] = { B00111100, B01100110, B11000110, B00001100, B00011000, B00110000, B01100000, B11111111 };
byte num3[8] = { B00111100, B01100110, B11000110, B00011100, B00011100, B11000110, B01100110, B00111100 };
byte num4[8] = { B00001100, B00011100, B00111100, B01101100, B11001100, B11111111, B00001100, B00001100 };
byte num5[8] = { B11111110, B11000000, B11111100, B00000110, B00000110, B00000110, B11000110, B01111100 };
byte num6[8] = { B00111100, B01100000, B11000000, B11111100, B11000110, B11000110, B01100110, B00111100 };
byte num7[8] = { B11111111, B11000110, B00001100, B00001100, B00011000, B00011000, B00110000, B00110000 };
byte num8[8] = { B00111100, B01100110, B01100110, B00111100, B01100110, B01100110, B01100110, B00111100 };
byte num9[8] = { B00111100, B01100110, B01100110, B00111110, B00000110, B00001100, B00011000, B00110000 };

byte * digits[] = { num0, num1, num2, num3, num4, num5, num6, num7, num8, num9 };

// ------------------- HÀM -------------------
void showChar(int device, byte ch[8]) {
    for (int row = 0; row < 8; row++) {
        lc.setRow(device, row, ch[row]);
    }
}

void clearAll() {
    for (int i = 0; i < 4; i++) lc.clearDisplay(i);
}

// ------------------- SETUP -------------------
void setup() {
    Serial.begin(9600);
    sensors.begin();

    gravityTds.setPin(TDS_PIN);
    gravityTds.setAref(5.0);
    gravityTds.setAdcRange(1024);
    gravityTds.begin();

    for (int i = 0; i < 4; i++) {
        lc.shutdown(i, false);
        lc.setIntensity(i, 6);
        lc.clearDisplay(i);
    }

    lcd.begin(16, 2);
    lcd.clear();
    lcd.print("Init...");
    delay(1000);
}

// ------------------- LOOP -------------------
void loop() {
    // 1. Đọc nhiệt độ
    sensors.requestTemperatures();
    float tempC = sensors.getTempCByIndex(0);

    // 2. Cập nhật TDS
    gravityTds.setTemperature(tempC);
    gravityTds.update();
    tdsValue = gravityTds.getTdsValue();

    Serial.print("Temp: ");
    Serial.print(tempC);
    Serial.print(" C, TDS: ");
    Serial.println(tdsValue);

    // -------- HIỂN THỊ NHIỆT ĐỘ --------
    // Hiển thị chữ TEMP
    clearAll();
    showChar(3, T);
    showChar(2, E);
    showChar(1, M);
    showChar(0, P);
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("TEMP");
    delay(1000);

    // Hiển thị số nhiệt độ
    clearAll();
    int tDigit1 = ((int)tempC / 10) % 10;
    int tDigit2 = ((int)tempC % 10);
    showChar(3, digits[tDigit1]);
    showChar(2, digits[tDigit2]);
    showChar(1, degree_char);
    showChar(0, C);
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("Temp: ");
    lcd.print(tempC, 1);
    lcd.print((char)223);
    lcd.print("C");
    delay(2000);

    // -------- HIỂN THỊ TDS --------
    // Hiển thị chữ TDS
    clearAll();
    showChar(3, T);
    showChar(2, D);
    showChar(1, S);
    lcd.setCursor(0, 1);
    lcd.print("TDS:");
    delay(500);

    // Hiển thị số TDS
    clearAll();
    int tdsInt = (int)tdsValue;
    int d3 = (tdsInt / 100) % 10;
    int d2 = (tdsInt / 10) % 10;
    int d1 = tdsInt % 10;
    showChar(3, digits[d3]);
    showChar(2, digits[d2]);
    showChar(1, digits[d1]);
    lcd.setCursor(0, 1);
    lcd.print("TDS: ");
    lcd.print(tdsInt);
    lcd.print(" ppm");
    delay(2000);
}