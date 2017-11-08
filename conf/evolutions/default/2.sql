# -- chapters schema

# -- ups

CREATE TABLE chapter(
  id_chapter INT NOT NULL UNIQUE,
  short_name_chapter VARCHAR(100),
  full_name_chapter VARCHAR(200),
  PRIMARY KEY (id_chapter)
);

# -- downs

DROP TABLE chapter;