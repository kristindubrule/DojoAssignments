#1. What query would you run to get the total revenue for March of 2012?
select MONTHNAME(charged_datetime) as month, sum(amount) from billing
where MONTHNAME(charged_datetime) = 'March' and YEAR(charged_datetime) = '2012';

#2. What query would you run to get total revenue collected from the client with an id of 2?
select client_id, sum(amount) from billing
where client_id = 2;

#3. What query would you run to get all the sites that client=10 owns?
select * from sites where client_id = 10;

#4. What query would you run to get total # of sites created per month per year for the 
# client with an id of 1? What about for client=20?
select count(sites.site_id), monthname(created_datetime) as month_created, year(created_datetime) as year_created
from sites
where client_id = 1
group by month(created_datetime), year(created_datetime)
order by year(created_datetime), month(created_datetime);

select client_id, count(sites.site_id) as number_of_websites, month(created_datetime), year(created_datetime)
from sites
where client_id = 20
group by month(created_datetime), year(created_datetime)
order by year(created_datetime), month(created_datetime);

#5. What query would you run to get the total # of leads generated for each of the sites 
# between January 1, 2011 to February 15, 2011?
select count(leads.leads_id), sites.domain_name, created_datetime
from leads
join sites on leads.site_id = sites.site_id
where leads.registered_datetime >= '2011-01-01' and leads.registered_datetime <= '2011-02-15'
group by sites.domain_name;

#6. What query would you run to get a list of client names and the total # of leads we've 
# generated for each of our clients between January 1, 2011 to December 31, 2011?
select clients.first_name, clients.last_name, count(leads.leads_id)
from clients
join sites on clients.client_id = sites.client_id
join leads on sites.site_id = leads.site_id
where leads.registered_datetime >= '2011-01-01' and leads.registered_datetime <= '2011-12-31'
group by clients.client_id;

#7. What query would you run to get a list of client names and the total # of leads we've 
# generated for each client each month between months 1 - 6 of Year 2011?
select clients.first_name, clients.last_name, count(leads.leads_id), monthname(leads.registered_datetime)
from clients
join sites on clients.client_id = sites.client_id
join leads on sites.site_id = leads.site_id
where month(leads.registered_datetime) <= 6 and year(leads.registered_datetime) = '2011'
group by clients.client_id, month(leads.registered_datetime)
order by month(leads.registered_datetime);

#8. What query would you run to get a list of client names and the total # of leads we've 
# generated for each of our clients' sites between January 1, 2011 to December 31, 2011? 
# Order this query by client id.  Come up with a second query that shows all the clients, the 
# site name(s), and the total number of leads generated from each site for all time.
select clients.client_id, clients.first_name, clients.last_name, sites.domain_name, count(leads.leads_id), leads.registered_datetime
from clients
left join sites on clients.client_id = sites.client_id
left join leads on sites.site_id = leads.site_id
where year(leads.registered_datetime) = '2011'
group by clients.client_id, sites.site_id
order by clients.client_id;

select clients.client_id, clients.first_name, clients.last_name, sites.domain_name, count(leads.leads_id)
from clients
left join sites on clients.client_id = sites.client_id
left join leads on sites.site_id = leads.site_id
group by clients.client_id, sites.site_id
order by clients.client_id;

#9. Write a single query that retrieves total revenue collected from each client for each month 
# of the year. Order it by client id.
select clients.client_id, clients.first_name, clients.last_name, month(charged_datetime), year(charged_datetime), sum(amount)
from clients
join billing on clients.client_id = billing.client_id
group by clients.client_id, year(charged_datetime), month(charged_datetime)
order by clients.client_id, year(charged_datetime), month(charged_datetime);

#10. Write a single query that retrieves all the sites that each client owns. Group the results 
# so that each row shows a new client. It will become clearer when you add a new field called 'sites' 
# that has all the sites that the client owns. (HINT: use GROUP_CONCAT)
select GROUP_CONCAT(sites.site_id separator ' / ') as site_id_list, GROUP_CONCAT(sites.domain_name separator ' / ') as site_list, clients.client_id, clients.first_name, clients.last_name
from sites
join clients on sites.client_id = clients.client_id
group by clients.client_id