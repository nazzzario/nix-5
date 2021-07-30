INSERT INTO public.users (id, email) VALUES (1, 'test@email.com');

INSERT INTO public.accounts (id, account_name, balance, user_id) VALUES (1, 'Bars', 1000, 1);
INSERT INTO public.accounts (id, account_name, balance, user_id) VALUES (2, 'Flat', 200, 1);

INSERT INTO public.categories (category_type, id, name) VALUES ('income', 1, 'salary');
INSERT INTO public.categories (category_type, id, name) VALUES ('expense', 2, 'rent');
INSERT INTO public.categories (category_type, id, name) VALUES ('expense', 3, 'taxes');
INSERT INTO public.categories (category_type, id, name) VALUES ('expense', 4, 'food');
