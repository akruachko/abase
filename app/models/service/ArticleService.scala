package models.service

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.entities.Article
import org.squeryl.adapters.PostgreSqlAdapter
import org.squeryl.{Session, SessionFactory, Table}
import play.api.db.Database
import org.squeryl.PrimitiveTypeMode._


@ImplementedBy(classOf[ArticleServiceImpl])
trait ArticleService extends Service[Article]{
  def deleteWhere(chapterId: String)
}


class ArticleServiceImpl @Inject()(db: Database) extends ArticleService {

  import models.DefineScheme._
  override val table: Table[Article] = article

  override def findById(id: String): Article = {
    transaction{
      from(article)(a => where(a.id === id) select a).headOption.get
    }
  }

  override def delete(id: String): Unit = {
    transaction{
      article.deleteWhere(a => a.id === id)
    }
  }

  override def deleteWhere(chapterId: String): Unit = {
    transaction{
      article.deleteWhere(a => a.chapterId === chapterId)
    }
  }
  override def updateEntity(data: Article): Unit = {
    transaction{
      article.update(data)
    }
  }
}
