package com.spnj.trender;

import android.widget.ImageView;

/**
 * Created by Alvin Tandiardi on 08/08/2018.
 */

public class ItemHome {

    private String userName;
    private String userPostImage;
    private String postDescription;

    public ItemHome() {
    }

    public ItemHome(String userName, String userPostImage, String postDescription) {
        this.userName = userName;
        this.userPostImage = userPostImage;
        this.postDescription = postDescription;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPostImage() {
        return userPostImage;
    }

    public void setUserPostImage(String userPostImage) {
        this.userPostImage = userPostImage;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }
}
