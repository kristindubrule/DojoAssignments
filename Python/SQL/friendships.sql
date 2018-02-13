use friendships;

select users.first_name, users.last_name, friends.first_name as friend_first_name, friends.last_name as friend_last_name
from users
left join friendships on users.id = friendships.user_id
left join users friends on friends.id = friendships.friend_id
order by friends.last_name