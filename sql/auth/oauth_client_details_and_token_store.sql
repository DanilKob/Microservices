drop table if exists oauth_client_details;
create table oauth_client_details(
  client_id varchar(256) null,
  resource_ids varchar(256) null,
  client_secret varchar(256) null,
  scope varchar(256) null,
  authorized_grant_types varchar(256) null,
  web_server_redirect_uri varchar(256) null,
  authorities varchar(256) null,
  access_token_validity int null,
  refresh_token_validity int null,
  additional_information varchar(4096) null,
  autoapprove varchar(256) null,
  primary key (client_id)
);

drop table if exists oauth_client_token;
create table oauth_client_token(
  token_id varchar(256) null,
  token bytea null,
  authentication_id varchar(256) null,
  user_name varchar(256) null,
  client_id varchar(256) null,
  primary key (authentication_id)
);
drop table if exists oauth_access_token;
create table oauth_access_token(
  token_id varchar(256) null,
  token bytea null,
  authentication_id varchar(256) null,
  user_name varchar(256) null,
  client_id varchar(256) null,
  authentication bytea null,
  refresh_token varchar(256) null,
  primary key (authentication_id)
);
drop table if exists oauth_refresh_token;
create table oauth_refresh_token(
  token_id varchar(256) null,
  token bytea null,
  authentication bytea null
);
drop table if exists oauth_code;
create table oauth_code(
  code varchar(256) null,
  authentication bytea null
);
drop table if exists oauth_approvals;
create table oauth_approvals(
  userId varchar(256) null,
  clientId varchar(256) null,
  scope varchar(256) null,
  status varchar(10) null,
  expiresAt timestamp null,
  lastModifiedAt timestamp null
);
