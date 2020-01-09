package com.gym.chater.ViewModel;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.gym.chater.Data.Session;
import com.gym.chater.Data.User;
import com.gym.chater.R;
import com.gym.chater.Repository.RealTimeDataBase;

import java.util.Arrays;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class Login {
    static private int RequestCode = 110;
    static private FirebaseUser fireBaseuser;
    static private IdpResponse response;
    static private boolean SingedUp;
    static private Activity Window;
    static private User user;
    static private String data_base_table = "users";

    static {
        SingedUp = false;
        RealTimeDataBase.addDataBaseTable(data_base_table);
        user = new User();
    }

    static public void setActivity(Activity window) {
        Window = window;
    }
    static public void SingIn() {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(//PROVIDERS LIST
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        // Create and launch sign-in intent
        Window.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RequestCode);
    }
    static public void Logout(){
        SingedUp = false;
        FirebaseAuth.getInstance().signOut();
    }
    static public void ObserveUserData(int requestCode, int resultCode, Intent data) {
        if (requestCode == RequestCode) {
            response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                fireBaseuser = FirebaseAuth.getInstance().getCurrentUser();

                /*Set user data*/
                user.setName(fireBaseuser.getDisplayName());
                user.setEmail(fireBaseuser.getEmail());
                user.setProfileImage(fireBaseuser.getPhotoUrl().toString());
                user.setPhoneNumber(fireBaseuser.getPhoneNumber());
                user.setUserID(fireBaseuser.getUid());
                user.setProvider(fireBaseuser.getProviderId());

                Session.user = user;

                /*Load data to data base*/
                /*user data structure name is set by user.getUserID()*/
                RealTimeDataBase.getDataBaseTable(data_base_table, user.getUserID()).setValue(user);

                Toast.makeText(Window, Window.getString(R.string.welcome_message) + " " + fireBaseuser.getDisplayName(), Toast.LENGTH_LONG).show();


                SingedUp = true;
            } else {
                SingedUp = false;
                Toast.makeText(Window, Window.getString(R.string.failed_login_message), Toast.LENGTH_LONG).show();
            }
        }
    }

    static public FirebaseUser getFireBaseUser() {
        return fireBaseuser;
    }
    static public IdpResponse getFireBaseResponse(){
        return response;
    }
    static public boolean isLoged() {
        return SingedUp;
    }

    static public User getUserConnected(){
        return user;
    }
}
