create view turnover_by_month as 
select t.company_id ,extract(month from t."date") as month, extract(year from t."date") as year,
sum(t.amount) as amount
from turnover as t group by 1,2,3 order by 2,3 