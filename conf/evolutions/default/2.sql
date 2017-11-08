# Article schema

# --- !Ups
CREATE TABLE Article(
  id INT NOT NULL UNIQUE,
  chapterId INT NULL,
  shortName VARCHAR(50),
  fullName VARCHAR(200),
  text TEXT,
  PRIMARY KEY (id),
  FOREIGN KEY (chapterId) REFERENCES Chapter(id)
);

# --- !Downs
DROP TABLE Article;