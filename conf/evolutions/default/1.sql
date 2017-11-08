CREATE TABLE article(
  id_article INT NOT NULL UNIQUE,
  short_name_article VARCHAR(50),
  full_name_article VARCHAR(200),
  text_article TEXT,
  PRIMARY KEY (id_article)
);

DROP TABLE article;

CREATE TABLE chapter(
  id_chapter INT NOT NULL UNIQUE,
  short_name_chapter VARCHAR(100),
  full_name_chapter VARCHAR(200),
  parent_article VARCHAR(200),
  parent_chapter VARCHAR(200),
  PRIMARY KEY (id_chapter),
  FOREIGN KEY (short_name_chapter) REFERENCES article(short_name_article)
);

DROP TABLE chapter;