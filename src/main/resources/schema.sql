CREATE TABLE IF NOT EXISTS Libro(
      id VARCHAR(80) NOT NULL,
      titulo VARCHAR2(80),
      autor VARCHAR2(80),
      isbn VARCHAR2(80),
      paginas INT,
      PRIMARY KEY (id));