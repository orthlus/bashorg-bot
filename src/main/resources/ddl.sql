create table if not exists aelaort_telegram_pack.quotes
(
    id serial primary key,
    rating            integer,
    quote             text,
    telegram_file_id  text,
    file_url_id       text,
    quote_id          integer,
    quote_date        date,
    thumb_file_exists boolean default false
);

CREATE EXTENSION IF NOT EXISTS pg_trgm;
create index if not exists quotes_gin_trgm_idx on aelaort_telegram_pack.quotes using gin (quote public.gin_trgm_ops);
