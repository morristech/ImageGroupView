package com.loopeer.android.librarys.imagegroupview;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class SquarePhotos implements Parcelable {

  public String url;
  public PhotoType type;

  public SquarePhotos(){}

  public SquarePhotos(String url, PhotoType type) {
    this.url = url;
    this.type = type;
  }

  protected SquarePhotos(Parcel in) {
    url = in.readString();
    type = in.readParcelable(PhotoType.class.getClassLoader());
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(url);
    dest.writeParcelable(type, flags);
  }

  public static final Creator<SquarePhotos> CREATOR = new Creator<SquarePhotos>() {
    @Override
    public SquarePhotos createFromParcel(Parcel in) {
      return new SquarePhotos(in);
    }

    @Override
    public SquarePhotos[] newArray(int size) {
      return new SquarePhotos[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  public enum PhotoType implements Parcelable {

    INTER("0"),

    LOCAL("1");


    private String mValue;

    PhotoType(String value) {
      mValue = value;
    }

    @Override
    public String toString() {
      return mValue;
    }

    private static final Map<String, PhotoType>
        STRING_MAPPING = new HashMap<>();

    static {
      for (PhotoType commentType : PhotoType.values()) {
        STRING_MAPPING.put(commentType.toString().toUpperCase(), commentType);
      }
    }

    public static PhotoType fromValue(String value) {
      return STRING_MAPPING.get(value.toUpperCase());
    }

    public static final Parcelable.Creator<PhotoType> CREATOR = new Parcelable.Creator<PhotoType>() {

      public PhotoType createFromParcel(Parcel in) {
        PhotoType option = PhotoType.values()[in.readInt()];
        option.mValue = in.readString();
        return option;
      }

      public PhotoType[] newArray(int size) {
        return new PhotoType[size];
      }

    };

    @Override
    public int describeContents() {
      return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
      out.writeInt(ordinal());
      out.writeString(mValue);
    }
  }
}