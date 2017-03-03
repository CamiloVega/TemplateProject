package com.cvdevelopers.restfull.util;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by CamiloVega on 3/1/17.
 */

public abstract class FileUtils {

    private static final String TAG = FileUtils.class.getName();

    /**
     * Save a stream of bytes into a file.  The InputStream from which the bytes
     * are read is wrapped in a BufferedInputStream and both are closed after completion.
     * @param destFile The file into which the bytes should be saved.
     * @param inputStream The stream of bytes to be saved.
     */
    public static void saveToFile(File destFile, InputStream inputStream) {
        int totalRead = 0;
        try {
            totalRead = copyTo(inputStream,
                    new BufferedOutputStream(new FileOutputStream(destFile, false)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            Log.d(TAG, "Wrote " + totalRead + "bytes to: " + destFile.getPath());
        }
    }

    protected static int copyTo(InputStream in, OutputStream out) throws IOException {
        try {
            int totalRead = 0;
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int len;
            while ((len = in.read(buffer)) != -1) {
                totalRead += len;
                out.write(buffer, 0, len);
            }
            out.flush();
            return totalRead;
        } finally {
            in.close();
            out.close();
        }
    }

    /**
     * Save a file from a Bitmap instance.
     * @param destFile
     * @param bitmap
     */
    public static void saveToFile(File destFile, Bitmap bitmap, Bitmap.CompressFormat format) {
        OutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(destFile, false));
            if (!bitmap.compress(format, 100, outputStream)) {
                throw new RuntimeException("Failed to compress bitmap to outputstream.");
            }
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (outputStream != null) try {
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
