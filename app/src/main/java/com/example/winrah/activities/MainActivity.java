package com.example.winrah.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.winrah.R;
import com.example.winrah.databinding.ActivityMainBinding;
import com.example.winrah.databinding.LayoutAddBinding;
import com.example.winrah.databinding.LayoutAddDepositBinding;
import com.example.winrah.databinding.LayoutAddProductBinding;
import com.example.winrah.databinding.LayoutAddSectionBinding;
import com.example.winrah.fragments.DepositFragment;
import com.example.winrah.fragments.FavoriteFragment;
import com.example.winrah.fragments.HomeFragment;
import com.example.winrah.fragments.SectionFragment;
import com.example.winrah.models.Deposit;
import com.example.winrah.models.Product;
import com.example.winrah.models.Section;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationBarView;
import com.orm.SugarDb;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SugarDb db = new SugarDb(this);
        db.onCreate(db.getDB());

        binding.bottomNV.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemID = item.getItemId();

                if(itemID == R.id.homeMenuBtn) {
                    showHomeFragment();
                    return true;
                } else if (itemID == R.id.depositMenuBtn) {
                    showDepositFragment();
                    return true;
                }
                else if (itemID == R.id.favoriteMenuBtn) {
                    showFavoriteFragment();
                    return true;
                }
                else if (itemID == R.id.sectionsMenuBtn) {
                    showSectionFragment();
                    return true;
                }
                else if (itemID == R.id.addMenuBtn) {
                    showAddOptions();
                    return true;
                }
                else return false;
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

        binding.addBtn.setOnClickListener(view -> {
            showAddOptions();
        });

    }

    private void showAddOptions() {
        final BottomSheetDialog dialog = new BottomSheetDialog(MainActivity.this);
        LayoutAddBinding addBinding = LayoutAddBinding.inflate(LayoutInflater.from(this), binding.getRoot(), false);

        dialog.setContentView(addBinding.getRoot());
        dialog.show();

        addBinding.addDepositBtn.setOnClickListener(view -> {
            final BottomSheetDialog depositDialog = new BottomSheetDialog(MainActivity.this);
            LayoutAddDepositBinding addDepositBinding = LayoutAddDepositBinding.inflate(LayoutInflater.from(this), binding.getRoot(), false);

            depositDialog.setContentView(addDepositBinding.getRoot());
            depositDialog.show();

            addDepositBinding.addBtn.setOnClickListener(depositView -> {
                Deposit deposit = new Deposit();
                deposit.setName(addDepositBinding.nameET.getText().toString());
                if (!deposit.validate()) {
                    Toast.makeText(this, R.string.enter_deposit_name, Toast.LENGTH_SHORT).show();
                    return;
                }

                deposit.save();
                Toast.makeText(this, R.string.deposit_created, Toast.LENGTH_SHORT).show();
            });
        });

        addBinding.addSectionBtn.setOnClickListener(view -> {
            final BottomSheetDialog sectionDialog = new BottomSheetDialog(MainActivity.this);
            LayoutAddSectionBinding addSectionBinding = LayoutAddSectionBinding.inflate(LayoutInflater.from(this), binding.getRoot(), false);

            sectionDialog.setContentView(addSectionBinding.getRoot());
            sectionDialog.show();

            addSectionBinding.depositTV.setOnClickListener(view1 -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                CharSequence[] names = Deposit.getAllDepositNames();
                builder.setTitle(getString(R.string.select_deposit))
                        .setItems(names, (dialogInterface, i) -> {
                            addSectionBinding.depositTV.setText(names[i]);
                        })
                        .create().show();
            });

            addSectionBinding.addBtn.setOnClickListener(depositView -> {
                if (addSectionBinding.depositTV.getText().toString().trim().isEmpty()) {
                    Toast.makeText(this, R.string.select_deposit, Toast.LENGTH_SHORT).show();
                    return;
                }

                String depositName = addSectionBinding.depositTV.getText().toString();
                String sectionName = addSectionBinding.nameET.getText().toString().trim(); // cannot be null
                Deposit deposit = Deposit.find(Deposit.class, "name = ?", depositName).get(0);

                Section section = new Section();
                section.setName(sectionName);
                section.setDeposit(deposit);

                if (!section.validate()) {
                    Toast.makeText(this, R.string.enter_section_name, Toast.LENGTH_SHORT).show();
                    return;
                }

                section.save();
                Toast.makeText(this, R.string.section_created, Toast.LENGTH_SHORT).show();
            });
        });

        addBinding.addProductBtn.setOnClickListener(view -> {
            final BottomSheetDialog productDialog = new BottomSheetDialog(MainActivity.this);
            LayoutAddProductBinding addProductBinding = LayoutAddProductBinding.inflate(LayoutInflater.from(this), binding.getRoot(), false);

            productDialog.setContentView(addProductBinding.getRoot());
            productDialog.show();

            addProductBinding.depositTV.setOnClickListener(view1 -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                CharSequence[] names = Deposit.getAllDepositNames();
                builder.setTitle(getString(R.string.select_deposit))
                        .setItems(names, (dialogInterface, i) -> {
                            addProductBinding.depositTV.setText(names[i]);
                            Deposit deposit = Deposit.find(Deposit.class, "name = ?", names[i].toString()).get(0);

                            addProductBinding.sectionTV.setText("");
                            addProductBinding.sectionTV.setOnClickListener(view2 -> {
                                AlertDialog.Builder sectionBuilder = new AlertDialog.Builder(this);
                                CharSequence[] sectionNames = deposit.sectionNames();

                                sectionBuilder.setTitle(getString(R.string.select_section))
                                        .setItems(sectionNames, (dialogInterface1, i1) -> {
                                            addProductBinding.sectionTV.setText(sectionNames[i1]);
                                        })
                                        .create().show();
                            });
                        })
                        .create().show();
            });

            addProductBinding.addBtn.setOnClickListener(view1 -> {
                Product product = new Product();
                product.setReference(addProductBinding.referenceET.getText().toString().trim());
                product.setPrice(addProductBinding.priceET.getText().toString().trim());
                product.setBoxColor(addProductBinding.boxColorET.getText().toString().trim());

                String sectionName = addProductBinding.sectionTV.getText().toString().trim();
                String depositName = addProductBinding.depositTV.getText().toString().trim();

                if (sectionName.isEmpty()) {
                    Toast.makeText(this, R.string.select_section, Toast.LENGTH_SHORT).show();
                    return;
                }

                Deposit deposit = Deposit.find(Deposit.class, "name = ?", depositName).get(0);
                Section section = Section.find(
                        Section.class,
                        "name = ? AND deposit = ?",
                        sectionName, String.valueOf(deposit.getId())
                ).get(0);
                product.setSection(section);

                if (!product.validate()) {
                    Toast.makeText(this, R.string.product_cannot_be_created, Toast.LENGTH_SHORT).show();
                    return;
                }

                product.save();
                Toast.makeText(this, R.string.product_created, Toast.LENGTH_SHORT).show();
            });
        });
    }

}