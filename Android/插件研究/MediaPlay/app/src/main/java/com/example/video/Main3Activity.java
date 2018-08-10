package com.example.video;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class Main3Activity extends Activity {

    private VideoView video;
    private Button btn;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        initView();
    }

    private void initView() {
        video = (VideoView) findViewById(R.id.video1);
//        video.setVideoLayout(VideoView.VIDEO_LAYOUT_ORIGIN, 0);
        btn = (Button) findViewById(R.id.btn1);
        et = (EditText) findViewById(R.id.et2);
        final MediaController mediaController = (MediaController) findViewById(R.id.mediaController);
       final FrameLayout fl_contioner = (FrameLayout) findViewById(R.id.fl_contioner);
        Vitamio.isInitialized(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = Environment.getExternalStorageDirectory().getPath()+"/"+et.getText().toString();
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getPath()+"/";
//        path = Environment.getExternalStorageDirectory().getPath()+"/";
                path = path + "hshxn.mp4";//sdcard的路径加上文件名称是文件全路径

                Uri uri = Uri.parse(path);
                video.setVideoURI(uri);

//                =new MediaController(Main3Activity.this,false,fl_contioner);
//                mediaController.setPadding(0,0,0,100);
                video.setMediaController(mediaController);
//                mediaController.setAnchorView(video);
                video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        video.start();
                    }
                });
            }
        });
    }
}
