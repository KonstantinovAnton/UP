package com.example.up;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterQuote extends BaseAdapter {

    private Context mContext;
    List<MaskQuote> maskList;

    public AdapterQuote(Context mContext, List<MaskQuote> maskList) {
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
        View v = View.inflate(mContext,R.layout.item_quote,null);

        TextView title = v.findViewById(R.id.tvTitle);
        ImageView Image = v.findViewById(R.id.image);
        TextView description = v.findViewById(R.id.tvDescription);

        MaskQuote maskQuote = maskList.get(position);
        title.setText(maskQuote.getTitle());


        Image.setImageURI(Uri.parse(maskQuote.getImage()));
        if(maskQuote.getImage().toString().equals("null"))
        {
            Image.setImageResource(R.drawable.absence);
        }

        description.setText(maskQuote.getDescription());
        return v;
    }
}
