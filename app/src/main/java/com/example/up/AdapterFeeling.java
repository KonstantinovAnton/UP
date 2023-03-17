package com.example.up;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterFeeling extends BaseAdapter {

    private Context mContext;
    List<MaskFeeling> maskList;

    public AdapterFeeling(Context mContext, List<MaskFeeling> maskList)
    {
        this.mContext = mContext;
        this.maskList = maskList;
    }

    @Override
    public int getCount() {
        return maskList.size();
    }

    @Override
    public Object getItem(int position) {
        return maskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return maskList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext,R.layout.template_feeling,null);

        TextView title = v.findViewById(R.id.tvTitle);
        ImageView Image = v.findViewById(R.id.image);

        MaskFeeling maskFeeling = maskList.get(position);
        title.setText(maskFeeling.getTitle());
        Image.setImageURI(Uri.parse(maskFeeling.getImage()));

        return v;
    }
}
