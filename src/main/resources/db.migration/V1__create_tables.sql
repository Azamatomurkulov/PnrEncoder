
create table arrival_airport
(
    id bigint not null primary key,
    airport varchar(255),
    country varchar(255),
    iata_code UNIQUE varchar(255)
);

create table avia_company
(
    id bigint not null primary key,
    airline_name varchar(255),
    flight_number varchar(255),
    iata_code UNIQUE varchar(255)
);

create table date_of_departure
(
    id bigint not null primary key,
    date varchar(255),
    date_code UNIQUE varchar(255),
    date_encode UNIQUE varchar(255)
);

create table departure_airport
(
    id bigint not null primary key,
    airport varchar(255),
    country varchar(255),
    iata_code UNIQUE varchar(255)
);

create table pnr_ticket
(
    id bigint not null primary key,
    arrival_time varchar(255),
    day_of_departure varchar(255),
    departure_time varchar(255),
    terminal varchar(255),
    type_of_air_plane varchar(255),
    type_of_ticket varchar(255),
    arrival_airport_id bigint CONSTRAINT fk_arrival_airport_id
        REFERENCES arrival_airport(id),
    avia_company_id bigint CONSTRAINT fk_avia_company_id
        REFERENCES avia_company(id),
    date_of_departure_id bigint CONSTRAINT fk_date_of_departure
        REFERENCES date_of_departure(id),
    departure_city_id bigint CONSTRAINT fk_departure_airport_id
        REFERENCES departure_airport(id)
);

create table users_table
(
    id bigint not null primary key,
    email UNIQUE varchar(255),
    full_name varchar(255),
    login UNIQUE varchar(255),
    password varchar(255),
    rdt date,
    role varchar(255)
);