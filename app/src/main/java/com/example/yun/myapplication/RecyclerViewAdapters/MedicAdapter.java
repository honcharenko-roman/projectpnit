package com.example.yun.myapplication.RecyclerViewAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yun.myapplication.Entities.Medic;
import com.example.yun.myapplication.R;

import java.util.List;


public class MedicAdapter extends RecyclerView.Adapter<MedicAdapter.MedicViewHolder> {

    private List<Medic> mMedicList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onFavoriteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class MedicViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public ImageView mImageFavorite;

        public MedicViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            mImageFavorite = itemView.findViewById(R.id.image_favorite);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            mImageFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onFavoriteClick(position);
                        }
                    }
                }
            });
        }
    }

    public MedicAdapter(List<Medic> exampleList) {
        mMedicList = exampleList;
    }

    @Override
    public MedicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.medic_list_item, parent, false);
        MedicViewHolder evh = new MedicViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(MedicViewHolder holder, int position) {
        Medic currentItem = mMedicList.get(position);

        //holder.mImageView.setImageResource(currentItem.getImageResource());
//        holder.mTextView1.setText(currentItem.getSurname());
        holder.mTextView1.setText(" " + currentItem.getName());
//        holder.mTextView2.setText(currentItem.getCategory());
    }

    @Override
    public int getItemCount() {
        return mMedicList.size();
    }
}