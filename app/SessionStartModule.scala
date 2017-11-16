
import com.google.inject.AbstractModule
import org.squeryl.{Session, SessionFactory}
import org.squeryl.adapters.PostgreSqlAdapter

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.

 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
class SessionStartModule extends AbstractModule {
  override def configure() = {
    SessionFactory.concreteFactory = Some(
      () => Session.create(
        java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/abase", "postgres", "L3282"), new PostgreSqlAdapter
      )
    )
  }

}
