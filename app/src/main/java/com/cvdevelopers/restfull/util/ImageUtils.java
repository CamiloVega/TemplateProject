package com.cvdevelopers.restfull.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;

/**
 * Created by CamiloVega on 3/1/17.
 */

public abstract class ImageUtils {

    /**
     * Checks the rotation of an in-memory Bitmap using exif data and correct it if necessary
     * http://stackoverflow.com/questions/12950954/bitmap-not-reading-exif-data-on-decode
     * @param photo
     * @param exif
     * @return A properly oriented version of input bitmap, or the original if no rotation was necessary.
     */
    public static Bitmap checkOrientation(Bitmap photo, ExifInterface exif) {

        int orientation = exif
                .getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        Matrix rotationMatrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                rotationMatrix.postRotate(90);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                rotationMatrix.postRotate(180);
                // TODO
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                rotationMatrix.postRotate(270);
                // TODO
                break;
            default:
                // rotation is fine, do nothing.
                return photo;
        }

        // orientation is off so we fix it by creating a new bitmap with the correct rotation and then
        // throw out the old one.  afaik there's no way to rotate an existing bitmap.
        Bitmap result = Bitmap
                .createBitmap(photo, 0, 0, photo.getWidth(), photo.getHeight(), rotationMatrix,
                        true);
        photo.recycle();
        return result;
    }
}
