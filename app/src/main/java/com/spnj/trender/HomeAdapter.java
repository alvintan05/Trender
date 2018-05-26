package com.spnj.trender;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Alvin Tandiardi on 25/05/2018.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{

    private static final String TAG = "HomeAdapter";

    private ArrayList<String> mUsername = new ArrayList<>();
    private Context mContext;

    public HomeAdapter(Context context, ArrayList<String> musername) {
        mUsername = musername;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.username.setText(mUsername.get(position));

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: "+mUsername.get(position));
                Toast.makeText(mContext, mUsername.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUsername.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView username;
        LinearLayout parent;

        public ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.user_name);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
