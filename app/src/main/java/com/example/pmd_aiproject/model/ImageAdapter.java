package com.example.pmd_aiproject.model;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.pmd_aiproject.ImageShowActivity;
import com.example.pmd_aiproject.R;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {

    private List<Image> data = new ArrayList<>();
    public  ImageAdapter (List<Image> data){
        this.data.addAll(data);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.image_row_layout,null);
        }
        ImageView iv = view.findViewById(R.id.iv_image_row_image);

        Bitmap bitmap = BitmapFactory.decodeByteArray(data.get(i).getImage(), 0, data.get(i).getImage().length);
        iv.setImageBitmap(bitmap);

        TextView tv = view.findViewById(R.id.txt_image_row_id);
        tv.setText(data.get(i).getId()+"");

        TextView tv1 = view.findViewById(R.id.txt_image_row_fecha);
        tv1.setText("Fecha: "+data.get(i).getFecha());

        TextView tv2 = view.findViewById(R.id.txt_image_row_propmt);
        tv2.setText("Prompt: "+data.get(i).getPrompt());

        TextView tv3 = view.findViewById(R.id.txt_image_row_user);
        tv3.setText("user: "+data.get(i).getUser_name());



        return view;
    }

    public void updatePlanets(List<Image> images){
        this.data.clear();
        this.data.addAll(images);
        super.notifyDataSetChanged();
    }

}
