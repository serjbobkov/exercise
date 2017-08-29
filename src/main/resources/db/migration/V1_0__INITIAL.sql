CREATE TABLE contacts (
  contact_id INT PRIMARY KEY,
);

CREATE TABLE applications (
  application_id INT AUTO_INCREMENT PRIMARY KEY,
  dt_created     DATETIME,
  product_name   VARCHAR,
  contact        INT REFERENCES contacts (contact_id) ON DELETE CASCADE

);

CREATE UNIQUE INDEX IDX_DT_CREATED ON applications(dt_created);