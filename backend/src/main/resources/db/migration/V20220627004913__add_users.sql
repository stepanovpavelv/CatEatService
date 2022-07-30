create table if not exists public.users(
    id serial primary key,
    login varchar(50) not null,
    password varchar(300) not null,
    enabled boolean default true
);

COMMENT ON TABLE users IS 'Пользователи';
COMMENT ON COLUMN users.id IS 'Идентификатор пользователя';
COMMENT ON COLUMN users.login IS 'Имя пользователя';
COMMENT ON COLUMN users.password IS 'Пароль пользователя';
COMMENT ON COLUMN users.enabled IS 'Отметка о включении';

insert into public.users(login, password) values('system_user', '$2a$10$HvT532ePSkUCs1AWgFUjPOsRDoS0FA5wSbw2CC6dgP6rNO6vVvRSC');

alter table public.indications
    add column if not exists user_id int null;

alter table public.indications
    drop constraint if exists indication_user_fk;

alter table public.indications
    add constraint indication_user_fk
    foreign key(user_id) references public.users(id);

update public.indications
set user_id = (select id from public.users where login = 'system_user' limit 1)
where user_id is null;