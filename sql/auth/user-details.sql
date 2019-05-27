CREATE TABLE users_scheme.users (
   username varchar(50) NOT NULL,
   password varchar(70) NOT NULL,
   enabled boolean NOT NULL DEFAULT FALSE,
   primary key(username)
);

create table users_scheme.user_roles(
  user_role_id SERIAL PRIMARY KEY,
  username varchar(50) NOT NULL,
  authority varchar(50) NOT NULL,
  UNIQUE (username,authority),
  FOREIGN KEY (username) REFERENCES users_scheme.users (username)
);
