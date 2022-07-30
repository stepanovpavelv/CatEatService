alter table public.indications
    alter column user_id set not null;

comment on column indications.user_id is 'Идентификатор пользователя';