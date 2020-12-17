package cat.itb.mylibrari;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String Title;
    private String Author;
    private String status;
    private float rate;


    public Book(String title, String author, String status, float rate) {
        Title = title;
        Author = author;
        this.status = status;
        this.rate = rate;
    }

    protected Book(Parcel in) {
        Title = in.readString();
        Author = in.readString();
        status = in.readString();
        rate = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public String getStatus() {
        return status;
    }

    public float getRate() {
        return rate;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(Author);
        dest.writeString(status);
        dest.writeFloat(rate);
    }
}
