package cat.itb.mylibrari.bookInformationFragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import cat.itb.mylibrari.Book;
import cat.itb.mylibrari.BookViewModel;
import cat.itb.mylibrari.R;
import cat.itb.mylibrari.bookRecyclerViewFragment.BookListAdapter;

public class AddBookFragment extends Fragment implements View.OnClickListener {

    EditText editTextTitle, editTextAuthor;
    Spinner spinnerState;
    Button buttonAdd, buttonUpdate;
    BookViewModel viewModel;
    RatingBar ratingBar;
    BookListAdapter adapter;
    Book book;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_book_fragment, container, false);

        if (viewModel == null) viewModel = new ViewModelProvider(this).get(BookViewModel.class);

        editTextTitle = view.findViewById(R.id.editTextTitle);
        editTextAuthor = view.findViewById(R.id.editTextAuthor);
        spinnerState = view.findViewById(R.id.spinner);
        buttonAdd = view.findViewById(R.id.button);
        ratingBar = view.findViewById(R.id.ratingBar);
        buttonUpdate = view.findViewById(R.id.button_update);

        buttonUpdate.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);


        if (getArguments() != null) book = getArguments().getParcelable("ObjectBook");
        if (book != null) {
            editTextTitle.setText(book.getTitle());
            editTextAuthor.setText(book.getAuthor());
            spinnerState.setSelection(viewModel.IntForSpinner(book.getStatus()));
            buttonAdd.setEnabled(false);
            ratingBar.setRating(book.getRate());

            buttonAdd.setVisibility(View.INVISIBLE);
            buttonUpdate.setVisibility(View.VISIBLE);

        } else {
            buttonUpdate.setVisibility(View.INVISIBLE);
        }


        adapter = new BookListAdapter(viewModel.getBookList());

        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                cambioDeBoton();
            }
        };

        editTextTitle.addTextChangedListener(tw);
        editTextAuthor.addTextChangedListener(tw);

        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 2) {
                    ratingBar.setVisibility(View.VISIBLE);
                } else ratingBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return view;
    }

    public void cambioDeBoton() {
        if (!editTextAuthor.getText().toString().equals("") && !editTextAuthor.getText().toString().equals("")) {
            buttonAdd.setEnabled(true);
            buttonUpdate.setEnabled(true);
        } else {
            buttonAdd.setEnabled(false);
            buttonUpdate.setEnabled(false);
        }
    }

    public void crearNewBook() {
        viewModel.addBookToList(new Book(editTextTitle.getText().toString(), editTextAuthor.getText().toString(), viewModel.StringForSpinner(spinnerState), ratingBar.getRating()));
        adapter.notifyDataSetChanged();
    }

    public void updateBook() {
        book.setTitle(editTextTitle.getText().toString());
        book.setAuthor(editTextAuthor.getText().toString());
        book.setStatus(viewModel.StringForSpinner(spinnerState));
        book.setRate(ratingBar.getRating());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_update:
                updateBook();
                Navigation.findNavController(v).navigate(R.id.action_addBookFragment_to_recyclerViewListFragment);
                break;
            case R.id.button:
                crearNewBook();
                Navigation.findNavController(v).navigate(R.id.action_addBookFragment_to_recyclerViewListFragment);
                break;
        }
    }


}
