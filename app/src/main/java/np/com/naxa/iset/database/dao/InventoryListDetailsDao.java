package np.com.naxa.iset.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import np.com.naxa.iset.inventory.model.InventoryListDetails;

@Dao
public interface InventoryListDetailsDao {

    @Query("SELECT * from InventoryListDetails ORDER BY iId ASC")
    Flowable<List<InventoryListDetails>> getAllInventoryListDetailsList();

    @Query("SELECT * from InventoryListDetails WHERE categorySlug LIKE :category AND subCatSlug LIKE :subCategory")
    Flowable<List<InventoryListDetails>> getCatSubCatWiseInventoryList(String category, String subCategory);

    @Query("SELECT * from InventoryListDetails WHERE categorySlug LIKE :category")
    Flowable<List<InventoryListDetails>> getCategoryWiseInventoryList(String category);

    @Query("SELECT * from InventoryListDetails WHERE subCatSlug LIKE :subCategory")
    Flowable<List<InventoryListDetails>> getSubCategoryWiseInventoryList(String subCategory);

    // We do not need a conflict strategy, because the word is our primary key, and you cannot
    // add two items with the same primary key to the database. If the table has more than one
    // column, you can use @Insert(onConflict = OnConflictStrategy.REPLACE) to update a row.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(InventoryListDetails inventoryListDetails);

    @Query("DELETE FROM InventoryListDetails")
    void deleteAll();

    @Query("DELETE FROM InventoryListDetails WHERE id LIKE :unique_id")
    void deleteSpecific(String unique_id);
}