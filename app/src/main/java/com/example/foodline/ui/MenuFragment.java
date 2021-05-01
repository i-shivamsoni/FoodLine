package com.example.foodline.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodline.R;
import com.example.foodline.databinding.FragmentMenuBinding;
import com.example.foodline.model.MenuItem;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem;

public class MenuFragment extends Fragment {

    private FragmentMenuBinding binding;
    private ImageView screenIcon;
    private TextView screenTitleText;

    private MenuItemAdapter adapter;
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    public MenuFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((BaseActivity) requireActivity()).setAppBar(0);

        adapter = new MenuItemAdapter();

        menuItems.add(new MenuItem("Pizza", "Fast Food", "200",""));
        menuItems.add(new MenuItem("Pizza", "Fast Food", "200",""));
        menuItems.add(new MenuItem("Pizza", "Fast Food", "200",""));
        menuItems.add(new MenuItem("Pizza", "Fast Food", "200",""));
        menuItems.add(new MenuItem("Pizza", "Fast Food", "200",""));
        menuItems.add(new MenuItem("Pizza", "Fast Food", "200",""));
        menuItems.add(new MenuItem("Pizza", "Fast Food", "200",""));
        menuItems.add(new MenuItem("Pizza", "Fast Food", "200",""));
        menuItems.add(new MenuItem("Pizza", "Fast Food", "200",""));
        menuItems.add(new MenuItem("Pizza", "Fast Food", "200",""));
        menuItems.add(new MenuItem("Pizza", "Fast Food", "200",""));
        menuItems.add(new MenuItem("Pizza", "Fast Food", "200",""));
        menuItems.add(new MenuItem("Pizza", "Fast Food", "200",""));
        menuItems.add(new MenuItem("Pizza", "Fast Food", "200",""));
        menuItems.add(new MenuItem("Pizza", "Fast Food", "200",""));
        menuItems.add(new MenuItem("Pizza", "Fast Food", "200",""));
        menuItems.add(new MenuItem("Pizza", "Fast Food", "200",""));
        menuItems.add(new MenuItem("Pizza", "Fast Food", "200",""));
        menuItems.add(new MenuItem("Pizza", "Fast Food", "200",""));

        adapter.submitList(menuItems);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.menuItemList.getContext(), ((LinearLayoutManager)binding.menuItemList.getLayoutManager()).getOrientation());
        binding.menuItemList.addItemDecoration(dividerItemDecoration);

        binding.menuItemList.setAdapter(adapter);


    }
}