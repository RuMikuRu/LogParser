package source;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Parser {
    private FileReader file;
    private final double percent_Available;
    private final double time;

    public Parser(String fileName, double percent_Available, double time) {
        this.percent_Available = percent_Available;
        this.time = time;
        try {
            file = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> makeStringArrayList() {
        Scanner reader = new Scanner(file);
        ArrayList<String> strArray = new ArrayList<>();
        while (reader.hasNextLine()) {
            strArray.add(reader.nextLine());
        }
        return strArray;
    }

    public String search(String strSearch, int index)
    {
        String[] strSearchArray = strSearch.split(" ");
        return strSearchArray[index];

    }

    int counterError(int indexError, int i)
    {
        if(indexError==-1)
        {
            return i;
        }
        return indexError;
    }

    public String[] getInterval(String str)
    { 
       String[] str2 = {""};
       str2 = str.split(":");
        return new String[]{str2[1],str2[2],str2[3]};
    }
//TODO Завести indexError2(возвращение героя) для percent
    //TODO Разбить условия на разные методы
    public void calc()
    {
        ArrayList<String> str = makeStringArrayList();
        int k=0,j=0;
        int indexError=-1;
        int indexError2=-1;
        double percent;
        for(int i=0;i<str.size()-1;i++)
        {
            j++;
            if (Integer.parseInt(search(str.get(i),8))>500 && Integer.parseInt(search(str.get(i),8))<599)
            {
                k++;
                indexError=counterError(indexError,i);
            }
            else if(time<Math.abs(Double.parseDouble(search(str.get(i),10))-Double.parseDouble(search(str.get(i+1),10))))
            {
                k++;
                indexError=counterError(indexError,i);
            }
            else
            {
                percent=(double) k/j*100;
                if(percent>percent_Available)
                {
                    if(indexError!=-1) {
                        //TODO Сделать нормальный вывод в консоль
                        System.out.println(getInterval(search(str.get(indexError), 3))[0] +":" + getInterval(search(str.get(indexError), 3))[1] + ":" + getInterval(search(str.get(indexError), 3))[2] +" начало ошибки");//это мем
                        System.out.println(Arrays.toString(getInterval(search(str.get(i - 1), 3)))+"Конец ошибок");//а это нет, поэтому не работает
                    }
                }
                indexError=-1;
                j=0;
                k=0;
            }
        }


    }

    public FileReader getFile() {
        return file;
    }

    public double getPercent_Available() {
        return percent_Available;
    }

    public double getTime() {
        return time;
    }

}
