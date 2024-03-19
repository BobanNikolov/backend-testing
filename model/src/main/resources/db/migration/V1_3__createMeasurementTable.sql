create table if not exists backend_testing.measurement
(
    measurement_id uuid primary key                  default gen_random_uuid(),
    sensor_id      uuid                     not null,
    stamp          timestamp with time zone not null default now(),
    value_type     varchar(100)             not null check (value_type in
                                                            ('PM10', 'PM25', 'TEMPERATURE', 'HUMIDITY', 'NOISE')),
    value          varchar(100)             not null,

    foreign key (sensor_id) references backend_testing.sensor (sensor_id)
);