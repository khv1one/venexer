create table auth_client_details (
    id bigint not null,
    access_token_validity integer not null,
    additional_information varchar(255),
    client_id varchar(255),
    client_secret varchar(255),
    grant_types varchar(255),
    redirect_uris varchar(255),
    refresh_token_validity integer not null,
    resources varchar(255),
    scopes varchar(255),
    primary key (id)
) engine=InnoDB;

create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );

create table users (
    id bigint not null,
    activated bit not null,
    activation_key varchar(255),
    authorities tinyblob,
    password varchar(200),
    reset_password_key varchar(255),
    username varchar(200),
    primary key (id)
) engine=InnoDB;

insert into auth_client_details (
    id,
    access_token_validity,
    refresh_token_validity,
    client_id,
    client_secret,
    grant_types,
    scopes
) values (
    1,
    9999999,
    9999999,
    'browser',
    '$2a$10$fWNTd3H.u7G/aNROVQSifebOkZ2xzU5nUPOCI2Ld42M8E25/ljJqK',
    'refresh_token,password',
    'ui'
);

insert into auth_client_details (
    id,
    access_token_validity,
    refresh_token_validity,
    client_id,
    client_secret,
    grant_types,
    scopes
) values (
    2,
    9999999,
    9999999,
    'account-service',
    '$2a$10$fWNTd3H.u7G/aNROVQSifebOkZ2xzU5nUPOCI2Ld42M8E25/ljJqK',
    'refresh_token,client_credentials',
    'server'
);

create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove tinyint
);

create table oauth_client_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

create table oauth_access_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BLOB,
  refresh_token VARCHAR(255)
);

create table oauth_refresh_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication BLOB
);

create table oauth_code (
  code VARCHAR(255), authentication BLOB
);