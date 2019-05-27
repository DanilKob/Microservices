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

delete from users_scheme.user_roles;
delete from users_scheme.users

drop table users_scheme.user_roles;
drop table  users_scheme.users;


insert into users_scheme.users
(username, password, enabled)
values
('dan', '$2a$10$0OwUx/OL5xnnwcAj6n2/iuLZvFn59GCF1o6BYV4seIhCMzmrt.VQW', true);

insert into users_scheme.user_roles
(username, authority)
values
('dan', 'admin')


select * from (
	select * from users_scheme.users
	where 
	username = 'dan'
) as selected_username
join users_scheme.user_roles
on users_scheme.user_roles.username = selected_username.username
