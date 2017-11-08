# chapter schema

# --- !Ups
CREATE TABLE chapter(
  id_chapter INT NOT NULL UNIQUE,
  short_name_chapter VARCHAR(100),
  full_name_chapter VARCHAR(200),
  parent_article VARCHAR(200),
  parent_chapter VARCHAR(200),
  PRIMARY KEY (id_chapter),
  FOREIGN KEY (id_chapter) REFERENCES article(id_article)
);

# --- !Downs
DROP TABLE chapter;