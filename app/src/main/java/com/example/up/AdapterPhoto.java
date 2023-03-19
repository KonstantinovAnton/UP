package com.example.up;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterPhoto extends BaseAdapter {
    private Context mContext;
    List<MaskPhoto> maskList;

    public AdapterPhoto(Context mContext, List<MaskPhoto> maskList) {
        this.mContext = mContext;
        this.maskList = maskList;
    }

    @Override
    public int getCount() {
        return maskList.size();
    }

    @Override
    public Object getItem(int i) {
        return maskList.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return maskList.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext,R.layout.template_image,null);

        ImageView img = v.findViewById(R.id.image);
       TextView dataCreate = v.findViewById(R.id.dateCreat);

        MaskPhoto maskPhoto  = maskList.get(position);

        if(maskPhoto.getImageProfile().exists()){

            Bitmap bitmap = BitmapFactory.decodeFile(maskPhoto.getImageProfile().getAbsolutePath());
            img.setImageBitmap(bitmap);
        }
        dataCreate.setText(maskPhoto.getData());

        return v;
    }
}
