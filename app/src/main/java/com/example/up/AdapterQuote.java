package com.example.up;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterQuote extends BaseAdapter {

    private Context mContext;
    List<MaskQuote> quoteList;

    public AdapterQuote(Context mContext, List<MaskQuote> maskList) {
        this.mContext = mContext;
        this.quoteList = maskList;
    }

    @Override
    public int getCount() {
        return quoteList.size();
    }

    @Override
    public Object getItem(int position) {
        return quoteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return quoteList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext,R.layout.item_quote,null);

        TextView title = v.findViewById(R.id.tvTitle);
        ImageView image = v.findViewById(R.id.image);
        TextView description = v.findViewById(R.id.tvDescription);

        MaskQuote maskQuote = quoteList.get(position);
        title.setText(maskQuote.getTitle());


        image.setImageURI(Uri.parse(maskQuote.getImage()));
        if(maskQuote.getImage().toString().equals("null"))
        {
            image.setImageResource(R.drawable.absence);
        }
        else
        {
            new DownloadImageTask((ImageView) image)
                    .execute(maskQuote.getImage());
        }
        description.setText(maskQuote.getDescription());
        return v;
    }
    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageTask(ImageView bitmapImage) {
            this.imageView = bitmapImage;
        }

        protected Bitmap doInBackground(String... url) {
            String urlString = url[0];

            try {

            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            if(urlString.charAt(urlString.length() - 5) == '1')
            {
                return BitmapFactory.decodeResource(AdapterQuote.this.mContext.getResources(), R.drawable.img);
            }
            else{
                return BitmapFactory.decodeResource(AdapterQuote.this.mContext.getResources(), R.drawable.img_1);
            }

        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }

}
