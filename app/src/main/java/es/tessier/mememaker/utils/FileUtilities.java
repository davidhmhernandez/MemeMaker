package es.tessier.mememaker.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Evan Anger on 7/28/14.
 */
public class FileUtilities {
    static final int  TAM_BUFFER =1024;
    private final static String STORAGE_TYPE = StorageType.PUBLIC_EXTERNAL;
    private final static String ALBUM_NAME = "mememaker";
    private final static String TAG = "Error";


    public static void saveAssetImage(Context context, String assetName) {
    File fieDirectory = context.getFilesDir();
        File fileToWrite = new File(fieDirectory,assetName);
        AssetManager assetManager = context.getAssets();

        InputStream in = null;
        try{
            in=assetManager.open(assetName);
        }catch(FileNotFoundException fnfe){
           fnfe.getMessage();
        }catch(IOException ioe){
            ioe.getMessage();
        }
        FileOutputStream out=null;

        try {
            out = new FileOutputStream(fileToWrite);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        copyFile(in, out);


    }

    private static void copyFile(InputStream in, FileOutputStream out) {
        byte[] buffer = new byte[TAM_BUFFER];
        int read;
        try {
            while((read=in.read(buffer))!=-1){
                out.write(buffer,0,read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Uri saveImageForSharing(Context context, Bitmap bitmap,  String assetName) {
        File fileToWrite = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), assetName);

        try {
            FileOutputStream outputStream = new FileOutputStream(fileToWrite);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return Uri.fromFile(fileToWrite);
        }
    }


    public static void saveImage(Context context, Bitmap bitmap, String name) {
        File fileDirectory = context.getFilesDir();
        File fileToWrite = new File(fileDirectory, name);

        try {
            FileOutputStream outputStream = new FileOutputStream(fileToWrite);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<File> filtrado(Context c){
        File fileDirectory = getFilesDirectory(c);
        final ArrayList<File>filteredFiles = new ArrayList();

        fileDirectory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if(pathname.getAbsolutePath().contains("jpg")){
                    filteredFiles.add(pathname);
                    return true;
                }else if(pathname.getAbsolutePath().contains("png")){
                    filteredFiles.add(pathname);
                    return true;
                }else{
                    return false;
                }
            }
        });

        return filteredFiles;

    }
    private static File getFilesDirectory(Context c){
        if(STORAGE_TYPE.equals(StorageType.INTERNAL)){
            c.getFilesDir();
        }else{
            if(isExternalStorageAvailable()){
                if(STORAGE_TYPE.equals(StorageType.PRIVATE_EXTERNAL)){
                    c.getExternalFilesDir(null);
                }else{
                    File file = new File(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES),ALBUM_NAME);
                    if(!file.mkdirs()){
                        Log.e(TAG,"Directory not created");
                    }
                    return file;
                }
            }
        }

        return null;

    }
    private static boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    }




}
