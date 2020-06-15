package konstmih.app.bookstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Book data class, using Room
@Entity
data class Book (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var author: String,
    var summary: String,
    @ColumnInfo(name = "picture_url")
    var pictureUrl: String
)