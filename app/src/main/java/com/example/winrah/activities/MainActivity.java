package com.example.winrah.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.winrah.R;
import com.example.winrah.databinding.ActivityMainBinding;
import com.example.winrah.fragments.DepositFragment;
import com.example.winrah.fragments.FavoriteFragment;
import com.example.winrah.fragments.HomeFragment;
import com.example.winrah.fragments.SectionFragment;
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

                SectionFragment sectionFragment = new SectionFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(binding.fragmentLayout.getId(), sectionFragment, "SectionFragment");
                fragmentTransaction.commit();
            }

            private void showFavoriteFragment() {
                binding.toolbarTV.setText(R.string.favorites);

                FavoriteFragment favoriteFragment = new FavoriteFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(binding.fragmentLayout.getId(), favoriteFragment, "FavoriteFragment");
                fragmentTransaction.commit();
            }

            private void showDepositFragment() {
                binding.toolbarTV.setText(R.string.deposits);

                DepositFragment depositFragment = new DepositFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(binding.fragmentLayout.getId(), depositFragment, "DepositFragment");
                fragmentTransaction.commit();
            }

            private void showHomeFragment() {
                binding.toolbarTV.setText(R.string.home);

                HomeFragment homeFragment = new HomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(binding.fragmentLayout.getId(), homeFragment, "HomeFragment");
                fragmentTransaction.commit();
            }
        });
    }
}