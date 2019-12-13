package com.example.digitalevidence.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitalevidence.R;
import com.example.digitalevidence.models.Model;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DeviceListFragmentAdapter extends RecyclerView.Adapter<DeviceListFragmentAdapter.ViewHolder> {
    private static List<Model> myList;

    public DeviceListFragmentAdapter(List<Model> myList) {
        this.myList = myList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.devicelist_fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Model model = myList.get(position);
        holder.setDevices(model);
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView0);
            textView = itemView.findViewById(R.id.textView1);
            textView = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);

        }

        private void setDevices(Model model) {
            this.textView.setText(model.getName());
            Picasso.get().load(model.getLink()).into(this.imageView);
        }
    }
}