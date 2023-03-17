create sequence hibernate_sequence start 1 increment 1;
create table arrival_city (
                              id int8 not null,
                              airport varchar(255),
                              country varchar(255),
                              iata_code varchar(255) UNIQUE,
                              primary key (id));
create table avia_company (
                              id int8 not null,
                              airline_name varchar(255),
                              flight_number varchar(255),
                              iata_code varchar(255) UNIQUE,
                              primary key (id));
create table date_of_departure (
                                   id int8 not null,
                                   date varchar(255),
                                   date_code varchar(255) UNIQUE,
                                   date_encode varchar(255) UNIQUE,
                                   primary key (id));
create table departure_city (
                                id int8 not null,
                                airport varchar(255),
                                country varchar(255),
                                iata_code varchar(255) UNIQUE,
                                primary key (id));
create table pnr_ticket (
                            id int8 not null,
                            arrival_time varchar(255),
                            day_of_departure varchar(255),
                            departure_time varchar(255),
                            terminal varchar(255),
                            type_of_air_plane varchar(255),
                            type_of_ticket varchar(255),
                            arrival_city_id int8,
                            avia_company_id int8,
                            date_of_departure_id int8,
                            departure_city_id int8,
                            primary key (id));
create table users_table (
                             id int8 not null,
                             email varchar(255) UNIQUE,
                             full_name varchar(255),
                             login varchar(255) UNIQUE,
                             password varchar(255),
                             rdt date,
                             role varchar(255),
                             primary key (id));
alter table if exists pnr_ticket add constraint FKknhod0qw5njh0ufacv68peono foreign key (arrival_city_id) references arrival_city;
alter table if exists pnr_ticket add constraint FKr9lrqgwfols6jjhimqp9r7tk foreign key (avia_company_id) references avia_company;
alter table if exists pnr_ticket add constraint FKrh8vcwd6pirhcdlf7td9vy54a foreign key (date_of_departure_id) references date_of_departure;
alter table if exists pnr_ticket add constraint FK6akwycgmn32gdod6fj5wma6xd foreign key (departure_city_id) references departure_city;