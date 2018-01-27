package com.king.test;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by 陈尘 on 2018/1/18.
 */

public class DoubleSamplingUtils {

    public static int calculateSampleSize(BitmapFactory.Options options, int imageWidth, int imageHeight){


        //获得 原图片的 宽高
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;

        /**
         * inSampleSize：

         这个值是一个int，当它小于1的时候，将会被当做1处理，如果大于1，
         那么就会按照比例（1 / inSampleSize）缩小bitmap的宽和高、降低分辨率，
         大于1时这个值将会被处置为2的倍数。例如，width=100，height=100，inSampleSize=2，
         那么就会将bitmap处理为，width=50，height=50，宽高降为1 / 2，像素数降为1 / 4。
         */
        int sampleSize = 1;

        while(outWidth / sampleSize > imageWidth || outHeight / sampleSize >imageHeight){
            sampleSize *= 2;
        }
        return sampleSize;
    }

    public static Bitmap decodeResourceImage(Resources res, int resId, int imageWidth, int imageHeight){

        //通过 BitmapFactory 对 Bitmap 进行解码
        //options 是对解码的 Bitmap 的各种参数进行控制
        BitmapFactory.Options options = new BitmapFactory.Options();

        // 将 inJustDecodeBounds 设置为 true ，解码时将不反返回 bitmap, 只会返回 这个 bitmap 的尺寸
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(res,resId,options);

        /**
         *  inSampleSize ：
         *      inSampleSize 是 int值；
         *      如果 inSampleSize < 1 ,会当做 1 来处理
         *      如果 inSampleSize > 1 ,会按照 1/inSampleSize 的比例缩小 bitmap 的宽和高
         */
        options.inSampleSize = calculateSampleSize(options, imageWidth, imageHeight);

        options.inJustDecodeBounds = false;

        //返回 bitmap
        return BitmapFactory.decodeResource(res,resId,options);
    }

    public static Bitmap decodeFileImage(String filePath, int imageWidth, int imageHeight){


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath,options);
        options.inSampleSize = calculateSampleSize(options,imageWidth,imageHeight);
//        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath,options);
    }
}
