# article schema

# --- !Ups
CREATE TABLE article(
  id_article INT NOT NULL UNIQUE,
  short_name_article VARCHAR(50),
  full_name_article VARCHAR(200),
  text_article TEXT,
  PRIMARY KEY (id_article)
);

# --- !Downs
DROP TABLE article;