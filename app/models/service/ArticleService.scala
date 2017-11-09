package models.service

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.entities.Article
import org.squeryl.adapters.PostgreSqlAdapter
import org.squeryl.{Session, SessionFactory}
import play.api.db.Database
import org.squeryl.PrimitiveTypeMode._


@ImplementedBy(classOf[ArticleServiceImpl])
trait ArticleService extends Service[Article]{
  def deleteWhere(chapterId: String)
}


class ArticleServiceImpl @Inject()(db: Database) extends ArticleService {

  import models.DefineScheme.article

  override def deleteWhere(chapterId: String): Unit = {
    transaction{
      article.deleteWhere(a => a.chapterId === chapterId)
    }
  }

  override def add(data: Article): Unit = {
    transaction{
      article.insert(data)
    }
  }

  override def list(): Seq[Article] = {
    transaction{
      from(article)(a => select(a)).toList
    }
  }

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

  override def updateEntity(data: Article): Unit = {
    transaction{
      update(article)(a => where(a.id === data.id)
        set(
            a.id := data.id,
            a.chapterId := data.chapterId,
            a.shortName := data.shortName,
            a.fullName := data.fullName,
            a.text := data.text
        )
      )
    }
  }
}
