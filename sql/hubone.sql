-- Creation of shop table
CREATE TABLE IF NOT EXISTS shop (
  id INT NOT NULL,
  name varchar(250) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS log_event (
  id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  logger varchar(250) NOT NULL,
  level varchar(250) NOT NULL,
  time_stamp TIMESTAMPTZ NOT NULL,
  message varchar(1024) NOT NULL
);