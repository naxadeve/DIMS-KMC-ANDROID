
package np.com.naxa.iset.disasterinfo.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "DisasterInfoDetailsEntity",
        indices = {@Index(value = {"id_from_server"},
                unique = true)})
public class DisasterInfoDetailsEntity {

    @PrimaryKey(autoGenerate = true)
    private String id;

    @SerializedName("id")
    @ColumnInfo(name = "id_from_server")
    @Expose
    private String id_from_server;

    @ColumnInfo(name = "short_desc")
    @SerializedName("short_desc")
    @Expose
    private String shortDesc;

    @ColumnInfo(name = "desc")
    @SerializedName("desc")
    @Expose
    private String desc;

    @ColumnInfo(name = "photo")
    @SerializedName("photo")
    @Expose
    private String photo;

    @ColumnInfo(name = "categoryname")
    @SerializedName("categoryname")
    @Expose
    private String categoryname;

    @ColumnInfo(name = "subcatname")
    @SerializedName("subcatname")
    @Expose
    private String subcatname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getSubcatname() {
        return subcatname;
    }

    public void setSubcatname(String subcatname) {
        this.subcatname = subcatname;
    }

}
