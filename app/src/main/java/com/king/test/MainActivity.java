package com.king.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

/**
 * 二次采样：
 * 原理 ： 第一次采样：主要获得图片要的压缩比例；
 * 第二次采样：通过第一次采样的基础上，将获得的比例作为一个参数 传递 给 BitmapFactory。
 * 优点 ： 提高了加载的效率，节省系统内存，且不会有视觉上的差异
 */
public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        Bitmap bitmapResource = DoubleSamplingUtils.decodeResourceImage(getResources(), R.mipmap.image, 200, 200);
        mImageView.setImageBitmap(bitmapResource);

    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.imageView);
    }
}
