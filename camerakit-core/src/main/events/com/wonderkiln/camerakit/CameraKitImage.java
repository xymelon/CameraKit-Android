package com.wonderkiln.camerakit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;

public class CameraKitImage extends CameraKitEvent {

    private byte[] jpeg;

    CameraKitImage(byte[] jpeg) {
        super(TYPE_IMAGE_CAPTURED);
        this.jpeg = jpeg;
    }

    @Nullable
    public byte[] getJpeg() {
        return jpeg;
    }

    @Nullable
    public Bitmap getBitmap() {
        if (jpeg == null) {
            return null;
        }
        return BitmapFactory.decodeByteArray(jpeg, 0, jpeg.length);
    }

}
