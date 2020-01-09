package com.gym.chater.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.gym.chater.Data.FragmentManager;
import com.gym.chater.Fragments.FriendsFragment;
import com.gym.chater.Fragments.HomeFragment;
import com.gym.chater.Fragments.LogoutFragment;
import com.gym.chater.Fragments.ProfileFragment;
import com.gym.chater.Fragments.SettingsFragment;
import com.gym.chater.Fragments.UsersFragment;
import com.gym.chater.R;
import com.gym.chater.ViewModel.Login;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {//implements for function setNavigationItemSelectedListener
    private DrawerLayout drawer;

    private TextView UserName;
    private TextView UserEmail;
    private ImageView UserImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(getApplicationContext());

        Toolbar toolbar = findViewById(R.id.toolbar);//on linear layout tool bar.
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);//This activity layout

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Add tool bar to activity with action toggle to open navigation menu
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();//Sync button rotation with drawer navigation menu.

        if (savedInstanceState == null) {//Activity created first time then laod fragment and select item on nav menu.
            View headerView = navigationView.getHeaderView(0);//Get header of navigation
            UserName        = headerView.findViewById(R.id.Nav_Header_Person);
            UserEmail       = headerView.findViewById(R.id.Nav_Header_Person_Email);
            UserImage       = headerView.findViewById(R.id.Nav_Header_Image);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();//show first fragment
            navigationView.setCheckedItem(R.id.menu_item_home);//make in nav menu item selected

            Login.setActivity(this);
        }
        FragmentManager.setManager(getSupportFragmentManager());
    }

    @Override //button back clicked close navigation first not activity.
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override//On item clicked in nav menu function occurs
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        final MediaPlayer player = MediaPlayer.create(getBaseContext(), R.raw.menu_sound);

        switch (menuItem.getItemId()) {
            case R.id.menu_item_profile:
                player.start();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();//Make fragment_profile.xml layout displayed in fragment_container
                break;
            case R.id.menu_item_home:
                player.start();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();//Make fragment_profile.xml layout displayed in fragment_container
                break;
            case R.id.menu_item_friends:
                player.start();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FriendsFragment()).commit();//Make fragment_profile.xml layout displayed in fragment_container
                break;
            case R.id.menu_item_add_friends:
                player.start();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UsersFragment()).commit();//Make fragment_profile.xml layout displayed in fragment_container
                break;
            case R.id.menu_item_logout:
                player.start();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LogoutFragment()).commit();//Make fragment_profile.xml layout displayed in fragment_container
                break;
            case R.id.menu_item_settings:
                player.start();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment(getBaseContext())).commit();//Make fragment_profile.xml layout displayed in fragment_container
                break;
            default:
                player.start();
                Toast.makeText(this, "Unknown command", Toast.LENGTH_LONG).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);//Close navigation menu on item clicked

        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Login.ObserveUserData(requestCode, resultCode, data);

        UserName.setText(Login.getFireBaseUser().getDisplayName());
        UserEmail.setText(Login.getFireBaseUser().getEmail());
        Picasso.get().load(Login.getFireBaseUser().getPhotoUrl()).into(UserImage);
    }


}