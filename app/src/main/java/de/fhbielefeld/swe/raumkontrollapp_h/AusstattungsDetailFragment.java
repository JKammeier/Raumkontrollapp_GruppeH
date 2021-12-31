package de.fhbielefeld.swe.raumkontrollapp_h;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import de.fhbielefeld.swe.raumkontrollapp_h.databinding.FragmentAusstattungsDetailBinding;

public class AusstattungsDetailFragment extends Fragment {

    private FragmentAusstattungsDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentAusstattungsDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.button00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AusstattungsDetailFragment.this).navigate(R.id.action_AusstattungsDetailFragment_to_AusstattungHinzuFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}