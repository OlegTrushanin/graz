package oleg.trushanin.graz;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;


@Dao
public interface TableDataBaseKpDao {




    @Insert
    Completable insertKp(TableDataBaseKp tableDataBaseKp);


    @Query("DELETE from kp_table where uriPdf = :uriPdf")
    Completable deleteKp(String uriPdf);


    @Query("SELECT * from kp_table")
    LiveData<List<TableDataBaseKp>> getListKp();








}
