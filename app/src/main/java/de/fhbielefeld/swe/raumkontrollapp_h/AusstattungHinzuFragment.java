package de.fhbielefeld.swe.raumkontrollapp_h;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;

import de.fhbielefeld.swe.raumkontrollapp_h.databinding.FragmentAusstattungHinzuBinding;

public class AusstattungHinzuFragment<raumnr> extends Fragment {

    private FragmentAusstattungHinzuBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = FragmentAusstattungHinzuBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
/*
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AusstattungHinzuFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}