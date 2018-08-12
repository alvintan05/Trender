package com.spnj.trender.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.spnj.trender.ItemHome;
import com.spnj.trender.activity.intro.Main2Intro;
import com.spnj.trender.adapter.HomeFragAdapter;
import com.spnj.trender.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private TextView mTextMessage;
    private Toolbar mToolbar;
    private RecyclerView mPostList;
    private LinearLayoutManager linearLayoutManager;

    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseUser;

    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private ArrayList<String> mName = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Toolbar
        mToolbar = (Toolbar) getActivity().findViewById(R.id.tb);
        mToolbar.setTitle("Trender");
        setHasOptionsMenu(true);
        //

        fAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    Intent mainIntent = new Intent(getActivity(), Main2Intro.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mainIntent);
                }
            }
        };

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Post");
        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Users").child(fAuth.getCurrentUser().getUid());

        //RecyclerView
        mPostList = (RecyclerView) view.findViewById(R.id.recycler_home);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        mPostList.setHasFixedSize(true);
        mPostList.setLayoutManager(linearLayoutManager);

        loadData();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void loadData() {
        FirebaseRecyclerAdapter<ItemHome, HomeViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ItemHome, HomeViewHolder>(
                ItemHome.class,
                R.layout.item_home,
                HomeViewHolder.class,
                mDatabase.orderByPriority()
        ) {
            @Override
            protected void populateViewHolder(final HomeViewHolder viewHolder, ItemHome model, int position) {
                final String postId = getRef(position).getKey();
//                viewHolder.setUsername(model.getUserName());
//                viewHolder.setPostDescription(model.getPostDescription());
//                viewHolder.setPostImage(getContext(), model.getUserPostImage());

                mDatabase.child(postId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String deskripsi = (String) dataSnapshot.child("deskripsi").getValue().toString();
                        String username = (String) dataSnapshot.child("username").getValue().toString();
                        String image = (String) dataSnapshot.child("image").getValue();

                        viewHolder.setUsername(username);
                        viewHolder.setPostDescription(deskripsi);
                        viewHolder.setPostImage(getContext(), image);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        };
        mPostList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView username;
        TextView postDescription;
        ImageView postImage;

        public HomeViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            username = (TextView) mView.findViewById(R.id.user_name);
            postImage = (ImageView) mView.findViewById(R.id.post_image);
            postDescription = (TextView) mView.findViewById(R.id.post_description);
        }

        public void setUsername(String post_name) {
            username.setText(post_name);
        }

        public void setPostImage(final Context ctx, final String imagePost) {
            Glide.with(ctx)
                    .load(imagePost)
                    .into(postImage);
        }

        public void setPostDescription(String post_desc) {
            postDescription.setText(post_desc);
        }


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home_option, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_search:
                Toast.makeText(getActivity(), "Search", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
