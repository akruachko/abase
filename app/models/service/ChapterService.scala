package models.service

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.entities.Chapter
import org.squeryl.adapters.PostgreSqlAdapter
import org.squeryl.{Session, SessionFactory}
import play.api.db.Database
import org.squeryl.PrimitiveTypeMode._

@ImplementedBy(classOf[ChapterServiceImpl])
trait ChapterService extends Service[Chapter]

class ChapterServiceImpl @Inject()(db: Database) extends ChapterService {
  import models.DefineScheme.chapter

  SessionFactory.concreteFactory = Some(
    () => Session.create(
      db.getConnection(), new PostgreSqlAdapter
    )
  )

  override def add(data: Chapter): Unit = {
    transaction{
      chapter.insert(data)
    }
  }

  override def list(): Seq[Chapter] = {
    transaction{
      from(chapter)(c => select(c)).toList
    }
  }

  override def findById(id: String): Chapter = {
    transaction{
      from(chapter)(c => select(c)).headOption.get
    }
  }

  override def delete(id: String): Unit = {
    transaction{
      chapter.deleteWhere(c => c.id === id)
    }
  }

  override def updateEntity(data: Chapter): Unit = {
    transaction{
      update(chapter)(c => where(c.id === data.id)
        set(
          c.id := data.id,
          c.parentId := data.parentId,
          c.shortName := data.shortName,
          c.fullName := data.fullName
        )
      )
    }
  }
}
