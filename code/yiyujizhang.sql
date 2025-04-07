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

