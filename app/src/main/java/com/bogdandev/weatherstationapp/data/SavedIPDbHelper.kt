import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bogdandev.weatherstationapp.data.SavedIP

@Dao
interface SavedIPDao {
    @Query("SELECT * FROM SavedIP")
    fun getAll(): List<SavedIP>

    @Insert
    fun insertAll(vararg ipaddr: SavedIP)

    @Delete
    fun delete(ipaddr: SavedIP)
}
