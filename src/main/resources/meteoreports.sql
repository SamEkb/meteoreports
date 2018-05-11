create table role(
	role_id serial primary key not null,
	role_name text
);

create table users(
	id serial primary key not null,
	login varchar,
	password varchar,
	user_name text,
	registration_date timestamp,
	role_id bigint,
	foreign key(role_id) references role(role_id)
);

create table meteo_station(
	id serial primary key not null,
	station_name text
);

create table meteo_station_data(
	meteo_station_id bigint,
	foreign key(meteo_station_id) references meteo_station(id),
	read_teimestamp timestamp,
	temperature decimal(4,1),
	pressure integer,
	wind_direction integer,
	wind_speed integer,
	primary key(meteo_station_id, read_teimestamp, temperature, pressure, wind_direction, wind_speed)
);

create table reports(
	id serial primary key not null,
	header text,
	footer text,
	meteo_station_id bigint,
	foreign key(meteo_station_id) references meteo_station(id),
	user_id bigint,
	foreign key(user_id) references users(id),
	creation_date timestamp
);

create table report_columns(
	id serial primary key not null,
	column_name text,
	report_id bigint,
	foreign key(report_id) references reports(id),
	order_number bigint,
	type text
);

create table report_rows(
	row_id bigint,
	column_id bigint,
	foreign key(column_id) references report_columns(id),
	row_value text
);
