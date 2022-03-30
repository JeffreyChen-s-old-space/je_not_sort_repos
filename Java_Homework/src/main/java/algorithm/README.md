## 使用文檔 doc

### gui package

#### main package

* MainForm 畫面進入點

#### sort_gui package

* SortGUI 排序功能

#### student_data_gui package

* EditStudentDataGUI 新增修改刪除學生資料
* ShowStudentDataGUI 顯示學生資料

#### GUIFather

* GUI's superclass

### sort package

* SortAlgorithm Interface 定義應該實作的排序
* SortUtils Sort的功能類別，包含輸出，比較，交換
* InsertionSort implements SortAlgorithm <br> Insertion sort T[]  extends comparable
* MergeSort implements SortAlgorithm <br> merge sort T[] extends comparable
* RadixSort implements SortAlgorithm <br> Radix sort T[] extends comparable or int []

### student package

* StudentData 資料結構 學生資料
* StudentDataProcess 處理文字檔至學生資料

### util package

* File Interface 尚未使用
* FileIO 檔案讀取
* FileUtils 檔案功能

### Main

* 程式進入點 & 執行進入點，只會開啟GUI，只有一行

### README.md

* 使用文檔

### info

* 如果無法使用 Eclipse 編譯請使用 Intellij 編譯
* 每個排序演算法裡都有main 可以執行
* src/test/java/algorithm 裡面有unit test
* 檔案需要事先建好再讀進去
* 資料結構有更改 增強功能 改為可以排序的HashMap<String, TreeMap<TreeMapKey, TreeMapValue>>
