package cat.itb.mylibrari.bookRecyclerViewFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cat.itb.mylibrari.BookViewModel;
import cat.itb.mylibrari.R;

public class RecyclerViewListFragment extends Fragment {
    RecyclerView recyclerView;
    BookListAdapter adapter;
    BookViewModel viewModel = null;
    FloatingActionButton button;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.book_list_fragment, container, false);

        if (viewModel == null) viewModel = new ViewModelProvider(this).get(BookViewModel.class);

        button = v.findViewById(R.id.floatingActionButton);
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BookListAdapter(viewModel.getBookList());
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_recyclerViewListFragment_to_addBookFragment);
            }
        });

        return v;
    }

}
