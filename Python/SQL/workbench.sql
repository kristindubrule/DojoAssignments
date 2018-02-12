select * from twitter.follows;

select * from faves;

select first_name, tweet
from users
LEFT JOIN faves on users.id = faves.user_id
LEFT JOIN tweets ON tweets.id = faves.tweet_id
WHERE users.id = 2;

select first_name, count(faves.id)
FROM users, faves
where users.id = faves.user_id
group by first_name
