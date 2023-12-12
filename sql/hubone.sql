-- Creation of shop table
CREATE TABLE IF NOT EXISTS shop (
  id INT NOT NULL,
  name varchar(250) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS log (
    id bigint NOT NULL,
    date_entered timestamp,
    interface character varying,
    method character varying,
    tries integer,
    last_execution timestamp,
    server character varying,
    traceid character varying,
    environment character varying,
    request character varying,
    request_type character varying,
    response character varying,
    errors character varying,
    warnings character varying,
    duration integer,
    duration_external integer,
    duration_db integer,
    caller character varying,
    customer_id character varying,
    additional_calls character varying,
    tracelink character varying
);

ALTER TABLE log ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);