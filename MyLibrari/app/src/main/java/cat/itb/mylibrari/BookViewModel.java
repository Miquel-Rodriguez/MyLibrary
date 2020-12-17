package cat.itb.mylibrari;

import android.widget.Spinner;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class BookViewModel extends ViewModel {

    private static List<Book> bookList = new ArrayList<>();

    public List<Book> getBookList() {
        return bookList;
    }


    public void addBookToList(Book book) {
        bookList.add(book);
    }


    public String StringForSpinner(Spinner spinner) {
        switch (spinner.getSelectedItemPosition()) {
            case 0:
                return "Want to read";
            case 1:
                return "Reading";
            case 2:
                return "Read";
        }
        return "";
    }

    public int IntForSpinner(String status) {
        switch (status) {
            case "Want to read":
                return 0;
            case "Reading":
                return 1;
            case "Read":
                return 2;
        }
        return 1;
    }

}
