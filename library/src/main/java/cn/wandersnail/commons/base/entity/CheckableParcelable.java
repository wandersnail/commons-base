package cn.wandersnail.commons.base.entity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * date: 2019/8/6 12:38
 * author: zengfansheng
 */
public class CheckableParcelable<T extends Parcelable> extends CheckableItem<T> implements Parcelable {
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("items", getData());
        dest.writeBundle(bundle);
        dest.writeByte(isChecked() ? (byte) 1 : (byte) 0);
    }

    public CheckableParcelable() {
    }

    public CheckableParcelable(T data) {
        super(data);
    }

    public CheckableParcelable(T data, boolean isChecked) {
        super(data, isChecked);
    }

    @SuppressWarnings("unchecked")
    protected CheckableParcelable(Parcel in) {
        Bundle bundle = in.readBundle(getClass().getClassLoader());
        if (bundle != null) {
            setData((T) bundle.getParcelable("items"));
        }
        setChecked(in.readByte() != 0);
    }

    public static final Creator<CheckableParcelable> CREATOR = new Creator<CheckableParcelable>() {
        @Override
        public CheckableParcelable createFromParcel(Parcel source) {
            return new CheckableParcelable(source);
        }

        @Override
        public CheckableParcelable[] newArray(int size) {
            return new CheckableParcelable[size];
        }
    };
}
