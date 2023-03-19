package com.example.up;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.List;

public class AdapterFeeling extends RecyclerView.Adapter<AdapterFeeling.ViewHolder> {

    private Context mContext;
    List<MaskFeeling> maskList;

    public AdapterFeeling(Context mContext, List<MaskFeeling> maskList)
    {
        this.mContext = mContext;
        this.maskList = maskList;
    }

    @NonNull
    @Override
    public AdapterFeeling.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterFeeling.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.template_feeling, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFeeling.ViewHolder holder, int position) {
        final MaskFeeling maskModel = maskList.get(position);
        holder.title.setText(maskModel.getTitle());

        if(maskModel.getImage().equals("null"))
        {
            holder.image.setImageResource(R.drawable.absence);
        }
        else
        {
            new AdapterFeeling.DownloadImageTask((ImageView) holder.image)
                    .execute(maskModel.getImage());
        }
    }
    @Override
    public long getItemId(int position) {
        return maskList.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return maskList.size();
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... url) {
            String strUrl = url[0];
            Bitmap icon = null;
            try {
                InputStream in = new java.net.URL(strUrl).openStream();
                icon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return icon;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }
}
