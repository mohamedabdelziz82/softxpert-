package com.mohamedabdelaziz.softxpert.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.mohamedabdelaziz.softxpert.databinding.CarItemBinding;
import com.mohamedabdelaziz.softxpert.model.Cars;

import java.util.ArrayList;
import java.util.List;


public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarViewHolder> {
    private Context mContext;
    private List<Cars> mList;
    private CarItemBinding binding;

    public CarsAdapter(Context mContext, List<Cars> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        binding = CarItemBinding.inflate(inflater,parent,false);
        return new CarViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Glide.with(mContext).load(mList.get(position).getImageUrl()).into(binding.imageCar);

       binding.textViewBrand.setText(mList.get(position).getBrand());
       binding.textViewConstractionYear.setText(mList.get(position).getConstractionYear());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class CarViewHolder extends RecyclerView.ViewHolder{
        private CarItemBinding itemBinding;

        public CarViewHolder(CarItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }

    public  void updateList(List<Cars> updatedList){
        mList = updatedList;
        notifyDataSetChanged();
    }

    public  Cars getCarAt(int position){
        return mList.get(position);
    }
}
