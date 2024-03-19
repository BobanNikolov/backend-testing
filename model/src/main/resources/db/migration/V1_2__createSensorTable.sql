create table backend_testing.sensor
(
    sensor_id   uuid primary key default gen_random_uuid(),
    position    varchar(256) not null,
    sensor_type varchar(100) not null check (sensor_type in
                                        ('MOEPP', 'SKOPJE_PULSE_LORAWAN', 'SKOPJE_PULSE_WIFI', 'PULSE_ECO_WIFI',
                                         'PULSE_ECO_LORAWAN', 'PENGY_DEVICE', 'URAD', 'AIR_THINGS',
                                         'SENSOR_COMMUNITY')),
    description varchar(256) not null,
    comments    varchar(256) not null,
    status      varchar(100) not null check (status in
                                        ('REQUESTED', 'ACTIVE', 'ACTIVE_UNCONFIRMED', 'INACTIVE', 'NOT_CLAIMED',
                                         'NOT_CLAIMED_UNCONFIRMED', 'BANNED'))
);