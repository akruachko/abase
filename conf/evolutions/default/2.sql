# Chapter schema

# --- !Ups
CREATE TABLE Chapter(
  id INT NOT NULL UNIQUE,
  parentId INT NULL,
  shortName VARCHAR(100),
  fullName VARCHAR(200),
  listOfNestedArticles TEXT,
  listOfNestedChapters TEXT,
  PRIMARY KEY (id),
  FOREIGN KEY (parentId) REFERENCES Chapter(id)
);

# --- !Downs
DROP TABLE Chapter;