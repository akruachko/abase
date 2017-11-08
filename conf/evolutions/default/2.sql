# Chapter schema

# --- !Ups
CREATE TABLE Chapter(
  idChapter INT NOT NULL UNIQUE,
  parentIdChapter INT NULL UNIQUE,
  shortNameChapter VARCHAR(100),
  fullNameChapter VARCHAR(200),
  listOfNestedArticles TEXT,
  listOfNestedChapters TEXT,
  PRIMARY KEY (idChapter),
  FOREIGN KEY (idChapter) REFERENCES Article(idArticle)
);

# --- !Downs
DROP TABLE Chapter;