package cat.itb.mylibrari.bookRecyclerViewFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.itb.mylibrari.Book;
import cat.itb.mylibrari.R;


public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListViewHolder> {

    private List<Book> bookList;


    public BookListAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    class BookListViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView author;
        TextView state;

        public BookListViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textViewTitle);
            author = itemView.findViewById(R.id.textViewAuthor);
            state = itemView.findViewById(R.id.textViewState);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    NavDirections detailsBook = RecyclerViewListFragmentDirections.actionRecyclerViewListFragmentToAddBookFragment(bookList.get(getAdapterPosition()));

                    Navigation.findNavController(v).navigate(detailsBook);
                }
            });
        }

        public void bindData(Book book) {
            if (book != null) {
                title.setText(book.getTitle());
                author.setText(book.getAuthor());
                state.setText(book.getStatus());
            }
        }

    }

    @NonNull
    @Override
    public BookListAdapter.BookListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, parent, false);
        return new BookListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListAdapter.BookListViewHolder holder, final int position) {
        holder.bindData(bookList.get(position));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

}
