package com.spnj.trender;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Alvin Tandiardi on 15/07/2018.
 */

public class HomeFragAdapter extends RecyclerView.Adapter<HomeFragAdapter.ViewHolder> {

    private static final String TAG = "HomeFragAdapter";

    private ArrayList<String> mUsername = new ArrayList<>();
    private Context mContext;

    public HomeFragAdapter(Context context, ArrayList<String> musername) {
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
    public void onBindViewHolder(HomeFragAdapter.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.username.setText(mUsername.get(position));

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: "+mUsername.get(position));
                Toast.makeText(mContext, mUsername.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        holder.option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogmenu();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsername.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView username;
        ImageView option;
        LinearLayout parent;

        public ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.user_name);
            parent = itemView.findViewById(R.id.parent);
            option = itemView.findViewById(R.id.moreIcon);
        }
    }

    private void dialogmenu() {
        CharSequence options[] = new CharSequence[]{"Report", "Copy Link", "Unfollow"};

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(true);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // the user clicked on options[which]
                switch (which) {
                    case 0:
                        Toast.makeText(mContext, "Report", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(mContext, "Copy Link", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(mContext, "Unfollow", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        builder.show();
    }

}
