package com.example.an.urlfilesoperation.Utils;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * description: 文件操作类
 * author: WDSG
 * date: 2016/10/17
 */
public class FileUtils {

    private static final String TAG = FileUtils.class.getSimpleName();

    private final static int BUFFER_SIZE = 4096; // 4*1024字节

    /**
     * 删除文件夹下所有内容包括文件夹本身
     *
     * @param folderPath
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    /**
     * 删除指定File，支持目录和文件
     *
     * @param file
     */
    public static void delFile(File file) {
        if (file == null || !file.exists()) {
            return;
        }

        if (file.isFile()) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File f : files) {
                    delFile(f);// 递归删除每一个文件
                }
            }

            file.delete();// 删除该文件夹
        }
    }

    /**
     * 保留空文件夹, 只删除文件夹下的内容
     *
     * @param folderPath
     */
    public static void delOnlyFolderContained(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    //删除指定路径下所有文件
    private static void delAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + File.separator + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + File.separator + tempList[i]);// 再删除空文件夹
            }
        }
    }

    public static String inputStreamToString(InputStream in) throws Exception {

        StringBuffer out = new StringBuffer();
        byte[] b = new byte[BUFFER_SIZE];
        int n;
        while ((n = in.read(b)) != -1) {
            out.append(new String(b, 0, n));
        }
        // Log.i("String的长度", new Integer(out.length()).toString());
        return out.toString();
    }

    public static void saveFile(File file, String content) {

        FileOutputStream os = null;
        try {
            os = new FileOutputStream(file);
            os.write(content.getBytes());
        } catch (Exception e) {

        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static boolean ensureFileExist(String filePath) {
        File dirFile = new File(filePath);
        return ensureFileExist(dirFile);
    }

    public static boolean ensureFileExist(File file) {
        if (!file.exists()) {
            return file.mkdirs();
        }
        return true;
    }

    public static boolean copySdcardFile(String fromFile, String toFile) {
        try {
            InputStream fosfrom = new FileInputStream(fromFile);
            OutputStream fosto = new FileOutputStream(toFile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0) {
                fosto.write(bt, 0, c);
            }
            fosfrom.close();
            fosto.close();
            return true;

        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
            return false;
        }
    }

    public static void closeInStream(InputStream input) {
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
    }

    public static void closeOutStream(OutputStream output) {
        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
    }

    public static void closeReader(Reader reader) {
        try {

            if (reader != null) {
                reader.close();
            }

        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
    }

    public static void closeWriter(Writer writer) {
        try {
            if (writer != null) {
                writer.close();
            }

        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
    }

    public static void writeObj(Object obj, File f) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(obj);

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        } finally {
            closeOutStream(oos);
        }
    }

    /**
     * 读取文件内容
     *
     * @param filePath 完整的文件路径, 包括Sdcard路径
     * @return 文本txt
     * @throws Exception
     */
    public static String readFilePath(String filePath) throws Exception {
        File file = new File(filePath);
        FileInputStream inStream = new FileInputStream(file);
        String data = inputStreamToString(inStream);
        inStream.close();
        return data;
    }

    /**
     * 获取文件夹大小
     *
     * @param file
     * @return 文件大小(字节)
     * @throws Exception
     */
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        File[] fileList = file.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isDirectory()) {
                size += getFolderSize(fileList[i]);
            } else {
                size += fileList[i].length();
            }
        }
        return size;
    }
}
