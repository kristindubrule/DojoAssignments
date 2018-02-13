use sakila;

# 1. all the customers inside city_id = 312? Your query should return customer first name, last name, email, and address.
select first_name, last_name, email, address.address
from customer
join address on customer.address_id = address.address_id
where city_id = 312;

# 2. all comedy films? Your query should return film title, description, release year, rating, special features, and genre (category)
select title, film.description, release_year, rating, special_features, category.name
from film
join film_category on film.film_id = film_category.film_id
join category on film_category.category_id = category.category_id
where category.name = 'Comedy';

# 3. all the films joined by actor_id=5? Your query should return the actor id, actor name, film title, description, and release year
select actor.actor_id, CONCAT_WS(' ',actor.first_name,actor.last_name), film.title, film.description, film.release_year
from actor
JOIN film_actor ON actor.actor_id = film_actor.actor_id
JOIN film ON film_actor.film_id = film.film_id
WHERE actor.actor_id = 5;

# 4. all the customers in store_id = 1 and inside these cities (1, 42, 312 and 459)? Your query should return customer first name, last name, email, and address
select customer.store_id, address.city_id, customer.first_name, customer.last_name, customer.email, address.address
from customer
join address ON customer.address_id = address.address_id
where customer.store_id = 1 and address.city_id IN (1,42,312,459);

# 5. all the films with a "rating = G" and "special feature = behind the scenes", joined by actor_id = 15? Your query 
# should return the film title, description, release year, rating, and special feature
select film.title, film.description, film.release_year, film.rating, film.special_features
from film
join film_actor on film.film_id = film_actor.film_id
where film_actor.actor_id = 15 and film.special_features like '%behind the scenes%'
and film.rating = 'G';

# 6. all the actors that joined in the film_id = 369? Your query should return the film_id, title, actor_id, and actor_name
select film.film_id, film.title, actor.actor_id, CONCAT_WS(' ',actor.first_name,actor.last_name)
from film
join film_actor on film.film_id = film_actor.film_id
join actor on film_actor.actor_id = actor.actor_id
where film.film_id = 369;

# 7. all drama films with a rental rate of 2.99? Your query should return film title, description, release year, rating, special features, and genre (category)
select film.title, film.description, film.release_year, film.rating, film.special_features, category.name
from film
join film_category on film.film_id = film_category.film_id
join category on film_category.category_id = category.category_id
where film.rental_rate = 2.99 and category.name = 'Drama';

# 8. all the action films which are joined by SANDRA KILMER? Your query should return film title, description, release year, 
# rating, special features, genre (category), and actor's first name and last name
select film.title, film.description, film.release_year, film.rating, film.special_features, category.name,
actor.first_name,actor.last_name
from film
join film_category on film.film_id = film_category.film_id
join category on film_category.category_id = category.category_id
join film_actor on film.film_id = film_actor.film_id
join actor on film_actor.actor_id = actor.actor_id
where category.name = 'Action' and actor.first_name = 'SANDRA' and actor.last_name = 'KILMER'
