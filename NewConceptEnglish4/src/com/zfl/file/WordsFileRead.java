package com.zfl.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordsFileRead
{
	//用來存儲從文件中解析到的單詞及對應的等級
	//key:單詞
	//value:等級
	public static Map<String, Integer> wordmap = new HashMap<>();
	
	
    /**
     * 
     * @param filePath
     * @param spec  允许解析的最大行数， spec==null时，解析所有行
     * @return
     */
    public void read(final File file, final Integer spec)
    {
        //File file = new File(filePath);
        // 当文件不存在或者不可读时
        if ((!isFileExists(file)) || (!file.canRead()))
        {
            System.out.println("file is not exist or cannot read!!!");
        }

        BufferedReader br = null;
        FileReader fb = null;
        StringBuffer sb = new StringBuffer();
        try
        {
            fb = new FileReader(file);
            br = new BufferedReader(fb);

            String str = br.readLine();
            int index = 0;
            while (((spec == null) || index++ < spec) && (str = br.readLine()) != null)
            {
                String word = FilterUtil.filterAlphabetAndSpace(str.trim());
                Integer level = new Integer(FilterUtil.filterNumber(str));
                wordmap.put(word,level);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            closeQuietly(br);
            closeQuietly(fb);
        }
    }
    

    private static void closeQuietly(Closeable closeable)
    {
        try
        {
            if (closeable != null)
            {
                closeable.close();
            }
        }
        catch (IOException e)
        {
        }
    }

    private static boolean isFileExists(final File file)
    {
        if (file.exists() && file.isFile())
        {
            return true;
        }

        return false;
    }

}
