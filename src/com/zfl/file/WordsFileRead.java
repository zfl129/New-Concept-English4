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
	//�Á�惦���ļ��н������Ć��~�������ĵȼ�
	//key:���~
	//value:�ȼ�
	public static Map<String, Integer> wordmap = new HashMap<>();
	
	
    /**
     * 
     * @param filePath
     * @param spec  ������������������ spec==nullʱ������������
     * @return
     */
    public void read(final File file, final Integer spec)
    {
        //File file = new File(filePath);
        // ���ļ������ڻ��߲��ɶ�ʱ
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
