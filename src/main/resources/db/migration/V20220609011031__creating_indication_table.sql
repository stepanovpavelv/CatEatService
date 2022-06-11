create table if not exists public.indications(
    id bigserial primary key,
    create_date timestamp not null,
    value int not null
);