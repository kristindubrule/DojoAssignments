use world;

# all the countries that speak Slovene
select countries.name, languages.language, languages.percentage
from countries
join languages ON languages.country_code = countries.code
where languages.language = 'Slovene'
order by languages.percentage DESC;

# the total number of cities for each country
select countries.id, countries.name, count(cities.id)
from countries
JOIN cities on cities.country_id = countries.id
group by countries.id
order by count(cities.id) DESC;

# cities in Mexico with population > 500,000
select cities.name, cities.population
from cities
JOIN countries ON cities.country_id = countries.id
WHERE cities.population > 500000 AND countries.name = 'Mexico'
order by cities.population DESC;

# languages in each country with a percentage greater than 89%
select languages.language, countries.name, languages.percentage
from languages
JOIN countries ON languages.country_id = countries.id
WHERE languages.percentage > 89
order by languages.percentage DESC;

# all countries with surface area < 501 and population > 100,000
select countries.name, countries.surface_area, countries.population
from countries
where countries.surface_area < 501 and countries.population > 100000;

# countries with only constiutional monarchy with a capital > 200 and a life expectancy greater than 75 years
select countries.name, cities.name as capital, cities.id as capital_id, countries.government_form, countries.life_expectancy
from countries
join cities on countries.capital = cities.id
where cities.id > 200 and countries.life_expectancy > 75 and countries.government_form = 'Constitutional Monarchy';


# cities of Argentina inside the Buenos Aires district & population > 500,000 (name, city, district, population)
select countries.name, cities.name, cities.district, cities.population
from countries
join cities on countries.id = cities.country_id
where countries.name = 'Argentina' and cities.district = 'Buenos Aires' and cities.population > 500000;

# summarize the number of countries in each region? name of region + number of countries; number of countries in desc
select countries.region, count(countries.id)
from countries
group by countries.region
order by count(countries.id) desc