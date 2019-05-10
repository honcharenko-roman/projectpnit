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
        public TextView mTextView3;
        public ImageView mImageFavorite;

        public MedicViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            mTextView3 = itemView.findViewById(R.id.textView3);
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
                            if (mImageFavorite.getDrawable().getConstantState() ==
                                    itemView.getResources().getDrawable( R.drawable.ic_favorite_filled).getConstantState()){
                                mImageFavorite.setImageResource(R.drawable.ic_favorite_blank);
                            }
                            else {
                                mImageFavorite.setImageResource(R.drawable.ic_favorite_filled);
                            }

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

        if (currentItem.isFavorite()){
            holder.mImageFavorite.setImageResource(R.drawable.ic_favorite_filled);
        }
        else
            holder.mImageFavorite.setImageResource(R.drawable.ic_favorite_blank);

        //аватар
        //holder.mImageView.setImageResource(currentItem.getImageResource());
        String nameSurname = currentItem.getName()+" "+currentItem.getSurname();
        holder.mTextView1.setText(nameSurname);
        holder.mTextView2.setText(currentItem.getCategory());
        holder.mTextView3.setText(currentItem.getAdress());
    }

    @Override
    public int getItemCount() {
        return mMedicList.size();
    }
}