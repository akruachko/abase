package models.service

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.entities.Chapter
import org.squeryl.Table
import play.api.db.Database
import org.squeryl.PrimitiveTypeMode._

@ImplementedBy(classOf[ChapterServiceImpl])
trait ChapterService extends Service[Chapter]

class ChapterServiceImpl @Inject()(db: Database) extends ChapterService {
  import models.DefineScheme.chapter

  override val table: Table[Chapter] = chapter

  override def findById(id: String): Chapter = {
    transaction{
      from(chapter)(c => where(c.id === id) select c).headOption.get
    }
  }

  override def delete(id: String): Unit = {
    transaction{
      chapter.deleteWhere(c => c.id === id)
    }
  }

  override def updateEntity(data: Chapter): Unit = {
    transaction{
      chapter.update(data)
    }
  }
}
