package oleg.trushanin.graz;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface PriceDao {


    @Query("SELECT * FROM price_table")
    LiveData<List<Price>> getPrice();



    @Query("DELETE FROM price_table")
    Completable deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<Price> prices);




}
