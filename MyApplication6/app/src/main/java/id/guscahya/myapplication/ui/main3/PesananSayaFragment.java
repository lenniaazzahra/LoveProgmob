package id.guscahya.myapplication.ui.main3;

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

public class PesananSayaFragment extends Fragment {



    public static PesananSayaFragment newInstance() {
        return new PesananSayaFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_fragment2, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        return root;    }



}