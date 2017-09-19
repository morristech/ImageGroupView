package com.loopeer.android.librarys.imagegroupview.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.loopeer.android.librarys.imagegroupview.NavigatorImage;
import com.loopeer.android.librarys.imagegroupview.R;
import com.loopeer.android.librarys.imagegroupview.model.Image;
import com.loopeer.android.librarys.imagegroupview.model.SquareImage;
import com.loopeer.android.librarys.imagegroupview.view.DragRecycleAdapter;
import com.loopeer.android.librarys.imagegroupview.view.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

//TODO step1. 长点击事件滑动图片；
//TODO step2. 对应图片消失留白；
//TODO step3. 图片划经位置其他图片滚动动画；
//TODO 三个页面逻辑和启动模式
public class ImagePickerActivity extends AppCompatActivity {


    List<Image> images;
    List<SquareImage> preImages;
    private RecyclerView dragRecycleView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);
        pareIntent();
        initData();
//        initView();
        initView2();
    }

    public void initData() {
        preImages = new ArrayList<>();
    }

    /*private void initView() {
        mDragView = (DragView) findViewById(R.id.drag_view);

        for (Image image : images) {
            SquareImage squareImage = new SquareImage(image.url, null, getPhotoKey(image.time), SquareImage.PhotoType.LOCAL);
            preImages.add(squareImage);
//            Log.d("asda",""+image.url);
        }
        DragAdapter adapter = new DragAdapter(this, preImages);
        mDragView.setAdapter(adapter);
    }*/

    private void initView2() {
        dragRecycleView = (RecyclerView) findViewById(R.id.drag_view);
        dragRecycleView.setLayoutManager(new GridLayoutManager(ImagePickerActivity.this, 3));
        dragRecycleView.setHasFixedSize(true);

        for (Image image : images) {
            SquareImage squareImage = new SquareImage(image.url, null, getPhotoKey(image.time), SquareImage.PhotoType.LOCAL);
            preImages.add(squareImage);
        }
        DragRecycleAdapter adapter = new DragRecycleAdapter(this, preImages);
        dragRecycleView.setAdapter(adapter);
        //创建SimpleItemTouchHelperCallback
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        //用Callback构造ItemtouchHelper
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        touchHelper.attachToRecyclerView(dragRecycleView);
    }

    private void pareIntent() {
        Intent intent = this.getIntent();
        images = (List<Image>) intent.getSerializableExtra(NavigatorImage.EXTRA_PHOTOS_URL);
    }

    @NonNull
    private String getPhotoKey(long time) {
        return "image_" + time;
    }

}
