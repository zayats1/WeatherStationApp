import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.bogdandev.weatherstationapp.data.SavedIP
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedIPDao {
    @Query("SELECT * FROM SavedIP")
    fun getAll(): List<SavedIP>

    @Insert(onConflict = REPLACE)
    fun insertAll(vararg ipaddr: SavedIP)

    @Delete
    fun delete(ipaddr: SavedIP)
}

