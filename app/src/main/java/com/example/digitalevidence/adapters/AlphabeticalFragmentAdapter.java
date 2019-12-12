package com.example.digitalevidence.adapters;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitalevidence.R;
import com.example.digitalevidence.models.Model;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlphabeticalFragmentAdapter extends RecyclerView.Adapter<AlphabeticalFragmentAdapter.ViewHolder> {
    private List<List<Model>> myList;

    public AlphabeticalFragmentAdapter(List<List<Model>> myList) {
        this.myList = myList;
    }

    @NonNull
    @Override
    public AlphabeticalFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.devicelist_fragment_item, parent, false);
        return new AlphabeticalFragmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlphabeticalFragmentAdapter.ViewHolder holder, int position) {
        List<Model> stringObject = myList.get(position);
        holder.setDevices(stringObject);
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView0;
        private ImageView imageView;
        private TextView textView1;
        private TextView textView2;

        ViewHolder(View itemView) {
            super(itemView);
            textView0 = itemView.findViewById(R.id.textView0);
            imageView = itemView.findViewById(R.id.imageView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
        }

        private void setDevices(List<Model> list) {
            String NAME = list.get(0).getName();
            String RELEASEDATE = list.get(0).getReleaseDate();
            String DIMENSIONS = list.get(0).getDimensions();

            if (list.size() > 2) {
                this.textView0.setText(NAME);
                Picasso.get().load(list.get(0).getLink()).into(this.imageView);
                this.textView1.append(RELEASEDATE);
                this.textView2.append(DIMENSIONS);
            }
        }
    }
}