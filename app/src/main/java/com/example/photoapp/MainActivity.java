package com.example.photoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    GridView photo_viewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photo_viewer = findViewById(R.id.photo_viewer);
        PhotoAdapter adapter = new PhotoAdapter(PhotoData.loadPhotosFromJSON(getApplicationContext()), getApplicationContext());
        photo_viewer.setAdapter(adapter);
        photo_viewer.setOnItemClickListener(onitemclick);
    }

    private AdapterView.OnItemClickListener onitemclick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MainActivity.this, ViewPhotoActivity.class);
            intent.putExtra("id", photo_viewer.getAdapter().getItemId(position));
            startActivity(intent);
        }
    };
}