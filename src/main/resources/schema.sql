DROP TABLE phones IF EXISTS;
DROP TABLE sessions IF EXISTS;
DROP TABLE users IF EXISTS;

CREATE TABLE phones (phone_id uuid NOT NULL, number int, citycode int, contrycode int,user_id uuid NOT NULL, PRIMARY KEY (phone_id));
CREATE TABLE sessions (session_id uuid NOT NULL, token varchar(255), isactive bit default 'true', last_login timestamp,user_id uuid NOT NULL, PRIMARY KEY (session_id));
CREATE TABLE users (user_id uuid NOT NULL, name varchar(50), email varchar(50) UNIQUE, password varchar(20), created timestamp, modified timestamp, PRIMARY KEY (user_id));
ALTER TABLE phones ADD CONSTRAINT user_phones FOREIGN KEY (user_id) REFERENCES users (user_id);
ALTER TABLE sessions ADD CONSTRAINT user_session FOREIGN KEY (user_id) REFERENCES users (user_id);

