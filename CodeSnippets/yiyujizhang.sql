




select * from record 

select CASE     
	when t1."type" =0 then '支出'
	when t1."type" =1 then '收入'
	when t1."type" =2 then '转账'
	when t1."type" =3 then '收入'
	when t1."type" =4 then '支出'
end as '类型', 
t1."year"||'/'|| t1."month"||'/'||t1."day" as '日期',
ifnull(t3.name,t2.name) as '大类', 
ifnull(t2.name,
	CASE     
		when t1."type" =4 then '其它'
		when t1."type" =3 then '其它'
		when t1."type" =2 then '账户互转'
	end
) as '小类', 
t1.money as '金额', ifnull(t4.name, t6.name) as '账户', 
ifnull(t5.name,'') as '账户2', '' as '报销',
t1.remark as '备注','' as '图片','' as '角色','' as '标签','' as '币种','' as '商家'
from record t1
left join record_category t2 on t2.id=t1.record_cat_id 
left join record_category t3 on t2.parent_cat_id =t3.id 
left join asset t4 on t4.id=t1.asset_id
left join asset t5 on t5.id=t1.to_asset_id 
left join asset t6 on t6.id=t1.from_asset_id  
order by t1.record_time desc



select t1.id as 'id',t2.id, t1.name as 'name', t2.name as 'sub_name' from record_category t1
left join record_category t2 on t2.parent_cat_id =t1.id
where t1.parent_cat_id = -1


select * from record where "type" = 0 order by record_time desc -- 消费

select * from record where "type" = 1 order by record_time desc -- 收入

select * from record where "type" = 2 order by record_time desc -- 转账

select * from record where "type" = 3 order by record_time desc -- 报销

select * from record where "type" = 4 order by record_time desc -- 余额变更



select distinct asset_id  from record 

select distinct from_asset_id  from record 


select distinct record_cat_id  from record 

select distinct sub_type  from record 

select distinct type  from record 


















