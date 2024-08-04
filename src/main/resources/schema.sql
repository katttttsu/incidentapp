CREATE TABLE IF NOT EXISTS incidents (
id                 INT       NOT NULL AUTO_INCREMENT,
   level              VARCHAR(256) NOT NULL,
   date               VARCHAR(256) NOT NULL,
   time               VARCHAR(256) NOT NULL,
   place              VARCHAR(256) NOT NULL,
   patientId          VARCHAR(256) NOT NULL,
   patientName        VARCHAR(256) NOT NULL,
   patientAge         VARCHAR(256) NOT NULL,
   department         VARCHAR(256) NOT NULL,
   job                VARCHAR(256) NOT NULL,
   continuousService  VARCHAR(256) NOT NULL,
   category1          VARCHAR(256) NOT NULL,
   category2          VARCHAR(256) NOT NULL,
   situation          VARCHAR(256) NOT NULL,
   cause              VARCHAR(256) NOT NULL,
   suggestion         VARCHAR(256) ,
   countermeasure     VARCHAR(256) NOT NULL,

PRIMARY KEY (id)
);