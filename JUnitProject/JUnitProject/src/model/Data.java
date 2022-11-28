package model;
import java.io.*;
class Data  {
    private String [][] elem;
    private int col, row;
    private int MaxSize=20;
    public Data(String inFileName)  {
        BufferedReader input=null;
        row=0;
        String [] temp = new String[MaxSize];
        String [] k;
        try {
            input  = new BufferedReader (new FileReader(new File(inFileName)));
            while ((temp[row++]=input.readLine())!=null) {   }
            row--;
            k = temp[0].split(",");
            col = k.length;
            elem = new String[row][col];
            for (int i=0;i<row; i++) {
                elem[i] = temp[i].split(",");
            }
        }
        catch (Exception e)  {
           System.out.println("Error:" + e);
           System.exit(1);
        }        
    }
    public String read(int x, int y) {
        return elem[x][y];	
    }	
    public int getRow() { return row; }
    public int getCol() { return col; } 
}