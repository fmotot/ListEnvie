package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Item implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int rating;
    private String link;
    private String description;
    private float price;
    private boolean isPurchased;

    public Item(int id, String name, int rating, String link, String description, float price, boolean isPurchased) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.link = link;
        this.description = description;
        this.price = price;
        this.isPurchased = isPurchased;
    }

    protected Item(Parcel in) {
        id = in.readInt();
        name = in.readString();
        rating = in.readInt();
        link = in.readString();
        description = in.readString();
        price = in.readFloat();
        isPurchased = in.readByte() != 0;
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isPurchased() {
        return isPurchased;
    }

    public void setPurchased(boolean purchased) {
        this.isPurchased = purchased;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", purchased=" + isPurchased +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(rating);
        parcel.writeString(link);
        parcel.writeString(description);
        parcel.writeFloat(price);
        parcel.writeByte((byte) (isPurchased ? 1 : 0));
    }
}
