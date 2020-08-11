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
