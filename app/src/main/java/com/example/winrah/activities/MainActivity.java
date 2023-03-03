package com.example.winrah.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.winrah.R;
import com.example.winrah.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        binding.bottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemID = item.getItemId();

                switch (itemID) {
                    case R.id.homeMenuBtn:
                        showHomeFragment();
                        return true;
                    case R.id.depositMenuBtn:
                        showDepositFragment();
                        return true;
                    case R.id.favoriteMenuBtn:
                        showFavoriteFragment();
                        return true;
                    case R.id.sectionsMenuBtn:
                        showSectionFragment();
                        return true;
                    case R.id.addMenuBtn:
                        showAddFragment();
                        return true;

                    default: return false;
                }
            }

            private void showAddFragment() {
                binding.toolbarTV.setText(R.string.add);
            }

            private void showSectionFragment() {
                binding.toolbarTV.setText(R.string.sections);
            }

            private void showFavoriteFragment() {
                binding.toolbarTV.setText(R.string.favorites);
            }

            private void showDepositFragment() {
                binding.toolbarTV.setText(R.string.deposits);
            }

            private void showHomeFragment() {
                binding.toolbarTV.setText(R.string.home);
            }
        });
    }
}