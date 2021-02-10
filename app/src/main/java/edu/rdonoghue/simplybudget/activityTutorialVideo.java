package edu.rdonoghue.simplybudget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class activityTutorialVideo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_video);

        VideoView videoView = (VideoView) findViewById(R.id.VVTurtorialVideo);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.verticaltest);
        videoView.start();
    }

    public void DoBack(View view) {
        finish();
    }
}