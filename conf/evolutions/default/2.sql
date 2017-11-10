# Article schema

# --- !Ups
CREATE TABLE "Article"(
  "id" VARCHAR(32) NOT NULL UNIQUE,
  "chapterId" VARCHAR(32) NULL,
  "shortName" VARCHAR(50),
  "fullName" VARCHAR(200),
  "text" TEXT,
  PRIMARY KEY (id),
  FOREIGN KEY ("chapterId") REFERENCES "Chapter"(id)
);

# --- !Downs
DROP TABLE "Article";