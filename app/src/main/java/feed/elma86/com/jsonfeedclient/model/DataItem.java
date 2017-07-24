package feed.elma86.com.jsonfeedclient.model;


import android.os.Parcel;
import android.os.Parcelable;

public class DataItem implements Parcelable {

//C# webapi properties jsonOutput naming conventions do not match javas naming conventions
// thus the capitals in order for GSON to parse the data
    private int Sort;
    private String Company;
    private String Province;
    private String City;
    private String Title;
    private String Description;
    private String Responsibility;
    private double Salary;
    private String Phone;
    private double Latitude;
    private double Longitude;
    private String Image;

    public int getSort() {
        return Sort;
    }

    public void setSort(int sort) {
        this.Sort = sort;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        this.Company = company;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        this.Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        this.City = city;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getResponsibility() {
        return Responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.Responsibility = responsibility;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        this.Salary = salary;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        this.Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        this.Longitude = longitude;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Sort);
        dest.writeString(this.Company);
        dest.writeString(this.Province);
        dest.writeString(this.City);
        dest.writeString(this.Title);
        dest.writeString(this.Description);
        dest.writeString(this.Responsibility);
        dest.writeDouble(this.Salary);
        dest.writeString(this.Phone);
        dest.writeDouble(this.Latitude);
        dest.writeDouble(this.Longitude);
        dest.writeString(this.Image);
    }

    public DataItem() {
    }

    protected DataItem(Parcel in) {
        this.Sort = in.readInt();
        this.Company = in.readString();
        this.Province = in.readString();
        this.City = in.readString();
        this.Title = in.readString();
        this.Description = in.readString();
        this.Responsibility = in.readString();
        this.Salary = in.readDouble();
        this.Phone = in.readString();
        this.Latitude = in.readDouble();
        this.Longitude = in.readDouble();
        this.Image = in.readString();
    }

    public static final Parcelable.Creator<DataItem> CREATOR = new Parcelable.Creator<DataItem>() {
        @Override
        public DataItem createFromParcel(Parcel source) {
            return new DataItem(source);
        }

        @Override
        public DataItem[] newArray(int size) {
            return new DataItem[size];
        }
    };
}

