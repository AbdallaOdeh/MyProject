package com.example.abdallap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdallap.Admin.ShowProduct;
import com.example.abdallap.DataTables.DBHelper;
import com.example.abdallap.User.HomeFragment;
import com.example.abdallap.User.CartFragment;
import com.example.abdallap.User.HomeFragment;
import com.example.abdallap.User.InfoFragment;
import com.example.abdallap.User.ProductFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.abdallap.DataTables.QueryString.SQL_CREATE_CART;
import static com.example.abdallap.DataTables.QueryString.SQL_DELETE_CART;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView username,email;

    private DrawerLayout drawerLayout;
    private FirebaseAuth fauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize Firebase Auth
        fauth = FirebaseAuth.getInstance();
        /*DBHelper dbHelper = new DBHelper(this);
        dbHelper.OpenWriteAble();
        dbHelper.getDb().execSQL(SQL_DELETE_CART);
        dbHelper.getDb().execSQL(SQL_CREATE_CART);
        dbHelper.Close();*/

        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);
        FirebaseUser user = fauth.getCurrentUser();
        if (user!=null){
            if(user.getDisplayName().startsWith("admin: ")) {
                Intent i = new Intent(MainActivity.this, ShowProduct.class);
                startActivity(i);
            }
            NavigationView navigationView = findViewById(R.id.nav_view);
            View headerview = navigationView.getHeaderView(0);
            username =(TextView) headerview.findViewById(R.id.tvUserName);
            email = (TextView) headerview.findViewById(R.id.tvUserEmail);
            username.setText(user.getDisplayName());
            email.setText(user.getEmail());
        }else{
            Intent i= new Intent(MainActivity.this, LogIn.class);
            startActivity(i);
        }
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }


    }


    @Override
    protected void onRestart() {

        super.onRestart();
    }
    @Override
    protected void onResume() {

        super.onResume();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(R.id.nav_home==item.getItemId()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        }
        else if(R.id.nav_cart==item.getItemId()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CartFragment()).commit();
        }
        else if(R.id.nav_product==item.getItemId()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProductFragment()).commit();
        }
        else if(R.id.nav_about==item.getItemId()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InfoFragment()).commit();
        }
        else if(R.id.nav_logout==item.getItemId()){
            fauth.signOut();
            startActivity(new Intent(this,LogIn.class));
            Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}