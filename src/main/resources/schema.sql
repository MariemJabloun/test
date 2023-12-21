DROP TABLE IF EXISTS subscription;
DROP TABLE IF EXISTS CUSTOMER;
create table IF NOT EXISTS customer (active boolean DEFAULT true, creationdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP not null, id bigint not null, email varchar(255), firstname varchar(255) not null, lastname varchar(255) not null, phone varchar(255) not null, primary key (id), phone_active int as 
      (case active when false then null else phone end) 
      unique);
create table IF NOT EXISTS subscription (active boolean not null default true, canceldate timestamp(6), expirydate timestamp(6) not null, fk_customer bigint not null, id bigint not null, startdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, cancel_reason varchar(255), currency varchar(255), description varchar(255), name varchar(255) not null, price varchar(255), recurrency varchar(255), primary key (id));
alter table if exists subscription add constraint FKbgvhd6ry5b9y43ufvo3rhdrpk foreign key (fk_customer) references customer on delete cascade;