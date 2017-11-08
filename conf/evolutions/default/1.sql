# Article schema

# --- !Ups
CREATE TABLE Article(
  idArticle INT NOT NULL UNIQUE,
  parentIdArticle INT NULL UNIQUE,
  shortNameArticle VARCHAR(50),
  fullNameArticle VARCHAR(200),
  textOfArticle TEXT,
  PRIMARY KEY (idArticle)
);

# --- !Downs
DROP TABLE Article;