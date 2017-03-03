package com.cvdevelopers.restfull.managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.widget.Filterable;

import com.cvdevelopers.restfull.util.FileUtils;
import com.cvdevelopers.restfull.util.ImageUtils;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by CamiloVega on 2/28/17.
 */

@EBean (scope = EBean.Scope.Singleton)
public class ImageManager {
    private static final String TAG = ImageManager.class.getName();
    public static final int REQUEST_CODE_SELECT_IMAGE = 211;

    @RootContext
    Context context;

    private File tempFile;

    @AfterInject
    protected void afterInject(){
        initTempFile();
    }

    protected void initTempFile() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
        try {
            // http://stackoverflow.com/questions/18711525/camera-not-working-saving-when-using-cache-uri-as-mediastore-extra-output
            tempFile = File.createTempFile("photo", ".jpg", context.getExternalCacheDir());
            if (!tempFile.exists()) {
                throw new RuntimeException("Failed to create tempfile for image capture.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Listener for Uploader events.
     */
    public interface PickerListener {

        /**
         * Invoked when an image is selected by the user.
         * @param fileUri
         */
        void onImageSelected(Uri fileUri);

        /**
         * Invoked when image selection is cancelled by the user.
         */
        void onImageSelectCanceled();

        /**
         * Invoked when image selection fails for some reason other than user cancelation.
         */
        void onImageSelectFailed();
    }

    public ImagePicker getPicker(@NonNull PickerListener listener) {
        return new ImagePicker(listener);
    }

    /**
     * Utility class that provides state / filtering for image selection / uploading.  Requires a unique
     * instance per use case.
     */
    public class ImagePicker {

        private final PickerListener listener;

        /**
         * @param listener Listener success / failure etc.  May not be null.
         */
        protected ImagePicker(@NonNull PickerListener listener) {
            this.listener = listener;
        }

        /**
         * Initialize the temp file that will contain the selected image,
         * deleting the previous one if it exists.
         */
        protected void init() {
            initTempFile();
        }

        /**
         * Prompt the user to select an image
         * @param fragment
         */
        public void pick(@NonNull Fragment fragment) {
            init();
            selectImage(fragment, tempFile);
        }

        /**
         * Prompt the user to select an image
         * @param activity
         */
        public void pick(@NonNull Activity activity) {
            init();
            selectImage(activity, tempFile);
        }

        /**
         * http://stackoverflow.com/questions/4455558/allow-user-to-select-camera-or-gallery-for-image/12347567#12347567
         * @param resultCode
         * @param data
         */
        public void handleImageSelectionResult(int resultCode, Intent data) throws IOException {
            if (listener == null) {
                return;
            } else if (resultCode == Activity.RESULT_OK) {
                // Unless we have extras from WebImageSearchActivity,
                // If the intent passed in is null, or empty, then we should expect tempFile to contain our image.

                final boolean isTempFileReady = tempFile != null
                        && (data == null || data.getData() == null);

                if (isTempFileReady) {
                    FileInputStream fis = new FileInputStream(tempFile);
                    Bitmap imageBitmap = BitmapFactory.decodeStream(fis);
                    imageBitmap = ImageUtils.checkOrientation(imageBitmap,
                            new ExifInterface(tempFile.getAbsolutePath()));
                    FileUtils.saveToFile(tempFile, imageBitmap, Bitmap.CompressFormat.JPEG);

                    listener.onImageSelected(getUriFromFile(tempFile));
                } else {
                    // local image
                    try {
                        if (data.getData() != null) {
                            FileUtils.saveToFile(tempFile,
                                    context.getContentResolver()
                                            .openInputStream(data.getData()));
                            FileInputStream fis = new FileInputStream(tempFile);
                            Bitmap imageBitmap = BitmapFactory.decodeStream(fis);
                            imageBitmap = ImageUtils.checkOrientation(imageBitmap,
                                    new ExifInterface(tempFile.getAbsolutePath()));
                            FileUtils.saveToFile(tempFile, imageBitmap, Bitmap.CompressFormat.JPEG);

                            listener.onImageSelected(getUriFromFile(tempFile));
                        } else {
                            listener.onImageSelectFailed();
                        }
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                listener.onImageSelectCanceled();
            } else {
                listener.onImageSelectFailed();
            }
        }
    }

    protected void selectImage(Fragment fragment, File tempFile) {
        selectImage(fragment, tempFile, REQUEST_CODE_SELECT_IMAGE);
    }

    protected void selectImage(Activity activity, File tempFile) {
        selectImage(activity, tempFile, REQUEST_CODE_SELECT_IMAGE);
    }

    protected void selectImage(final Fragment fragment, final File tempFile,
                               final int requestCode) {
        fragment.startActivityForResult(getCameraIntent(tempFile),
                        requestCode);

    }

    protected void selectImage(final Activity activity, final File tempFile,
                               final int requestCode) {

        activity.startActivityForResult(getCameraIntent(tempFile),
                        requestCode);

    }

    protected Intent getCameraIntent (File tempFile) {
        final Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        if (tempFile != null) {
            // create a temp file to contain the photo:
            Uri imageURI = FileProvider.getUriForFile(
                    context,
                    context.getApplicationContext()
                            .getPackageName() + ".provider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageURI);
        }
        return intent;
    }

    public Uri getUriFromFile (File tempFile) {
        return FileProvider.getUriForFile(
                context,
                context.getApplicationContext()
                        .getPackageName() + ".provider", tempFile);
    }
}
