# Chapter schema

# --- !Ups
CREATE TABLE "Chapter"(
  "id" VARCHAR(32) NOT NULL UNIQUE,
  "parentId" VARCHAR(32) NULL,
  "shortName" VARCHAR(100),
  "fullName" VARCHAR(200),
  PRIMARY KEY (id),
  FOREIGN KEY ("parentId") REFERENCES "Chapter"(id)
);

# --- !Downs
DROP TABLE "Chapter";