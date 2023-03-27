## 天氣資料欄位說明

---

### 一般天氣預報 - 七天天氣預報

* locationName : 城市名
* startTime : 開始時間
* endTime : 結束時間
    * elementName Wx : Wx 屬性
        * Wx : 天氣現象
            * parameterName : 天氣狀況
            * parameterValue : 上面的編碼 ex 晴時多雲:2,多雲:4
    * elementName MaxT : MaxT 屬性
        * MaxT : 最高溫度
            * parameterName : 最高溫度
            * parameterUnit : 屬性單位
            * startTime : 開始時間
            * endTime : 結束時間
    * elementName MinT : MinT 屬性
        * MinT : 最低溫度
            * startTime : 開始時間
            * endTime : 結束時間
            * parameterName : 最低溫度
            * parameterUnit : 屬性單位
    
<br>
* 總共14個 早上6點至晚上6點 及 晚上6點至早上6點

---

### 一般天氣預報 - 一週縣市天氣預報

* locationName : 城市名
* startTime : 開始時間
* endTime : 結束時間
    * elementName Wx : Wx 屬性
        * Wx : 天氣現象
            * parameterName : 天氣狀況
            * parameterValue : 上面的編碼 ex 晴時多雲:2,多雲:4
    * elementName MaxT : MaxT 屬性
        * MaxT : 最高溫度
            * parameterName : 最高溫度
            * parameterUnit : 屬性單位
            * startTime : 開始時間
            * endTime : 結束時間
    * elementName MinT : MinT 屬性
        * MinT : 最低溫度
            * startTime : 開始時間
            * endTime : 結束時間
            * parameterName : 最低溫度
            * parameterUnit : 屬性單位

---

#### 天氣特報

* locationName : 城市名
* geocode : 地理編碼
* hazardConditions : 危害原因 正常為null

---