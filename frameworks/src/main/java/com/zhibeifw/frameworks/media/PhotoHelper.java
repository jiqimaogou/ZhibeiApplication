package com.zhibeifw.frameworks.media;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.FileNotFoundException;

import javax.inject.Inject;

public class PhotoHelper {

    public static final int SELECT_PICTURE = 0;
    public static final int SELECT_CAMER = 1;
    public static final int CROP_PHOTO = 2;

    @Inject
    public PhotoHelper() {
        // TODO Auto-generated constructor stub
    }

    public void showSelectPhotoDialog(final Activity context) {
        CharSequence[] items = {"相册", "相机"};
        new AlertDialog.Builder(context).setTitle("选择图片来源").setItems(items, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == SELECT_PICTURE) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("image/*");
                    context.startActivityForResult(Intent.createChooser(intent, "选择图片"), SELECT_PICTURE);
                } else {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    context.startActivityForResult(intent, SELECT_CAMER);
                }
            }
        }).create().show();
    }

    public void cropImageUri(Activity context, Uri uri, int outputX, int outputY, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        context.startActivityForResult(intent, requestCode);
    }

    public Bitmap decodeUriAsBitmap(Context context, Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }
}
