package com.fhl.picasso2demo;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity{
//定义一张网络图片的uri，其实就是上面的测试图片
    private static final String imageUrl = "http://upload-images.jianshu.io/upload_images/589909-e339eb2763fa172c.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240";
    private ImageView mLoadImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoadImg = (ImageView) findViewById(R.id.loadImg);
    }
    //点击事件
    public void loadImage(View view) {
//        basicLoad(imageUrl,mLoadImg);

        loadAndSaveImage(imageUrl,mLoadImg);
//
//        tranformationImage(imageUrl,mLoadImg);
    }

    //1.picasso的基本用法
    private void basicLoad(String url,ImageView iv){
        Picasso.with(this)   //context
                .load(url)     //图片加载地址
                .placeholder(R.mipmap.ic_launcher)    //图片加载中显示的页面
                .error(android.R.drawable.ic_menu_delete)   //图片记载失败时显示的页面
                .noFade()       //设置淡入淡出效果
                .resize(500,400)     //自定义图片加载的大小，会根据你传入的尺寸进行采样
                .centerCrop()    //图片会被裁剪，但是质量没有影响，等比例放大
//             .centerInside()   //图片完整展示，不会被压缩或挤压，一般是等比例缩小
//             .fit()      //智能展示图片，对于图片的大小和imageview的尺寸进行了测量，计算出最佳的大小和最佳的质量显示出来
                .into(iv);    //需要加载图片的控件
    }

    //2.Picasso 加载的图片的bitmap对象并且保存到磁盘当中
    private void loadAndSaveImage(String imgUrl,ImageView iv){
//        Picasso.get().load(Uri.parse(imageUrl)).into(iv);
        Picasso.with(this)
                .load(imgUrl)
                .rotate(90f)   //简单的旋转处理，传入的数据大于0度小于360度
                .into(target);
    }
    //Target不能写成匿名内部类形式，垃圾回收器在你获取不到bitmap的引用时，会把他回收
    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            //加载成功后，得到bitmap对象，可以对其进行操作
            mLoadImg.setImageBitmap(bitmap);
            File file = new File(getExternalCacheDir().getAbsolutePath()+File.separator+"picasso.png");
            Log.d("fhl",file.getAbsolutePath());
            try {
                FileOutputStream fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    //3.Picasso可以定制图片的处理
    private void tranformationImage(String imgPaht,ImageView iv){
        Picasso.with(this)
                .load(imgPaht)
                .transform(new CircleTransform())
                .into(iv);
    }
}
