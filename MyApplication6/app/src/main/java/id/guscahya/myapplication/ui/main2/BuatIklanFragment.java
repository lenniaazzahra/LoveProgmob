package id.guscahya.myapplication.ui.main2;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.guscahya.myapplication.R;

public class BuatIklanFragment extends Fragment {


    public static BuatIklanFragment newInstance() {
        return new BuatIklanFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.iklan_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        return root;    }

}