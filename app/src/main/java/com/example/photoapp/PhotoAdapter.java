package com.example.photoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhotoAdapter extends BaseAdapter {
    private ArrayList<Photo> photoList;
    private Context context;

    public PhotoAdapter(ArrayList<Photo> photoList, Context context) {
        this.photoList = photoList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return photoList.size();
    }

    @Override
    public Object getItem(int position) {
        return photoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return photoList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyView dataItem;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            dataItem = new MyView();
            convertView = inflater.inflate(R.layout.photo_disp_tpl, null);
            dataItem.iv_photo = convertView.findViewById(R.id.image);
            dataItem.tv_caption = convertView.findViewById(R.id.txt_title);

            convertView.setTag(dataItem);
        }else {
            dataItem = (MyView) convertView.getTag();
        }

        /*new DownloadImage(dataItem.iv_photo).execute(photoList.get(position).getSource_photo());*/
        Picasso.get().load(photoList.get(position).getSource_photo()).resize(300, 400).centerCrop().into(dataItem.iv_photo);
        dataItem.tv_caption.setText(photoList.get(position).getTitle_photo());
        return convertView;
    }

    private static class MyView {
        ImageView iv_photo;
        TextView tv_caption;
    }
}


