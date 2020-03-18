package edu.cnm.deepdive.quoteclient.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.quoteclient.R;
import edu.cnm.deepdive.quoteclient.view.QuoteRecyclerAdapter;

public class QuotesFragment extends Fragment {

    private MainViewModel viewModel;
    private RecyclerView quoteList;


    public View onCreateView(@NonNull LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_quotes, container, false);
        quoteList = root.findViewById(R.id.quote_list);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        viewModel.getQuotes().observe(getViewLifecycleOwner(), (quotes) -> {
     // TODO Attach any appropriate listeners
            QuoteRecyclerAdapter adapter = new QuoteRecyclerAdapter(getContext(), quotes);
            quoteList.setAdapter(adapter);
        });
        viewModel.refreshQuotes();
    }
}